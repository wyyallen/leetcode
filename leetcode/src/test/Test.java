package test;

import sort.SortUtils;

/**
 * @author yiwang <>
 * Created on 2019-08-15
 */
public class Test {
    public static void main(String[] args) {
        int[] arrays = new int[]{1,3,4,6,7,3,2,8,9};
        show(arrays);
        SortUtils.countingSort(arrays);
        show(arrays);
    }

    public static void show(int[] array) {
        for (int i : array) {
            System.out.print(i + ",");
        }
        System.out.println();
    }
}
