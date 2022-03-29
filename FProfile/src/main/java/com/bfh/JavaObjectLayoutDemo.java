package com.bfh;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

/**
 * @author benfeihu
 */
public class JavaObjectLayoutDemo {
    public static void main(String[] args) {
        System.out.println(VM.current().details());
        /**
         * # Running 64-bit HotSpot VM.
         * # Using compressed oop with 3-bit shift.
         * # Using compressed klass with 3-bit shift.
         * # WARNING | Compressed references base/shifts are guessed by the experiment!
         * # WARNING | Therefore, computed addresses are just guesses, and ARE NOT RELIABLE.
         * # WARNING | Make sure to attach Serviceability Agent to get the reliable addresses.
         * # Objects are 8 bytes aligned.
         * # Field sizes by type: 4, 1, 1, 2, 2, 4, 4, 8, 8 [bytes]
         * # Array element sizes: 4, 1, 1, 2, 2, 4, 4, 8, 8 [bytes]
         */


        /**
         * com.bfh.JavaObjectLayoutDemo$A object internals:
         *  OFFSET  SIZE      TYPE DESCRIPTION                               VALUE
         *       0    12           (object header)                           N/A
         *      12     1   boolean A.f                                       N/A
         *      13     3           (loss due to the next object alignment)
         * Instance size: 16 bytes
         * Space losses: 0 bytes internal + 3 bytes external = 3 bytes total
         */
        System.out.println(ClassLayout.parseClass(A.class).toPrintable());


        /**
         * com.bfh.JavaObjectLayoutDemo$A object internals:
         *  OFFSET  SIZE      TYPE DESCRIPTION                               VALUE
         *       0     4           (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
         *       4     4           (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
         *       8     4           (object header)                           20 b6 01 f8 (00100000 10110110 00000001 11111000) (-134105568)
         *      12     1   boolean A.f                                       false
         *      13     3           (loss due to the next object alignment)
         * Instance size: 16 bytes
         * Space losses: 0 bytes internal + 3 bytes external = 3 bytes total
         */
        A a = new A();
        System.out.println(ClassLayout.parseInstance(a).toPrintable());

        System.out.println(Integer.toBinaryString(a.hashCode()));  // 101110101000100010011110101111
        /**
         * com.bfh.JavaObjectLayoutDemo$A object internals:
         *  OFFSET  SIZE      TYPE DESCRIPTION                               VALUE
         *       0     4           (object header)                           01 af 27 a2 (00000001 10101111 00100111 10100010) (-1574457599)
         *       4     4           (object header)                           2e 00 00 00 (00101110 00000000 00000000 00000000) (46)
         *       8     4           (object header)                           20 b6 01 f8 (00100000 10110110 00000001 11111000) (-134105568)
         *      12     1   boolean A.f                                       false
         *      13     3           (loss due to the next object alignment)
         * Instance size: 16 bytes
         * Space losses: 0 bytes internal + 3 bytes external = 3 bytes total
         * 1. 在调用了因此 hashCode 之后，会将 hash 值存放在对象头中 2 ~ 5 字节
         * 2. 第一个字节：高4位分代年龄，低两位 锁标志 01
         */
        System.out.println(ClassLayout.parseInstance(a).toPrintable());

        synchronized (a) {
            /**
             * com.bfh.JavaObjectLayoutDemo$A object internals:
             *  OFFSET  SIZE      TYPE DESCRIPTION                               VALUE
             *       0     4           (object header)                           38 da 4a 0a (00111000 11011010 01001010 00001010) (172677688)
             *       4     4           (object header)                           00 70 00 00 (00000000 01110000 00000000 00000000) (28672)
             *       8     4           (object header)                           20 b6 01 f8 (00100000 10110110 00000001 11111000) (-134105568)
             *      12     1   boolean A.f                                       false
             *      13     3           (loss due to the next object alignment)
             * Instance size: 16 bytes
             * Space losses: 0 bytes internal + 3 bytes external = 3 bytes total
             * 1. 因为有
             */
            System.out.println(ClassLayout.parseInstance(a).toPrintable());
        }


    }
    public static class A {
        boolean f;
    }
}
