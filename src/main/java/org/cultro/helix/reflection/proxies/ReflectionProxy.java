package org.cultro.helix.reflection.proxies;

import org.cultro.helix.lang.ReflectionException;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Creates dynamic proxies for interfaces to add additional behavior using reflection.
 */
public class ReflectionProxy implements InvocationHandler {

    private final Object target;

    public ReflectionProxy(Object target) {
        this.target = target;
    }

    /**
     * Creates a proxy instance for the specified interface.
     *
     * @param interfaceClass The interface class to proxy.
     * @param target         The target object to delegate method calls to.
     * @param <T>            The type of the interface.
     * @return A proxy instance implementing the specified interface.
     * @throws ReflectionException If proxy creation fails.
     */
    @SuppressWarnings("unchecked")
    public static <T> T createProxy(Class<T> interfaceClass, T target) throws ReflectionException {
        if (!interfaceClass.isInterface()) {
            throw new ReflectionException(interfaceClass.getName() + " is not an interface.");
        }
        ReflectionProxy handler = new ReflectionProxy(target);
        return (T) Proxy.newProxyInstance(
                interfaceClass.getClassLoader(),
                new Class<?>[]{interfaceClass},
                handler
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(target, args);
    }
}
