package processor;

import com.google.testing.compile.Compiler;
import com.google.testing.compile.JavaFileObjects;
import org.junit.Test;
import ru.otus.javapro.homeworks.annotationprocessor.processor.CustomAnnotationProcessor;

public class CustomAnnotationProcessorTest {

    @Test
    public void test(){
        System.out.println(Compiler.javac()
                .withProcessors(new CustomAnnotationProcessor())
                .compile(JavaFileObjects.forResource("java-files/ToStringAnnotationTestClass.java"))
                .generatedSourceFile("ToStringAnnotationTestClassGenerated.java")
//                .isPresent()
                );
    }
}
