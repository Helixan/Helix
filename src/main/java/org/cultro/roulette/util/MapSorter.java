package org.cultro.roulette.util;

import org.cultro.roulette.lang.Validate;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class MapSorter {

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
}