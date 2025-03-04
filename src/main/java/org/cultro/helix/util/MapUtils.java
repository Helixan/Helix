package org.cultro.helix.util;

import org.cultro.helix.lang.Validate;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public final class MapUtils {

    public static <K, V> Map<K, V> filterByKeyPredicate(Map<K, V> map, Predicate<K> predicate) {
        for (K key : map.keySet()) {
            if (!predicate.test(key)) {
                map.remove(key);
            }
        }
        return map;
    }

    public static <K, V> Map<K, V> filterByValuePredicate(Map<K, V> map, Predicate<V> predicate) {
        for (K key : map.keySet()) {
            if (!predicate.test(map.get(key))) {
                map.remove(key);
            }
        }
        return map;
    }

    public static <K> K getKeyAtIndex(LinkedHashMap<K, ?> map, int index) {
        Validate.notNull(map, "A null map does not have keys");
        Validate.isValidIndex(index, "An index may not be negative");
        for (K current : map.keySet()) {
            if (index == 0) {
                return current;
            }
            index--;
        }
        throw new IndexOutOfBoundsException();
    }

    public static <K, V> Set<K> getKeysByValue(Map<K, V> map, V value) {
        Validate.notNull(map, "A null map does not have keys");
        return map.entrySet().stream()
                .filter(entry -> Objects.equals(entry.getValue(), value))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }

    public static <K,V> Map<K, V> getSubMapByKeys(Map<K, V> map, Set<K> keys) {
        Map<K, V> subMap = new HashMap<>();

        for (K key : keys) {
            if (map.containsKey(key)) {
                subMap.put(key, map.get(key));
            }
        }
        return subMap;
    }

    public static <K,V> Map<K, V> getSubMapByValues(Map<K, V> map, Set<V> values) {
        Map<K, V> subMap = new HashMap<>();

        for (K key : map.keySet()) {
            if (values.contains(map.get(key))) {
                subMap.put(key, map.get(key));
            }
        }
        return subMap;
    }

    public static <V> V getValueAtIndex(LinkedHashMap<?, V> map, int index) {
        Validate.notNull(map, "A null map does not have keys");
        Validate.isValidIndex(index, "An index may not be negative");
        for (V current : map.values()) {
            if (index == 0) {
                return current;
            }
            index--;
        }
        throw new IndexOutOfBoundsException();
    }

    public static <K extends Comparable<K>, V> LinkedHashMap<K, V> sortByKey(Map<K, V> map, boolean reverseOrder) {
        Validate.notNull(map, "A null map cannot be sorted");
        return map.entrySet().stream()
                .sorted(((o1, o2) -> reverseOrder ? o2.getKey().compareTo(o1.getKey()) : o1.getKey().compareTo(o2.getKey())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    public static <K extends Comparable<K>, V> LinkedHashMap<K, V> sortByKey(Map<K, V> map) {
        return sortByKey(map, false);
    }

    public static <K, V extends Comparable<V>> LinkedHashMap<K, V> sortByValue(Map<K, V> map, boolean reverseOrder) {
        Validate.notNull(map, "A null map cannot be sorted");
        return map.entrySet().stream()
                .sorted(((o1, o2) -> reverseOrder ? o2.getValue().compareTo(o1.getValue()) : o1.getValue().compareTo(o2.getValue())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    public static <K, V extends Comparable<V>> LinkedHashMap<K, V> sortByValue(Map<K, V> map) {
        return sortByValue(map, false);
    }
}
