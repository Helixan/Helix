package org.cultro.roulette.util;

import org.cultro.roulette.lang.RString;

@SuppressWarnings("unused")
public final class StringUtils {

    public static int editDistance(String first, String second) {
        int[][] table = new int[first.length() + 1][second.length() + 1];

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

    public static int editDistance(String first, RString second) {
        return editDistance(first, second.toString());
    }

    public static int editDistance(RString first, String second) {
        return editDistance(first.toString(), second);
    }

    public static int editDistance(RString first, RString second) {
        return editDistance(first.toString(), second.toString());
    }

    public static int editDistanceIgnoreCase(String first, String second) {
        return editDistance(first.toLowerCase(), second.toLowerCase());
    }

    public static int editDistanceIgnoreCase(String first, RString second) {
        return editDistance(first.toLowerCase(), second.toString().toLowerCase());
    }
}
