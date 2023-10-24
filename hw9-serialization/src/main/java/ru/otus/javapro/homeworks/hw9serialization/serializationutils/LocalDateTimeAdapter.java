package ru.otus.javapro.homeworks.hw9serialization.serializationutils;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");

    @Override
    public LocalDateTime unmarshal(String dataTimeString) {
        return LocalDateTime.parse(dataTimeString, formatter);
    }

    @Override
    public String marshal(LocalDateTime dateTime) {
        return dateTime.format(formatter);
    }
}
