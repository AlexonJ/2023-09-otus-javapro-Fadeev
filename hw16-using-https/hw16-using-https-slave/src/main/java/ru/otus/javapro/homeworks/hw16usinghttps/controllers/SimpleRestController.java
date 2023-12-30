package ru.otus.javapro.homeworks.hw16usinghttps.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


@RestController
public class SimpleRestController {

    @GetMapping(path = "api/v1/getTestString")
    public ResponseEntity<String> getTestString() {
        return new ResponseEntity<>("Current time is %s. Random number is %d.".formatted(LocalDateTime.now(), (int) (Math.random() * 100)), HttpStatus.OK);
    }
}
