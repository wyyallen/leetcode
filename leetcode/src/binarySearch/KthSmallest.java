package binarySearch;

import java.util.Arrays;

/**
 * 378. 有序矩阵中第K小的元素
 *
 * 给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第k小的元素。
 * 请注意，它是排序后的第k小元素，而不是第k个元素。
 */

public class KthSmallest {
    //简单粗暴，直接将二维数组变成一维排序数组后输出.时间复杂度O（n2）,额外空间n2。
    public int kthSmallest1(int[][] matrix, int k) {
        int n = matrix.length;
        int[] res = new int[n*n];
        for (int i = 0;i < n;i++){
            for (int j = 0;j < n;j++){
                res[i*n + j] = matrix[i][j];
            }
        }
        Arrays.sort(res);
        return res[k-1];
    }

    /**
     * 二分查找解法
     */
    public int kthSmallest2(int[][] matrix, int k) {
        int n = matrix.length;
        int lo = matrix[0][0];
        int hi = matrix[n-1][n-1];
        while (lo < hi){
            int mid = lo + (hi - lo)/2;
            int count = 0;
            int j = matrix[0].length - 1;
            for (int i = 0;i < matrix.length;i++){
                while (j >= 0 && matrix[i][j] > mid) j--;
                count += (j +1);
            }
            if (count < k){
                lo = mid +1;
            }else {
                hi = mid;
            }
        }
        return lo;
    }

    /**
     * 可将每一行作为归并排序的一个分支，然后使用归并排序的策略进行排序并查找
     */
    public int kthSmallest(int[][] matrix, int k) {

    }

    /**
     * 用最大堆的性质，构建一个存放元素个数为k的最大堆，当元素个数大于k时存入，会把最大的首元素剔除。循环结束后返回堆顶元素
     * 就是有序矩阵中第k小的元素。
     */
}
