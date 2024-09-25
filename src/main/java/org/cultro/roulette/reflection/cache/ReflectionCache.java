package org.cultro.roulette.reflection.cache;

import org.cultro.roulette.cache.CacheFactory;
import org.cultro.roulette.lang.MetadataException;
import org.cultro.roulette.lang.ReflectionException;
import org.cultro.roulette.reflection.metadata.ClassMetadata;
import org.cultro.roulette.reflection.metadata.FieldMetadata;
import org.cultro.roulette.reflection.metadata.MethodMetadata;

/**
 * Provides a simplified interface to interact with reflection-specific caches.
 */
public class ReflectionCache {

    private static final ReflectionCacheManager cacheManager;

    static {
        cacheManager = new ReflectionCacheManager(1000, 5000, 5000);
    }

    private ReflectionCache() {
    }

    /**
     * Retrieves ClassMetadata from the cache or loads and caches it if not present.
     *
     * @param clazz The class to retrieve metadata for.
     * @return The ClassMetadata object.
     * @throws ReflectionException If metadata retrieval fails.
     */
    public static ClassMetadata getClassMetadata(Class<?> clazz) throws ReflectionException, MetadataException {
        return cacheManager.getClassMetadata(clazz);
    }

    /**
     * Retrieves FieldMetadata from the cache or loads and caches it if not present.
     *
     * @param field The field to retrieve metadata for.
     * @return The FieldMetadata object.
     * @throws ReflectionException If metadata retrieval fails.
     */
    public static FieldMetadata getFieldMetadata(FieldMetadata field) throws ReflectionException {
        return cacheManager.getFieldMetadata(field);
    }

    /**
     * Retrieves MethodMetadata from the cache or loads and caches it if not present.
     *
     * @param method The method to retrieve metadata for.
     * @return The MethodMetadata object.
     * @throws ReflectionException If metadata retrieval fails.
     */
    public static MethodMetadata getMethodMetadata(MethodMetadata method) throws ReflectionException {
        return cacheManager.getMethodMetadata(method);
    }

    /**
     * Clears all reflection-specific caches.
     */
    public static void clearAllCaches() {
        cacheManager.clearAllCaches();
    }
}
