package org.cultro.roulette.cache;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class FIFOCache<K, V> extends AbstractCache<K, V> {
    private final Map<K, V> cacheMap;
    private final LinkedList<K> queue;

    public FIFOCache(int capacity) {
        super(capacity);
        this.cacheMap = new HashMap<>();
        this.queue = new LinkedList<>();
    }

    @Override
    public V get(K key) {
        lock.lock();
        try {
            return cacheMap.get(key);
        } finally {
            lock.unlock();
        }
    }

    @Override
    protected void evictIfNeeded() {
        K oldestKey = queue.poll();
        if (oldestKey != null) {
            cacheMap.remove(oldestKey);
        }
    }

    @Override
    protected void updateValue(K key, V value) {
        cacheMap.put(key, value);
        // FIFO does not change the order on update
    }

    @Override
    protected void insertEntry(K key, V value) {
        cacheMap.put(key, value);
        queue.add(key);
    }

    @Override
    protected void removeEntry(K key) {
        cacheMap.remove(key);
        queue.remove(key);
    }

    @Override
    protected void clearEntries() {
        cacheMap.clear();
        queue.clear();
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
}
