package org.cultro.roulette.util;

import org.cultro.roulette.lang.RString;
import org.cultro.roulette.lang.Validate;

@SuppressWarnings("unused")
public final class StringUtils {

    public static int editDistance(final String first, final String second) {
        Validate.notNull(first, "No edit distance can be calculated on a null String");
        Validate.notNull(second, "No edit distance can be calculated on a null String");
        final int[][] table = new int[first.length() + 1][second.length() + 1];

        for (int i = 0; i <= first.length(); i++) {
            for (int j = 0; j <= second.length(); j++) {
                if (i == 0) {
                    table[i][j] = j;
                } else if (j == 0) {
                    table[i][j] = i;
                } else {
                    table[i][j] = MathUtils.min(table[i - 1][j - 1] + (first.charAt(i - 1) == second.charAt(j - 1) ? 0 : 1), table[i - 1][j] + 1, table[i][j - 1] + 1);
                }
            }
        }
        return table[first.length()][second.length()];
    }

    public static int editDistance(final String first, final RString second) {
        Validate.notNull(first, "No edit distance can be calculated on a null String");
        Validate.notNull(second, "No edit distance can be calculated on a null RString");
        return editDistance(first, second.toString());
    }

    public static int editDistance(final RString first, final String second) {
        Validate.notNull(first, "No edit distance can be calculated on a null RString");
        Validate.notNull(second, "No edit distance can be calculated on a null String");
        return editDistance(first.toString(), second);
    }

    public static int editDistance(final RString first, final RString second) {
        Validate.notNull(first, "No edit distance can be calculated on a null RString");
        Validate.notNull(second, "No edit distance can be calculated on a null RString");
        return editDistance(first.toString(), second.toString());
    }

    public static int editDistanceIgnoreCase(final String first, final String second) {
        Validate.notNull(first, "No edit distance can be calculated on a null String");
        Validate.notNull(second, "No edit distance can be calculated on a null String");
        return editDistance(first.toLowerCase(), second.toLowerCase());
    }

    public static int editDistanceIgnoreCase(final String first, final RString second) {
        Validate.notNull(first, "No edit distance can be calculated on a null String");
        Validate.notNull(second, "No edit distance can be calculated on a null RString");
        return editDistance(first.toLowerCase(), second.toString().toLowerCase());
    }

    public static int editDistanceIgnoreCase(final RString first, final String second) {
        Validate.notNull(first, "No edit distance can be calculated on a null RString");
        Validate.notNull(second, "No edit distance can be calculated on a null String");
        return editDistance(first.toString().toLowerCase(), second.toLowerCase());
    }

    public static int editDistanceIgnoreCase(final RString first, final RString second) {
        Validate.notNull(first, "No edit distance can be calculated on a null RString");
        Validate.notNull(second, "No edit distance can be calculated on a null RString");
        return editDistance(first.toString().toLowerCase(), second.toString().toLowerCase());
    }

    public static String join(String delimiter, String[] array, int startIndex, int endIndex) {
        Validate.isGreaterThanOrEqualTo(startIndex, 0, "The start index of an array must be non-negative");
        Validate.isLessThanOrEqualTo(array.length - 1, endIndex, "The end index cannot be outside of the array");
        Validate.isGreaterThanOrEqualTo(startIndex, endIndex, "The start index cannot be more than the end index");

        StringBuilder builder = new StringBuilder(array[startIndex]);
        for (int i = startIndex + 1; i < endIndex; i++) {
            builder.append(delimiter).append(array[i]);
        }
        return builder.toString();
    }

    public static boolean isByte(String value) {
        try {
            Byte.parseByte(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isDouble(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isFloat(String value) {
        try {
            Float.parseFloat(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isLong(String value) {
        try {
            Long.parseLong(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isShort(String value) {
        try {
            Short.parseShort(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String[] toLowerCase(String[] inputArray) {
        Validate.notNull(inputArray, "Cannot convert a null String array to an array up lowercase Strings");

        String[] lowercaseArray = new String[inputArray.length];
        for (int i = 0; i < inputArray.length; i++) {
            if (inputArray[i] != null) {
                lowercaseArray[i] = inputArray[i].toLowerCase();
            }
        }
        return lowercaseArray;
    }

    public static String[] toUpperCase(String[] inputArray) {
        Validate.notNull(inputArray, "Cannot convert a null String array to an array up uppercase Strings");

        String[] uppercaseArray = new String[inputArray.length];
        for (int i = 0; i < inputArray.length; i++) {
            if (inputArray[i] != null) {
                uppercaseArray[i] = inputArray[i].toUpperCase();
            }
        }
        return uppercaseArray;
    }
}
