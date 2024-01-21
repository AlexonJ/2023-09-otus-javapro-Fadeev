package ru.otus.javapro.homeworks.hw19grpc.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.IOException;
import java.io.InputStream;

@Data
public class AppConfig {

    private static AppConfig configuration;

    private String host;

    private int port;

    private int nodesPort;

    private int numberOfNodes;

    static {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ClassLoader classLoader = AppConfig.class.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("config.json");
            if (inputStream != null) {
                configuration = objectMapper.readValue(inputStream, AppConfig.class);
            } else {
                System.err.println("config.json not found in the resources directory.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static AppConfig getConfiguration() {
        return configuration;
    }

}
