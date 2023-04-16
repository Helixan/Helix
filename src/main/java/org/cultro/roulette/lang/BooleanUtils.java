package org.cultro.roulette.lang;

public class BooleanUtils {

    public static int compareTo(boolean first, boolean second) {
        if (first && !second) {
            return 1;
        } else if (!first && second) {
            return -1;
        }
        return 0;
    }
}
