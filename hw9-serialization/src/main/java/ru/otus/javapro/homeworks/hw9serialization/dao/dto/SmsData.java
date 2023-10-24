package ru.otus.javapro.homeworks.hw9serialization.dao.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.javapro.homeworks.hw9serialization.serializationutils.LocalDateTimeAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SmsData implements Serializable {

    @JsonProperty("chat_identifier")
    String chatIdentifier;

    @JsonProperty("members_last")
    String membersLast;

    @JsonProperty("message_send_date")
    @JsonFormat(pattern = "MM-dd-yyyy HH:mm:ss")
    LocalDateTime sendDate;

    @JsonProperty("text")
    String text;

    @XmlElement
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    public LocalDateTime getSendDate(){
        return sendDate;
    }
}
