package com.solopov.urlshorter.sevice.impl;

import com.solopov.urlshorter.dao.entity.ShortenedUrl;
import com.solopov.urlshorter.dao.repository.ShortenedUrlRepository;
import com.solopov.urlshorter.exception.UrlNotFoundException;
import com.solopov.urlshorter.sevice.UrlShorterService;
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

    @Cacheable("urls")
    @Override
    public String fetch(String shorted) {
        return shortenedRepository.findById(shorted)
                .map(ShortenedUrl::getOriginalUrl)
                .orElseThrow(UrlNotFoundException::new);
    }

    private String appendServiceHost(String urlPath) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .pathSegment(urlPath).build()
                .toUriString();
    }
}
