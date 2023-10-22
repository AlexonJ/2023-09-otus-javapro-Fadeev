package ru.otus.javapro.homeworks.hw9serialization.service;

import java.io.*;

public class FileServiceImpl implements FileService {

    @Override
    public File createOutputDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        return directory;
    }

    @Override
    public void writeObjectToFile(String fileName, Object object) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(fileName);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(object);

        objectOutputStream.close();
    }

    @Override
    public Object readObjectFromFile(String fileName, Object object) throws IOException, ClassNotFoundException {
        FileInputStream inputStream = new FileInputStream(fileName);
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        return objectInputStream.readObject();
    }

    @Override
    public void writeStringToFile(String fileName, String content) throws IOException {

//        String directoryPath = "src/main/resources/output";
//        String directoryPath = "output";
        File outputFile = new File(fileName);

        FileWriter writer = new FileWriter(outputFile);

        writer.write(content);
        writer.close();
    }

    @Override
    public String readFromFileAsResource(String filename) throws IOException {
        Reader reader = getReader(filename);
        return readerToString(reader);
    }

    private String readerToString(Reader reader) throws IOException {

        StringBuilder stringBuilder = new StringBuilder();
        char[] buffer = new char[1024];
        int length;

        while ((length = reader.read(buffer)) != -1) {
            stringBuilder.append(buffer, 0, length);
        }

        reader.close();
        return stringBuilder.toString();
    }

    private Reader getReader(String filename) {
        InputStream inputStream = getFileFromResourceAsStream(filename);

        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        return new BufferedReader(inputStreamReader);
    }


    private InputStream getFileFromResourceAsStream(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }
    }
}
