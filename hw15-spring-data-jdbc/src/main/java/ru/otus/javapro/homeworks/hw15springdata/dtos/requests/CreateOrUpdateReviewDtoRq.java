package ru.otus.javapro.homeworks.hw15springdata.dtos.requests;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Data
public class CreateOrUpdateReviewDtoRq {

    private Long bookId;

    private Date reviewDate;

    @NotBlank(message = "Имя пользователя не может быть пустым")
    private String userName;

    @Min(0) @Max(10)
    private Integer score;

    private String text;

}
