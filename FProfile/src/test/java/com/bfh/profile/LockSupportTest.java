package com.bfh.profile;

import org.junit.Test;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * @author benfeihu
 */
public class LockSupportTest {
    @Test
    public void test01() throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println(System.currentTimeMillis() + ": 开始子线程");
            LockSupport.park();
            System.out.println(System.currentTimeMillis() + ": 子线程结束");
        });
        thread.start();
        Thread.sleep(3000);
        System.out.println(System.currentTimeMillis() + ": main unpark");
        LockSupport.unpark(thread);
        thread.join();
        System.out.println("main 结束");
    }
}
