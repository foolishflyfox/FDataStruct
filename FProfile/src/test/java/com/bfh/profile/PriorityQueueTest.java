package com.bfh.profile;

import org.junit.Test;

import java.util.PriorityQueue;

/**
 * @author benfeihu
 */
public class PriorityQueueTest {
    @Test
    public void testDefaultHeap() {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        // 默认为小根堆
        heap.add(5);
        heap.add(10);
        heap.add(3);
        while (!heap.isEmpty()) {
            System.out.println(heap.remove());
        }
    }

    @Test
    public void testBigRootHeap() {
        PriorityQueue<Integer> heap = new PriorityQueue<>((v1, v2) -> v1<v2 ? 1:(v1==v2?0:-1));
        // PriorityQueue<Integer> heap = new PriorityQueue<>((v1, v2) -> v2.compareTo(v1));
        // PriorityQueue<Integer> heap = new PriorityQueue<>((v1, v2) -> v1.compareTo(v2));
        heap.add(5);
        heap.add(10);
        heap.add(3);
//        while (!heap.isEmpty()) {
//            System.out.println(heap.remove());
//        }
    }
}
