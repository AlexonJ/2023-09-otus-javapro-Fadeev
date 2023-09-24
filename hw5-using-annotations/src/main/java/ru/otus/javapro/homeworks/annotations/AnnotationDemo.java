package ru.otus.javapro.homeworks.annotations;

import ru.otus.javapro.homeworks.annotations.customtest.AnnotationDemoTest;
import ru.otus.javapro.homeworks.annotations.customtest.CustomTestRunner;

import java.lang.reflect.InvocationTargetException;

public class AnnotationDemo {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        CustomTestRunner.runAllTestForObject(AnnotationDemoTest.class);
    }
}
