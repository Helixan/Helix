package org.cultro.roulette.util;

public final class CharacterUtils {

    public static int compareTo(char first, char second) {
        if (first > second) {
            return 1;
        } else if (first < second) {
            return -1;
        }
        return 0;
    }
}
