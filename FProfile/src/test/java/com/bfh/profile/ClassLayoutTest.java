package com.bfh.profile;

import org.junit.Test;
import org.openjdk.jol.vm.VM;

import java.nio.ByteOrder;

/**
 * @author benfeihu
 * 查看类的内存结构
 */
public class ClassLayoutTest {
    @Test
    public void test() {
        System.out.println(this.hashCode());
        // 查看字节序, LITTLE_ENDIAN 表示小端模式
        System.out.println(ByteOrder.nativeOrder());
    }
}
