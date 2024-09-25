package org.cultro.roulette.reflection.invokers;

import org.cultro.roulette.lang.ReflectionException;
import org.cultro.roulette.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * Utility class to get and set field values via reflection.
 */
public class FieldAccessor {

    /**
     * Retrieves the value of a specified field from the target object.
     *
     * @param target    The object from which to retrieve the field value.
     * @param fieldName The name of the field.
     * @return The value of the field.
     * @throws ReflectionException If the field does not exist or cannot be accessed.
     */
    public static Object getFieldValue(Object target, String fieldName) throws ReflectionException {
        Class<?> clazz = target.getClass();
        Field field = ReflectionUtils.findField(clazz, fieldName);
        if (field == null) {
            throw new ReflectionException("Field " + fieldName + " not found in class " + clazz.getName());
        }
        try {
            field.setAccessible(true);
            return field.get(target);
        } catch (IllegalAccessException e) {
            throw new ReflectionException("Unable to access field " + fieldName, e);
        }
    }

    /**
     * Sets the value of a specified field in the target object.
     *
     * @param target    The object in which to set the field value.
     * @param fieldName The name of the field.
     * @param value     The value to set.
     * @throws ReflectionException If the field does not exist or cannot be accessed.
     */
    public static void setFieldValue(Object target, String fieldName, Object value) throws ReflectionException {
        Class<?> clazz = target.getClass();
        Field field = ReflectionUtils.findField(clazz, fieldName);
        if (field == null) {
            throw new ReflectionException("Field " + fieldName + " not found in class " + clazz.getName());
        }
        try {
            field.setAccessible(true);
            field.set(target, value);
        } catch (IllegalAccessException e) {
            throw new ReflectionException("Unable to set field " + fieldName, e);
        }
    }
}