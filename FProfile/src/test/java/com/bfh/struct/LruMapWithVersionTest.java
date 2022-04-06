package com.bfh.struct;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author benfeihu
 */
public class LruMapWithVersionTest {

    @Test
    public void testLruMapWithVersion() {
        LruMapWithVersion<String, String> lruMap = new LruMapWithVersion<>(2);
        Assert.assertNull(lruMap.put("a", "apple"));
        Assert.assertNull(lruMap.put("b", "banana"));
        Assert.assertEquals("banana", lruMap.put("b", "baby"));  // 测试原始 map 功能
        Assert.assertNull(lruMap.put("c", "cherry"));
        Assert.assertNull(lruMap.get("a"));
        Assert.assertEquals("baby", lruMap.get("b"));  // 因为 b 被访问过，因此变为最新
        Assert.assertNull(lruMap.put("d", "durian"));
        Assert.assertNull(lruMap.get("c"));
        Assert.assertEquals("baby", lruMap.get("b"));
    }
    @Test
    public void testLruMap() {
        LruMap<String, String> lruMap = new LruMap<>(2);
        Assert.assertNull(lruMap.put("a", "apple"));
        Assert.assertNull(lruMap.put("b", "banana"));
        Assert.assertEquals("banana", lruMap.put("b", "baby"));  // 测试原始 map 功能
        Assert.assertNull(lruMap.put("c", "cherry"));
        Assert.assertNull(lruMap.get("a"));
        Assert.assertEquals("baby", lruMap.get("b"));  // 因为 b 被访问过，因此变为最新
        Assert.assertNull(lruMap.put("d", "durian"));
        Assert.assertNull(lruMap.get("c"));
        Assert.assertEquals("baby", lruMap.get("b"));
    }
}