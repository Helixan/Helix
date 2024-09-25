package org.cultro.roulette.cache;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class AbstractCache<K, V> implements Cache<K, V> {
    protected final int capacity;
    protected final Lock lock = new ReentrantLock();

    public AbstractCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Cache capacity must be greater than zero.");
        }
        this.capacity = capacity;
    }

    /**
     * Evicts an entry based on the specific eviction policy.
     */
    protected abstract void evictIfNeeded();

    @Override
    public void put(K key, V value) {
        lock.lock();
        try {
            if (containsKey(key)) {
                updateValue(key, value);
            } else {
                if (size() >= capacity) {
                    evictIfNeeded();
                }
                insertEntry(key, value);
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void remove(K key) {
        lock.lock();
        try {
            removeEntry(key);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void clear() {
        lock.lock();
        try {
            clearEntries();
        } finally {
            lock.unlock();
        }
    }

    protected abstract void updateValue(K key, V value);

    protected abstract void insertEntry(K key, V value);

    protected abstract void removeEntry(K key);

    protected abstract void clearEntries();
}
