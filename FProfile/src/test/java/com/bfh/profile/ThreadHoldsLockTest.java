package com.bfh.profile;

import org.junit.Test;

/**
 * @author benfeihu
 */
public class ThreadHoldsLockTest {

    private synchronized void foo() {
        System.out.println(Thread.holdsLock(this));
    }

    @Test
    public void test01() {
        foo();
    }
}
