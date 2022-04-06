package com.bfh.struct;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author benfeihu
 */
public class LruMapWithVersion<K, V> {
    @Data
    @AllArgsConstructor
    static class VersionValue <V> {
        private V value;
        private int version;
    }
    @Data
    @AllArgsConstructor
    static class VersionKey <K> {
        private K key;
        private int version;
    }
    private Map<K, VersionValue<V>> innerMap;
    private LinkedList<VersionKey<K>> innerList;
    private int capacity;

    public LruMapWithVersion() {
        this(16);
    }
    public LruMapWithVersion(int capacity) {
        this.capacity = capacity;
        innerMap = new HashMap<>();
        innerList = new LinkedList<>();
    }
    public V put(K key, V value) {
        if (innerMap.containsKey(key)) {
            VersionValue<V> versionValue = innerMap.get(key);
            V v = versionValue.getValue();
            versionValue.setValue(value);
            versionValue.setVersion(versionValue.getVersion()+1);
            // innerMap.put(key, versionValue);
            innerList.addLast(new VersionKey<>(key, versionValue.getVersion()));
            return v;
        } else {
            while (innerMap.size() >= capacity) {
                while (!innerList.isEmpty()) {
                    VersionKey<K> first = innerList.removeFirst();
                    if (first.version == innerMap.get(first.key).getVersion()) {
                        innerMap.remove(first.key);
                        break;
                    }
                }
            }
        }
        int initVersion = 1;
        VersionValue<V> versionValue = new VersionValue<>(value, initVersion);
        innerMap.put(key, versionValue);
        innerList.push(new VersionKey<>(key, initVersion));
        return null;
    }

    public V get(K key) {
        VersionValue<V> versionValue = innerMap.get(key);
        if (versionValue == null) {
            return null;
        }
        versionValue.setVersion(versionValue.getVersion() + 1);
        innerList.addLast(new VersionKey<>(key, versionValue.getVersion()));
        return versionValue.getValue();
    }
}
