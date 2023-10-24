package ru.otus.javapro.homeworks.hw8designpatterns.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class ObjectForMessage {

    private List<String> data;

    public ObjectForMessage(ObjectForMessage objectForMessage){
        this.setData(new ArrayList<>());
        this.getData().addAll(objectForMessage.getData());
    }
}
