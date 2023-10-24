package ru.otus.javapro.homeworks.hw9serialization.dao.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SmsDataGroupByNumber implements Serializable {

    @JsonProperty("belong_number")
    String belongNumber;

    @JsonProperty("data")
    List<SmsData> smsDataList;

}
