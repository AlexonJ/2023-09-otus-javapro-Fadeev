package ru.otus.javapro.homeworks.hw20webflux.reactive.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorDto> handleAppException(AppException e) {
        return new ResponseEntity<>(
                new ErrorDto(e.getCode()), Objects.isNull(e.getStatus()) ? HttpStatus.INTERNAL_SERVER_ERROR : e.getStatus()
        );
    }
}
