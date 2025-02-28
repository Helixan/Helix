package org.cultro.helix.util;


import org.cultro.helix.lang.Validate;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public final class ObjectUtils {

    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T deepCopy(T object) {
        T copy;
        try {
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
            objectStream.writeObject(object);
            objectStream.flush();
            ByteArrayInputStream inputByteStream = new ByteArrayInputStream(byteStream.toByteArray());
            ObjectInputStream objectInputStream = new ObjectInputStream(inputByteStream);
            copy = (T) objectInputStream.readObject();
            objectStream.close();
            inputByteStream.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return copy;
    }

    @SafeVarargs
    public static <T> boolean anyEquals(T object, T... objects) {
        Validate.notNull(object, "You may not pass a null object");
        Validate.notNull(objects, "You may not pass a null list of objects");
        for (T current : objects) {
            if (object.equals(current)) {
                return true;
            }
        }
        return false;
    }

    public static <T> boolean isPrimitive(T object) {
        if (object != null) {
            return object.getClass().isPrimitive();
        }
        return false;
    }

    @SafeVarargs
    private static <T> Set<T> asSet(T... objects) {
        Validate.notNull(objects, "You may not convert null into a set");
        return Arrays.stream(objects).collect(Collectors.toSet());
    }

    @SafeVarargs
    private static <T> List<T> asList(T... objects) {
        Validate.notNull(objects, "You may not convert null into a list");
        return Arrays.stream(objects).collect(Collectors.toList());
    }
}
