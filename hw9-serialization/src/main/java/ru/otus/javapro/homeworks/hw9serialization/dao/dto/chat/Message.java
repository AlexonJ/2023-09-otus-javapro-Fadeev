package ru.otus.javapro.homeworks.hw9serialization.dao.dto.chat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    @JsonProperty("ROWID")
    Integer RowId;

    @JsonProperty("attributedBody")
    String attributedBody;

    @JsonProperty("belong_number")
    String belongNumber;

    @JsonProperty("date")
    Long date;

    @JsonProperty("date_read")
    Long dateRead;

    @JsonProperty("guid")
    UUID guid;

    @JsonProperty("handle_id")
    Long handleId;

    @JsonProperty("has_dd_results")
    Boolean hasDdResults;

    @JsonProperty("is_deleted")
    Boolean isDeleted;

    @JsonProperty("is_from_me")
    Boolean isFromMe;

    @JsonProperty("send_date")
    @JsonFormat(pattern = "MM-dd-yyyy HH:mm:ss")
    LocalDateTime sendDate;

    @JsonProperty("send_status")
    String sendStatus;

    @JsonProperty("service")
    String service;

    @JsonProperty("text")
    String text;

}
