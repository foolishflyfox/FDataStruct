package com.bfh.profile;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author benfeihu
 */
public class ConditionTest {

    static class ShareClass {
        int v = 0;
        boolean effective = false;
    }

    @Test
    public void lockConditionTest() throws InterruptedException {
        Random random = new Random();
        ShareClass share = new ShareClass();
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Runnable consumer = () -> {
            int tv = 0;
            while (true) {
                lock.lock();
                try {
                    while (!share.effective) {
                        condition.await();
                    }
                    System.out.println("thread-" + Thread.currentThread().getName() + " get share value");
                    tv = share.v;
                    share.effective = false;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
                System.out.println("thread-" + Thread.currentThread() + " process " + tv);
            }
        };
        Runnable producer = () -> {
            while (true) {
                lock.lock();
                try {
                    while (share.effective) {
                        condition.await();
                    }
                    share.v = random.nextInt();
                    share.effective = true;
                    condition.signalAll();
                    System.out.println("thread-" + Thread.currentThread() + " produce " + share.v);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        for (int i = 0; i < 3; ++i ) {
            executorService.execute(consumer);
        }
        executorService.execute(producer);
        executorService.awaitTermination(10, TimeUnit.SECONDS);
    }

    @Test
    public void waitNotifyTest() throws InterruptedException {
        Random random = new Random();
        ShareClass share = new ShareClass();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Runnable consumer = () -> {
            int tv = 0;
            while (true) {
                synchronized (share) {
                    try {
                        while (!share.effective) {
                            share.wait();
                        }
                        System.out.println("thread-" + Thread.currentThread().getName() + " get share value");
                        tv = share.v;
                        share.effective = false;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                    }
                }
                System.out.println("thread-" + Thread.currentThread() + " process " + tv);
            }
        };
        Runnable producer = () -> {
            while (true) {
                synchronized (share) {
                    try {
                        while (share.effective) {
                            share.wait();
                        }
                        share.v = random.nextInt();
                        share.effective = true;
                        share.notifyAll();
                        System.out.println("thread-" + Thread.currentThread() + " produce " + share.v);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        for (int i = 0; i < 3; ++i ) {
            executorService.execute(consumer);
        }
        executorService.execute(producer);
        executorService.awaitTermination(10, TimeUnit.SECONDS);
    }

}
