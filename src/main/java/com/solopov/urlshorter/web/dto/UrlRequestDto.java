package com.solopov.urlshorter.web.dto;

import com.solopov.urlshorter.dao.entities.ShortenedUrl;

import javax.validation.constraints.NotBlank;

public record UrlRequestDto(@NotBlank String url, @NotBlank String title) {

    public ShortenedUrl convert() {
        ShortenedUrl shortenedUrl = new ShortenedUrl();
        shortenedUrl.setTitle(this.title);
        shortenedUrl.setOriginalUrl(this.url);
        return shortenedUrl;
    }

}
