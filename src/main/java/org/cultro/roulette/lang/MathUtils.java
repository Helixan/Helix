package org.cultro.roulette.lang;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public final class MathUtils {

    @SafeVarargs
    public static <V extends Comparable<V>> V min(final V... values) {
        final int firstNotNullIndex = ArrayUtils.firstNotNullElementIndex(values);
        if (firstNotNullIndex == -1) {
            return null;
        }
        V currentMin = values[firstNotNullIndex];
        for (int i = firstNotNullIndex + 1; i < values.length; i++) {
            if (values[i] != null) {
                if (values[i].compareTo(currentMin) < 0) {
                    currentMin = values[i];
                }
            }
        }
        return currentMin;
    }

    @SafeVarargs
    public static <V extends Comparable<V>> V max(final V... values) {
        final int firstNotNullIndex = ArrayUtils.firstNotNullElementIndex(values);
        if (firstNotNullIndex == -1) {
            return null;
        }
        V currentMax = values[firstNotNullIndex];
        for (int i = firstNotNullIndex + 1; i < values.length; i++) {
            if (values[i] != null) {
                if (values[i].compareTo(currentMax) > 0) {
                    currentMax = values[i];
                }
            }
        }
        return currentMax;
    }

    public static <V extends Comparable<V>> boolean isBetween(final V input, final V first, final V second, final boolean includeLowerBound, final boolean includeUpperBound) {
        if (input == null || first == null || second == null) {
            return false;
        }
        final V min = min(first, second);
        final V max = max(first, second);
        if (min == null || max == null) {
            return false;
        }
        boolean isBetween = input.compareTo(min) > 0 && input.compareTo(max) < 0;
        if (includeLowerBound && !isBetween) {
            isBetween = input.compareTo(min) == 0;
        }
        if (includeUpperBound && !isBetween) {
            isBetween = input.compareTo(max) == 0;
        }
        return isBetween;
    }

    public static <V extends Comparable<V>> boolean isBetween(final V input, final V first, final V second) {
        return isBetween(input, first, second, true, true);
    }

    public static int addOrThrow(int a, int b) {
        if (b == 0) {
            return a;
        }
        if (b > 0 && a <= (Integer.MAX_VALUE - b)) {
            return a + b;
        }
        if (b < 0 && a >= (Integer.MIN_VALUE - b)) {
            return a + b;
        }
        throw new IllegalArgumentException("Integer arithmetic overflow a=" + a + ", b=" + b);
    }

    public static long addOrThrow(long a, long b) {
        if (b == 0) {
            return a;
        }
        if (b > 0 && a <= (Long.MAX_VALUE - b)) {
            return a + b;
        }
        if (b < 0 && a >= (Long.MIN_VALUE - b)) {
            return a + b;
        }
        throw new IllegalArgumentException("Long arithmetic overflow a=" + a + ", b=" + b);
    }

    public static int subOrThrow(int a, int b) {
        return addOrThrow(a, -b);
    }

    public static long subOrThrow(long a, long b) {
        return addOrThrow(a, -b);
    }

    public static int factorial(int n) {
        int value = 1;
        for (int i = 2; i <= n; i++) {
            value *= i;
        }
        return value;
    }

    public static long factorialLong(int n) {
        long value = 1;
        for (int i = 2; i <= n; i++) {
            value *= i;
        }
        return value;
    }

    public static double factorialDouble(int n) {
        double value = 1;
        for (int i = 2; i <= n; i++) {
            value *= i;
        }
        return value;
    }

    public static double logGamma(double x) {
        double tmp = (x - 0.5) * Math.log(x + 4.5) - (x + 4.5);
        double ser = 1.0 + 76.18009173 / (x + 0) - 86.50532033 / (x + 1)
                + 24.01409822 / (x + 2) - 1.231739516 / (x + 3)
                + 0.00120858003 / (x + 4) - 0.00000536382 / (x + 5);
        return tmp + Math.log(ser * Math.sqrt(2 * Math.PI));
    }

    public static double gamma(double x) {
        return Math.exp(logGamma(x));
    }

    public static double fairRound(double x, Random random) {
        if (x > Integer.MAX_VALUE || x < Integer.MIN_VALUE) {
            throw new IllegalArgumentException("Double value=" + x + " cannot be converted to an int");
        }
        if (random == null) {
            random = random();
        }
        double intPart = x >= 0 ? Math.floor(x) : Math.ceil(x);
        double decimal = x - intPart;
        if (decimal < 0) {
            decimal += 1;
        }
        if (random.nextDouble() < decimal) {
            return Math.ceil(x);
        }
        return Math.floor(x);
    }

    public static double fairRound(double x) {
        return fairRound(x, random());
    }

    public static boolean isPowerOfTwo(int n) {
        if (n == 0) {
            return true;
        }
        return (n > 0) && ((n & (n - 1)) == 0);
    }

    public static boolean isPowerOfTwo(long n) {
        if (n == 0) {
            return true;
        }
        return (n > 0) && ((n & (n - 1)) == 0);
    }

    public static boolean isPrime(int n) {
        if (n <= 0) {
            return false;
        }
        if (n < 4) {
            return true;
        }
        int sqrt = (int) Math.sqrt(n);
        int currentDivisor = 2;
        while (currentDivisor <= sqrt) {
            if (n % currentDivisor == 0) {
                return false;
            }
            currentDivisor++;
        }
        return true;
    }

    public static boolean isPrime(long n) {
        if (n <= 0) {
            return false;
        }
        if (n < 4) {
            return true;
        }
        long sqrt = (long) Math.sqrt(n);
        long currentDivisor = 2;
        while (currentDivisor <= sqrt) {
            if (n % currentDivisor == 0) {
                return false;
            }
            currentDivisor++;
        }
        return true;
    }

    private static Random random() {
        return ThreadLocalRandom.current();
    }
}
