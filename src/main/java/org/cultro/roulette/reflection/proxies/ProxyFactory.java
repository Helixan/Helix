package org.cultro.roulette.reflection.proxies;

import org.cultro.roulette.lang.ReflectionException;

import java.lang.reflect.Proxy;

/**
 * Factory class to create proxy instances for interfaces.
 */
public class ProxyFactory {

    /**
     * Creates a proxy instance for the specified interface, delegating method calls to the target object.
     *
     * @param interfaceClass The interface to proxy.
     * @param target         The target object implementing the interface.
     * @param <T>            The type of the interface.
     * @return A proxy instance implementing the specified interface.
     * @throws ReflectionException If proxy creation fails.
     */
    @SuppressWarnings("unchecked")
    public static <T> T createProxy(Class<T> interfaceClass, T target) throws ReflectionException {
        if (!interfaceClass.isInterface()) {
            throw new ReflectionException(interfaceClass.getName() + " is not an interface.");
        }
        InvocationHandlerImpl handler = new InvocationHandlerImpl(target);
        return (T) Proxy.newProxyInstance(
                interfaceClass.getClassLoader(),
                new Class<?>[]{interfaceClass},
                handler
        );
    }
}