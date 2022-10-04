package com.solopov.urlshorter.dao.repository;

import com.solopov.urlshorter.dao.entity.ShortenedUrl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortenedUrlRepository extends JpaRepository<ShortenedUrl, String> {

}
