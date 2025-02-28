package org.cultro.helix.reflection.proxies;

import org.cultro.helix.lang.ReflectionException;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Implementation of InvocationHandler to handle method calls on proxy instances.
 */
public class InvocationHandlerImpl implements InvocationHandler {

    private final Object target;

    public InvocationHandlerImpl(Object target) {
        this.target = target;
    }

    /**
     * Handles the method invocation on the proxy instance.
     *
     * @param proxy  The proxy instance.
     * @param method The method being invoked.
     * @param args   The arguments passed to the method.
     * @return The result of the method invocation.
     * @throws Throwable If an error occurs during method invocation.
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            return method.invoke(target, args);
        } catch (Exception e) {
            throw new ReflectionException("Error invoking method " + method.getName(), e);
        }
    }
}