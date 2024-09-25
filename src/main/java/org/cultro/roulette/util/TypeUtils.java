package org.cultro.roulette.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for type-related operations.
 */
public class TypeUtils {

    private static final Map<Class<?>, Class<?>> PRIMITIVE_WRAPPER_MAP = new HashMap<>();

    static {
        PRIMITIVE_WRAPPER_MAP.put(boolean.class, Boolean.class);
        PRIMITIVE_WRAPPER_MAP.put(byte.class, Byte.class);
        PRIMITIVE_WRAPPER_MAP.put(char.class, Character.class);
        PRIMITIVE_WRAPPER_MAP.put(double.class, Double.class);
        PRIMITIVE_WRAPPER_MAP.put(float.class, Float.class);
        PRIMITIVE_WRAPPER_MAP.put(int.class, Integer.class);
        PRIMITIVE_WRAPPER_MAP.put(long.class, Long.class);
        PRIMITIVE_WRAPPER_MAP.put(short.class, Short.class);
        PRIMITIVE_WRAPPER_MAP.put(void.class, Void.class);
    }

    /**
     * Checks if the given class is a primitive type.
     *
     * @param clazz The class to check.
     * @return True if primitive, else false.
     */
    public static boolean isPrimitive(Class<?> clazz) {
        return clazz.isPrimitive();
    }

    /**
     * Converts a primitive type to its corresponding wrapper class.
     *
     * @param clazz The primitive class.
     * @return The wrapper class, or null if not a primitive.
     */
    public static Class<?> toWrapper(Class<?> clazz) {
        return PRIMITIVE_WRAPPER_MAP.get(clazz);
    }

    /**
     * Checks if two classes are compatible, considering primitive-wrapper relationships.
     *
     * @param paramType The parameter type.
     * @param argType   The argument type.
     * @return True if compatible, else false.
     */
    public static boolean isCompatible(Class<?> paramType, Class<?> argType) {
        if (paramType.isAssignableFrom(argType)) {
            return true;
        }
        if (paramType.isPrimitive()) {
            Class<?> wrapper = toWrapper(paramType);
            return wrapper != null && wrapper.isAssignableFrom(argType);
        }
        return false;
    }
}