package org.cultro.helix.util;

import org.cultro.helix.lang.Validate;

@SuppressWarnings("unused")
public final class BooleanUtils {

    public static boolean and(final boolean... values) {
        Validate.notNull(values, "Cannot run the 'and' operation on a null array of values");
        for (final boolean value : values) {
            if (!value) {
                return false;
            }
        }
        return true;
    }

    public static boolean and(final Boolean... values) {
        Validate.notNull(values, "Cannot run the 'and' operation on a null array of values");
        Validate.containsNoNullElements(values, "Cannot run the 'and' operation on an array with null elements");
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
        Validate.notNull(values, "Cannot run the 'or' operation on a null array of values");
        for (final boolean value : values) {
            if (value) {
                return true;
            }
        }
        return false;
    }

    public static boolean or(final Boolean... values) {
        Validate.notNull(values, "Cannot run the 'or' operation on a null array of values");
        Validate.containsNoNullElements(values, "Cannot run the 'or' operation on an array with null elements");
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

    public static Boolean toBooleanObject(boolean value) {
        return value ? Boolean.TRUE : Boolean.FALSE;
    }

    public static Boolean toBooleanObject(final int value) {
        return value != 0;
    }

    public static Boolean toBooleanObject(final long value) {
        return value != 0;
    }

    public static boolean xor(final boolean... values) {
        Validate.notNull(values, "Cannot run the 'xor' operation on a null array of values");
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
        Validate.notNull(values, "Cannot run the 'xor' operation on a null array of values");
        Validate.containsNoNullElements(values, "Cannot run the 'xor' operation on an array with null elements");
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
