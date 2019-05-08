package binarySearch;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 *
 *  给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 如果数组中不存在目标值，返回 [-1, -1]。
 */

public class SearchRange {

    public static int[] searchRange(int[] nums, int target) {
        int[] res = {-1,-1};
        if (nums == null || nums.length == 0) {
            return res;
        }
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left)/2;
            if (nums[mid] == target) {
                //这里应该是个出口
                int temp = mid;
                while ( temp >= 0 && nums[temp] == target) {
                    temp--;
                }
                res[0] = temp + 1;
                while ( mid < nums.length && nums[mid] == target) {
                    mid++;
                }
                res[1] = mid-1;
                break;
            }else if (nums[mid] < target) {
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
    return res;
    }

    public static void main(String[] args) {
        int[] nums = {1,4};
        int[] res = searchRange(nums,4);
        System.out.println(res[0] + ":"+ res[1]);
    }
}
