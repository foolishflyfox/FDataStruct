package com.bfh;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

/**
 * @author benfeihu
 */
public class TestMain {
    public static void main(String[] args) {
        TestMain testMain = new TestMain();
        System.out.println(System.getProperty("java.version"));
        // System.out.println(VM.current().details());
        // jol-core-0.9 只能运行在 openjdk 之上
        String classLayout = ClassLayout.parseInstance(testMain).toPrintable();
        System.out.println(classLayout);

        System.out.println("-------");
        System.out.println(VM.current().details());
    }
}
