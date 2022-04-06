package com.bfh.profile;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

/**
 * @author benfeihu
 */
public class ThreadTest {

    @Test
    public void testFutureTask () throws ExecutionException, InterruptedException {
        FutureTask<String> task = new FutureTask<>(() -> {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
        });
        new Thread(task).start();
        String r = task.get();
        System.out.println("r = " + r);
    }

    @Test
    public void testThreadPool1() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                System.out.println("thread id is: " + Thread.currentThread().getId());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("end of " + Thread.currentThread().getId());
            });
        }

        executor.submit(() -> {
            System.out.println("xxxxxx");
        });
        executor.shutdown();
        while (executor.isTerminated() == false) {
            Thread.sleep(1000);
        }
    }

    @Test
    public void testExecutorSubmit() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        Future<?> r = executor.submit(() -> {
            System.out.println("start thread...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("end thread...");
            return 10;
        });
        System.out.println("xxxx: " + r.get().getClass().getName());
    }
}
