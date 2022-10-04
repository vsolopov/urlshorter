package com.solopov.urlshorter.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ExceptionDto {

    private String error;
    private int status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;

    public ExceptionDto(String message, HttpStatus status) {
        this.setError(status.getReasonPhrase());
        this.setStatus(status.value());
        this.setTimestamp(LocalDateTime.now());
        this.setMessage(message);
    }

}
