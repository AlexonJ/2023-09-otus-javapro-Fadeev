package ru.otus.javapro.homeworks.hw14dependencyinjection.appcontainer;

import ru.otus.javapro.homeworks.hw14dependencyinjection.appcontainer.api.AppComponent;
import ru.otus.javapro.homeworks.hw14dependencyinjection.appcontainer.api.AppComponentsContainer;
import ru.otus.javapro.homeworks.hw14dependencyinjection.appcontainer.api.AppComponentsContainerConfig;
import ru.otus.javapro.homeworks.hw14dependencyinjection.exception.DuplicateComponentException;
import ru.otus.javapro.homeworks.hw14dependencyinjection.exception.NoSuchComponentException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppComponentsContainerImpl implements AppComponentsContainer {

    private final List<Object> appComponents = new ArrayList<>();
    private final Map<String, Object> appComponentsByName = new HashMap<>();

    private final String COMPONENT_NOT_FOUND_MESSAGE = "Component %s found in context";
    private final String DUPLICATE_COMPONENT_MESSAGE = "Duplicate component found in context %s";

    public AppComponentsContainerImpl(Class<?> initialConfigClass) {
        processClassInitialization(initialConfigClass);
    }

    public AppComponentsContainerImpl(Class<?> ... initialConfigClasses){
        Arrays.stream(initialConfigClasses)
                .sorted(Comparator.comparingInt(o -> o.getDeclaredAnnotation(AppComponentsContainerConfig.class).order()))
                .forEach(this::processClassInitialization);
    }

    public void processClassInitialization(Class<?> initialConfigClass){
        try {
            processConfig(initialConfigClass);
        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 NoSuchComponentException | DuplicateComponentException e) {
            throw new RuntimeException(e);
        }
    }
    private void processConfig(Class<?> configClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchComponentException, DuplicateComponentException {
        checkConfigClass(configClass);

        Object configInstance;

        configInstance = configClass.getDeclaredConstructor().newInstance();

        Method[] declaredMethods = configClass.getDeclaredMethods();
        Arrays.sort(declaredMethods, Comparator.comparingInt(o -> o.getDeclaredAnnotation(AppComponent.class).order()));
        for (Method method : declaredMethods) {
            if(method.isAnnotationPresent(AppComponent.class)) {
                List<Object> parameters = new ArrayList<>();
                for (Class<?> parameterClass : method.getParameterTypes()) {
                    parameters.add(getAppComponent(parameterClass));
                }
                Object newComponent = method.invoke(configInstance, parameters.toArray());
                appComponents.add(newComponent);
                String componentName = method.getDeclaredAnnotation(AppComponent.class).name();
                if (appComponentsByName.containsKey(componentName))
                    throw new NoSuchComponentException(COMPONENT_NOT_FOUND_MESSAGE.formatted(componentName));
                appComponentsByName.put(componentName, newComponent);
            }
        }
    }

    private void checkConfigClass(Class<?> configClass) {
        if (!configClass.isAnnotationPresent(AppComponentsContainerConfig.class)) {
            throw new IllegalArgumentException(String.format("Given class is not config %s", configClass.getName()));
        }
    }

    @Override
    public <C> C getAppComponent(Class<C> componentClass) throws NoSuchComponentException, DuplicateComponentException {
        List<C> foundedComponents = appComponents.stream()
                .filter(o -> componentClass.isAssignableFrom(o.getClass()))
                .map(componentClass::cast)
                .toList();
        if (foundedComponents.size() > 1) {
            throw new DuplicateComponentException(DUPLICATE_COMPONENT_MESSAGE.formatted(componentClass.getName()));
        } else if (appComponents.isEmpty()) {
            throw new NoSuchComponentException(COMPONENT_NOT_FOUND_MESSAGE.formatted(componentClass.getName()));
        }
        return foundedComponents.get(0);
    }

    @Override
    public <C> C getAppComponent(String componentName) throws NoSuchComponentException {
        if (!appComponentsByName.containsKey(componentName))
            throw new NoSuchComponentException(COMPONENT_NOT_FOUND_MESSAGE.formatted(componentName));

        return (C) appComponentsByName.get(componentName);

    }

}
