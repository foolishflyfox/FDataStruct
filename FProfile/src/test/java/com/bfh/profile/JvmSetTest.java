package com.bfh.profile;

import org.junit.Test;

/**
 * @author benfeihu
 */
public class JvmSetTest {
    @Test
    public void testSetInitHeapSize() {
        long heapSize = Runtime.getRuntime().totalMemory();
        // 默认 257,425,408 = 0.25G , 机子内存为 16, 因此初始内存为机子内存的 1/64
        // 可以通过添加配置 -Xms64m , 显式指定为 64m, 打印结果为 64487424
        System.out.println("init heapSize = " + heapSize);
    }
}
