package org.cultro.roulette.reflection.metadata;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents metadata of a single constructor in a class.
 */
public class ConstructorMetadata {

    private final List<Class<?>> parameterTypes;
    private final List<String> annotations;

    public ConstructorMetadata(Constructor<?> constructor) {
        this.parameterTypes = Arrays.asList(constructor.getParameterTypes());
        this.annotations = Arrays.stream(constructor.getAnnotations())
                .map(annotation -> annotation.annotationType().getSimpleName())
                .collect(Collectors.toList());
    }

    public List<Class<?>> getParameterTypes() {
        return parameterTypes;
    }

    public List<String> getAnnotations() {
        return annotations;
    }

    @Override
    public String toString() {
        return "ConstructorMetadata{" +
                "parameterTypes=" + parameterTypes.stream().map(Class::getName).collect(Collectors.toList()) +
                ", annotations=" + annotations +
                '}';
    }
}