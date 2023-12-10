package ru.otus.javapro.homeworks.hw14dependencyinjection.config;

import ru.otus.javapro.homeworks.hw14dependencyinjection.appcontainer.api.AppComponent;
import ru.otus.javapro.homeworks.hw14dependencyinjection.appcontainer.api.AppComponentsContainerConfig;
import ru.otus.javapro.homeworks.hw14dependencyinjection.services.EquationPreparer;
import ru.otus.javapro.homeworks.hw14dependencyinjection.services.GameProcessor;
import ru.otus.javapro.homeworks.hw14dependencyinjection.services.GameProcessorImpl;
import ru.otus.javapro.homeworks.hw14dependencyinjection.services.IOService;
import ru.otus.javapro.homeworks.hw14dependencyinjection.services.PlayerService;

@AppComponentsContainerConfig(order = 1)
public class AppConfig2 {

    @AppComponent(order = 2, name = "gameProcessor")
    public GameProcessor gameProcessor(IOService ioService,
                                       PlayerService playerService,
                                       EquationPreparer equationPreparer) {
        return new GameProcessorImpl(ioService, equationPreparer, playerService);
    }

}
