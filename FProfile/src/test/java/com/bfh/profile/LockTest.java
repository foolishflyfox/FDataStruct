package com.bfh.profile;

import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

/**
 * @author benfeihu
 */
public class LockTest {


    private synchronized void foo(int v) {
        System.out.println("foo(" + v + ") exec");
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testSynchronizedFair() throws InterruptedException {
        //测试是否公平
        // 输出为如下，表示非公平锁
        // foo(0) exec
        // foo(2) exec
        // foo(1) exec
        Thread[] ts = new Thread[3];
        for (int i = 0; i < 3; ++i) {
            final int j = i;
            ts[i] = new Thread(() -> foo(j));
            ts[i].start();
        }
        for (int i = 0; i < 3; ++i) {
            ts[i].join();
        }
    }
    private synchronized void bar(int i) {
        System.out.println("bar(" + i + ")");
        if (i > 0) {
            bar(i-1);
        }
    }
    @Test
    public void testSynchronizedReentrant() {
        // 输出如下，表示可重入
        // bar(2)
        // bar(1)
        // bar(0)
        bar(2);
    }

}
