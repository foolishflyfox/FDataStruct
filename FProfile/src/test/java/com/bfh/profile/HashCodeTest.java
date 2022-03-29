package com.bfh.profile;

import lombok.EqualsAndHashCode;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

import java.util.Objects;

/**
 * @author benfeihu
 */
public class HashCodeTest {

    static class A {
        String s;
        public A (String s) {
            this.s = s;
        }
        @Override
        public boolean equals(Object o) {
            if (o == null) {
                return false;
            }
            if (this == o) {
                return true;
            }
            if (!(o instanceof A)) {
                return false;
            }
            return Objects.equals(s, ((A) o).s);
        }

        @Override
        public int hashCode() {
            return Objects.hash(s);
        }
    }

    @Test
    public void testObjectHeaderHashCode() {
        A a = new A("abcdefg");
        System.out.println(Integer.toBinaryString(a.s.length()));

        /**
         * com.bfh.profile.HashCodeTest$A object internals:
         *  OFFSET  SIZE               TYPE DESCRIPTION                               VALUE
         *       0     4                    (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
         *       4     4                    (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
         *       8     4                    (object header)                           55 a7 01 f8 (01010101 10100111 00000001 11111000) (-134109355)
         *      12     4   java.lang.String A.s                                       (object)
         * Instance size: 16 bytes
         * Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
         * 没有显示 hash 值
         */
        System.out.println(ClassLayout.parseInstance(a).toPrintable());

        // 从 Object 继承来的 hashCode 会修改对象头
        // 如果自定义了 hashCode，对象头中将不再存储 hashCode，效率会稍有影响
        // 为了弥补这种影响，可以在对象中对元素的哈希值进行缓存，并且在修改内容后使 hash 值失效
        System.out.println(a.hashCode());

        System.out.println(ClassLayout.parseInstance(a).toPrintable());
    }

    @Test
    public void testCustomStringHashCode() {
        String s = "abcdefg";
        int h1 = s.hashCode();
        int h2 = myHashCode(s.toCharArray());
        System.out.println(String.format("h1 = %d, h2 = %d", h1, h2));
    }
    int myHashCode(char[] a) {
        if (null == a || a.length == 0) {
            return 0;
        }
        int r = 0;
        for (char c: a) {
            r = r * 31 + c;
        }
        return r;
    }

    @Test
    public void testNumberHashCode() {
        Integer i = 123;
        Long j = 123L;
        Boolean k = true;
        Short l = 123;
        Byte m = 123;
        Character n = '{';
        Float o = 123.0f;
        Double p = 123.0;
        System.out.println(i.hashCode());  // 123
        System.out.println(j.hashCode());  // 123
        System.out.println(k.hashCode());  // true = 1231, false = 1237
        System.out.println(l.hashCode());  // 123
        System.out.println(m.hashCode());  // 123
        System.out.println(n.hashCode());  // 123
        System.out.println(o.hashCode());  // 1123418112
        System.out.println(p.hashCode());  // 1079951360
    }

    @EqualsAndHashCode
    static class B {
        int v1;
        String v2;
        double v3;
        public B (int v1, String v2, double v3) {
            this.v1 = v1;
            this.v2 = v2;
            this.v3 = v3;
        }
    }
    @Test
    public void testObjectHashCode () {
        B b = new B(123, "abc", 123.0);
        System.out.println(b.hashCode());
        int r = Integer.hashCode(b.v1);
        r = r * 31 + b.v2.hashCode();
        r = r * 31 + Double.hashCode(b.v3);
        System.out.println(r);
    }

}
