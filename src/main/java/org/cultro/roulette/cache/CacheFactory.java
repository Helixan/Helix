package org.cultro.roulette.cache;

public class CacheFactory {
    public enum EvictionPolicy {
        /**
         * **First-In-First-Out (FIFO)** eviction policy.
         * <p>
         * The FIFO policy evicts the oldest entry in the cache, i.e., the entry that
         * was added first. This approach does not consider how frequently or recently
         * an entry is accessed, making it simple but potentially suboptimal for some
         * access patterns.
         * </p>
         *
         * <p>
         * **Use Case:** Suitable for scenarios where the order of insertion is important
         * and older entries are less likely to be needed.
         * </p>
         */
        FIFO,

        /**
         * **Least Recently Used (LRU)** eviction policy.
         * <p>
         * The LRU policy evicts the entry that has not been accessed for the longest
         * period of time. This approach assumes that entries accessed recently are more
         * likely to be accessed again, making it effective for caching scenarios with
         * temporal locality.
         * </p>
         *
         * <p>
         * **Use Case:** Ideal for applications where recent access patterns are more
         * indicative of future accesses, such as web page caching or session management.
         * </p>
         */
        LRU,

        /**
         * **Least Frequently Used (LFU)** eviction policy.
         * <p>
         * The LFU policy evicts the entry with the lowest access frequency. This approach
         * keeps frequently accessed entries in the cache, which can be beneficial for
         * workloads with certain access patterns where some entries are consistently
         * more popular than others.
         * </p>
         *
         * <p>
         * **Use Case:** Suitable for scenarios where certain items are accessed much
         * more frequently than others, such as caching database query results or
         * static resources.
         * </p>
         */
        LFU
    }

    /**
     * Creates a cache with the specified eviction policy and capacity.
     *
     * @param policy   The eviction policy to use.
     * @param capacity The maximum number of entries the cache can hold.
     * @param <K>      The type of keys.
     * @param <V>      The type of values.
     * @return An instance of Cache with the specified policy.
     */
    public static <K, V> Cache<K, V> createCache(EvictionPolicy policy, int capacity) {
        switch (policy) {
            case FIFO:
                return new FIFOCache<>(capacity);
            case LRU:
                return new LRUCache<>(capacity);
            case LFU:
                return new LFUCache<>(capacity);
            default:
                throw new IllegalArgumentException("Unsupported Eviction Policy: " + policy);
        }
    }
}
