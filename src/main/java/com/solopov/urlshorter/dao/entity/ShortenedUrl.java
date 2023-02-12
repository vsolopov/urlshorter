package com.solopov.urlshorter.dao.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "shortened_urls")
@Data
@NoArgsConstructor
public class ShortenedUrl {

    @Id
    @GeneratedValue(generator = "custom-generator", strategy = GenerationType.IDENTITY)
    @GenericGenerator(
            name = "custom-generator",
            strategy = "com.solopov.urlshorter.dao.entity.generator.id.ShortUrlGenerator"
    )
    private String id;

    @Column(unique = true, nullable = false)
    private String originalUrl;

    @Column(nullable = false)
    private String title;

    private LocalDate createdAt;

}
