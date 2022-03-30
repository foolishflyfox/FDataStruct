package com.bfh.profile;

import com.sun.xml.internal.org.jvnet.mimepull.CleanUpExecutorFactory;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author benfeihu
 */
public class CommonAlgorithmTest {

    // 二分查找
    public int binaryFind(int[] a, int target) {
        if (a == null) {
            return -1;
        }
        int left = 0, right = a.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (a[mid] == target) {
                left = right;
                break;
            }
            if (a[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if (left < a.length && a[left] == target) {
            return left;
        }
        return -1 * (left + 1);
    }

    @Test
    public void testBinaryFind() {
        int[] a = new int[] {1,2,3,4,6,7,8,9,10};
        System.out.println(binaryFind(a, 7));  // 5
        System.out.println(binaryFind(a, -1)); // -1
        System.out.println(binaryFind(a, 5));  // -5
    }


    public void swap(int a[], int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    public void shuffle(int a[]) {
        Random random = new Random();
        for (int i = a.length-1; i > 0; --i) {
            int j = random.nextInt(i);
            swap(a, i, j);
        }
    }
    public int[] quickSort(int a[]) {
        shuffle(a);
        quickSortHelper(a, 0, a.length-1);
        return a;
    }
    public void quickSortHelper(int a[], int start, int end) {
        if (start >= end) {
            return;
        }
        int pivot = start;
        int index = pivot + 1;
        for (int i = index; i <= end; ++i) {
            if (a[i] <= a[pivot]) {
                swap(a, index, i);
                index++;
            }
        }
        swap(a, pivot, index-1);
        quickSortHelper(a, start, index-1);
        quickSortHelper(a, index, end);
    }
    @Test
    public void testQuickSort() {
        System.out.println(Arrays.toString(quickSort(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1})));
    }

    public void mergeSort(int[] a) {
        if (a.length <= 1) {
            return;
        }
        int mid = a.length / 2;
        int[] left = Arrays.copyOfRange(a, 0, mid);
        int[] right = Arrays.copyOfRange(a, mid, a.length);
        mergeSort(left);
        mergeSort(right);
        int pleft = 0, pright = 0;
        while (pleft < left.length && pright < right.length) {
            if (left[pleft] < right[pright]) {
                a[pleft + pright] = left[pleft];
                pleft++;
            } else {
                a[pleft + pright] = right[pright];
                pright++;
            }
        }
        while (pleft < left.length) {
            a[pleft + pright] = left[pleft];
            pleft++;
        }
        while (pright < right.length) {
            a[pleft + pright] = right[pright];
            pright++;
        }
    }

    @Test
    public void testMergeSort() {
        int[] a = new int[]{3,1,6,5,2,7,8,4,9};
        mergeSort(a);
        System.out.println(Arrays.toString(a));
    }
}
