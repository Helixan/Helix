package org.cultro.helix.reflection.metadata;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Represents metadata of a single annotation on a class, field, or method.
 */
public class AnnotationMetadata {

    private final String annotationName;
    private final String[] elementNames;
    private final Object[] elementValues;

    public AnnotationMetadata(Annotation annotation) {
        this.annotationName = annotation.annotationType().getSimpleName();
        Method[] methods = annotation.annotationType().getDeclaredMethods();
        this.elementNames = Arrays.stream(methods)
                .map(Method::getName)
                .toArray(String[]::new);
        this.elementValues = Arrays.stream(methods)
                .map(method -> {
                    try {
                        return method.invoke(annotation);
                    } catch (Exception e) {
                        return null;
                    }
                })
                .toArray();
    }

    public String getAnnotationName() {
        return annotationName;
    }

    public String[] getElementNames() {
        return elementNames;
    }

    public Object[] getElementValues() {
        return elementValues;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AnnotationMetadata{")
                .append("annotationName='").append(annotationName).append('\'');
        if (elementNames.length > 0) {
            sb.append(", elements={");
            for (int i = 0; i < elementNames.length; i++) {
                sb.append(elementNames[i]).append('=').append(elementValues[i]);
                if (i < elementNames.length - 1) sb.append(", ");
            }
            sb.append("}");
        }
        sb.append('}');
        return sb.toString();
    }
}
