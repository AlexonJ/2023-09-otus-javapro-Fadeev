package ru.otus.javapro.homeworks.hw9serialization.dao.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.javapro.homeworks.hw9serialization.dao.dto.chat.ChatSession;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sms {
    @JsonProperty("chat_sessions")
    List<ChatSession> chatSessions;

}
