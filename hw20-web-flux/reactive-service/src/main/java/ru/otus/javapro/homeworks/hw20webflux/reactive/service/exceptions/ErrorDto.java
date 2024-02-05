package ru.otus.javapro.homeworks.hw20webflux.reactive.service.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class ErrorDto {

    private String code;
    private LocalDateTime date;

    public ErrorDto() {

    }

    public ErrorDto(String code) {
        this.code = code;
        this.date = LocalDateTime.now();
    }

}
