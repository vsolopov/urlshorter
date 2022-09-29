package com.solopov.urlshorter.dao.repositories;

import com.solopov.urlshorter.dao.entities.ShortenedUrl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortenedUrlRepository extends JpaRepository<ShortenedUrl, String> {

}
