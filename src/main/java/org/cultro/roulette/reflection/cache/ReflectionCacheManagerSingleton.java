package org.cultro.roulette.reflection.cache;

/**
 * Singleton instance of ReflectionCacheManager for global access.
 */
public class ReflectionCacheManagerSingleton {

    private static final ReflectionCacheManager INSTANCE = new ReflectionCacheManager(1000, 5000, 5000);

    private ReflectionCacheManagerSingleton() {
    }

    /**
     * Retrieves the singleton instance of ReflectionCacheManager.
     *
     * @return The ReflectionCacheManager instance.
     */
    public static ReflectionCacheManager getInstance() {
        return INSTANCE;
    }
}