package org.cultro.roulette.cache;

public interface Cache<K, V> {
    /**
     * Retrieves the value associated with the given key.
     *
     * @param key The key to look up.
     * @return The value associated with the key, or null if not present.
     */
    V get(K key);

    /**
     * Inserts or updates the value associated with the given key.
     *
     * @param key   The key to insert/update.
     * @param value The value to associate with the key.
     */
    void put(K key, V value);

    /**
     * Removes the entry associated with the given key.
     *
     * @param key The key to remove.
     */
    void remove(K key);

    /**
     * Clears all entries in the cache.
     */
    void clear();

    /**
     * Returns the current number of entries in the cache.
     *
     * @return The size of the cache.
     */
    int size();

    /**
     * Checks if the cache contains the given key.
     *
     * @param key The key to check.
     * @return True if the key exists, false otherwise.
     */
    boolean containsKey(K key);
}
