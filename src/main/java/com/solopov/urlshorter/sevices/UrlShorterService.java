package com.solopov.urlshorter.sevices;


import com.solopov.urlshorter.web.dto.UrlGenerateRequestDto;

public interface UrlShorterService {
    String shorten(UrlGenerateRequestDto dto);

    String fetch(String shorted);
}
