package ru.otus.javapro.homeworks.hw14dependencyinjection.config;

import ru.otus.javapro.homeworks.hw14dependencyinjection.appcontainer.api.AppComponent;
import ru.otus.javapro.homeworks.hw14dependencyinjection.appcontainer.api.AppComponentsContainerConfig;
import ru.otus.javapro.homeworks.hw14dependencyinjection.services.EquationPreparer;
import ru.otus.javapro.homeworks.hw14dependencyinjection.services.EquationPreparerImpl;
import ru.otus.javapro.homeworks.hw14dependencyinjection.services.IOService;
import ru.otus.javapro.homeworks.hw14dependencyinjection.services.IOServiceStreams;
import ru.otus.javapro.homeworks.hw14dependencyinjection.services.PlayerService;
import ru.otus.javapro.homeworks.hw14dependencyinjection.services.PlayerServiceImpl;

@AppComponentsContainerConfig(order = 1)
public class AppConfig1 {

    @AppComponent(order = 0, name = "equationPreparer")
    public EquationPreparer equationPreparer(){
        return new EquationPreparerImpl();
    }

    @AppComponent(order = 1, name = "playerService")
    public PlayerService playerService(IOService ioService) {
        return new PlayerServiceImpl(ioService);
    }

    @AppComponent(order = 0, name = "ioService")
    public IOService ioService() {
        return new IOServiceStreams(System.out, System.in);
    }

}
