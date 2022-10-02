package com.solopov.urlshorter.web.controllers;

import com.solopov.urlshorter.sevices.UrlShorterService;
import com.solopov.urlshorter.web.dto.UrlGenerateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

import static org.apache.tomcat.websocket.Constants.MOVED_PERMANENTLY;

@RestController
@RequestMapping("${com.solopov.urlshorter.service.api.shorten.url}")
@RequiredArgsConstructor
public class UrlShorterController {

    private final UrlShorterService service;

    @PostMapping
    public ResponseEntity<?> generateLink(@Valid @RequestBody UrlGenerateRequestDto dto) {
        return ResponseEntity
                .created(URI.create(service.shorten(dto)))
                .build();
    }

    @GetMapping("/{shortenUrlId}")
    public ResponseEntity<?> redirect(@PathVariable String shortenUrlId) {
            return ResponseEntity.status(MOVED_PERMANENTLY)
                    .header(HttpHeaders.LOCATION, service.fetch(shortenUrlId))
                    .build();
    }

}
