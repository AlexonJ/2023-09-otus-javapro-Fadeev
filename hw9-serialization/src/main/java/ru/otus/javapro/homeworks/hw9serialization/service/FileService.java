package ru.otus.javapro.homeworks.hw9serialization.service;

import java.io.*;

public interface FileService {

    File createOutputDirectory(String directoryPath);

    void writeObjectToFile(String fileName, Object object) throws IOException;

    Object readObjectFromFile(String fileName, Object object) throws IOException, ClassNotFoundException;

    void writeStringToFile(String fileName, String content) throws IOException;

    String readFromFileAsResource(String filename) throws IOException;
}
