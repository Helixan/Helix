package org.cultro.helix.reflection.metadata;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents metadata of a single field in a class.
 */
public class FieldMetadata {

    private final Field field;
    private final String name;
    private final Class<?> type;
    private final List<String> annotations;

    public FieldMetadata(Field field) {
        this.field = field;
        this.name = field.getName();
        this.type = field.getType();
        this.annotations = Arrays.stream(field.getAnnotations())
                .map(annotation -> annotation.annotationType().getSimpleName())
                .collect(Collectors.toList());
    }

    public Field getField() {
        return field;
    }

    public String getName() {
        return name;
    }

    public Class<?> getType() {
        return type;
    }

    public List<String> getAnnotations() {
        return annotations;
    }

    @Override
    public String toString() {
        return "FieldMetadata{" +
                "name='" + name + '\'' +
                ", type=" + type.getName() +
                ", annotations=" + annotations +
                '}';
    }
}
