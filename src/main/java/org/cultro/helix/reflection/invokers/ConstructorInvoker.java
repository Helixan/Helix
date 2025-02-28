package org.cultro.helix.reflection.invokers;

import org.cultro.helix.lang.ReflectionException;
import org.cultro.helix.util.ReflectionUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Utility class to create instances via constructors using reflection.
 */
public class ConstructorInvoker {

    /**
     * Creates a new instance of the specified class using the constructor that matches the provided arguments.
     *
     * @param clazz The class to instantiate.
     * @param args  The arguments to pass to the constructor.
     * @return A new instance of the specified class.
     * @throws ReflectionException If the constructor cannot be found or invoked.
     */
    public static <T> T createInstance(Class<T> clazz, Object... args) throws ReflectionException {
        Constructor<T> constructor = ReflectionUtils.findConstructor(clazz, args);
        if (constructor == null) {
            throw new ReflectionException("No suitable constructor found for class " + clazz.getName());
        }
        try {
            constructor.setAccessible(true);
            return constructor.newInstance(args);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new ReflectionException("Failed to create instance of class " + clazz.getName(), e);
        }
    }
}
