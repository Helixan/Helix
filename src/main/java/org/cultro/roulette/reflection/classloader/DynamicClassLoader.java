package org.cultro.roulette.reflection.classloader;

import org.cultro.roulette.lang.ReflectionException;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.IOException;

/**
 * Custom ClassLoader to load classes dynamically at runtime.
 */
public class DynamicClassLoader extends ClassLoader {

    public DynamicClassLoader(ClassLoader parent) {
        super(parent);
    }

    /**
     * Loads a class from its binary name.
     *
     * @param className The fully qualified name of the class.
     * @return The Class object.
     * @throws ClassNotFoundException If the class cannot be loaded.
     */
    @Override
    public Class<?> loadClass(String className) throws ClassNotFoundException {
        Class<?> clazz = findLoadedClass(className);
        if (clazz == null) {
            if (className.startsWith("java.")) {
                clazz = getParent().loadClass(className);
            } else {
                clazz = findClass(className);
            }
        }
        return clazz;
    }

    /**
     * Finds the class with the specified name by reading its bytecode.
     *
     * @param name The name of the class.
     * @return The Class object.
     * @throws ClassNotFoundException If the class cannot be found.
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String path = name.replace('.', '/') + ".class";
        try (InputStream is = getResourceAsStream(path);
             ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {

            if (is == null) {
                throw new ClassNotFoundException("Cannot find class: " + name);
            }

            int data;
            while ((data = is.read()) != -1) {
                buffer.write(data);
            }

            byte[] classData = buffer.toByteArray();
            return defineClass(name, classData, 0, classData.length);
        } catch (IOException e) {
            throw new ClassNotFoundException("Failed to load class " + name, e);
        }
    }
}