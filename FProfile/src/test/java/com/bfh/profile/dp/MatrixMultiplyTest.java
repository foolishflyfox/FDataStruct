package com.bfh.profile.dp;

import org.junit.Test;

/**
 * @author benfeihu
 */
public class MatrixMultiplyTest {

    Result minMatrixMultiply(int[] matrixSizes) {
        if (matrixSizes.length < 3) {
            return null;
        }
        int n = matrixSizes.length - 1;
        Result[][] r = new Result[n+1][n+1];
        return minMatrixMultiplyHelper(matrixSizes, r, 1, n);
    }
    static class Result {
        int multiplyTimes;
        Result left;
        int midMatrix = -1;
        Result right;
        @Override
        public String toString() {
            if (midMatrix == -1) {
                return "";
            }
            String sleft = left==null?"":left.toString();
            String sright = right==null?"":right.toString();
            return "(" + sleft + midMatrix + ")" + (sright.isEmpty()?"":"("+sright+")");
        }
        public Result() {
            this.multiplyTimes = -1;
        }
        public Result(int multiplyTimes) {
            this.multiplyTimes = multiplyTimes;
        }
        public Result(int multiplyTimes, Result left, Result right, int midMatrix) {
            this.multiplyTimes = multiplyTimes;
            this.left = left;
            this.right = right;
            this.midMatrix = midMatrix;
        }
    }
    Result minMatrixMultiplyHelper(int[] matrixSizes, Result[][] r, int start, int end) {
        if (start > end) {
            return null;
        }
        if (r[start][end] != null) {
            return r[start][end];
        }
        if (start == end) {
            r[start][end] = new Result(0, null, null, start);
            return r[start][end];
        }
        if (start + 1 == end) {
            r[start][end] = new Result(matrixSizes[start-1] * matrixSizes[start] * matrixSizes[end], null, null,
                    start);
            return r[start][end];
        }
        int curR = Integer.MAX_VALUE;
        int midMatrix = -1;
        Result left = null, right = null;
        for (int i = start; i < end; ++i) {
            Result r1 = minMatrixMultiplyHelper(matrixSizes, r, start, i);
            Result r2 = minMatrixMultiplyHelper(matrixSizes, r, i+1, end);
            int tr = r1.multiplyTimes + r2.multiplyTimes + matrixSizes[start-1]*matrixSizes[i]*matrixSizes[end];
            if (curR > tr) {
                left = r1;
                right = r2;
                curR = tr;
                midMatrix = i;
            }
        }
        r[start][end] = new Result(curR, left, right, midMatrix);
        return r[start][end];
    }

    @Test
    public void test() {
        int[] matrixSizes = new int[] {30,35,15,5,10,20,25};
        Result result = minMatrixMultiply(matrixSizes);
        System.out.println(result.multiplyTimes);
        System.out.println(result);
    }
}
