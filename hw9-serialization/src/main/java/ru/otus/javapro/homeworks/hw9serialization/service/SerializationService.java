package ru.otus.javapro.homeworks.hw9serialization.service;

public interface SerializationService {

    String serialize(Object object) throws Exception;

    <T> T deserialize(String dataString, Class<T> clazz) throws Exception;
}
