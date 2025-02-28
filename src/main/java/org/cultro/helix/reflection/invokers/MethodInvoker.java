package org.cultro.helix.reflection.invokers;

import org.cultro.helix.lang.ReflectionException;
import org.cultro.helix.util.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Utility class to invoke methods via reflection.
 */
public class MethodInvoker {

    /**
     * Invokes a method on the given object with specified arguments.
     *
     * @param target     The object on which to invoke the method.
     * @param methodName The name of the method to invoke.
     * @param args       The arguments to pass to the method.
     * @return The result of the method invocation.
     * @throws ReflectionException If the method cannot be found or invoked.
     */
    public static Object invokeMethod(Object target, String methodName, Object... args) throws ReflectionException {
        Class<?> clazz = target.getClass();
        Method method = ReflectionUtils.findMethod(clazz, methodName, args);
        if (method == null) {
            throw new ReflectionException("Method " + methodName + " not found in class " + clazz.getName());
        }
        try {
            method.setAccessible(true);
            return method.invoke(target, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new ReflectionException("Failed to invoke method " + methodName, e);
        }
    }
}
