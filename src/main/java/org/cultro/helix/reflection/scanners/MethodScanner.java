package org.cultro.helix.reflection.scanners;

import org.cultro.helix.lang.MetadataException;
import org.cultro.helix.reflection.metadata.MethodMetadata;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Scans methods within a specified class and retrieves their metadata.
 */
public class MethodScanner {

    /**
     * Scans all methods in the specified class and retrieves their metadata.
     *
     * @param clazz The class to scan.
     * @return A list of MethodMetadata objects.
     * @throws MetadataException If scanning fails.
     */
    public List<MethodMetadata> scan(Class<?> clazz) throws MetadataException {
        try {
            Method[] methods = clazz.getDeclaredMethods();
            List<MethodMetadata> metadataList = new ArrayList<>();
            for (Method method : methods) {
                metadataList.add(new MethodMetadata(method));
            }
            return metadataList;
        } catch (Exception e) {
            throw new MetadataException("Failed to scan methods in class " + clazz.getName(), e);
        }
    }
}