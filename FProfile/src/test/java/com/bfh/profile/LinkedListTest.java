package com.bfh.profile;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author benfeihu
 */
public class LinkedListTest {
    @Test
    public void testQueue() {
        Queue<String> queue = new LinkedList<>();
        queue.add("1");
        queue.add("2");
        queue.add("3");
        while (!queue.isEmpty()) {
            System.out.println(queue.remove());
        }
    }
    @Test
    public void testStack() {
        Deque<String> stack = new LinkedList<>();
        stack.addLast("1");
        stack.addLast("2");
        stack.addLast("3");
        while (!stack.isEmpty()) {
            System.out.println(stack.removeLast());
        }
    }
}
