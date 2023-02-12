package com.solopov.urlshorter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.retry.annotation.EnableRetry;

@EnableCaching
@EnableRetry
@EnableJpaRepositories
@SpringBootApplication
public class UrlShorterApplication {

    public static void main(String[] args) {
        SpringApplication.run(UrlShorterApplication.class, args);
    }

}
