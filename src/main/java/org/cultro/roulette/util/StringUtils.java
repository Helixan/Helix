package org.cultro.roulette.util;

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
}
