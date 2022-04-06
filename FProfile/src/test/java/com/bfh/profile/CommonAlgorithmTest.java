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
        quickSortHelper(a, start, index-2);
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

    void insertSort(int a[]) {
        if (a == null || a.length < 2) {
            return;
        }
        for (int i = 1; i < a.length; ++i) {
            int k = a[i], j = i - 1;
            while (j >= 0 && a[j] > k) {
                a[j+1] = a[j];
                --j;
            }
            a[j+1] = k;
        }
    }

    @Test
    public void testInsertSort() {
        int[] a = new int[]{3,1,6,5,2,3,7,1,8,4,9};
        insertSort(a);
        System.out.println(Arrays.toString(a));
    }

    int selectMinValueIndex(int a[], int start) {
        if (start >= a.length) {
            return -1;
        }
        int r = start;
        for (int i = start+1; i < a.length; ++i) {
            if (a[r] > a[i]) {
                r = i;
            }
        }
        return r;
    }
    void selectSort(int[] a) {
        if (a == null || a.length < 2) {
            return;
        }
        for (int i = 0; i < a.length; ++i) {
            int index = selectMinValueIndex(a, i);
            swap(a, i, index);
        }
    }
    @Test
    public void testSelectSort() {
        int[] a = new int[]{3,1,6,5,2,3,7,1,8,4,9};
        selectSort(a);
        System.out.println(Arrays.toString(a));
    }

    void bubbleSort(int[] a) {
        System.out.println("bubbleSort:");
        if (a == null || a.length < 2) {
            return;
        }
        for (int i = a.length-1; i > 0; --i) {
            for (int j = 0; j < i; ++j) {
                if (a[j] > a[j+1]) {
                    swap(a, j, j+1);
                }
            }
        }
    }

    @Test
    public void testBubbleSort() {
        int[] a = new int[]{3,1,6,5,2,3,7,1,8,4,9};
        bubbleSort(a);
        System.out.println(Arrays.toString(a));
    }

    public void shellSort(int[] a) {
        if (a == null || a.length < 2) {
            return;
        }
         int len = a.length;
        for (int step = len/2; step > 0; --step) {
            // 一次插入排序
            for (int i = step; i < len; ++i) {
                int k = a[i];
                int j = i - step;
                for (; j >= 0; j -= step) {
                    if (a[j] > k) {
                        a[j + step] = a[j];
                    } else {
                        break;
                    }
                }
                a[j + step] = k;
            }
        }
    }

    @Test
    public void testShellSort() {
        int[] a = new int[]{3,1,6,5,2,3,7,1,8,4,9};
        shellSort(a);
        System.out.println(Arrays.toString(a));
    }

    Random random = new Random();
    public void quickSort2(int[] a) {
        if (null == a || a.length < 2) {
            return;
        }
        quickSortHelper(a, 0, a.length-1);
    }
    private void quickSort2Helper(int[] a, int begin, int end) {
        if (begin >= end) {
            return;
        }
        swap(a, begin, random.nextInt(end - begin + 1) + begin);
        int k = a[begin];
        int left = begin, right = end;
        while (left < right) {
            while (left < right && a[right] >= k) {
                right--;
            }
            if (left < right) {
                swap(a, left, right);
                right--;
            }
            while (left < right && a[left] < k) {
                left++;
            }
            if (left < right) {
                swap(a, left, right);
                ++left;
            }
        }
        a[left] = k;
        quickSortHelper(a, begin, left-1);
        quickSortHelper(a, left+1, end);

    }

    @Test
    public void testQuickSort2() {
        int[] a = new int[]{3,1,6,5,2,3,7,1,8,4,9};
        quickSort2(a);
        System.out.println(Arrays.toString(a));
    }

    public void quickSort3(int[] a) {
        if (a==null || a.length < 2) {
            return;
        }
        quickSort3Helper(a, 0, a.length-1);
    }

    public void quickSort3Helper(int[] a, int begin, int end) {
        if (begin >= end) {
            return;
        }
        swap(a, begin, random.nextInt(end-begin+1)+begin);
        int k = a[begin];
        int bigKIndex = begin+1;
        while (bigKIndex <= end && a[bigKIndex] <= k) {
            ++bigKIndex;
        }
        for (int i = bigKIndex + 1; i <= end; ++i) {
            if (a[i] > k) {
                continue;
            }
            swap(a, bigKIndex++, i);
        }
        swap(a, begin, bigKIndex-1);
        quickSort3Helper(a, begin, bigKIndex-2);
        quickSort3Helper(a, bigKIndex, end);
    }

    @Test
    public void testQuickSort3() {
        int[] a = new int[]{3,1,6,5,2,3,7,1,8,4,9};
        quickSort3(a);
        System.out.println(Arrays.toString(a));
    }

}
