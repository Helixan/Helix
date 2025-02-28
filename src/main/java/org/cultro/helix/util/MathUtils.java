package org.cultro.helix.util;

import org.cultro.helix.lang.Function;
import org.cultro.helix.lang.Validate;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("unused")
public final class MathUtils {


    /**
     * Finds and returns the minimum value of a given array of comparable values
     *
     * @param values the array of values to find the minimum from
     * @return the minimum value from the array
     * @param <V> the type parameter representing the values, which must implement the {@link Comparable} interface
     * @throws IllegalArgumentException if all values in the array are null
     */
    @SafeVarargs
    public static <V extends Comparable<V>> V min(final V... values) {
        final int firstNotNullIndex = ArrayUtils.firstNotNullElementIndex(values);
        if (firstNotNullIndex == -1) {
            throw new IllegalArgumentException("There are no valid elements in the array to take a min of");
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


    /**
     * Finds and returns the maximum value of a given array of comparable values
     *
     * @param values the array of values to find the maximum from
     * @return the maximum value from the array
     * @param <V> the type parameter representing the values, which must implement the {@link Comparable} interface
     * @throws IllegalArgumentException if all values in the array are null
     */
    @SafeVarargs
    public static <V extends Comparable<V>> V max(final V... values) {
        final int firstNotNullIndex = ArrayUtils.firstNotNullElementIndex(values);
        if (firstNotNullIndex == -1) {
            throw new IllegalArgumentException("There are no valid elements in the array to take a max of");
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


    /**
     * Checks if a given value is between two other values and optionally including or excluding the lower/upper bounds
     *
     * @param input a reference to an object that will be compared against two other values
     * @param first the first bound that the {@code input} value will be compared against
     * @param second the second bound that the {@code input} value will be compared against
     * @param includeLowerBound if true, includes the lower-bound in the range
     * @param includeUpperBound if true, includes the upper-bound in the range
     * @return true if the input value is between {@code first} and {@code second} inclusive of their respective bounds. False otherwise
     * @param <V> the type parameter representing the values, which must implement the {@link Comparable} interface
     */
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


    /**
     * Checks if a given value is between two other values and optionally including the upper/lower bounds
     *
     * @param input a reference to value that will be compared against two other values
     * @param first the first bound that the {@code input} value will be compared against
     * @param second the second bound that the {@code input} value will be compared against
     * @return true if the input value is between {@code first} and {@code second} inclusive. False otherwise
     * @param <V> the type parameter representing the values, which must implement the {@link Comparable} interface
     */
    public static <V extends Comparable<V>> boolean isBetween(final V input, final V first, final V second) {
        return isBetween(input, first, second, true, true);
    }


    /**
     * Adds two integers and returns the result, or throws an exception if the result overflows the range of integers.
     *
     * @param a the first integer
     * @param b the second integer
     * @return the sum of a and b
     * @throws IllegalArgumentException if the result overflows the range of integers
     */
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


    /**
     * Adds two longs and returns the result, or throws an exception if the result overflows the range of longs.
     *
     * @param a the first long
     * @param b the second long
     * @return the sum of a and b
     * @throws IllegalArgumentException if the result overflows the range of longs
     */
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


    /**
     * Subtracts two integers and returns the result, or throws an exception if the result overflows the range of integers.
     *
     * @param a the first integer
     * @param b the second integer
     * @return the difference of a and b
     * @throws IllegalArgumentException if the result overflows the range of integers
     */
    public static int subOrThrow(int a, int b) {
        return addOrThrow(a, -b);
    }


    /**
     * Subtracts two longs and returns the result, or throws an exception if the result overflows the range of longs.
     *
     * @param a the first long
     * @param b the second long
     * @return the difference of a and b
     * @throws IllegalArgumentException if the result overflows the range of longs
     */
    public static long subOrThrow(long a, long b) {
        return addOrThrow(a, -b);
    }


    /**
     * Calculates the factorial of a given number.
     *
     * @param n the number for which the factorial needs to be calculated
     * @return the factorial of the given number
     * @throws IllegalArgumentException if the given number is negative
     */
    public static int factorial(int n) {
        Validate.isNotNegative(n, "You cannot take a factorial of negative numbers");
        int value = 1;
        for (int i = 2; i <= n; i++) {
            value *= i;
        }
        return value;
    }


    /**
     * Calculates the factorial of a given number.
     *
     * @param n the number for which the factorial needs to be calculated
     * @return the factorial of the given number
     * @throws IllegalArgumentException if the given number is negative
     */
    public static long factorialLong(int n) {
        Validate.isNotNegative(n, "You cannot take a factorial of negative numbers");
        long value = 1;
        for (int i = 2; i <= n; i++) {
            value *= i;
        }
        return value;
    }


    /**
     * Calculates the factorial of a given number.
     *
     * @param n the number for which the factorial needs to be calculated
     * @return the factorial of the given number
     * @throws IllegalArgumentException if the given number is negative
     */
    public static double factorialDouble(int n) {
        Validate.isNotNegative(n, "You cannot take a factorial of negative numbers");
        double value = 1;
        for (int i = 2; i <= n; i++) {
            value *= i;
        }
        return value;
    }


    /**
     * Calculates the natural logarithm of the gamma function for a given number.
     *
     * @param x the number for which the logarithm of the gamma function needs to be calculated
     * @return the natural logarithm of the gamma function for the given number
     */
    public static double logGamma(double x) {
        double tmp = (x - 0.5) * Math.log(x + 4.5) - (x + 4.5);
        double ser = 1.0 + 76.18009173 / (x + 0) - 86.50532033 / (x + 1)
                + 24.01409822 / (x + 2) - 1.231739516 / (x + 3)
                + 0.00120858003 / (x + 4) - 0.00000536382 / (x + 5);
        return tmp + Math.log(ser * Math.sqrt(2 * Math.PI));
    }


    /**
     * Calculates the gamma function for a given number.
     *
     * @param x the number for which the gamma function needs to be calculated
     * @return the gamma function for the given number
     */
    public static double gamma(double x) {
        return Math.exp(logGamma(x));
    }


    /**
     * Rounds the given number to the nearest integer in a fair way.
     * The fractional part of the number is taken into account when rounding.
     * The closer the fractional part of the number is to a certain integer, the more likely
     * the number will be rounded in that direction.
     *
     * @param x the number to be rounded
     * @param random the random number generator used for rounding
     * @return the rounded number
     * @throws IllegalArgumentException if the given number cannot be converted to an int
     */
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


    /**
     * Rounds the given number to the nearest integer in a fair way.
     * The fractional part of the number is taken into account when rounding.
     * The closer the fractional part of the number is to a certain integer, the more likely
     * the number will be rounded in that direction.
     *
     * @param x the number to be rounded
     * @return the rounded number
     * @throws IllegalArgumentException if the given number cannot be converted to an int
     */
    public static double fairRound(double x) {
        return fairRound(x, random());
    }


    /**
     * Determines if a given integer is a power of two
     *
     * @param n the integer to check
     * @return true if the integer is a power of two, false otherwise
     */
    public static boolean isPowerOfTwo(int n) {
        if (n == 0) {
            return true;
        }
        return (n > 0) && ((n & (n - 1)) == 0);
    }


    /**
     * Determines if a given long is a power of two
     *
     * @param n the long to check
     * @return true if the long is a power of two, false otherwise
     */
    public static boolean isPowerOfTwo(long n) {
        if (n == 0) {
            return true;
        }
        return (n > 0) && ((n & (n - 1)) == 0);
    }


    /**
     * Determines if a given integer is prime
     *
     * @param n the integer to check
     * @return true if the integer is a power of two, false otherwise
     */
    public static boolean isPrime(int n) {
        if (n <= 1) {
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


    /**
     * Determines if a given long is prime
     *
     * @param n the long to check
     * @return true if the integer is prime, false otherwise
     */
    public static boolean isPrime(long n) {
        if (n <= 1) {
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


    /**
     * Generates an array of prime numbers up to a given integer using the Sieve of Eratosthenes algorithm
     *
     * @param n the upper limit of the range of numbers to check for primality
     * @return an array of prime numbers up to n
     */
    public static int[] sieveOfEratosthenes(int n) {
        boolean[] primes = new boolean[n + 1];
        List<Integer> primeList = new ArrayList<>();

        for (int i = 2; i * i <= n; i++) {
            if (!primes[i]) {
                for (int j = i * i; j <= n; j += i) {
                    primes[j] = true;
                }
            }
        }

        for (int i = 2; i < primes.length; i++) {
            if (!primes[i]) {
                primeList.add(i);
            }
        }

        int[] primesArray = new int[primeList.size()];
        for (int i = 0; i < primeList.size(); i++) {
            primesArray[i] = primeList.get(i);
        }
        return primesArray;
    }


    /**
     * Calculates the combination (n C r) of two given integers.
     *
     * @param n the total number of items
     * @param r the number of items to be chosen
     * @return the combination (n C r)
     */
    public static double combination(int n, int r) {
        if (r > n - r) {
            r = n - r;
        }
        double result = 1;

        for (int i = 1; i <= r; i++) {
            result *= n - r + i;
            result /= i;
        }

        return result;
    }


    /**
     * Calculates the permutation (n P r) of two given integers.
     *
     * @param n the total number of items
     * @param r the number of items to be chosen
     * @return the permutation (n P r)
     */
    public static double permutation(int n, int r) {
        double result = 1;

        for (int i = 0; i < r; i++) {
            result *= n - i;
        }

        return result;
    }


    /**
     * Approximates the definite integral of a given function using the trapezoidal rule
     *
     * @param function the function to integrate
     * @param a the lower limit of integration
     * @param b the upper limit of integration
     * @param n the number of sub-intervals (must be an even positive integer)
     * @return the approximate value of the definite integral
     */
    public static double integrate(Function function, double a, double b, long n) {
        Validate.isNotNegative(n, "The number of intervals must be a positive integer");
        Validate.isEven(n, "The number of intervals must be even");

        double deltaX = (b - a) / n;
        double area = function.valueAt(a) + function.valueAt(b);

        for (int i = 1; i < n; i++) {
            area += function.valueAt(a + i * deltaX) * (i % 2 == 0 ? 2 : 4);
        }
        return area * (deltaX / 3);
    }


    /**
     * Approximates the definite integral of a given function using the trapezoidal rule
     *
     * @param function the function to integrate
     * @param a the lower limit of integration
     * @param b the upper limit of integration
     * @return the approximate value of the definite integral
     */
    public static double integrate(Function function, double a, double b) {
        return integrate(function, a, b, (long) (Math.ceil(max(a, b) - min(a, b)) * 100));
    }


    /**
     * Gets the thread's current random number generator
     *
     * @return an instance of Random
     */
    private static Random random() {
        return ThreadLocalRandom.current();
    }
}
