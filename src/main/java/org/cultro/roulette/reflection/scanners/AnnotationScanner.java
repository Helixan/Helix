package org.cultro.roulette.reflection.scanners;

import org.cultro.roulette.lang.MetadataException;
import org.cultro.roulette.reflection.metadata.AnnotationMetadata;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Scans annotations on classes, fields, or methods and retrieves their metadata.
 */
public class AnnotationScanner {

    /**
     * Scans all annotations on the specified element and retrieves their metadata.
     *
     * @param element The element (class, field, or method) to scan.
     * @return A list of AnnotationMetadata objects.
     * @throws MetadataException If scanning fails.
     */
    public List<AnnotationMetadata> scan(AnnotatedElement element) throws MetadataException {
        try {
            Annotation[] annotations = element.getAnnotations();
            List<AnnotationMetadata> metadataList = new ArrayList<>();
            for (Annotation annotation : annotations) {
                metadataList.add(new AnnotationMetadata(annotation));
            }
            return metadataList;
        } catch (Exception e) {
            throw new MetadataException("Failed to scan annotations on element " + element, e);
        }
    }
}
