package org.cultro.roulette.cache;

import java.util.HashMap;
import java.util.Map;

public class LRUCache<K, V> extends AbstractCache<K, V> {
    private final Map<K, Node<K, V>> cacheMap;
    private final DoublyLinkedList<K, V> dll;

    public LRUCache(int capacity) {
        super(capacity);
        this.cacheMap = new HashMap<>();
        this.dll = new DoublyLinkedList<>();
    }

    @Override
    public V get(K key) {
        lock.lock();
        try {
            Node<K, V> node = cacheMap.get(key);
            if (node == null) {
                return null;
            }
            dll.moveToFront(node);
            return node.value;
        } finally {
            lock.unlock();
        }
    }

    @Override
    protected void evictIfNeeded() {
        Node<K, V> node = dll.removeLast();
        if (node != null) {
            cacheMap.remove(node.key);
        }
    }

    @Override
    protected void updateValue(K key, V value) {
        Node<K, V> node = cacheMap.get(key);
        if (node != null) {
            node.value = value;
            dll.moveToFront(node);
        }
    }

    @Override
    protected void insertEntry(K key, V value) {
        Node<K, V> node = new Node<>(key, value);
        dll.addFirst(node);
        cacheMap.put(key, node);
    }

    @Override
    protected void removeEntry(K key) {
        Node<K, V> node = cacheMap.remove(key);
        if (node != null) {
            dll.remove(node);
        }
    }

    @Override
    protected void clearEntries() {
        cacheMap.clear();
        dll.clear();
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

    // Node class for doubly linked list
    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> prev;
        Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    // Doubly linked list implementation
    private static class DoublyLinkedList<K, V> {
        private Node<K, V> head;
        private Node<K, V> tail;

        public DoublyLinkedList() {
            head = null;
            tail = null;
        }

        public void addFirst(Node<K, V> node) {
            if (head == null) {
                head = tail = node;
            } else {
                node.next = head;
                head.prev = node;
                head = node;
            }
        }

        public void moveToFront(Node<K, V> node) {
            if (node == head) {
                return;
            }
            remove(node);
            addFirst(node);
        }

        public Node<K, V> removeLast() {
            if (tail == null) {
                return null;
            }
            Node<K, V> node = tail;
            remove(node);
            return node;
        }

        public void remove(Node<K, V> node) {
            if (node.prev != null) {
                node.prev.next = node.next;
            } else {
                head = node.next;
            }

            if (node.next != null) {
                node.next.prev = node.prev;
            } else {
                tail = node.prev;
            }

            node.prev = null;
            node.next = null;
        }

        public void clear() {
            head = null;
            tail = null;
        }
    }
}
