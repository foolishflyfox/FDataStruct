package com.bfh.profile;

import org.junit.Test;

import java.util.*;

/**
 * @author benfeihu
 */
public class MapTest {

    @Test
    public void testLinkedHashMap() {
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 1);
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
    }

    @Test
    public void testHashMap () {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        hashMap.put(10, null);
        System.out.println(hashMap);
        hashMap.put(null, 20);
        System.out.println(hashMap);
    }

    @Test
    public void testHashTable() {
        Hashtable<Integer, Integer> hashtable = new Hashtable<>();
        // hashtable.put(10, null);  // value 不能为 null
        // hashtable.put(null, 20);  // key 不能为 null
    }
}
