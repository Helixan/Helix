package org.cultro.roulette.reflection.metadata;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents metadata of a single method in a class.
 */
public class MethodMetadata {

    private final Method method;
    private final String name;
    private final Class<?> returnType;
    private final List<Class<?>> parameterTypes;
    private final List<String> annotations;

    public MethodMetadata(Method method) {
        this.method = method;
        this.name = method.getName();
        this.returnType = method.getReturnType();
        this.parameterTypes = Arrays.asList(method.getParameterTypes());
        this.annotations = Arrays.stream(method.getAnnotations())
                .map(annotation -> annotation.annotationType().getSimpleName())
                .collect(Collectors.toList());
    }

    public Method getMethod() {
        return method;
    }

    public String getName() {
        return name;
    }

    public Class<?> getReturnType() {
        return returnType;
    }

    public List<Class<?>> getParameterTypes() {
        return parameterTypes;
    }

    public List<String> getAnnotations() {
        return annotations;
    }

    @Override
    public String toString() {
        return "MethodMetadata{" +
                "name='" + name + '\'' +
                ", returnType=" + returnType.getName() +
                ", parameterTypes=" + parameterTypes.stream().map(Class::getName).collect(Collectors.toList()) +
                ", annotations=" + annotations +
                '}';
    }
}
