package ru.otus.javapro.homeworks.annotationprocessor.processor;

import com.google.auto.service.AutoService;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.QualifiedNameable;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

@SupportedAnnotationTypes("ru.otus.javapro.homeworks.annotationprocessor.annotations.ToStringCustom")
@AutoService(CustomAnnotationProcessor.class)
public class CustomAnnotationProcessor extends AbstractProcessor {

    private static final String SUFFIX_GENERATED = "Generated";
    private ProcessingEnvironment processingEnv = null;

    @Override
    public void init(ProcessingEnvironment processingEnv) {
        this.processingEnv = processingEnv;
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.out.println(String.format("Start annotation processing ..."));
        for (TypeElement annotation : annotations) {
            Set<? extends Element> annotatedElements
                    = roundEnv.getElementsAnnotatedWith(annotation);
            for (Element element : annotatedElements) {
                try {
                    generateProcessedFile(element,annotation);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(String.format("current element is:%s", element));
            }
        }
        return false;
    }

    private void generateProcessedFile(Element element, TypeElement annotation) throws IOException {

        String packageName = ((QualifiedNameable) element.getEnclosingElement()).getQualifiedName().toString();
        String className = element.getSimpleName().toString();
        String classDeclarationTitle = "public class " + className + SUFFIX_GENERATED + "{";

        JavaFileObject outPutFile = processingEnv.getFiler()
                .createSourceFile(className + SUFFIX_GENERATED + ".java");
        try (PrintWriter out = new PrintWriter(outPutFile.openWriter())) {

            if (packageName != null) {
                out.print("package ");
                out.print(packageName);
                out.println(";");
                out.println();
            }
            out.println(classDeclarationTitle);
            out.println("   @Override");
            out.println("   public String toString() {");
            out.printf ("   return \"This is overrided toString method for %s class \";", className);
            out.println("   }");
            out.println("}");
        }
    }
}
