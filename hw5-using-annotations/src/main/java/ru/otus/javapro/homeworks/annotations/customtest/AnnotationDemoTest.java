package ru.otus.javapro.homeworks.annotations.customtest;

import ru.otus.javapro.homeworks.annotations.annotations.After;
import ru.otus.javapro.homeworks.annotations.annotations.Before;
import ru.otus.javapro.homeworks.annotations.annotations.Test;

public class AnnotationDemoTest {

    @After
    void annotationDemoAfterTestMethod(){
        System.out.println("Method After is running");
    }

    @Before
    @After
    void annotationDemoInvalidTestMethod(){
        System.out.println("Method Before-after is running");
    }

    @Test
    void annotationDemoTestMethod(){
        System.out.println("Method Test is running");
    }

    @Test
    void annotationDemoTestMethod2(){
        System.out.println("Second method Test is running");
    }

    @Test
    @Before
    @After
    void annotationDemoTestInvalidMethod(){
        System.out.println("Method with all annotations is running");
    }

}
