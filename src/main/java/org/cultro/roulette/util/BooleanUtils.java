package org.cultro.roulette.util;

import org.cultro.roulette.lang.Validate;

@SuppressWarnings("unused")
public final class BooleanUtils {

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

    public static int compareTo(final boolean first, final boolean second) {
        if (first && !second) {
            return 1;
        } else if (!first && second) {
            return -1;
        }
        return 0;
    }

    public static boolean negate(final boolean value) {
        return !value;
    }

    public static Boolean negate(final Boolean value) {
        if (value == null) {
            return null;
        }
        return !value;
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

    public static boolean toBoolean(final Boolean value) {
        Validate.notNull(value, "Cannot convert null to a primitive boolean");
        return value;
    }

    public static boolean toBoolean(final int value) {
        return value != 0;
    }

    public static boolean toBoolean(final long value) {
        return value != 0;
    }

    public static boolean xor(final boolean... values) {
        Validate.notNull(values);
        boolean found = false;
        for (final boolean value : values) {
            if (value) {
                if (found) {
                    return false;
                }
                found = true;
            }
        }
        return found;
    }

    public static boolean xor(final Boolean... values) {
        Validate.notNull(values);
        Validate.containsNoNullElements(values);
        boolean found = false;
        for (final Boolean value : values) {
            if (value) {
                if (found) {
                    return false;
                }
                found = true;
            }
        }
        return found;
    }
}
