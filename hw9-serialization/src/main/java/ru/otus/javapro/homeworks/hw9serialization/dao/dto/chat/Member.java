package ru.otus.javapro.homeworks.hw9serialization.dao.dto.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @JsonProperty("first")
    String first;

    @JsonProperty("handle_id")
    String handleId;

    @JsonProperty("image_path")
    String imagePath;

    @JsonProperty("last")
    String last;

    @JsonProperty("middle")
    String middle;

    @JsonProperty("phone_number")
    String phoneNumber;

    @JsonProperty("service")
    String service;

    @JsonProperty("thumb_path")
    String thumbPath;

}
