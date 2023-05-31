package org.cultro.roulette.util;

@SuppressWarnings("unused")
public final class CharacterUtils {

    public static final char BACKSPACE = '\b';

    public static final char CR = '\r';

    public static final char FF = '\f';

    public static final char LF = '\n';

    public static final char NULL = '\0';

    public static final char TAB = '\t';

    public static boolean isAscii(final char value) {
        return value < 128;
    }

    public static boolean isAsciiAlpha(final char value) {
        return isAsciiAlphaLowercase(value) || isAsciiAlphaUppercase(value);
    }

    public static boolean isAsciiAlphaLowercase(final char value) {
        return value >= 'a' && value <= 'z';
    }

    public static boolean isAsciiAlphaUppercase(final char value) {
        return value >= 'A' && value <= 'Z';
    }

    public static boolean isAsciiControl(final char value) {
        return value < 32 || value == 127;
    }

    public static boolean isAsciiNumeral(final char value) {
        return value >= '0' && value <= '9';
    }

    public static boolean isAsciiPrintable(final char value) {
        return value >= 32 && value < 127;
    }

    public static int toIntValue(final char value) {
        if (!isAsciiNumeral(value)) {
            return value - '0';
        }
        throw new IllegalArgumentException("The character " + value + " is not an ASCII numeral");
    }

    public static int toIntValue(final char value, int defaultValue) {
        return isAsciiNumeral(value) ? value - '0' : defaultValue;
    }

    public static String toString(final char value) {
        return String.valueOf(value);
    }

    public static int compareTo(char first, char second) {
        if (first > second) {
            return 1;
        } else if (first < second) {
            return -1;
        }
        return 0;
    }
}
