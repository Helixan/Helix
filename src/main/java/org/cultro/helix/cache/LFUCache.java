package org.cultro.helix.cache;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class LFUCache<K, V> extends AbstractCache<K, V> {
    private final Map<K, V> cacheMap;
    private final Map<K, Integer> frequencyMap;
    private final Map<Integer, LinkedHashSet<K>> frequencyBuckets;
    private int minFrequency;

    public LFUCache(int capacity) {
        super(capacity);
        this.cacheMap = new HashMap<>();
        this.frequencyMap = new HashMap<>();
        this.frequencyBuckets = new HashMap<>();
        this.minFrequency = 1;
    }

    @Override
    public V get(K key) {
        lock.lock();
        try {
            if (!cacheMap.containsKey(key)) {
                return null;
            }
            // Update frequency
            int freq = frequencyMap.get(key);
            frequencyMap.put(key, freq + 1);
            frequencyBuckets.get(freq).remove(key);

            // Clean up frequency bucket
            if (frequencyBuckets.get(freq).isEmpty()) {
                frequencyBuckets.remove(freq);
                if (minFrequency == freq) {
                    minFrequency++;
                }
            }

            // Add to new frequency bucket
            frequencyBuckets.computeIfAbsent(freq + 1, k -> new LinkedHashSet<>()).add(key);

            return cacheMap.get(key);
        } finally {
            lock.unlock();
        }
    }

    @Override
    protected void evictIfNeeded() {
        LinkedHashSet<K> keys = frequencyBuckets.get(minFrequency);
        if (keys != null && !keys.isEmpty()) {
            K evictKey = keys.iterator().next();
            keys.remove(evictKey);
            if (keys.isEmpty()) {
                frequencyBuckets.remove(minFrequency);
            }
            cacheMap.remove(evictKey);
            frequencyMap.remove(evictKey);
        }
    }

    @Override
    protected void updateValue(K key, V value) {
        cacheMap.put(key, value);
        get(key); // Update frequency
    }

    @Override
    protected void insertEntry(K key, V value) {
        cacheMap.put(key, value);
        frequencyMap.put(key, 1);
        frequencyBuckets.computeIfAbsent(1, k -> new LinkedHashSet<>()).add(key);
        minFrequency = 1;
    }

    @Override
    protected void removeEntry(K key) {
        cacheMap.remove(key);
        int freq = frequencyMap.remove(key);
        LinkedHashSet<K> keys = frequencyBuckets.get(freq);
        if (keys != null) {
            keys.remove(key);
            if (keys.isEmpty()) {
                frequencyBuckets.remove(freq);
                if (minFrequency == freq) {
                    minFrequency = findNextMinFrequency();
                }
            }
        }
    }

    @Override
    protected void clearEntries() {
        cacheMap.clear();
        frequencyMap.clear();
        frequencyBuckets.clear();
        minFrequency = 1;
    }

    @Override
    public boolean containsKey(K key) {
        lock.lock();
        try {
            return cacheMap.containsKey(key);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int size() {
        lock.lock();
        try {
            return cacheMap.size();
        } finally {
            lock.unlock();
        }
    }

    private int findNextMinFrequency() {
        return frequencyBuckets.keySet().stream().min(Integer::compareTo).orElse(1);
    }
}
