package com.bfh;

import lombok.Getter;
import lombok.Setter;

/**
 * @author benfeihu
 */
public class ThreadLocalDemo {

    ThreadLocal<String> t1 = new ThreadLocal<>();

    private String content;

    public void setContent(String content) {
        // this.content = content;
        t1.set(content);
    }

    public String getContent() {
        // return this.content;
        String s = t1.get();
        return s;
    }

    public static void main(String[] args) {
        ThreadLocalDemo demo = new ThreadLocalDemo();

        for (int i= 0; i < 5; ++i) {
            Thread thread = new Thread(() -> {
                demo.setContent(Thread.currentThread().getName() + "的数据");
                System.out.println("-------------");
                System.out.println(Thread.currentThread().getName() + " ---> " + demo.getContent());
            });
            thread.setName("线程" + i);
            thread.start();
        }

    }
}
