package org.cultro.roulette.util;

import org.cultro.roulette.lang.Validate;

import java.lang.reflect.Array;
import java.util.Comparator;

@SuppressWarnings({"unused", "DuplicatedCode"})
public final class ArrayUtils {

    public static final boolean[] EMPTY_BOOLEAN_ARRAY = {};
    public static final Boolean[] EMPTY_BOOLEAN_OBJECT_ARRAY = {};
    public static final byte[] EMPTY_BYTE_ARRAY = {};
    public static final Byte[] EMPTY_BYTE_OBJECT_ARRAY = {};
    public static final char[] EMPTY_CHAR_ARRAY = {};
    public static final Character[] EMPTY_CHARACTER_OBJECT_ARRAY = {};
    public static final Class<?>[] EMPTY_CLASS_ARRAY = {};
    public static final double[] EMPTY_DOUBLE_ARRAY = {};
    public static final Double[] EMPTY_DOUBLE_OBJECT_ARRAY = {};
    public static final float[] EMPTY_FLOAT_ARRAY = {};
    public static final Float[] EMPTY_FLOAT_OBJECT_ARRAY = {};
    public static final int[] EMPTY_INT_ARRAY = {};
    public static final Integer[] EMPTY_INTEGER_OBJECT_ARRAY = {};
    public static final long[] EMPTY_LONG_ARRAY = {};
    public static final Long[] EMPTY_LONG_OBJECT_ARRAY = {};
    public static final Object[] EMPTY_OBJECT_ARRAY = {};
    public static final short[] EMPTY_SHORT_ARRAY = {};
    public static final Short[] EMPTY_SHORT_OBJECT_ARRAY = {};
    public static final String[] EMPTY_STRING_ARRAY = {};
    public static final int INDEX_NOT_FOUND = -1;

    /**
     * The constructor for this class should never be called.
     * This constructor is only public in the case that a tool requires
     * a JavaBean instance to run.
     */
    public ArrayUtils() {
    }


    /**
     * Determines whether an input array contains at least one non-null element
     *
     * @param array the array to check for non-null elements
     * @return true if the input array contains at least one non-null element, false otherwise
     * @param <T> the type of the elements in the array
     */
    static <T> boolean containsOneNonNullElement(final T[] array) {
        if (array == null) {
            return false;
        }
        for (final T type : array) {
            if (type != null) {
                return true;
            }
        }
        return false;
    }


    /**
     * Finds the first index of a non-null element
     *
     * @param array the array to check for the index of a non-null element
     * @return the index of the first non-null array element, or {@link #INDEX_NOT_FOUND} otherwise
     * @param <T> the type of the elements in the array
     */
    static <T> int firstNotNullElementIndex(final T[] array) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }


    /**
     * Returns the index of the first null element in an array
     *
     * @param array the array to check for the index of a null element
     * @return the index of the first null array element, or -1 otherwise
     * @param <T> the type of the elements in the array
     */
    static <T> int firstNullElementIndex(final T[] array) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }


    /**
     * Returns the component type of the input array
     *
     * @param array the array whose component type is to be returned
     * @return the component type of the input array, or null if the input array is null
     * @param <T> the type of the elements in the array
     */
    @SuppressWarnings("unchecked")
    static <T> Class<T> getComponentType(final T[] array) {
        if (array == null) {
            return null;
        }
        return (Class<T>) array.getClass().getComponentType();
    }


    /**
     * Returns the total length of all input arrays combined
     *
     * @param arrays the arrays whose total length is to be returned
     * @return the total length of all input arrays combined
     * @param <T> the type of the elements in the array
     */
    @SafeVarargs
    static <T> int lengthOfAllArraysCombined(final T[]... arrays) {
        int length = 0;
        for (final T[] array : arrays) {
            if (array != null) {
                length += array.length;
            }
        }
        return length;
    }


    /**
     * Returns a new array instance of the specified type and length
     *
     * @param type the type of the elements in the new array
     * @param length the length of the new array
     * @return a new array instance of the specified type and length
     * @param <T> the type of the elements in the array
     */
    @SuppressWarnings("unchecked")
    static <T> T[] newArrayInstance(final Class<T> type, final int length) {
        return (T[]) Array.newInstance(type, length);
    }


    /**
     * Returns a new array that contains all elements of the input array followed by all elements of the
     * input varargs array
     *
     * @param array the array to which the elements are to be added
     * @param elementsToAdd the elements to be added to the array
     * @return a new array that contains all elements of the input array followed by all elements of the
     * input varargs array
     * @param <T> the type of the elements in the array
     */
    @SafeVarargs
    public static <T> T[] addAll(final T[] array, final T... elementsToAdd) {
        if (elementsToAdd == null) {
            return clone(array);
        }
        if (array == null) {
            return clone(elementsToAdd);
        }
        final Class<T> componentType = getComponentType(array);
        final T[] newArray = newArrayInstance(componentType, array.length + elementsToAdd.length);
        System.arraycopy(array, 0, newArray, 0, array.length);
        System.arraycopy(elementsToAdd, 0, newArray, array.length, elementsToAdd.length);
        return newArray;
    }

    public static boolean[] addAll(final boolean[] array, final boolean... elementsToAdd) {
        if (elementsToAdd == null) {
            return clone(array);
        }
        if (array == null) {
            return clone(elementsToAdd);
        }
        final boolean[] newArray = new boolean[array.length + elementsToAdd.length];
        System.arraycopy(array, 0, newArray, 0, array.length);
        System.arraycopy(elementsToAdd, 0, newArray, array.length, elementsToAdd.length);
        return newArray;
    }

    public static byte[] addAll(final byte[] array, final byte... elementsToAdd) {
        if (elementsToAdd == null) {
            return clone(array);
        }
        if (array == null) {
            return clone(elementsToAdd);
        }
        final byte[] newArray = new byte[array.length + elementsToAdd.length];
        System.arraycopy(array, 0, newArray, 0, array.length);
        System.arraycopy(elementsToAdd, 0, newArray, array.length, elementsToAdd.length);
        return newArray;
    }

    public static char[] addAll(final char[] array, final char... elementsToAdd) {
        if (elementsToAdd == null) {
            return clone(array);
        }
        if (array == null) {
            return clone(elementsToAdd);
        }
        final char[] newArray = new char[array.length + elementsToAdd.length];
        System.arraycopy(array, 0, newArray, 0, array.length);
        System.arraycopy(elementsToAdd, 0, newArray, array.length, elementsToAdd.length);
        return newArray;
    }

    public static double[] addAll(final double[] array, final double... elementsToAdd) {
        if (elementsToAdd == null) {
            return clone(array);
        }
        if (array == null) {
            return clone(elementsToAdd);
        }
        final double[] newArray = new double[array.length + elementsToAdd.length];
        System.arraycopy(array, 0, newArray, 0, array.length);
        System.arraycopy(elementsToAdd, 0, newArray, array.length, elementsToAdd.length);
        return newArray;
    }

    public static float[] addAll(final float[] array, final float... elementsToAdd) {
        if (elementsToAdd == null) {
            return clone(array);
        }
        if (array == null) {
            return clone(elementsToAdd);
        }
        final float[] newArray = new float[array.length + elementsToAdd.length];
        System.arraycopy(array, 0, newArray, 0, array.length);
        System.arraycopy(elementsToAdd, 0, newArray, array.length, elementsToAdd.length);
        return newArray;
    }

    public static int[] addAll(final int[] array, final int... elementsToAdd) {
        if (elementsToAdd == null) {
            return clone(array);
        }
        if (array == null) {
            return clone(elementsToAdd);
        }
        final int[] newArray = new int[array.length + elementsToAdd.length];
        System.arraycopy(array, 0, newArray, 0, array.length);
        System.arraycopy(elementsToAdd, 0, newArray, array.length, elementsToAdd.length);
        return newArray;
    }

    public static long[] addAll(final long[] array, final long... elementsToAdd) {
        if (elementsToAdd == null) {
            return clone(array);
        }
        if (array == null) {
            return clone(elementsToAdd);
        }
        final long[] newArray = new long[array.length + elementsToAdd.length];
        System.arraycopy(array, 0, newArray, 0, array.length);
        System.arraycopy(elementsToAdd, 0, newArray, array.length, elementsToAdd.length);
        return newArray;
    }

    public static short[] addAll(final short[] array, final short... elementsToAdd) {
        if (elementsToAdd == null) {
            return clone(array);
        }
        if (array == null) {
            return clone(elementsToAdd);
        }
        final short[] newArray = new short[array.length + elementsToAdd.length];
        System.arraycopy(array, 0, newArray, 0, array.length);
        System.arraycopy(elementsToAdd, 0, newArray, array.length, elementsToAdd.length);
        return newArray;
    }

    public static <T> T[] clone(final T[] arrayToClone) {
        return arrayToClone == null ? null : arrayToClone.clone();
    }

    public static boolean[] clone(final boolean[] arrayToClone) {
        return arrayToClone == null ? null : arrayToClone.clone();
    }

    public static byte[] clone(final byte[] arrayToClone) {
        return arrayToClone == null ? null : arrayToClone.clone();
    }

    public static char[] clone(final char[] arrayToClone) {
        return arrayToClone == null ? null : arrayToClone.clone();
    }

    public static double[] clone(final double[] arrayToClone) {
        return arrayToClone == null ? null : arrayToClone.clone();
    }

    public static float[] clone(final float[] arrayToClone) {
        return arrayToClone == null ? null : arrayToClone.clone();
    }

    public static int[] clone(final int[] arrayToClone) {
        return arrayToClone == null ? null : arrayToClone.clone();
    }

    public static long[] clone(final long[] arrayToClone) {
        return arrayToClone == null ? null : arrayToClone.clone();
    }

    public static short[] clone(final short[] arrayToClone) {
        return arrayToClone == null ? null : arrayToClone.clone();
    }

    @SafeVarargs
    public static <T> T[] insert(final T[] array, final int index, final T... values) {
        if (array == null) {
            return null;
        }
        if (values == null) {
            return clone(array);
        }
        if (index < 0 || index > array.length) {
            throw new IndexOutOfBoundsException("Index " + index + " is outside out of bounds of array of length " + array.length);
        }
        T[] newArray = newArrayInstance(getComponentType(array), lengthOfAllArraysCombined(array, values));
        System.arraycopy(values, 0, newArray, index, values.length);
        if (index != 0) {
            System.arraycopy(array, 0, newArray, 0, index);
        }
        if (index < array.length) {
            System.arraycopy(array, index, newArray, index + values.length, array.length - index);
        }
        return newArray;
    }

    public static boolean[] insert(final boolean[] array, final int index, final boolean... values) {
        if (array == null) {
            return null;
        }
        if (values == null) {
            return clone(array);
        }
        if (index < 0 || index > array.length) {
            throw new IndexOutOfBoundsException("Index " + index + " is outside out of bounds of array of length " + array.length);
        }
        boolean[] newArray = new boolean[array.length + values.length];
        System.arraycopy(values, 0, newArray, index, values.length);
        if (index != 0) {
            System.arraycopy(array, 0, newArray, 0, index);
        }
        if (index < array.length) {
            System.arraycopy(array, index, newArray, index + values.length, array.length - index);
        }
        return newArray;
    }

    public static byte[] insert(final byte[] array, final int index, final byte... values) {
        if (array == null) {
            return null;
        }
        if (values == null) {
            return clone(array);
        }
        if (index < 0 || index > array.length) {
            throw new IndexOutOfBoundsException("Index " + index + " is outside out of bounds of array of length " + array.length);
        }
        byte[] newArray = new byte[array.length + values.length];
        System.arraycopy(values, 0, newArray, index, values.length);
        if (index != 0) {
            System.arraycopy(array, 0, newArray, 0, index);
        }
        if (index < array.length) {
            System.arraycopy(array, index, newArray, index + values.length, array.length - index);
        }
        return newArray;
    }

    public static char[] insert(final char[] array, final int index, final char... values) {
        if (array == null) {
            return null;
        }
        if (values == null) {
            return clone(array);
        }
        if (index < 0 || index > array.length) {
            throw new IndexOutOfBoundsException("Index " + index + " is outside out of bounds of array of length " + array.length);
        }
        char[] newArray = new char[array.length + values.length];
        System.arraycopy(values, 0, newArray, index, values.length);
        if (index != 0) {
            System.arraycopy(array, 0, newArray, 0, index);
        }
        if (index < array.length) {
            System.arraycopy(array, index, newArray, index + values.length, array.length - index);
        }
        return newArray;
    }

    public static double[] insert(final double[] array, final int index, final double... values) {
        if (array == null) {
            return null;
        }
        if (values == null) {
            return clone(array);
        }
        if (index < 0 || index > array.length) {
            throw new IndexOutOfBoundsException("Index " + index + " is outside out of bounds of array of length " + array.length);
        }
        double[] newArray = new double[array.length + values.length];
        System.arraycopy(values, 0, newArray, index, values.length);
        if (index != 0) {
            System.arraycopy(array, 0, newArray, 0, index);
        }
        if (index < array.length) {
            System.arraycopy(array, index, newArray, index + values.length, array.length - index);
        }
        return newArray;
    }

    public static float[] insert(final float[] array, final int index, final float... values) {
        if (array == null) {
            return null;
        }
        if (values == null) {
            return clone(array);
        }
        if (index < 0 || index > array.length) {
            throw new IndexOutOfBoundsException("Index " + index + " is outside out of bounds of array of length " + array.length);
        }
        float[] newArray = new float[array.length + values.length];
        System.arraycopy(values, 0, newArray, index, values.length);
        if (index != 0) {
            System.arraycopy(array, 0, newArray, 0, index);
        }
        if (index < array.length) {
            System.arraycopy(array, index, newArray, index + values.length, array.length - index);
        }
        return newArray;
    }

    public static int[] insert(final int[] array, final int index, final int... values) {
        if (array == null) {
            return null;
        }
        if (values == null) {
            return clone(array);
        }
        if (index < 0 || index > array.length) {
            throw new IndexOutOfBoundsException("Index " + index + " is outside out of bounds of array of length " + array.length);
        }
        int[] newArray = new int[array.length + values.length];
        System.arraycopy(values, 0, newArray, index, values.length);
        if (index != 0) {
            System.arraycopy(array, 0, newArray, 0, index);
        }
        if (index < array.length) {
            System.arraycopy(array, index, newArray, index + values.length, array.length - index);
        }
        return newArray;
    }

    public static long[] insert(final long[] array, final int index, final long... values) {
        if (array == null) {
            return null;
        }
        if (values == null) {
            return clone(array);
        }
        if (index < 0 || index > array.length) {
            throw new IndexOutOfBoundsException("Index " + index + " is outside out of bounds of array of length " + array.length);
        }
        long[] newArray = new long[array.length + values.length];
        System.arraycopy(values, 0, newArray, index, values.length);
        if (index != 0) {
            System.arraycopy(array, 0, newArray, 0, index);
        }
        if (index < array.length) {
            System.arraycopy(array, index, newArray, index + values.length, array.length - index);
        }
        return newArray;
    }

    public static short[] insert(final short[] array, final int index, final short... values) {
        if (array == null) {
            return null;
        }
        if (values == null) {
            return clone(array);
        }
        if (index < 0 || index > array.length) {
            throw new IndexOutOfBoundsException("Index " + index + " is outside out of bounds of array of length " + array.length);
        }
        short[] newArray = new short[array.length + values.length];
        System.arraycopy(values, 0, newArray, index, values.length);
        if (index != 0) {
            System.arraycopy(array, 0, newArray, 0, index);
        }
        if (index < array.length) {
            System.arraycopy(array, index, newArray, index + values.length, array.length - index);
        }
        return newArray;
    }

    public static <T> boolean isSorted(final T[] array, Comparator<T> comparator) {
        if (array == null || array.length < 2) {
            return true;
        }
        if (firstNullElementIndex(array) != INDEX_NOT_FOUND){
            return false;
        }
        int ordering = 0;
        T last = array[0];
        for (int i = 1; i < array.length; i++) {
            final T current = array[i];
            int comparison = comparator.compare(current, last);
            if (comparison != 0) {
                comparison = comparison > 0 ? 1 : -1;
            }
            if (ordering == 0 && comparison != 0) {
                ordering = comparison;
            } else if (ordering != 0 && comparison != 0) {
                if (ordering - comparison != 0) {
                    return false;
                }
            }
            last = current;
        }
        return true;
    }

    public static <T extends Comparable<T>> boolean isSorted(final T[] array) {
        if (array == null || array.length < 2) {
            return true;
        }
        if (firstNullElementIndex(array) != INDEX_NOT_FOUND){
            return false;
        }
        int ordering = 0;
        T last = array[0];
        for (int i = 1; i < array.length; i++) {
            final T current = array[i];
            int comparison = current.compareTo(last);
            if (comparison != 0) {
                comparison = comparison > 0 ? 1 : -1;
            }
            if (ordering == 0 && comparison != 0) {
                ordering = comparison;
            } else if (ordering != 0 && comparison != 0) {
                if (ordering - comparison != 0) {
                    return false;
                }
            }
            last = current;
        }
        return true;
    }

    public static boolean isSorted(final boolean[] array) {
        if (array == null || array.length < 2) {
            return true;
        }
        int ordering = 0;
        boolean last = array[0];
        for (int i = 1; i < array.length; i++) {
            final boolean current = array[i];
            final int comparison = BooleanUtils.compareTo(current, last);
            if (ordering == 0 && comparison != 0) {
                ordering = comparison;
            } else if (ordering != 0 && comparison != 0) {
                if (ordering - comparison != 0) {
                    return false;
                }
            }
            last = current;
        }
        return true;
    }

    public static boolean isSorted(final byte[] array) {
        if (array == null || array.length < 2) {
            return true;
        }
        int ordering = 0;
        byte last = array[0];
        for (int i = 1; i < array.length; i++) {
            final byte current = array[i];
            final int comparison = NumberUtils.compareTo(current, last);
            if (ordering == 0 && comparison != 0) {
                ordering = comparison;
            } else if (ordering != 0 && comparison != 0) {
                if (ordering - comparison != 0) {
                    return false;
                }
            }
            last = current;
        }
        return true;
    }

    public static boolean isSorted(final char[] array) {
        if (array == null || array.length < 2) {
            return true;
        }
        int ordering = 0;
        char last = array[0];
        for (int i = 1; i < array.length; i++) {
            final char current = array[i];
            final int comparison = CharacterUtils.compareTo(current, last);
            if (ordering == 0 && comparison != 0) {
                ordering = comparison;
            } else if (ordering != 0 && comparison != 0) {
                if (ordering - comparison != 0) {
                    return false;
                }
            }
            last = current;
        }
        return true;
    }

    public static boolean isSorted(final double[] array) {
        if (array == null || array.length < 2) {
            return true;
        }
        int ordering = 0;
        double last = array[0];
        for (int i = 1; i < array.length; i++) {
            final double current = array[i];
            final int comparison = NumberUtils.compareTo(current, last);
            if (ordering == 0 && comparison != 0) {
                ordering = comparison;
            } else if (ordering != 0 && comparison != 0) {
                if (ordering - comparison != 0) {
                    return false;
                }
            }
            last = current;
        }
        return true;
    }

    public static boolean isSorted(final float[] array) {
        if (array == null || array.length < 2) {
            return true;
        }
        int ordering = 0;
        float last = array[0];
        for (int i = 1; i < array.length; i++) {
            final float current = array[i];
            final int comparison = NumberUtils.compareTo(current, last);
            if (ordering == 0 && comparison != 0) {
                ordering = comparison;
            } else if (ordering != 0 && comparison != 0) {
                if (ordering - comparison != 0) {
                    return false;
                }
            }
            last = current;
        }
        return true;
    }

    public static boolean isSorted(final int[] array) {
        if (array == null || array.length < 2) {
            return true;
        }
        int ordering = 0;
        int last = array[0];
        for (int i = 1; i < array.length; i++) {
            final int current = array[i];
            final int comparison = NumberUtils.compareTo(current, last);
            if (ordering == 0 && comparison != 0) {
                ordering = comparison;
            } else if (ordering != 0 && comparison != 0) {
                if (ordering - comparison != 0) {
                    return false;
                }
            }
            last = current;
        }
        return true;
    }

    public static boolean isSorted(final long[] array) {
        if (array == null || array.length < 2) {
            return true;
        }
        int ordering = 0;
        long last = array[0];
        for (int i = 1; i < array.length; i++) {
            final long current = array[i];
            final int comparison = NumberUtils.compareTo(current, last);
            if (ordering == 0 && comparison != 0) {
                ordering = comparison;
            } else if (ordering != 0 && comparison != 0) {
                if (ordering - comparison != 0) {
                    return false;
                }
            }
            last = current;
        }
        return true;
    }

    public static boolean isSorted(final short[] array) {
        if (array == null || array.length < 2) {
            return true;
        }
        int ordering = 0;
        short last = array[0];
        for (int i = 1; i < array.length; i++) {
            final short current = array[i];
            final int comparison = NumberUtils.compareTo(current, last);
            if (ordering == 0 && comparison != 0) {
                ordering = comparison;
            } else if (ordering != 0 && comparison != 0) {
                if (ordering - comparison != 0) {
                    return false;
                }
            }
            last = current;
        }
        return true;
    }

    @SafeVarargs
    public static <T> T[] mergeArrays(final T[]... arraysToMerge) {
        final int firstNotNullIndex = firstNotNullElementIndex(arraysToMerge);
        if (firstNotNullIndex == INDEX_NOT_FOUND) {
            return null;
        }
        final Class<T> componentType = getComponentType(arraysToMerge[firstNotNullIndex]);
        final T[] newArray = newArrayInstance(componentType, lengthOfAllArraysCombined(arraysToMerge));
        int currentMergeLocation = 0;
        for (final T[] array : arraysToMerge) {
            if (array != null) {
                System.arraycopy(array, 0, newArray, currentMergeLocation, array.length);
                currentMergeLocation += array.length;
            }
        }
        return newArray;
    }

    public static boolean[] mergeArrays(final boolean[]... arraysToMerge) {
        if (arraysToMerge == null) {
            return null;
        }
        if (firstNotNullElementIndex(arraysToMerge) == INDEX_NOT_FOUND) {
            return null;
        }
        final boolean[] newArray = new boolean[lengthOfAllArraysCombined(arraysToMerge)];
        int currentMergeLocation = 0;
        for (final boolean[] array : arraysToMerge) {
            if (array != null) {
                System.arraycopy(array, 0, newArray, currentMergeLocation, array.length);
                currentMergeLocation += array.length;
            }
        }
        return newArray;
    }

    public static byte[] mergeArrays(final byte[]... arraysToMerge) {
        if (arraysToMerge == null) {
            return null;
        }
        if (firstNotNullElementIndex(arraysToMerge) == INDEX_NOT_FOUND) {
            return null;
        }
        final byte[] newArray = new byte[lengthOfAllArraysCombined(arraysToMerge)];
        int currentMergeLocation = 0;
        for (final byte[] array : arraysToMerge) {
            if (array != null) {
                System.arraycopy(array, 0, newArray, currentMergeLocation, array.length);
                currentMergeLocation += array.length;
            }
        }
        return newArray;
    }

    public static char[] mergeArrays(final char[]... arraysToMerge) {
        if (arraysToMerge == null) {
            return null;
        }
        if (firstNotNullElementIndex(arraysToMerge) == INDEX_NOT_FOUND) {
            return null;
        }
        final char[] newArray = new char[lengthOfAllArraysCombined(arraysToMerge)];
        int currentMergeLocation = 0;
        for (final char[] array : arraysToMerge) {
            if (array != null) {
                System.arraycopy(array, 0, newArray, currentMergeLocation, array.length);
                currentMergeLocation += array.length;
            }
        }
        return newArray;
    }

    public static double[] mergeArrays(final double[]... arraysToMerge) {
        if (arraysToMerge == null) {
            return null;
        }
        if (firstNotNullElementIndex(arraysToMerge) == INDEX_NOT_FOUND) {
            return null;
        }
        final double[] newArray = new double[lengthOfAllArraysCombined(arraysToMerge)];
        int currentMergeLocation = 0;
        for (final double[] array : arraysToMerge) {
            if (array != null) {
                System.arraycopy(array, 0, newArray, currentMergeLocation, array.length);
                currentMergeLocation += array.length;
            }
        }
        return newArray;
    }

    public static float[] mergeArrays(final float[]... arraysToMerge) {
        if (arraysToMerge == null) {
            return null;
        }
        if (firstNotNullElementIndex(arraysToMerge) == INDEX_NOT_FOUND) {
            return null;
        }
        final float[] newArray = new float[lengthOfAllArraysCombined(arraysToMerge)];
        int currentMergeLocation = 0;
        for (final float[] array : arraysToMerge) {
            if (array != null) {
                System.arraycopy(array, 0, newArray, currentMergeLocation, array.length);
                currentMergeLocation += array.length;
            }
        }
        return newArray;
    }

    public static int[] mergeArrays(final int[]... arraysToMerge) {
        if (arraysToMerge == null) {
            return null;
        }
        if (firstNotNullElementIndex(arraysToMerge) == INDEX_NOT_FOUND) {
            return null;
        }
        final int[] newArray = new int[lengthOfAllArraysCombined(arraysToMerge)];
        int currentMergeLocation = 0;
        for (final int[] array : arraysToMerge) {
            if (array != null) {
                System.arraycopy(array, 0, newArray, currentMergeLocation, array.length);
                currentMergeLocation += array.length;
            }
        }
        return newArray;
    }

    public static long[] mergeArrays(final long[]... arraysToMerge) {
        if (arraysToMerge == null) {
            return null;
        }
        if (firstNotNullElementIndex(arraysToMerge) == INDEX_NOT_FOUND) {
            return null;
        }
        final long[] newArray = new long[lengthOfAllArraysCombined(arraysToMerge)];
        int currentMergeLocation = 0;
        for (final long[] array : arraysToMerge) {
            if (array != null) {
                System.arraycopy(array, 0, newArray, currentMergeLocation, array.length);
                currentMergeLocation += array.length;
            }
        }
        return newArray;
    }

    public static short[] mergeArrays(final short[]... arraysToMerge) {
        if (arraysToMerge == null) {
            return null;
        }
        if (firstNotNullElementIndex(arraysToMerge) == INDEX_NOT_FOUND) {
            return null;
        }
        final short[] newArray = new short[lengthOfAllArraysCombined(arraysToMerge)];
        int currentMergeLocation = 0;
        for (final short[] array : arraysToMerge) {
            if (array != null) {
                System.arraycopy(array, 0, newArray, currentMergeLocation, array.length);
                currentMergeLocation += array.length;
            }
        }
        return newArray;
    }

    public static <T> T[] nullToEmpty(final T[] array, final Class<T[]> type) {
        Validate.notNull(type, "The type may not be null");
        return array == null ? type.cast(Array.newInstance(type.getComponentType(), 0)) : array;
    }

    public static boolean[] nullToEmpty(final boolean[] array) {
        return array == null ? EMPTY_BOOLEAN_ARRAY : array;
    }

    public static byte[] nullToEmpty(final byte[] array) {
        return array == null ? EMPTY_BYTE_ARRAY : array;
    }

    public static char[] nullToEmpty(final char[] array) {
        return array == null ? EMPTY_CHAR_ARRAY : array;
    }

    public static double[] nullToEmpty(final double[] array) {
        return array == null ? EMPTY_DOUBLE_ARRAY : array;
    }

    public static float[] nullToEmpty(final float[] array) {
        return array == null ? EMPTY_FLOAT_ARRAY : array;
    }

    public static int[] nullToEmpty(final int[] array) {
        return array == null ? EMPTY_INT_ARRAY : array;
    }

    public static long[] nullToEmpty(final long[] array) {
        return array == null ? EMPTY_LONG_ARRAY : array;
    }

    public static short[] nullToEmpty(final short[] array) {
        return array == null ? EMPTY_SHORT_ARRAY : array;
    }

    public static <T> T[] remove(final T[] array, final int index) {
        if (array == null) {
            return null;
        }
        if (index < 0 || index >= array.length) {
            throw new IndexOutOfBoundsException("Array index " + index + " is out of bounds on array sized " + array.length);
        }
        final Class<T> componentType = getComponentType(array);
        final T[] newArray = newArrayInstance(componentType, array.length - 1);
        System.arraycopy(array, 0, newArray, 0, index);
        if (index < array.length - 1) {
            System.arraycopy(array, index + 1, newArray, index, array.length - index - 1);
        }
        return newArray;
    }

    public static boolean[] remove(final boolean[] array, final int index) {
        if (array == null) {
            return null;
        }
        if (index < 0 || index >= array.length) {
            throw new IndexOutOfBoundsException("Array index " + index + " is out of bounds on array sized " + array.length);
        }
        final boolean[] newArray = new boolean[array.length - 1];
        System.arraycopy(array, 0, newArray, 0, index);
        if (index < array.length - 1) {
            System.arraycopy(array, index + 1, newArray, index, array.length - index - 1);
        }
        return newArray;
    }

    public static byte[] remove(final byte[] array, final int index) {
        if (array == null) {
            return null;
        }
        if (index < 0 || index >= array.length) {
            throw new IndexOutOfBoundsException("Array index " + index + " is out of bounds on array sized " + array.length);
        }
        final byte[] newArray = new byte[array.length - 1];
        System.arraycopy(array, 0, newArray, 0, index);
        if (index < array.length - 1) {
            System.arraycopy(array, index + 1, newArray, index, array.length - index - 1);
        }
        return newArray;
    }

    public static char[] remove(final char[] array, final int index) {
        if (array == null) {
            return null;
        }
        if (index < 0 || index >= array.length) {
            throw new IndexOutOfBoundsException("Array index " + index + " is out of bounds on array sized " + array.length);
        }
        final char[] newArray = new char[array.length - 1];
        System.arraycopy(array, 0, newArray, 0, index);
        if (index < array.length - 1) {
            System.arraycopy(array, index + 1, newArray, index, array.length - index - 1);
        }
        return newArray;
    }

    public static double[] remove(final double[] array, final int index) {
        if (array == null) {
            return null;
        }
        if (index < 0 || index >= array.length) {
            throw new IndexOutOfBoundsException("Array index " + index + " is out of bounds on array sized " + array.length);
        }
        final double[] newArray = new double[array.length - 1];
        System.arraycopy(array, 0, newArray, 0, index);
        if (index < array.length - 1) {
            System.arraycopy(array, index + 1, newArray, index, array.length - index - 1);
        }
        return newArray;
    }

    public static float[] remove(final float[] array, final int index) {
        if (array == null) {
            return null;
        }
        if (index < 0 || index >= array.length) {
            throw new IndexOutOfBoundsException("Array index " + index + " is out of bounds on array sized " + array.length);
        }
        final float[] newArray = new float[array.length - 1];
        System.arraycopy(array, 0, newArray, 0, index);
        if (index < array.length - 1) {
            System.arraycopy(array, index + 1, newArray, index, array.length - index - 1);
        }
        return newArray;
    }

    public static int[] remove(final int[] array, final int index) {
        if (array == null) {
            return null;
        }
        if (index < 0 || index >= array.length) {
            throw new IndexOutOfBoundsException("Array index " + index + " is out of bounds on array sized " + array.length);
        }
        final int[] newArray = new int[array.length - 1];
        System.arraycopy(array, 0, newArray, 0, index);
        if (index < array.length - 1) {
            System.arraycopy(array, index + 1, newArray, index, array.length - index - 1);
        }
        return newArray;
    }

    public static long[] remove(final long[] array, final int index) {
        if (array == null) {
            return null;
        }
        if (index < 0 || index >= array.length) {
            throw new IndexOutOfBoundsException("Array index " + index + " is out of bounds on array sized " + array.length);
        }
        final long[] newArray = new long[array.length - 1];
        System.arraycopy(array, 0, newArray, 0, index);
        if (index < array.length - 1) {
            System.arraycopy(array, index + 1, newArray, index, array.length - index - 1);
        }
        return newArray;
    }

    public static short[] remove(final short[] array, final int index) {
        if (array == null) {
            return null;
        }
        if (index < 0 || index >= array.length) {
            throw new IndexOutOfBoundsException("Array index " + index + " is out of bounds on array sized " + array.length);
        }
        final short[] newArray = new short[array.length - 1];
        System.arraycopy(array, 0, newArray, 0, index);
        if (index < array.length - 1) {
            System.arraycopy(array, index + 1, newArray, index, array.length - index - 1);
        }
        return newArray;
    }

    public static <T> void reverse(final T[] array, final int firstBoundInclusive, final int secondBoundInclusive) {
        if (array == null) {
            return;
        }

        int start = MathUtils.max(MathUtils.min(firstBoundInclusive, secondBoundInclusive), 0);
        int end = MathUtils.min(MathUtils.max(firstBoundInclusive, secondBoundInclusive), array.length - 1);

        T temp;
        while (start < end) {
            temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }
    }

    public static <T> void reverse(final T[] array) {
        reverse(array, 0, array.length - 1);
    }

    public static void reverse(final boolean[] array, final int firstBoundInclusive, final int secondBoundInclusive) {
        if (array == null) {
            return;
        }

        int start = MathUtils.max(MathUtils.min(firstBoundInclusive, secondBoundInclusive), 0);
        int end = MathUtils.min(MathUtils.max(firstBoundInclusive, secondBoundInclusive), array.length - 1);

        boolean temp;
        while (start < end) {
            temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }
    }

    public static void reverse(final boolean[] array) {
        reverse(array, 0, array.length - 1);
    }

    public static void reverse(final byte[] array, final int firstBoundInclusive, final int secondBoundInclusive) {
        if (array == null) {
            return;
        }

        int start = MathUtils.max(MathUtils.min(firstBoundInclusive, secondBoundInclusive), 0);
        int end = MathUtils.min(MathUtils.max(firstBoundInclusive, secondBoundInclusive), array.length - 1);

        byte temp;
        while (start < end) {
            temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }
    }

    public static void reverse(final byte[] array) {
        reverse(array, 0, array.length - 1);
    }

    public static void reverse(final char[] array, final int firstBoundInclusive, final int secondBoundInclusive) {
        if (array == null) {
            return;
        }

        int start = MathUtils.max(MathUtils.min(firstBoundInclusive, secondBoundInclusive), 0);
        int end = MathUtils.min(MathUtils.max(firstBoundInclusive, secondBoundInclusive), array.length - 1);

        char temp;
        while (start < end) {
            temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }
    }

    public static void reverse(final char[] array) {
        reverse(array, 0, array.length - 1);
    }

    public static void reverse(final double[] array, final int firstBoundInclusive, final int secondBoundInclusive) {
        if (array == null) {
            return;
        }

        int start = MathUtils.max(MathUtils.min(firstBoundInclusive, secondBoundInclusive), 0);
        int end = MathUtils.min(MathUtils.max(firstBoundInclusive, secondBoundInclusive), array.length - 1);

        double temp;
        while (start < end) {
            temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }
    }

    public static void reverse(final double[] array) {
        reverse(array, 0, array.length - 1);
    }

    public static void reverse(final float[] array, final int firstBoundInclusive, final int secondBoundInclusive) {
        if (array == null) {
            return;
        }

        int start = MathUtils.max(MathUtils.min(firstBoundInclusive, secondBoundInclusive), 0);
        int end = MathUtils.min(MathUtils.max(firstBoundInclusive, secondBoundInclusive), array.length - 1);

        float temp;
        while (start < end) {
            temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }
    }

    public static void reverse(final float[] array) {
        reverse(array, 0, array.length - 1);
    }

    public static void reverse(final int[] array, final int firstBoundInclusive, final int secondBoundInclusive) {
        if (array == null) {
            return;
        }

        int start = MathUtils.max(MathUtils.min(firstBoundInclusive, secondBoundInclusive), 0);
        int end = MathUtils.min(MathUtils.max(firstBoundInclusive, secondBoundInclusive), array.length - 1);

        int temp;
        while (start < end) {
            temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }
    }

    public static void reverse(final int[] array) {
        reverse(array, 0, array.length - 1);
    }

    public static void reverse(final long[] array, final int firstBoundInclusive, final int secondBoundInclusive) {
        if (array == null) {
            return;
        }

        int start = MathUtils.max(MathUtils.min(firstBoundInclusive, secondBoundInclusive), 0);
        int end = MathUtils.min(MathUtils.max(firstBoundInclusive, secondBoundInclusive), array.length - 1);

        long temp;
        while (start < end) {
            temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }
    }

    public static void reverse(final long[] array) {
        reverse(array, 0, array.length - 1);
    }

    public static void reverse(final short[] array, final int firstBoundInclusive, final int secondBoundInclusive) {
        if (array == null) {
            return;
        }

        int start = MathUtils.max(MathUtils.min(firstBoundInclusive, secondBoundInclusive), 0);
        int end = MathUtils.min(MathUtils.max(firstBoundInclusive, secondBoundInclusive), array.length - 1);

        short temp;
        while (start < end) {
            temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }
    }

    public static void reverse(final short[] array) {
        reverse(array, 0, array.length - 1);
    }

    public static <T> void swap(final T[] array, final int firstOffset, final int secondOffset) {
        if (array == null || firstOffset >= array.length ||
                secondOffset >= array.length || firstOffset == 0 ||
                secondOffset == 0 || firstOffset == secondOffset) {
            return;
        }
        final T temp = array[firstOffset];
        array[firstOffset] = array[secondOffset];
        array[secondOffset] = temp;
    }

    public static void swap(final boolean[] array, final int firstOffset, final int secondOffset) {
        if (array == null || firstOffset >= array.length ||
                secondOffset >= array.length || firstOffset == 0 ||
                secondOffset == 0 || firstOffset == secondOffset) {
            return;
        }
        final boolean temp = array[firstOffset];
        array[firstOffset] = array[secondOffset];
        array[secondOffset] = temp;
    }

    public static void swap(final byte[] array, final int firstOffset, final int secondOffset) {
        if (array == null || firstOffset >= array.length ||
                secondOffset >= array.length || firstOffset == 0 ||
                secondOffset == 0 || firstOffset == secondOffset) {
            return;
        }
        final byte temp = array[firstOffset];
        array[firstOffset] = array[secondOffset];
        array[secondOffset] = temp;
    }

    public static void swap(final char[] array, final int firstOffset, final int secondOffset) {
        if (array == null || firstOffset >= array.length ||
                secondOffset >= array.length || firstOffset == 0 ||
                secondOffset == 0 || firstOffset == secondOffset) {
            return;
        }
        final char temp = array[firstOffset];
        array[firstOffset] = array[secondOffset];
        array[secondOffset] = temp;
    }

    public static void swap(final double[] array, final int firstOffset, final int secondOffset) {
        if (array == null || firstOffset >= array.length ||
                secondOffset >= array.length || firstOffset == 0 ||
                secondOffset == 0 || firstOffset == secondOffset) {
            return;
        }
        final double temp = array[firstOffset];
        array[firstOffset] = array[secondOffset];
        array[secondOffset] = temp;
    }

    public static void swap(final float[] array, final int firstOffset, final int secondOffset) {
        if (array == null || firstOffset >= array.length ||
                secondOffset >= array.length || firstOffset == 0 ||
                secondOffset == 0 || firstOffset == secondOffset) {
            return;
        }
        final float temp = array[firstOffset];
        array[firstOffset] = array[secondOffset];
        array[secondOffset] = temp;
    }

    public static void swap(final int[] array, final int firstOffset, final int secondOffset) {
        if (array == null || firstOffset >= array.length ||
                secondOffset >= array.length || firstOffset == 0 ||
                secondOffset == 0 || firstOffset == secondOffset) {
            return;
        }
        final int temp = array[firstOffset];
        array[firstOffset] = array[secondOffset];
        array[secondOffset] = temp;
    }

    public static void swap(final long[] array, final int firstOffset, final int secondOffset) {
        if (array == null || firstOffset >= array.length ||
                secondOffset >= array.length || firstOffset == 0 ||
                secondOffset == 0 || firstOffset == secondOffset) {
            return;
        }
        final long temp = array[firstOffset];
        array[firstOffset] = array[secondOffset];
        array[secondOffset] = temp;
    }

    public static void swap(final short[] array, final int firstOffset, final int secondOffset) {
        if (array == null || firstOffset >= array.length ||
                secondOffset >= array.length || firstOffset == 0 ||
                secondOffset == 0 || firstOffset == secondOffset) {
            return;
        }
        final short temp = array[firstOffset];
        array[firstOffset] = array[secondOffset];
        array[secondOffset] = temp;
    }

    public static Boolean[] toObject(final boolean[] array) {
        if (array == null) {
            return null;
        }
        final Boolean[] newArray = new Boolean[array.length];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    public static Byte[] toObject(final byte[] array) {
        if (array == null) {
            return null;
        }
        final Byte[] newArray = new Byte[array.length];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    public static Character[] toObject(final char[] array) {
        if (array == null) {
            return null;
        }
        final Character[] newArray = new Character[array.length];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    public static Double[] toObject(final double[] array) {
        if (array == null) {
            return null;
        }
        final Double[] newArray = new Double[array.length];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    public static Float[] toObject(final float[] array) {
        if (array == null) {
            return null;
        }
        final Float[] newArray = new Float[array.length];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    public static Integer[] toObject(final int[] array) {
        if (array == null) {
            return null;
        }
        final Integer[] newArray = new Integer[array.length];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    public static Long[] toObject(final long[] array) {
        if (array == null) {
            return null;
        }
        final Long[] newArray = new Long[array.length];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    public static Short[] toObject(final short[] array) {
        if (array == null) {
            return null;
        }
        final Short[] newArray = new Short[array.length];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    public static boolean[] toPrimitive(final Boolean[] array) {
        if (array == null) {
            return null;
        }
        final boolean[] newArray = new boolean[array.length];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    public static byte[] toPrimitive(final Byte[] array) {
        if (array == null) {
            return null;
        }
        final byte[] newArray = new byte[array.length];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    public static char[] toPrimitive(final Character[] array) {
        if (array == null) {
            return null;
        }
        final char[] newArray = new char[array.length];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    public static double[] toPrimitive(final Double[] array) {
        if (array == null) {
            return null;
        }
        final double[] newArray = new double[array.length];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    public static float[] toPrimitive(final Float[] array) {
        if (array == null) {
            return null;
        }
        final float[] newArray = new float[array.length];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    public static int[] toPrimitive(final Integer[] array) {
        if (array == null) {
            return null;
        }
        final int[] newArray = new int[array.length];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    public static long[] toPrimitive(final Long[] array) {
        if (array == null) {
            return null;
        }
        final long[] newArray = new long[array.length];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    public static short[] toPrimitive(final Short[] array) {
        if (array == null) {
            return null;
        }
        final short[] newArray = new short[array.length];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }
}
