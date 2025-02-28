package org.cultro.helix.reflection.scanners;

import java.util.ArrayList;

import org.cultro.helix.lang.MetadataException;
import org.cultro.helix.lang.ReflectionException;
import org.cultro.helix.reflection.metadata.ClassMetadata;
import org.cultro.helix.util.ReflectionUtils;

import java.util.List;

/**
 * Scans classes within a specified package and retrieves their metadata using caching.
 */
public class ClassScanner {

    /**
     * Scans all classes in the specified package and retrieves their metadata.
     *
     * @param packageName The package to scan.
     * @return A list of ClassMetadata objects.
     * @throws ReflectionException If scanning fails.
     */
    public List<ClassMetadata> scan(String packageName) throws ReflectionException, MetadataException {
        List<Class<?>> classes = ReflectionUtils.getClassesInPackage(packageName);
        List<ClassMetadata> metadataList = new ArrayList<>();

        for (Class<?> clazz : classes) {
            ClassMetadata metadata = ClassMetadata.getCachedMetadata(clazz);
            metadataList.add(metadata);
        }
        return metadataList;
    }
}
