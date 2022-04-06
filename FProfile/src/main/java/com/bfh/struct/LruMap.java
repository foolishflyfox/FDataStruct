package com.bfh.struct;

import java.util.HashMap;
import java.util.Map;

/**
 * @author benfeihu
 */
public class LruMap <K, V> {
    static class LinkedNode<K, V> {
        K key;
        V value;
        LinkedNode<K, V> pre;
        LinkedNode<K, V> next;
        public LinkedNode() {}
        public LinkedNode(K k, V v) {
            this(k, v, null, null);
        }
        public LinkedNode(K k, V v, LinkedNode<K, V> pre, LinkedNode<K, V> next) {
            this.key = k;
            this.value = v;
            this.pre = pre;
            this.next = next;
        }
    }
    static class LinkedNodeList<K, V> {
        LinkedNode<K, V> hair;
        LinkedNode<K, V> tail;
        public LinkedNodeList() {
            hair = new LinkedNode<>();
            tail = hair;
        }
        public boolean isEmpty() {
            return hair == tail;
        }
        public void addFirst(LinkedNode<K, V> node) {
            node.next = hair.next;
            if (hair.next != null) {
                hair.next.pre = node;
            }
            hair.next = node;
            node.pre = hair;
            if (hair == tail) {
                tail = node;
            }
        }
        public void remove(LinkedNode<K, V> node) {
            node.pre.next = node.next;
            if (node.next != null) {
                node.next.pre = node.pre;
            } else {
                tail = node.pre;
            }

        }
        public LinkedNode<K, V> removeLast() {
            if (isEmpty()) {
                return null;
            }
            LinkedNode<K, V> r = tail;
            tail = r.pre;
            r.pre = null;
            tail.next = null;
            return r;
        }
    }
    private LinkedNodeList<K, V> innerList;
    private Map<K, LinkedNode<K, V>> innerMap;
    private int capacity;

    public LruMap(int capacity) {
        this.capacity = capacity;
        innerMap = new HashMap<>();
        innerList = new LinkedNodeList<>();
    }

    public V put(K key, V value) {
        if (innerMap.containsKey(key)) {
            LinkedNode<K, V> node = innerMap.get(key);
            LinkedNode<K, V> newNode = new LinkedNode<>(key, value);
            V r = node.value;
            innerMap.put(key, newNode);
            innerList.remove(node);
            innerList.addFirst(newNode);
            return r;
        } else {
            if (innerMap.size() == this.capacity) {
                LinkedNode<K, V> tail = innerList.removeLast();
                innerMap.remove(tail.key);
            }
            LinkedNode<K, V> newNode = new LinkedNode<>(key, value);
            innerList.addFirst(newNode);
            innerMap.put(key, newNode);
            return null;
        }
    }

    public V get(K key) {
        if (!innerMap.containsKey(key)) {
            return null;
        }
        V r = innerMap.get(key).value;
        put(key, r);
        return r;
    }
}
