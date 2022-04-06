package com.bfh.profile.dp;

import com.bfh.MyAqsLock;
import org.junit.Test;

import java.util.concurrent.locks.Lock;

/**
 * @author benfeihu
 */
public class MyAqsLockTest {
    private Lock lock = new MyAqsLock();
    private int value;

    public int getNext() {
        lock.lock();
        value++;
        lock.unlock();
        return value;
    }

    @Test
    public void test01() throws InterruptedException {
        int n = 5;
        Thread[] threads = new Thread[5];
        for (int i = 0; i < n; ++i) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; ++j) {
                    getNext();
                }
            });
            threads[i].start();
        }
        for (int i = 0; i < n; ++i) {
            threads[i].join();
        }
        System.out.println(value);
    }
}
