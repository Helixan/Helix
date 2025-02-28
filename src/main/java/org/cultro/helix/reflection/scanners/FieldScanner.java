package org.cultro.helix.reflection.scanners;

import org.cultro.helix.lang.MetadataException;
import org.cultro.helix.reflection.metadata.FieldMetadata;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Scans fields within a specified class and retrieves their metadata.
 */
public class FieldScanner {

    /**
     * Scans all fields in the specified class and retrieves their metadata.
     *
     * @param clazz The class to scan.
     * @return A list of FieldMetadata objects.
     * @throws MetadataException If scanning fails.
     */
    public List<FieldMetadata> scan(Class<?> clazz) throws MetadataException {
        try {
            Field[] fields = clazz.getDeclaredFields();
            List<FieldMetadata> metadataList = new ArrayList<>();
            for (Field field : fields) {
                metadataList.add(new FieldMetadata(field));
            }
            return metadataList;
        } catch (Exception e) {
            throw new MetadataException("Failed to scan fields in class " + clazz.getName(), e);
        }
    }
}