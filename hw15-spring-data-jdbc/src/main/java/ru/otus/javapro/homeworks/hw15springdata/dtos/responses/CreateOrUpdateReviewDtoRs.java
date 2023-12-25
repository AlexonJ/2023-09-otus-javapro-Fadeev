package ru.otus.javapro.homeworks.hw15springdata.dtos.responses;

import lombok.Data;

import java.util.Date;

@Data
public class CreateOrUpdateReviewDtoRs {

    private Long id;

    private Long bookId;

    private Date reviewDate;

    private String userName;

    private Integer score;

    private String text;
}
