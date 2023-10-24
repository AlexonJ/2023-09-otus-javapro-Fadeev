package ru.otus.javapro.homeworks.hw9serialization.dao.dto.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ChatSession {

    @JsonProperty("chat_id")
    Integer id;

    @JsonProperty("chat_identifier")
    String identifier;

    @JsonProperty("display_name")
    String displayName;

    @JsonProperty("is_deleted")
    Boolean isDeleted;

    @JsonProperty("members")
    List<Member> members;

    @JsonProperty("messages")
    List<Message> messages;
}
