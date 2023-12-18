package ru.otus.javapro.homeworks.hw14dependencyinjection.appcontainer.api;

import ru.otus.javapro.homeworks.hw14dependencyinjection.exception.DuplicateComponentException;
import ru.otus.javapro.homeworks.hw14dependencyinjection.exception.NoSuchComponentException;

public interface AppComponentsContainer {
    <C> C getAppComponent(Class<C> componentClass) throws NoSuchComponentException, DuplicateComponentException;
    <C> C getAppComponent(String componentName) throws NoSuchComponentException;
}
