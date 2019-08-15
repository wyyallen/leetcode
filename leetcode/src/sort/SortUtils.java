package sort;

/**
 * 各种排序算法汇总
 */
public class SortUtils {
    /**
     * 快排,基准分组排序。
     */
    public static void quickSort(int[] arrays) {

    }

    /**
     *
     * @param array
     * @param left
     * @param right
     * @return
     */
    public static int quickSort(int[] array,int left,int right){
        int temp = array[right];
        while (left < right){
            while (left < right && array[left] <= temp){
                left++;
            }
            if (left < right){
                array[right--] = array[left];
            }
            while (left < right && array[right] >= temp){
                right--;
            }
            if (left < right){
                array[left++] = array[right];
            }
        }
        array[right] = temp;
        return right;
    }

    /**
     * 冒泡排序,相邻交换，稳定
     */
    public static void bubbleSort(int[] arrays) {
        for (int i = 0; i< arrays.length-1; i++) { //排序轮数
            for (int j = 0; j<arrays.length-i-1;j++) { //比较次数
                if (arrays[j] > arrays[j+1]) {
                    int temp = arrays[j];
                    arrays[j] = arrays[j+1];
                    arrays[j+1] = temp;
                }
            }
        }
    }

    /**
     * 冒泡排序优化，如果一次排序过程中没有发生一次交换，说明该数组已经是有序的了
     */
    public static void bubbleSortoptimize(int[] arrays) {
        boolean flag = true;
        for (int i = 0; i< arrays.length-1; i++) { //排序轮数
            for (int j = 0; j<arrays.length-i-1;j++) { //比较次数
                if (arrays[j] > arrays[j+1]) {
                    flag = false;
                    int temp = arrays[j];
                    arrays[j] = arrays[j+1];
                    arrays[j+1] = temp;
                }
            }
            if (flag) {
                break;
            }
        }
    }
}
