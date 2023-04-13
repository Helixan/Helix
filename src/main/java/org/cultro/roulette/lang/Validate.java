package org.cultro.roulette.lang;

public class Validate {

    public static <T> void notNull(T object, String message) {
        if (object == null) {
            if (message != null) {
                throw new IllegalArgumentException(message);
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    public static <T> void notNull(T object) {
        if (object == null) {
            throw new IllegalArgumentException();
        }
    }

    public static void finite(double value, String message) {
        if (!Double.isFinite(value)) {
            if (message != null) {
                throw new IllegalArgumentException(message);
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    public static void finite(double value) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException();
        }
    }

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
}
