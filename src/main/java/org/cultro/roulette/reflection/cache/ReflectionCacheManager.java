package org.cultro.roulette.reflection.cache;

import org.cultro.roulette.cache.Cache;
import org.cultro.roulette.cache.CacheFactory;
import org.cultro.roulette.lang.MetadataException;
import org.cultro.roulette.lang.ReflectionException;
import org.cultro.roulette.reflection.metadata.ClassMetadata;
import org.cultro.roulette.reflection.metadata.FieldMetadata;
import org.cultro.roulette.reflection.metadata.MethodMetadata;

/**
 * Manages reflection-specific caches, such as ClassMetadata, FieldMetadata, and MethodMetadata caches.
 */
public class ReflectionCacheManager {

    private final Cache<Class<?>, ClassMetadata> classMetadataCache;
    private final Cache<FieldMetadata, FieldMetadata> fieldMetadataCache;
    private final Cache<MethodMetadata, MethodMetadata> methodMetadataCache;

    /**
     * Initializes the reflection cache manager with specified cache capacities and eviction policies.
     *
     * @param classCacheCapacity  The maximum number of entries in the ClassMetadata cache.
     * @param fieldCacheCapacity  The maximum number of entries in the FieldMetadata cache.
     * @param methodCacheCapacity The maximum number of entries in the MethodMetadata cache.
     */
    public ReflectionCacheManager(int classCacheCapacity, int fieldCacheCapacity, int methodCacheCapacity) {
        this.classMetadataCache = CacheFactory.createCache(CacheFactory.EvictionPolicy.LRU, classCacheCapacity);

        this.fieldMetadataCache = CacheFactory.createCache(CacheFactory.EvictionPolicy.LFU, fieldCacheCapacity);

        this.methodMetadataCache = CacheFactory.createCache(CacheFactory.EvictionPolicy.FIFO, methodCacheCapacity);
    }

    /**
     * Retrieves ClassMetadata from the cache or loads and caches it if not present.
     *
     * @param clazz The class to retrieve metadata for.
     * @return The ClassMetadata object.
     * @throws ReflectionException If metadata retrieval fails.
     */
    public ClassMetadata getClassMetadata(Class<?> clazz) throws ReflectionException, MetadataException {
        ClassMetadata metadata = classMetadataCache.get(clazz);
        if (metadata == null) {
            metadata = new ClassMetadata(clazz);
            classMetadataCache.put(clazz, metadata);
        }
        return metadata;
    }

    /**
     * Retrieves FieldMetadata from the cache or loads and caches it if not present.
     *
     * @param field The field to retrieve metadata for.
     * @return The FieldMetadata object.
     * @throws ReflectionException If metadata retrieval fails.
     */
    public FieldMetadata getFieldMetadata(FieldMetadata field) throws ReflectionException {
        FieldMetadata metadata = fieldMetadataCache.get(field);
        if (metadata == null) {
            metadata = new FieldMetadata(field.getField());
            fieldMetadataCache.put(metadata, metadata);
        }
        return metadata;
    }

    /**
     * Retrieves MethodMetadata from the cache or loads and caches it if not present.
     *
     * @param method The method to retrieve metadata for.
     * @return The MethodMetadata object.
     * @throws ReflectionException If metadata retrieval fails.
     */
    public MethodMetadata getMethodMetadata(MethodMetadata method) throws ReflectionException {
        MethodMetadata metadata = methodMetadataCache.get(method);
        if (metadata == null) {
            metadata = new MethodMetadata(method.getMethod());
            methodMetadataCache.put(metadata, metadata);
        }
        return metadata;
    }

    /**
     * Clears all reflection-specific caches.
     */
    public void clearAllCaches() {
        classMetadataCache.clear();
        fieldMetadataCache.clear();
        methodMetadataCache.clear();
    }
}
