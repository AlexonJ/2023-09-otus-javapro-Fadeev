package ru.otus.javapro.homeworks.hw14dependencyinjection;

import ru.otus.javapro.homeworks.hw14dependencyinjection.appcontainer.AppComponentsContainerImpl;
import ru.otus.javapro.homeworks.hw14dependencyinjection.appcontainer.api.AppComponentsContainer;
import ru.otus.javapro.homeworks.hw14dependencyinjection.config.AppConfig;
import ru.otus.javapro.homeworks.hw14dependencyinjection.services.GameProcessor;

public class App {

    public static void main(String[] args) throws Exception {

        AppComponentsContainer container = new AppComponentsContainerImpl(AppConfig.class);
        GameProcessor gameProcessor = container.getAppComponent(GameProcessor.class);
        gameProcessor.startGame();

    }
}
