package binarySearch;

/**
 * 162 寻找峰值
 * 峰值元素是指其值大于左右相邻值的元素。
 *
 * 给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。
 *
 * 数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。
 *
 * 你可以假设 nums[-1] = nums[n] = -∞。
 *
 * 说明:
 *
 * 你的解法应该是 O(logN) 时间复杂度的。
 */
public class FindPeakElement {
    /**
     * 题目要求算法时间复杂度应该是O(logN)的，所以首先考虑二分查找。根据峰值的特点，可以总结两个规律：
     * 1：如果 num[i] > nums[i+1] ,那么在i+1之前一定存在一个峰值。
     * 2：如果 num[i] < nums[i+1], 那么在i之后一定存在一个峰值。
     * @param nums
     * @return
     */
    public int findPeakElementTwo(int[] nums) {
        int left = 0;
        int right = nums.length-1;
        for (;left < right;) {
            int mid = left + (right - left)/2;
            if (nums[mid] > nums[mid +1]) {
                right = mid;
            }else {
                left = mid +1;
            }
        }

        return left;
    }

    /**
     * 数组的最大值肯定是峰值之一，直接找到最大值
     * @param nums
     * @return
     */
    public int findPeakElementOne(int[] nums) {
        int res = 0;
        int max = Integer.MIN_VALUE;
        for(int i = 0;i < nums.length;i++){
            if(nums[i] > max){
                max = nums[i];
                res = i;
            }
        }
        return res;
    }
}
