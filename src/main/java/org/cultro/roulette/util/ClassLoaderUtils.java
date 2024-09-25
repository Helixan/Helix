package org.cultro.roulette.util;

import org.cultro.roulette.reflection.classloader.DynamicClassLoader;

/**
 * Utility class for class loader related operations.
 */
public class ClassLoaderUtils {

    private static final DynamicClassLoader dynamicClassLoader = new DynamicClassLoader(ClassLoaderUtils.class.getClassLoader());

    /**
     * Loads a class dynamically using the DynamicClassLoader.
     *
     * @param className The fully qualified name of the class.
     * @return The Class object.
     * @throws ClassNotFoundException If the class cannot be loaded.
     */
    public static Class<?> loadClassDynamically(String className) throws ClassNotFoundException {
        return dynamicClassLoader.loadClass(className);
    }
}