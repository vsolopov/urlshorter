package com.solopov.urlshorter.sevices.impl;

import com.solopov.urlshorter.dao.entities.ShortenedUrl;
import com.solopov.urlshorter.dao.repositories.ShortenedUrlRepository;
import com.solopov.urlshorter.exceptions.LinkNotFoundException;
import com.solopov.urlshorter.sevices.UrlShorterService;
import com.solopov.urlshorter.web.dto.UrlRequestDto;
import com.solopov.urlshorter.web.mapper.UrlRequestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class UrlShorterServiceImpl implements UrlShorterService {

    private final ShortenedUrlRepository shortenedRepository;

    @Retryable
    @Override
    public String shorten(UrlRequestDto dto) {

        ShortenedUrl shortenedUrl = UrlRequestMapper.INSTANCE.toShortenedUrl(dto);
        String urlPartPath = shortenedRepository.save(shortenedUrl).getId();

        return appendServiceHost(urlPartPath);
    }

    @Cacheable("shortenedToOriginal")
    @Override
    public String fetch(String shorted) {
        return shortenedRepository.findById(shorted)
                .map(ShortenedUrl::getOriginalUrl)
                .orElseThrow(LinkNotFoundException::new);
    }

    private String appendServiceHost(String urlPath) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .pathSegment(urlPath).build()
                .toUriString();
    }
}
