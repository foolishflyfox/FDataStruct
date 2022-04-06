package com.bfh.profile;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author benfeihu
 */
public class ArrayTest {
    @Test
    public void test01() {
        int[] a = new int[] {1,2,3,4};
        int[] b = Arrays.copyOf(a, 2);
        System.out.println(Arrays.toString(b));
        b[0] = 10;
        System.out.println(Arrays.toString(a));
    }

    @Test
    public void test02() {
        int[] a = new int[] {1,2,3,4};
        int[] b = Arrays.copyOf(a, 6);
        System.out.println(Arrays.toString(b));
    }

}
