package com.bfh.profile;

import org.junit.Test;

import java.util.*;

/**
 * @author benfeihu
 */
public class ListTest {
    @Test
    public void test() {
        List<String> list = new ArrayList<>();

        // 增
        list.add("0");
        list.add("1");
        // 删
        list.remove(1);
        // 改
        System.out.println(list.set(0, "10"));
        // 查
        System.out.println(list.get(0));
    }
}
