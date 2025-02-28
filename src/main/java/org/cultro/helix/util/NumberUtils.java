package org.cultro.helix.util;

@SuppressWarnings("unused")
public final class NumberUtils {

    public static int compareTo(byte first, byte second) {
        if (first > second) {
            return 1;
        } else if (first < second) {
            return -1;
        }
        return 0;
    }

    public static int compareTo(double first, double second) {
        if (first > second) {
            return 1;
        } else if (first < second) {
            return -1;
        }
        return 0;
    }

    public static int compareTo(float first, float second) {
        if (first > second) {
            return 1;
        } else if (first < second) {
            return -1;
        }
        return 0;
    }

    public static int compareTo(int first, int second) {
        if (first > second) {
            return 1;
        } else if (first < second) {
            return -1;
        }
        return 0;
    }

    public static int compareTo(long first, long second) {
        if (first > second) {
            return 1;
        } else if (first < second) {
            return -1;
        }
        return 0;
    }

    public static int compareTo(short first, short second) {
        if (first > second) {
            return 1;
        } else if (first < second) {
            return -1;
        }
        return 0;
    }
}
