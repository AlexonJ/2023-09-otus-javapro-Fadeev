package ru.otus.javapro.homeworks.hw9serialization.dao.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@XmlRootElement
public class SmsReport implements Serializable {

    @JsonProperty("sms_data")
    List<SmsDataGroupByNumber> smsDataGroupList;

}
