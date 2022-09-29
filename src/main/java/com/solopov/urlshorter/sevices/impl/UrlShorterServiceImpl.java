package com.solopov.urlshorter.sevices.impl;

import com.solopov.urlshorter.dao.entities.ShortenedUrl;
import com.solopov.urlshorter.dao.repositories.ShortenedUrlRepository;
import com.solopov.urlshorter.exceptions.LinkNotFoundException;
import com.solopov.urlshorter.sevices.UrlShorterService;
import com.solopov.urlshorter.web.dto.UrlGenerateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import javax.persistence.LockModeType;

@Service
@RequiredArgsConstructor
public class UrlShorterServiceImpl implements UrlShorterService {

    private final ShortenedUrlRepository shortenedRepository;

    @Value("${com.solopov.urlshorter.service.url}")
    private String rootUrl;

    @Value("${com.solopov.urlshorter.service.api.shorten.url}")
    private String shortenUrlPath;


    @Retryable
    @Override
    @Lock(LockModeType.WRITE)
    public String shorten(UrlGenerateRequestDto dto) {
        return appendServiceHost(shortenedRepository.save(dto.convert()).getId());
    }

    @Cacheable("shortenedToOriginal")
    @Override
    @Lock(LockModeType.READ)
    public String fetch(String shorted) {
        return shortenedRepository.findById(shorted)
                .map(ShortenedUrl::getOriginalUrl)
                .orElseThrow(LinkNotFoundException::new);
    }

    private String appendServiceHost(String urlPath) {
        return rootUrl + shortenUrlPath + "/" + urlPath;
    }
}
