package com.solopov.urlshorter.web.mapper;

import com.solopov.urlshorter.dao.entities.ShortenedUrl;
import com.solopov.urlshorter.web.dto.UrlRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UrlRequestMapper {

    UrlRequestMapper INSTANCE = Mappers.getMapper(UrlRequestMapper.class);

    @Mapping(target = "originalUrl", source = "url")
    ShortenedUrl toShortenedUrl(UrlRequestDto dto);

}

