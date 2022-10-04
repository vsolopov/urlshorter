package com.solopov.urlshorter.sevice;


import com.solopov.urlshorter.web.dto.UrlRequestDto;

public interface UrlShorterService {
    String shorten(UrlRequestDto dto);

    String fetch(String shorted);
}
