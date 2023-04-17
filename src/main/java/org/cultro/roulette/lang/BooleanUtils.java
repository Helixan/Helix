package org.cultro.roulette.lang;

public class BooleanUtils {

    public static boolean and(final boolean... values) {
        Validate.notNull(values);
        for (final boolean value : values) {
            if (!value) {
                return false;
            }
        }
        return true;
    }

    public static boolean and(final Boolean... values) {
        Validate.notNull(values);
        Validate.containsNoNullElements(values);
        for (final Boolean value : values) {
            if (!value) {
                return false;
            }
        }
        return true;
    }

    public static boolean or(final boolean... values) {
        Validate.notNull(values);
        for (final boolean value : values) {
            if (value) {
                return true;
            }
        }
        return false;
    }

    public static boolean or(final Boolean... values) {
        Validate.notNull(values);
        Validate.containsNoNullElements(values);
        for (final Boolean value : values) {
            if (value) {
                return true;
            }
        }
        return false;
    }

    public static int compareTo(final boolean first, final boolean second) {
        if (first && !second) {
            return 1;
        } else if (!first && second) {
            return -1;
        }
        return 0;
    }
}
