package org.cultro.roulette.lang;

public class Validate {

    public static <T> boolean notNull(T object, String message) {
        if (object == null) {
            if (message != null) {
                throw new NullPointerException(message);
            } else {
                throw new NullPointerException();
            }
        }
        return true;
    }

    public static <T> boolean notNull(T object) {
        if (object == null) {
            throw new NullPointerException();
        }
        return true;
    }
}
