package ru.otus.javapro.homeworks.hw20webflux.reactive.service.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@Setter
public class AppException extends RuntimeException {

    private String code;

    private HttpStatus status;

    public AppException(String code) {
        this.code = code;
    }

}
