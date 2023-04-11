package org.cultro.roulette.lang;

public class EnumUtils {

    public static <E extends Enum<E>> E getEnum(final Class<E> enumClass, final String enumName, final E defaultValue) {
        if (enumClass == null || enumName == null) {
            return defaultValue;
        }
        try {
            return Enum.valueOf(enumClass, enumName);
        } catch (IllegalArgumentException e) {
            return defaultValue;
        }
    }

    public static <E extends Enum<E>> E getEnum(final Class<E> enumClass, final String enumName) {
        return getEnum(enumClass, enumName, null);
    }

    public static <E extends Enum<E>> E getFirstEnumIgnoreCase(final Class<E> enumClass, final String enumName, final E defaultValue) {
        if (enumClass == null || enumName == null) {
            return defaultValue;
        }
        for (final E value : enumClass.getEnumConstants()) {
            if (enumName.equalsIgnoreCase(value.toString())) {
                return value;
            }
        }
        return defaultValue;
    }

    public static <E extends Enum<E>> E getFirstEnumIgnoreCase(final Class<E> enumClass, final String enumName) {
        return getFirstEnumIgnoreCase(enumClass, enumName, null);
    }

    public static <E extends Enum<E>> boolean isValidEnum(final Class<E> enumClass, final String enumName) {
        return getEnum(enumClass, enumName) != null;
    }

    public static <E extends Enum<E>> boolean isValidEnumIgnoreCase(final Class<E> enumClass, final String enumName) {
        return getFirstEnumIgnoreCase(enumClass, enumName) != null;
    }
}
