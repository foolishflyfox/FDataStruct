package com.bfh.profile;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

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
}
