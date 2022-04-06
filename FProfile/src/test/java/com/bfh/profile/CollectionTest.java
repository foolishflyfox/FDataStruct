package com.bfh.profile;

import org.junit.Test;

import java.util.*;

/**
 * @author benfeihu
 */
public class CollectionTest {
    @Test
    public void testAdd() {
        Collection<Integer> collection = new TreeSet<Integer>();
        System.out.println(collection.add(1));  // true
        System.out.println(collection.add(1));  // false
        // System.out.println(collection.add(null));  // throw NullPointerException
    }

    @Test
    public void testAddAll() {
        Collection<Integer> collection = new TreeSet<Integer>();
        collection.add(1);
        collection.add(2);
        System.out.println(collection.addAll(Arrays.asList(1,2)));  // false
        System.out.println(collection.addAll(Arrays.asList(2,3)));  // true
    }

    @Test
    public void testRemove() {
        Collection<String> collection = new ArrayList<String>();
        collection.add("a");
        collection.add("a");
        collection.add("b");
        System.out.println(collection.remove("a"));  // true
        System.out.println(collection);  // [a, b]
        System.out.println(collection.remove("c"));  // false
    }

    @Test
    public void testRemoveAll() {
        Collection<String> collection = new ArrayList<String>();
        collection.add("a");
        collection.add("a");
        collection.add("a");
        collection.add("b");
        System.out.println(collection.removeAll(Arrays.asList("a")));  // true
        System.out.println(collection);  // [b]
        System.out.println(collection.removeAll(Arrays.asList("a")));  // false
    }

    @Test
    public void testRetainAll() {
        Collection<String> collection = new ArrayList<String>(
                Arrays.asList("a", "a", "a", "b"));
        System.out.println(collection.retainAll(Arrays.asList("b")));
        System.out.println(collection);
        System.out.println(collection.retainAll(Arrays.asList("b")));
    }

    @Test
    public void testRemoveIf() {
        Collection<Integer> collection = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7));
        System.out.println(collection.removeIf((v) -> v % 9 == 0));  // false
        System.out.println(collection.removeIf((v) -> v % 3 == 0));  // true
        System.out.println(collection.removeIf((v) -> v % 3 == 0));  // false
        System.out.println(collection);  // 1, 2, 4, 5, 7]
    }

    static class Pos {
        int x;
        int y;
        public Pos(int x, int y) { this.x = x; this.y = y; }
        @Override
        public String toString() { return String.format("(%d,%d)", x, y); }
    }
    @Test
    public void testToArray1() {
        Collection<Pos> collection = new ArrayList<Pos>(Arrays.asList(new Pos(1,1),
                new Pos(2,2), new Pos(3, 3)));
        System.out.println(collection);  // [(1,1), (2,2), (3,3)]
        Object[] array = collection.toArray();  // 返回类型为 Object[]，需要强转
        array[1] = array[2];
        System.out.println(collection);  // [(1,1), (2,2), (3,3)]   改变数组不会导致集合改变
        ((Pos)array[0]).x = 10;
        System.out.println(collection);  // [(10,1), (2,2), (3,3)]  改变集合元素会导致集合改变，因为共用引用
    }

    @Test
    public void testToArray2() {
        Collection<String> collection = new ArrayList<>(Arrays.asList("a", "b", "c"));
        String[] a1 = new String[] {"1", "2", "3"};
        String[] b1 = collection.toArray(a1);
        System.out.println("a1 == b1 : " + (a1 == b1));  // a1 == b1 : true
        System.out.println(Arrays.toString(a1));  // [a, b, c]

        String[] a2 = new String[] {"1", "2", "3", "4", "5"};
        String[] b2 = collection.toArray(a2);
        System.out.println("a2 == b2 : " + (a2 == b2));  // a2 == b2 : true
        System.out.println(Arrays.toString(a2));  // [a, b, c, null, 5] , 第三个元素后补 null

        String[] a3 = new String[] {"1"};
        String[] b3 = collection.toArray(a3);
        System.out.println("a3 == b3 : " + (a3 == b3));  // a3 == b3 : false
        System.out.println(Arrays.toString(a3));  // [1] , a3 容量不足，重新创建新数组，原数组内容不变
        System.out.println(Arrays.toString(b3));  // [a, b, c]
    }

    @Test
    public void testSynchronizedList() {

    }
}
