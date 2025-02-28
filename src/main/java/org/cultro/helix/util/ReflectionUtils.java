package org.cultro.helix.util;

import org.cultro.helix.lang.MetadataException;
import org.cultro.helix.lang.ReflectionException;
import org.cultro.helix.reflection.metadata.ConstructorMetadata;
import org.cultro.helix.reflection.metadata.FieldMetadata;
import org.cultro.helix.reflection.metadata.MethodMetadata;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Utility class providing common reflection operations.
 */
public class ReflectionUtils {

    /**
     * Finds a method in the given class that matches the method name and argument types.
     *
     * @param clazz      The class to search for the method.
     * @param methodName The name of the method.
     * @param args       The arguments to match the method parameters.
     * @return The matching Method object, or null if not found.
     */
    public static Method findMethod(Class<?> clazz, String methodName, Object... args) {
        Method[] methods = clazz.getDeclaredMethods();
        outer:
        for (Method method : methods) {
            if (!method.getName().equals(methodName)) continue;
            Class<?>[] paramTypes = method.getParameterTypes();
            if (paramTypes.length != args.length) continue;
            for (int i = 0; i < paramTypes.length; i++) {
                if (args[i] != null && !paramTypes[i].isAssignableFrom(args[i].getClass())) {
                    continue outer;
                }
            }
            return method;
        }
        return null;
    }

    /**
     * Finds a constructor in the given class that matches the argument types.
     *
     * @param clazz The class to search for the constructor.
     * @param args  The arguments to match the constructor parameters.
     * @param <T>   The type of the class.
     * @return The matching Constructor object, or null if not found.
     */
    public static <T> Constructor<T> findConstructor(Class<T> clazz, Object... args) {
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        outer:
        for (Constructor<?> constructor : constructors) {
            Class<?>[] paramTypes = constructor.getParameterTypes();
            if (paramTypes.length != args.length) continue;
            for (int i = 0; i < paramTypes.length; i++) {
                if (args[i] != null && !paramTypes[i].isAssignableFrom(args[i].getClass())) {
                    continue outer;
                }
            }
            @SuppressWarnings("unchecked")
            Constructor<T> matchedConstructor = (Constructor<T>) constructor;
            return matchedConstructor;
        }
        return null;
    }

    /**
     * Retrieves metadata for all fields in the given class.
     *
     * @param clazz The class to inspect.
     * @return A list of FieldMetadata objects.
     * @throws MetadataException If reflection fails.
     */
    public static List<FieldMetadata> getFieldMetadata(Class<?> clazz) throws MetadataException {
        try {
            List<FieldMetadata> fieldMetadataList = new ArrayList<>();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                fieldMetadataList.add(new FieldMetadata(field));
            }
            return fieldMetadataList;
        } catch (Exception e) {
            throw new MetadataException("Failed to retrieve field metadata for class " + clazz.getName(), e);
        }
    }

    /**
     * Retrieves metadata for all methods in the given class.
     *
     * @param clazz The class to inspect.
     * @return A list of MethodMetadata objects.
     * @throws MetadataException If reflection fails.
     */
    public static List<MethodMetadata> getMethodMetadata(Class<?> clazz) throws MetadataException {
        try {
            List<MethodMetadata> methodMetadataList = new ArrayList<>();
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                methodMetadataList.add(new MethodMetadata(method));
            }
            return methodMetadataList;
        } catch (Exception e) {
            throw new MetadataException("Failed to retrieve method metadata for class " + clazz.getName(), e);
        }
    }

    /**
     * Finds a field in the given class by name.
     *
     * @param clazz     The class to search.
     * @param fieldName The name of the field.
     * @return The Field object, or null if not found.
     */
    public static Field findField(Class<?> clazz, String fieldName) {
        try {
            return clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            return null;
        }
    }

    /**
     * Retrieves metadata for all constructors in the given class.
     *
     * @param clazz The class to inspect.
     * @return A list of ConstructorMetadata objects.
     * @throws ReflectionException If reflection fails.
     */
    public static List<ConstructorMetadata> getConstructorMetadata(Class<?> clazz) throws ReflectionException {
        try {
            List<ConstructorMetadata> constructorMetadataList = new ArrayList<>();
            Constructor<?>[] constructors = clazz.getDeclaredConstructors();
            for (Constructor<?> constructor : constructors) {
                constructorMetadataList.add(new ConstructorMetadata(constructor));
            }
            return constructorMetadataList;
        } catch (Exception e) {
            throw new ReflectionException("Failed to retrieve constructor metadata for class " + clazz.getName(), e);
        }
    }

    /**
     * Retrieves all classes within the specified package.
     *
     * @param packageName The package name to scan.
     * @return A list of Class objects.
     * @throws MetadataException If classes cannot be loaded.
     */
    public static List<Class<?>> getClassesInPackage(String packageName) throws MetadataException {
        try {
            String path = packageName.replace('.', '/');
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            Enumeration<URL> resources = classLoader.getResources(path);
            List<java.io.File> dirs = new ArrayList<>();
            while (resources.hasMoreElements()) {
                java.net.URL resource = resources.nextElement();
                dirs.add(new java.io.File(resource.getFile()));
            }
            ArrayList<Class<?>> classes = new ArrayList<>();
            for (java.io.File directory : dirs) {
                classes.addAll(findClasses(directory, packageName));
            }
            return classes;
        } catch (Exception e) {
            throw new MetadataException("Failed to get classes in package " + packageName, e);
        }
    }

    private static List<Class<?>> findClasses(java.io.File directory, String packageName) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }
        java.io.File[] files = directory.listFiles();
        if (files == null) {
            return classes;
        }
        for (java.io.File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }
}
