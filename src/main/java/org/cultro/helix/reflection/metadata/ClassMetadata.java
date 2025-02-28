package org.cultro.helix.reflection.metadata;

import org.cultro.helix.lang.MetadataException;
import org.cultro.helix.lang.ReflectionException;
import org.cultro.helix.reflection.cache.ReflectionCache;
import org.cultro.helix.util.ReflectionUtils;

import java.util.List;

/**
 * Represents metadata of a class, including its fields, methods, and constructors.
 */
public class ClassMetadata {

    private final Class<?> clazz;
    private final List<FieldMetadata> fields;
    private final List<MethodMetadata> methods;
    private final List<ConstructorMetadata> constructors;

    /**
     * Constructs ClassMetadata for the given class.
     *
     * @param clazz The class to retrieve metadata for.
     * @throws ReflectionException If metadata retrieval fails.
     */
    public ClassMetadata(Class<?> clazz) throws ReflectionException, MetadataException {
        this.clazz = clazz;
        this.fields = ReflectionUtils.getFieldMetadata(clazz);
        this.methods = ReflectionUtils.getMethodMetadata(clazz);
        this.constructors = ReflectionUtils.getConstructorMetadata(clazz);
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public List<FieldMetadata> getFields() {
        return fields;
    }

    public List<MethodMetadata> getMethods() {
        return methods;
    }

    public List<ConstructorMetadata> getConstructors() {
        return constructors;
    }

    @Override
    public String toString() {
        return "ClassMetadata{" +
                "clazz=" + clazz.getName() +
                ", fields=" + fields +
                ", methods=" + methods +
                ", constructors=" + constructors +
                '}';
    }

    /**
     * Factory method to retrieve ClassMetadata using the ReflectionCache.
     *
     * @param clazz The class to retrieve metadata for.
     * @return The ClassMetadata object.
     * @throws ReflectionException If metadata retrieval fails.
     */
    public static ClassMetadata getCachedMetadata(Class<?> clazz) throws ReflectionException, MetadataException {
        return ReflectionCache.getClassMetadata(clazz);
    }
}
