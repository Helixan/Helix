package org.cultro.roulette.lang;


import java.io.*;

public class ObjectUtils {

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
}
