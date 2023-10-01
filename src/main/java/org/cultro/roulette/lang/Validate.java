package org.cultro.roulette.lang;

/**
 * The Validate class provides static methods for performing various validation checks.
 */
@SuppressWarnings("unused")
public final class Validate {

    /**
     * Checks if an object is not null.
     *
     * @param object  The object to check.
     * @param message The error message to throw if the object is null.
     * @throws IllegalArgumentException If the object is null.
     */
    public static <T> void notNull(T object, String message) {
        if (object == null) {
            if (message != null) {
                throw new IllegalArgumentException(message);
            } else {
                throw new IllegalArgumentException();
            }
        }
    }


    /**
     * Checks if an object is not null.
     *
     * @param object The object to check.
     * @throws IllegalArgumentException If the object is null.
     */
    public static <T> void notNull(T object) {
        if (object == null) {
            throw new IllegalArgumentException();
        }
    }


    /**
     * Checks if a double value is finite.
     *
     * @param value   The double value to check.
     * @param message The error message to throw if the value is not finite.
     * @throws IllegalArgumentException If the value is not finite.
     */
    public static void finite(double value, String message) {
        if (!Double.isFinite(value)) {
            if (message != null) {
                throw new IllegalArgumentException(message);
            } else {
                throw new IllegalArgumentException();
            }
        }
    }


    /**
     * Checks if a double value is finite.
     *
     * @param value The double value to check.
     * @throws IllegalArgumentException If the value is not finite.
     */
    public static void finite(double value) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException();
        }
    }


    /**
     * Checks if an iterable contains at least one not-null element.
     *
     * @param iterable The iterable to check.
     * @param message  The error message to throw if the iterable contains only null elements.
     * @throws IllegalArgumentException If the iterable contains only null elements.
     */
    public static <T> void containsAtLeastOneNotNullElement(Iterable<T> iterable, String message) {
        boolean onlyNullElements = true;
        if (iterable != null) {
            while (iterable.iterator().hasNext() && onlyNullElements) {
                T temp = iterable.iterator().next();
                if (temp != null) {
                    onlyNullElements = false;
                }
            }
        }
        if (onlyNullElements) {
            if (message != null) {
                throw new IllegalArgumentException(message);
            } else {
                throw new IllegalArgumentException();
            }
        }
    }


    /**
     * Checks if an iterable contains at least one not-null element.
     *
     * @param iterable The iterable to check.
     * @throws IllegalArgumentException If the iterable contains only null elements.
     */
    public static <T> void containsAtLeastOneNotNullElement(Iterable<T> iterable) {
        boolean onlyNullElements = true;
        if (iterable != null) {
            while (iterable.iterator().hasNext() && onlyNullElements) {
                T temp = iterable.iterator().next();
                if (temp != null) {
                    onlyNullElements = false;
                }
            }
        }
        if (onlyNullElements) {
            throw new IllegalArgumentException();
        }
    }


    /**
     * Checks if an array contains at least one not-null element.
     *
     * @param array   The array to check.
     * @param message The error message to throw if the array contains only null elements.
     * @throws IllegalArgumentException If the array contains only null elements.
     */
    public static <T> void containsAtLeastOneNotNullElement(T[] array, String message) {
        boolean onlyNullElements = true;
        if (array != null) {
            for (T current : array) {
                if (current != null) {
                    onlyNullElements = false;
                    break;
                }
            }
        }
        if (onlyNullElements) {
            if (message != null) {
                throw new IllegalArgumentException(message);
            } else {
                throw new IllegalArgumentException();
            }
        }
    }


    /**
     * Checks if an array contains at least one not-null element.
     *
     * @param array The array to check.
     * @throws IllegalArgumentException If the array contains only null elements.
     */
    public static <T> void containsAtLeastOneNotNullElement(T[] array) {
        boolean onlyNullElements = true;
        if (array != null) {
            for (T current : array) {
                if (current != null) {
                    onlyNullElements = false;
                    break;
                }
            }
        }
        if (onlyNullElements) {
            throw new IllegalArgumentException();
        }
    }


    /**
     * Checks if an iterable contains no null elements.
     *
     * @param iterable The iterable to check.
     * @param message  The error message to throw if the iterable contains null elements.
     * @throws IllegalArgumentException If the iterable contains null elements.
     */
    public static <T> void containsNoNullElements(Iterable<T> iterable, String message) {
        boolean nullElementFound = false;
        if (iterable != null) {
            while (iterable.iterator().hasNext() && !nullElementFound) {
                T temp = iterable.iterator().next();
                if (temp == null) {
                    nullElementFound = true;
                }
            }
        }
        if (nullElementFound) {
            if (message != null) {
                throw new IllegalArgumentException(message);
            } else {
                throw new IllegalArgumentException();
            }
        }
    }


    /**
     * Checks if an iterable contains no null elements.
     *
     * @param iterable The iterable to check.
     * @throws IllegalArgumentException If the iterable contains null elements.
     */
    public static <T> void containsNoNullElements(Iterable<T> iterable) {
        boolean nullElementFound = false;
        if (iterable != null) {
            while (iterable.iterator().hasNext() && !nullElementFound) {
                T temp = iterable.iterator().next();
                if (temp == null) {
                    nullElementFound = true;
                }
            }
        }
        if (nullElementFound) {
            throw new IllegalArgumentException();
        }
    }


    /**
     * Checks if an array contains no null elements.
     *
     * @param array   The array to check for null elements.
     * @param message The error message to throw if null elements are found.
     * @throws IllegalArgumentException If the array contains null elements.
     */
    public static <T> void containsNoNullElements(T[] array, String message) {
        boolean nullElementFound = false;
        if (array != null) {
            for (T current : array) {
                if (current == null) {
                    nullElementFound = true;
                    break;
                }
            }
        }
        if (nullElementFound) {
            if (message != null) {
                throw new IllegalArgumentException(message);
            } else {
                throw new IllegalArgumentException();
            }
        }
    }


    /**
     * Checks if an array contains no null elements.
     *
     * @param array The array to check for null elements.
     * @throws IllegalArgumentException If the array contains null elements.
     */
    public static <T> void containsNoNullElements(T[] array) {
        boolean nullElementFound = false;
        if (array != null) {
            for (T current : array) {
                if (current == null) {
                    nullElementFound = true;
                    break;
                }
            }
        }
        if (nullElementFound) {
            throw new IllegalArgumentException();
        }
    }


    /**
     * Checks if two comparable objects are equivalent (have the same value).
     *
     * @param first   The first comparable object.
     * @param second  The second comparable object.
     * @param message The error message to throw if the objects are not equivalent.
     * @throws IllegalArgumentException If the objects are not equivalent.
     */
    public static <T extends Comparable<T>> void isEquivalent(T first, T second, String message) {
        if (first != null && second != null) {
            if (first.compareTo(second) == 0) {
                return;
            }
        }
        throw new IllegalArgumentException(message);
    }


    /**
     * Checks if two comparable objects are equivalent (have the same value).
     *
     * @param first  The first comparable object.
     * @param second The second comparable object.
     * @throws IllegalArgumentException If the objects are not equivalent.
     */
    public static <T extends Comparable<T>> void isEquivalent(T first, T second) {
        if (first != null && second != null) {
            if (first.compareTo(second) == 0) {
                return;
            }
        }
        throw new IllegalArgumentException();
    }


    /**
     * Checks if a byte value is even.
     *
     * @param value   The byte value to check.
     * @param message The error message to throw if the value is not even.
     * @throws IllegalArgumentException If the value is not even.
     */
    public static void isEven(byte value, String message) {
        if (value % 2 == 0) {
            return;
        }
        throw new IllegalArgumentException(message);
    }


    /**
     * Checks if a byte value is even.
     *
     * @param value The byte value to check.
     * @throws IllegalArgumentException If the value is not even.
     */
    public static void isEven(byte value) {
        if (value % 2 == 0) {
            return;
        }
        throw new IllegalArgumentException();
    }


    /**
     * Checks if a double value is even.
     *
     * @param value   The double value to check.
     * @param message The error message to throw if the value is not even.
     * @throws IllegalArgumentException If the value is not even.
     */
    public static void isEven(double value, String message) {
        if (value % 2 == 0) {
            return;
        }
        throw new IllegalArgumentException(message);
    }


    /**
     * Checks if a double value is even.
     *
     * @param value The double value to check.
     * @throws IllegalArgumentException If the value is not even.
     */
    public static void isEven(double value) {
        if (value % 2 == 0) {
            return;
        }
        throw new IllegalArgumentException();
    }


    /**
     * Checks if a float value is even.
     *
     * @param value   The float value to check for evenness.
     * @param message The error message to throw if the value is not even.
     * @throws IllegalArgumentException If the value is not even.
     */
    public static void isEven(float value, String message) {
        if (value % 2 == 0) {
            return;
        }
        throw new IllegalArgumentException(message);
    }


    /**
     * Checks if a float value is even.
     *
     * @param value The float value to check for evenness.
     * @throws IllegalArgumentException If the value is not even.
     */
    public static void isEven(float value) {
        if (value % 2 == 0) {
            return;
        }
        throw new IllegalArgumentException();
    }


    /**
     * Checks if an int value is even.
     *
     * @param value   The int value to check for evenness.
     * @param message The error message to throw if the value is not even.
     * @throws IllegalArgumentException If the value is not even.
     */
    public static void isEven(int value, String message) {
        if (value % 2 == 0) {
            return;
        }
        throw new IllegalArgumentException(message);
    }


    /**
     * Checks if an int value is even.
     *
     * @param value The int value to check for evenness.
     * @throws IllegalArgumentException If the value is not even.
     */
    public static void isEven(int value) {
        if (value % 2 == 0) {
            return;
        }
        throw new IllegalArgumentException();
    }


    /**
     * Checks if a long value is even.
     *
     * @param value   The long value to check for evenness.
     * @param message The error message to throw if the value is not even.
     * @throws IllegalArgumentException If the value is not even.
     */
    public static void isEven(long value, String message) {
        if (value % 2 == 0) {
            return;
        }
        throw new IllegalArgumentException(message);
    }


    /**
     * Checks if a long value is even.
     *
     * @param value The long value to check for evenness.
     * @throws IllegalArgumentException If the value is not even.
     */
    public static void isEven(long value) {
        if (value % 2 == 0) {
            return;
        }
        throw new IllegalArgumentException();
    }


    /**
     * Checks if a short value is even.
     *
     * @param value   The short value to check for evenness.
     * @param message The error message to throw if the value is not even.
     * @throws IllegalArgumentException If the value is not even.
     */
    public static void isEven(short value, String message) {
        if (value % 2 == 0) {
            return;
        }
        throw new IllegalArgumentException(message);
    }


    /**
     * Checks if a short value is even.
     *
     * @param value The short value to check for evenness.
     * @throws IllegalArgumentException If the value is not even.
     */
    public static void isEven(short value) {
        if (value % 2 == 0) {
            return;
        }
        throw new IllegalArgumentException();
    }


    /**
     * Checks if a boolean value is false.
     *
     * @param value   The boolean value to check for falseness.
     * @param message The error message to throw if the value is not false.
     * @throws IllegalArgumentException If the value is not false.
     */
    public static void isFalse(boolean value, String message) {
        if (!value) {
            return;
        }
        throw new IllegalArgumentException(message);
    }


    /**
     * Checks if a boolean value is false.
     *
     * @param value The boolean value to check for falseness.
     * @throws IllegalArgumentException If the value is not false.
     */
    public static void isFalse(boolean value) {
        if (!value) {
            return;
        }
        throw new IllegalArgumentException();
    }


    /**
     * Checks if the first comparable object is greater than the second comparable object.
     *
     * @param first   The first comparable object.
     * @param second  The second comparable object.
     * @param message The error message to throw if the first is not greater than the second.
     * @throws IllegalArgumentException If the first is not greater than the second.
     */
    public static <T extends Comparable<T>> void isGreaterThan(T first, T second, String message) {
        if (first != null && second != null) {
            if (first.compareTo(second) > 0) {
                return;
            }
        }
        throw new IllegalArgumentException(message);
    }


    /**
     * Checks if the first comparable object is greater than the second comparable object.
     *
     * @param first  The first comparable object.
     * @param second The second comparable object.
     * @throws IllegalArgumentException If the first is not greater than the second.
     */
    public static <T extends Comparable<T>> void isGreaterThan(T first, T second) {
        if (first != null && second != null) {
            if (first.compareTo(second) > 0) {
                return;
            }
        }
        throw new IllegalArgumentException();
    }


    /**
     * Checks if the first comparable object is greater than or equal to the second comparable object.
     *
     * @param first   The first comparable object.
     * @param second  The second comparable object.
     * @param message The error message to throw if the first is not greater than or equal to the second.
     * @throws IllegalArgumentException If the first is not greater than or equal to the second.
     */
    public static <T extends Comparable<T>> void isGreaterThanOrEqualTo(T first, T second, String message) {
        if (first != null && second != null) {
            if (first.compareTo(second) >= 0) {
                return;
            }
        }
        throw new IllegalArgumentException(message);
    }


    /**
     * Checks if the first comparable object is greater than or equal to the second comparable object.
     *
     * @param first  The first comparable object.
     * @param second The second comparable object.
     * @throws IllegalArgumentException If the first is not greater than or equal to the second.
     */
    public static <T extends Comparable<T>> void isGreaterThanOrEqualTo(T first, T second) {
        if (first != null && second != null) {
            if (first.compareTo(second) >= 0) {
                return;
            }
        }
        throw new IllegalArgumentException();
    }


    /**
     * Checks if the first comparable object is less than the second comparable object.
     *
     * @param first   The first comparable object.
     * @param second  The second comparable object.
     * @param message The error message to throw if the first is not less than the second.
     * @throws IllegalArgumentException If the first is not less than the second.
     */
    public static <T extends Comparable<T>> void isLessThan(T first, T second, String message) {
        if (first != null && second != null) {
            if (first.compareTo(second) < 0) {
                return;
            }
        }
        throw new IllegalArgumentException(message);
    }


    /**
     * Checks if the first comparable object is less than the second comparable object.
     *
     * @param first  The first comparable object.
     * @param second The second comparable object.
     * @throws IllegalArgumentException If the first is not less than the second.
     */
    public static <T extends Comparable<T>> void isLessThan(T first, T second) {
        if (first != null && second != null) {
            if (first.compareTo(second) < 0) {
                return;
            }
        }
        throw new IllegalArgumentException();
    }


    /**
     * Checks if the first comparable object is less than or equal to the second comparable object.
     *
     * @param first   The first comparable object.
     * @param second  The second comparable object.
     * @param message The error message to throw if the first is not less than or equal to the second.
     * @throws IllegalArgumentException If the first is not less than or equal to the second.
     */
    public static <T extends Comparable<T>> void isLessThanOrEqualTo(T first, T second, String message) {
        if (first != null && second != null) {
            if (first.compareTo(second) <= 0) {
                return;
            }
        }
        throw new IllegalArgumentException(message);
    }


    /**
     * Checks if the first comparable object is less than or equal to the second comparable object.
     *
     * @param first  The first comparable object.
     * @param second The second comparable object.
     * @throws IllegalArgumentException If the first is not less than or equal to the second.
     */
    public static <T extends Comparable<T>> void isLessThanOrEqualTo(T first, T second) {
        if (first != null && second != null) {
            if (first.compareTo(second) <= 0) {
                return;
            }
        }
        throw new IllegalArgumentException();
    }


    /**
     * Checks if a byte value is negative.
     *
     * @param number  The byte value to check for negativity.
     * @param message The error message to throw if the value is not negative.
     * @throws IllegalArgumentException If the value is not negative.
     */
    public static void isNegative(byte number, String message) {
        if (number < 0) {
            return;
        }
        throw new IllegalArgumentException(message);
    }


    /**
     * Checks if a byte value is negative.
     *
     * @param number The byte value to check for negativity.
     * @throws IllegalArgumentException If the value is not negative.
     */
    public static void isNegative(byte number) {
        if (number < 0) {
            return;
        }
        throw new IllegalArgumentException();
    }


    /**
     * Checks if a double value is negative.
     *
     * @param number  The double value to check for negativity.
     * @param message The error message to throw if the value is not negative.
     * @throws IllegalArgumentException If the value is not negative.
     */
    public static void isNegative(double number, String message) {
        if (number < 0) {
            return;
        }
        throw new IllegalArgumentException(message);
    }


    /**
     * Checks if a double value is negative.
     *
     * @param number The double value to check for negativity.
     * @throws IllegalArgumentException If the value is not negative.
     */
    public static void isNegative(double number) {
        if (number < 0) {
            return;
        }
        throw new IllegalArgumentException();
    }


    /**
     * Checks if a float value is negative.
     *
     * @param number  The float value to check for negativity.
     * @param message The error message to throw if the value is not negative.
     * @throws IllegalArgumentException If the value is not negative.
     */
    public static void isNegative(float number, String message) {
        if (number < 0) {
            return;
        }
        throw new IllegalArgumentException(message);
    }


    /**
     * Checks if a float value is negative.
     *
     * @param number The float value to check for negativity.
     * @throws IllegalArgumentException If the value is not negative.
     */
    public static void isNegative(float number) {
        if (number < 0) {
            return;
        }
        throw new IllegalArgumentException();
    }


    /**
     * Checks if an int value is negative.
     *
     * @param number  The int value to check for negativity.
     * @param message The error message to throw if the value is not negative.
     * @throws IllegalArgumentException If the value is not negative.
     */
    public static void isNegative(int number, String message) {
        if (number < 0) {
            return;
        }
        throw new IllegalArgumentException(message);
    }


    /**
     * Checks if an int value is negative.
     *
     * @param number The int value to check for negativity.
     * @throws IllegalArgumentException If the value is not negative.
     */
    public static void isNegative(int number) {
        if (number < 0) {
            return;
        }
        throw new IllegalArgumentException();
    }


    /**
     * Checks if a long value is negative.
     *
     * @param number  The long value to check for negativity.
     * @param message The error message to throw if the value is not negative.
     * @throws IllegalArgumentException If the value is not negative.
     */
    public static void isNegative(long number, String message) {
        if (number < 0) {
            return;
        }
        throw new IllegalArgumentException(message);
    }


    /**
     * Checks if a long value is negative.
     *
     * @param number The long value to check for negativity.
     * @throws IllegalArgumentException If the value is not negative.
     */
    public static void isNegative(long number) {
        if (number < 0) {
            return;
        }
        throw new IllegalArgumentException();
    }


    /**
     * Checks if a short value is negative.
     *
     * @param number  The short value to check for negativity.
     * @param message The error message to throw if the value is not negative.
     * @throws IllegalArgumentException If the value is not negative.
     */
    public static void isNegative(short number, String message) {
        if (number < 0) {
            return;
        }
        throw new IllegalArgumentException(message);
    }


    /**
     * Checks if a short value is negative.
     *
     * @param number The short value to check for negativity.
     * @throws IllegalArgumentException If the value is not negative.
     */
    public static void isNegative(short number) {
        if (number < 0) {
            return;
        }
        throw new IllegalArgumentException();
    }


    /**
     * Checks if the given byte number is not negative. If it is negative, it throws an IllegalArgumentException with the provided message.
     *
     * @param number  The byte number to check.
     * @param message The error message to include in the exception if the check fails.
     * @throws IllegalArgumentException If the number is negative.
     */
    public static void isNotNegative(byte number, String message) {
        if (number >= 0) {
            return;
        }
        throw new IllegalArgumentException(message);
    }


    /**
     * Checks if the given byte number is not negative. If it is negative, it throws an IllegalArgumentException.
     *
     * @param number The byte number to check.
     * @throws IllegalArgumentException If the number is negative.
     */
    public static void isNotNegative(byte number) {
        if (number >= 0) {
            return;
        }
        throw new IllegalArgumentException();
    }


    /**
     * Checks if the given double number is not negative. If it is negative, it throws an IllegalArgumentException with the provided message.
     *
     * @param number  The double number to check.
     * @param message The error message to include in the exception if the check fails.
     * @throws IllegalArgumentException If the number is negative.
     */
    public static void isNotNegative(double number, String message) {
        if (number >= 0) {
            return;
        }
        throw new IllegalArgumentException(message);
    }


    /**
     * Checks if the given double number is not negative. If it is negative, it throws an IllegalArgumentException.
     *
     * @param number The double number to check.
     * @throws IllegalArgumentException If the number is negative.
     */
    public static void isNotNegative(double number) {
        if (number >= 0) {
            return;
        }
        throw new IllegalArgumentException();
    }


    /**
     * Checks if the given float number is not negative. If it is negative, it throws an IllegalArgumentException with the provided message.
     *
     * @param number  The float number to check.
     * @param message The error message to include in the exception if the check fails.
     * @throws IllegalArgumentException If the number is negative.
     */
    public static void isNotNegative(float number, String message) {
        if (number >= 0) {
            return;
        }
        throw new IllegalArgumentException(message);
    }


    /**
     * Checks if the given float number is not negative. If it is negative, it throws an IllegalArgumentException.
     *
     * @param number The float number to check.
     * @throws IllegalArgumentException If the number is negative.
     */
    public static void isNotNegative(float number) {
        if (number >= 0) {
            return;
        }
        throw new IllegalArgumentException();
    }


    /**
     * Checks if the given int number is not negative. If it is negative, it throws an IllegalArgumentException with the provided message.
     *
     * @param number  The int number to check.
     * @param message The error message to include in the exception if the check fails.
     * @throws IllegalArgumentException If the number is negative.
     */
    public static void isNotNegative(int number, String message) {
        if (number >= 0) {
            return;
        }
        throw new IllegalArgumentException(message);
    }


    /**
     * Checks if the given int number is not negative. If it is negative, it throws an IllegalArgumentException.
     *
     * @param number The int number to check.
     * @throws IllegalArgumentException If the number is negative.
     */
    public static void isNotNegative(int number) {
        if (number >= 0) {
            return;
        }
        throw new IllegalArgumentException();
    }


    /**
     * Checks if the given long number is not negative. If it is negative, it throws an IllegalArgumentException with the provided message.
     *
     * @param number  The long number to check.
     * @param message The error message to include in the exception if the check fails.
     * @throws IllegalArgumentException If the number is negative.
     */
    public static void isNotNegative(long number, String message) {
        if (number >= 0) {
            return;
        }
        throw new IllegalArgumentException(message);
    }


    /**
     * Checks if the given long number is not negative. If it is negative, it throws an IllegalArgumentException.
     *
     * @param number The long number to check.
     * @throws IllegalArgumentException If the number is negative.
     */
    public static void isNotNegative(long number) {
        if (number >= 0) {
            return;
        }
        throw new IllegalArgumentException();
    }


    /**
     * Checks if the given short number is not negative. If it is negative, it throws an IllegalArgumentException with the provided message.
     *
     * @param number  The short number to check.
     * @param message The error message to include in the exception if the check fails.
     * @throws IllegalArgumentException If the number is negative.
     */
    public static void isNotNegative(short number, String message) {
        if (number >= 0) {
            return;
        }
        throw new IllegalArgumentException(message);
    }


    /**
     * Checks if the given short number is not negative. If it is negative, it throws an IllegalArgumentException.
     *
     * @param number The short number to check.
     * @throws IllegalArgumentException If the number is negative.
     */
    public static void isNotNegative(short number) {
        if (number >= 0) {
            return;
        }
        throw new IllegalArgumentException();
    }


    /**
     * Checks if the given byte number is not positive. If it is positive or zero, it throws an IllegalArgumentException with the provided message.
     *
     * @param number  The byte number to check.
     * @param message The error message to include in the exception if the check fails.
     * @throws IllegalArgumentException If the number is positive or zero.
     */
    public static void isNotPositive(byte number, String message) {
        if (number <= 0) {
            return;
        }
        throw new IllegalArgumentException(message);
    }


    /**
     * Checks if the given byte number is not positive. If it is positive or zero, it throws an IllegalArgumentException.
     *
     * @param number The byte number to check.
     * @throws IllegalArgumentException If the number is positive or zero.
     */
    public static void isNotPositive(byte number) {
        if (number <= 0) {
            return;
        }
        throw new IllegalArgumentException();
    }


    /**
     * Checks if the given double number is not positive. If it is positive or zero, it throws an IllegalArgumentException with the provided message.
     *
     * @param number  The double number to check.
     * @param message The error message to include in the exception if the check fails.
     * @throws IllegalArgumentException If the number is positive or zero.
     */
    public static void isNotPositive(double number, String message) {
        if (number <= 0) {
            return;
        }
        throw new IllegalArgumentException(message);
    }


    /**
     * Checks if the given double number is not positive. If it is positive or zero, it throws an IllegalArgumentException.
     *
     * @param number The double number to check.
     * @throws IllegalArgumentException If the number is positive or zero.
     */
    public static void isNotPositive(double number) {
        if (number <= 0) {
            return;
        }
        throw new IllegalArgumentException();
    }


    /**
     * Checks if the given float number is not positive. If it is positive or zero, it throws an IllegalArgumentException with the provided message.
     *
     * @param number  The float number to check.
     * @param message The error message to include in the exception if the check fails.
     * @throws IllegalArgumentException If the number is positive or zero.
     */
    public static void isNotPositive(float number, String message) {
        if (number <= 0) {
            return;
        }
        throw new IllegalArgumentException(message);
    }


    /**
     * Checks if the given float number is not positive. If it is positive or zero, it throws an IllegalArgumentException.
     *
     * @param number The float number to check.
     * @throws IllegalArgumentException If the number is positive or zero.
     */
    public static void isNotPositive(float number) {
        if (number <= 0) {
            return;
        }
        throw new IllegalArgumentException();
    }


    /**
     * Checks if the given int number is not positive. If it is positive or zero, it throws an IllegalArgumentException with the provided message.
     *
     * @param number  The int number to check.
     * @param message The error message to include in the exception if the check fails.
     * @throws IllegalArgumentException If the number is positive or zero.
     */
    public static void isNotPositive(int number, String message) {
        if (number <= 0) {
            return;
        }
        throw new IllegalArgumentException(message);
    }


    /**
     * Checks if the given int number is not positive. If it is positive or zero, it throws an IllegalArgumentException.
     *
     * @param number The int number to check.
     * @throws IllegalArgumentException If the number is positive or zero.
     */
    public static void isNotPositive(int number) {
        if (number <= 0) {
            return;
        }
        throw new IllegalArgumentException();
    }


    /**
     * Checks if the given long number is not positive. If it is positive or zero, it throws an IllegalArgumentException with the provided message.
     *
     * @param number  The long number to check.
     * @param message The error message to include in the exception if the check fails.
     * @throws IllegalArgumentException If the number is positive or zero.
     */
    public static void isNotPositive(long number, String message) {
        if (number <= 0) {
            return;
        }
        throw new IllegalArgumentException(message);
    }


    /**
     * Checks if the given long number is not positive. If it is positive or zero, it throws an IllegalArgumentException.
     *
     * @param number The long number to check.
     * @throws IllegalArgumentException If the number is positive or zero.
     */
    public static void isNotPositive(long number) {
        if (number <= 0) {
            return;
        }
        throw new IllegalArgumentException();
    }


    /**
     * Checks if the given short number is not positive. If it is positive or zero, it throws an IllegalArgumentException with the provided message.
     *
     * @param number  The short number to check.
     * @param message The error message to include in the exception if the check fails.
     * @throws IllegalArgumentException If the number is positive or zero.
     */
    public static void isNotPositive(short number, String message) {
        if (number <= 0) {
            return;
        }
        throw new IllegalArgumentException(message);
    }


    /**
     * Checks if the given short number is not positive. If it is positive or zero, it throws an IllegalArgumentException.
     *
     * @param number The short number to check.
     * @throws IllegalArgumentException If the number is positive or zero.
     */
    public static void isNotPositive(short number) {
        if (number <= 0) {
            return;
        }
        throw new IllegalArgumentException();
    }


    /**
     * Checks if the given byte value is odd. If it is not odd, it throws an IllegalArgumentException with the provided message.
     *
     * @param value   The byte value to check.
     * @param message The error message to include in the exception if the check fails.
     * @throws IllegalArgumentException If the value is not odd.
     */
    public static void isOdd(byte value, String message) {
        if (value % 2 == 1) {
            return;
        }
        throw new IllegalArgumentException(message);
    }


    /**
     * Checks if the given byte value is odd. If it is not odd, it throws an IllegalArgumentException.
     *
     * @param value The byte value to check.
     * @throws IllegalArgumentException If the value is not odd.
     */
    public static void isOdd(byte value) {
        if (value % 2 == 1) {
            return;
        }
        throw new IllegalArgumentException();
    }


    /**
     * Checks if the given double value is odd. If it is not odd, it throws an IllegalArgumentException with the provided message.
     *
     * @param value   The double value to check.
     * @param message The error message to include in the exception if the check fails.
     * @throws IllegalArgumentException If the value is not odd.
     */
    public static void isOdd(double value, String message) {
        if (value % 2 == 1) {
            return;
        }
        throw new IllegalArgumentException(message);
    }


    /**
     * Checks if the given double value is odd. If it is not odd, it throws an IllegalArgumentException.
     *
     * @param value The double value to check.
     * @throws IllegalArgumentException If the value is not odd.
     */
    public static void isOdd(double value) {
        if (value % 2 == 1) {
            return;
        }
        throw new IllegalArgumentException();
    }


    /**
     * Checks if the given float value is odd. If it is not odd, it throws an IllegalArgumentException with the provided message.
     *
     * @param value   The float value to check.
     * @param message The error message to include in the exception if the check fails.
     * @throws IllegalArgumentException If the value is not odd.
     */
    public static void isOdd(float value, String message) {
        if (value % 2 == 1) {
            return;
        }
        throw new IllegalArgumentException(message);
    }


    /**
     * Checks if the given float value is odd. If it is not odd, it throws an IllegalArgumentException.
     *
     * @param value The float value to check.
     * @throws IllegalArgumentException If the value is not odd.
     */
    public static void isOdd(float value) {
        if (value % 2 == 1) {
            return;
        }
        throw new IllegalArgumentException();
    }


    /**
     * Checks if the given int value is odd. If it is not odd, it throws an IllegalArgumentException with the provided message.
     *
     * @param value   The int value to check.
     * @param message The error message to include in the exception if the check fails.
     * @throws IllegalArgumentException If the value is not odd.
     */
    public static void isOdd(int value, String message) {
        if (value % 2 == 1) {
            return;
        }
        throw new IllegalArgumentException(message);
    }


    /**
     * Checks if the given int value is odd. If it is not odd, it throws an IllegalArgumentException.
     *
     * @param value The int value to check.
     * @throws IllegalArgumentException If the value is not odd.
     */
    public static void isOdd(int value) {
        if (value % 2 == 1) {
            return;
        }
        throw new IllegalArgumentException();
    }


    /**
     * Checks if the given long value is odd. If it is not odd, it throws an IllegalArgumentException with the provided message.
     *
     * @param value   The long value to check.
     * @param message The error message to include in the exception if the check fails.
     * @throws IllegalArgumentException If the value is not odd.
     */
    public static void isOdd(long value, String message) {
        if (value % 2 == 1) {
            return;
        }
        throw new IllegalArgumentException(message);
    }


    /**
     * Checks if the given long value is odd. If it is not odd, it throws an IllegalArgumentException.
     *
     * @param value The long value to check.
     * @throws IllegalArgumentException If the value is not odd.
     */
    public static void isOdd(long value) {
        if (value % 2 == 1) {
            return;
        }
        throw new IllegalArgumentException();
    }


    /**
     * Checks if the given short value is odd. If it is not odd, it throws an IllegalArgumentException with the provided message.
     *
     * @param value   The short value to check.
     * @param message The error message to include in the exception if the check fails.
     * @throws IllegalArgumentException If the value is not odd.
     */
    public static void isOdd(short value, String message) {
        if (value % 2 == 1) {
            return;
        }
        throw new IllegalArgumentException(message);
    }


    /**
     * Checks if the given short value is odd. If it is not odd, it throws an IllegalArgumentException.
     *
     * @param value The short value to check.
     * @throws IllegalArgumentException If the value is not odd.
     */
    public static void isOdd(short value) {
        if (value % 2 == 1) {
            return;
        }
        throw new IllegalArgumentException();
    }


    /**
     * Checks if the given byte value is positive (greater than 0). If it is not positive, it throws an IllegalArgumentException with the provided message.
     *
     * @param number  The byte value to check.
     * @param message The error message to include in the exception if the check fails.
     * @throws IllegalArgumentException If the value is not positive.
     */
    public static void isPositive(byte number, String message) {
        if (number > 0) {
            return;
        }
        throw new IllegalArgumentException(message);
    }


    /**
     * Checks if the given byte value is positive (greater than 0). If it is not positive, it throws an IllegalArgumentException.
     *
     * @param number The byte value to check.
     * @throws IllegalArgumentException If the value is not positive.
     */
    public static void isPositive(byte number) {
        if (number > 0) {
            return;
        }
        throw new IllegalArgumentException();
    }


    /**
     * Checks if the given double value is positive (greater than 0). If it is not positive, it throws an IllegalArgumentException with the provided message.
     *
     * @param number  The double value to check.
     * @param message The error message to include in the exception if the check fails.
     * @throws IllegalArgumentException If the value is not positive.
     */
    public static void isPositive(double number, String message) {
        if (number > 0) {
            return;
        }
        throw new IllegalArgumentException(message);
    }


    /**
     * Checks if the given double value is positive (greater than 0). If it is not positive, it throws an IllegalArgumentException.
     *
     * @param number The double value to check.
     * @throws IllegalArgumentException If the value is not positive.
     */
    public static void isPositive(double number) {
        if (number > 0) {
            return;
        }
        throw new IllegalArgumentException();
    }


    /**
     * Checks if the given float value is positive (greater than 0). If it is not positive, it throws an IllegalArgumentException with the provided message.
     *
     * @param number  The float value to check.
     * @param message The error message to include in the exception if the check fails.
     * @throws IllegalArgumentException If the value is not positive.
     */
    public static void isPositive(float number, String message) {
        if (number > 0) {
            return;
        }
        throw new IllegalArgumentException(message);
    }


    /**
     * Checks if the given float value is positive (greater than 0). If it is not positive, it throws an IllegalArgumentException.
     *
     * @param number The float value to check.
     * @throws IllegalArgumentException If the value is not positive.
     */
    public static void isPositive(float number) {
        if (number > 0) {
            return;
        }
        throw new IllegalArgumentException();
    }


    /**
     * Checks if the given int value is positive (greater than 0). If it is not positive, it throws an IllegalArgumentException with the provided message.
     *
     * @param number  The int value to check.
     * @param message The error message to include in the exception if the check fails.
     * @throws IllegalArgumentException If the value is not positive.
     */
    public static void isPositive(int number, String message) {
        if (number > 0) {
            return;
        }
        throw new IllegalArgumentException(message);
    }


    /**
     * Checks if the given int value is positive (greater than 0). If it is not positive, it throws an IllegalArgumentException.
     *
     * @param number The int value to check.
     * @throws IllegalArgumentException If the value is not positive.
     */
    public static void isPositive(int number) {
        if (number > 0) {
            return;
        }
        throw new IllegalArgumentException();
    }


    /**
     * Checks if the given long value is positive (greater than 0). If it is not positive, it throws an IllegalArgumentException with the provided message.
     *
     * @param number  The long value to check.
     * @param message The error message to include in the exception if the check fails.
     * @throws IllegalArgumentException If the value is not positive.
     */
    public static void isPositive(long number, String message) {
        if (number > 0) {
            return;
        }
        throw new IllegalArgumentException(message);
    }


    /**
     * Checks if the given long value is positive (greater than 0). If it is not positive, it throws an IllegalArgumentException.
     *
     * @param number The long value to check.
     * @throws IllegalArgumentException If the value is not positive.
     */
    public static void isPositive(long number) {
        if (number > 0) {
            return;
        }
        throw new IllegalArgumentException();
    }


    /**
     * Checks if the given short value is positive (greater than 0). If it is not positive, it throws an IllegalArgumentException with the provided message.
     *
     * @param number  The short value to check.
     * @param message The error message to include in the exception if the check fails.
     * @throws IllegalArgumentException If the value is not positive.
     */
    public static void isPositive(short number, String message) {
        if (number > 0) {
            return;
        }
        throw new IllegalArgumentException(message);
    }


    /**
     * Checks if the given short value is positive (greater than 0). If it is not positive, it throws an IllegalArgumentException.
     *
     * @param number The short value to check.
     * @throws IllegalArgumentException If the value is not positive.
     */
    public static void isPositive(short number) {
        if (number > 0) {
            return;
        }
        throw new IllegalArgumentException();
    }


    /**
     * Checks if a boolean value is true.
     *
     * @param value   The boolean value to check.
     * @param message The error message to throw if the value is not true.
     * @throws IllegalArgumentException If the value is not true.
     */
    public static void isTrue(boolean value, String message) {
        if (value) {
            return;
        }
        throw new IllegalArgumentException(message);
    }


    /**
     * Checks if a boolean value is true.
     *
     * @param value The boolean value to check.
     * @throws IllegalArgumentException If the value is not true.
     */
    public static void isTrue(boolean value) {
        if (value) {
            return;
        }
        throw new IllegalArgumentException();
    }


    /**
     * Checks if an index is valid (non-negative).
     *
     * @param index   The index to check.
     * @param message The error message to throw if the index is invalid.
     * @throws IllegalArgumentException If the index is not valid.
     */
    public static void isValidIndex(int index, String message) {
        if (index < 0) {
            if (message != null) {
                throw new IllegalArgumentException(message);
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    /**
     * Checks if an index is valid (non-negative).
     *
     * @param index The index to check.
     * @throws IllegalArgumentException If the index is not valid.
     */

    public static void isValidIndex(int index) {
        if (index < 0) {
            throw new IllegalArgumentException();
        }
    }
}
