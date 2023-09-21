package ru.otus.javapro.homeworks.annotations.customtest;

import ru.otus.javapro.homeworks.annotations.annotations.After;
import ru.otus.javapro.homeworks.annotations.annotations.Before;
import ru.otus.javapro.homeworks.annotations.annotations.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;

public class CustomTestRunner {

    static private final ArrayList<Class> annotations = new ArrayList<>();

    static {
        annotations.add(Before.class);
        annotations.add(Test.class);
        annotations.add(After.class);
    }

    static public void runAllTestForObject(Class cls) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        HashSet<Method> totalMethods = new HashSet<>();
        HashSet<Method> withFaultsMethods = new HashSet<>();
        HashSet<Method> completedMethods = new HashSet<>();

        // create instance of cls class for invoke() method
        Constructor<?> constructor = cls.getDeclaredConstructor();
        Object objectWithTestMethods = constructor.newInstance();
        constructor.setAccessible(true);

        System.out.printf("Start running test for class: %s%n", cls.getName());

        for (Class annotation : annotations) {
            for (Method method : getMethodsByAnnotation(cls, annotation)) {
                if(!withFaultsMethods.contains(method)) {
                    totalMethods.add(method);
                    if (isAnnotationSetCorrect(method)) {
                        try {
                            method.invoke(objectWithTestMethods);
                            completedMethods.add(method);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            System.out.printf("An error occurred while invoking method: %s%n", method.getName());
                            withFaultsMethods.add(method);
                        }
                    } else {
                        withFaultsMethods.add(method);
                        System.out.printf("Method %s contains invalid annotation set%n", method.getName());
                    }
                }
            }
        }
        System.out.printf("Testing completed.%n" +
                        "Total methods executed: %d%n" +
                        "Methods executed with faults: %d%n" +
                        "Methods completed successfully: %d%n",
                totalMethods.size(), withFaultsMethods.size(), completedMethods.size());
    }

    static private ArrayList<Method> getMethodsByAnnotation(Class cls, Class annotation) {
        ArrayList<Method> result = new ArrayList<>();
        for (Method method : cls.getDeclaredMethods()) {
            if (method.isAnnotationPresent(annotation)) {
                result.add(method);
            }
        }
        return result;
    }

    static private boolean isAnnotationSetCorrect(Method method) {
        boolean isAnyAnnotationPresent = false;
        for (Class annotation : annotations) {
            boolean currentAnnotationPresent = method.isAnnotationPresent(annotation);
            if (isAnyAnnotationPresent && currentAnnotationPresent) {
                return false;
            }
            isAnyAnnotationPresent |= currentAnnotationPresent;
        }
        return true;
    }
}
