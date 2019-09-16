package sort;

import java.util.Stack;

/**
 * 各种排序算法汇总
 * https://www.cnblogs.com/onepixel/p/7674659.html
 *
 * 1: 快排
 * 2: 冒泡
 * 3: 选择排序
 * 4: 插入排序
 * 5: 希尔排序
 * 6：归并排序
 * 7：堆排序
 * 8：计数排序
 * 9：桶排序
 * 10：基数排序
 */
public class SortUtils {
    private static Comparable[] aux;
    /**
     * 1:快排,基准分组排序。
     */
    public static void quickSort(int[] array){
        if (array == null || array.length <= 0) {
            return;
        }
        Stack<Integer> stack = new Stack<>();
        int left = 0;
        int right = array.length -1;
        stack.push(left);
        stack.push(right);
        while (!stack.isEmpty()){
            right = stack.pop();
            left = stack.pop();
            int pos = quickSort(array,left,right);
            if (pos-1 > left){
                stack.push(left);
                stack.push(pos -1);
            }
            if (pos+1 < right){
                stack.push(pos+1);
                stack.push(right);
            }
        }
    }

    /**
     *
     * @param array
     * @param left
     * @param right
     * @return
     */
    public static int quickSort(int[] array,int left,int right) {
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
     * 2:冒泡排序,相邻交换，稳定。时间复杂度O(n^2)
     * 一次遍历后会将最大值放到末尾。
     */
    public static void bubbleSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        for (int i = 0; i < array.length -1; i ++) { //排序次数
            for (int j = 0; j < array.length - 1 - i; j++) { //比较次数
                if (array[j] > array[j+1]) {
                    swap(array, j, j+1);
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

    /**
     * 3: 选择排序
     * 在未排序序列中每次选择一个最大或者最小值，放置末尾或者开头。
     */
    public static void selectedSort(int[] arrays) {
        if (arrays == null || arrays.length <= 1) {
            return;
        }
        for (int i = 0; i < arrays.length - 1; i++) { //排序次数
            int minIndex = 0;
            for (int j = i + 1 ; j < arrays.length; j++) { //选择次数
                minIndex = arrays[minIndex] < arrays[j] ? minIndex : j;
            }
            swap(arrays, minIndex, i);
        }
    }

    /**
     * 4： 插入排序
     * 思路：构建有序队列，将未排序的插入其中.
     */
    public static void insertSort(int[] arrays) {
        if (arrays == null || arrays.length <= 1) {
            return;
        }
        int preIndex;
        int cur;
        for (int i = 1; i < arrays.length; i++) { //排序次数
            preIndex = i -1;
            cur = arrays[i];
            while (preIndex >= 0 && arrays[preIndex] > cur) {
                arrays[preIndex + 1] = arrays[preIndex];
                preIndex--;
            }
            arrays[preIndex + 1] = cur;
        }
    }

    /**
     * 5: 希尔排序
     * 插入排序的优化版，通过分组的形式实现比较距离较远的元素，然后逐渐缩小距离，所以也叫缩小增量排序。
     */

    public static void shellSort(int[] arrays) {
        if (arrays == null || arrays.length <= 1) {
            return;
        }
        for (int gap = arrays.length/2; gap > 0 ; gap = gap/2) { //分组次数
            for (int i = gap ; i < arrays.length ; i++) {
                //分组实际是交替执行，这里面的执行就是插入排序
                int j = i;
                int cur = arrays[j];
                while (j -gap >= 0 && cur < arrays[j - gap]) { //比较次数
                    arrays[j] = arrays[j -gap];
                    j = j-gap;
                }
                arrays[j] = cur;
            }
        }
    }
    /**
     * 6 ：归并排序
     * @param a
     */
    public static void mergeSort(Comparable[] a){
        //一次性分配空间
        aux = new Comparable[a.length];

        MergeSort(a,0,a.length-1);
    }

    /**
     * 归并排序是典型的分治法，先使每个子序列有序，然后合并子序列。将两个有序子序列合并为一个，称为2路归并。
     * 时间复杂度O(nlogn)， 代价是需要额外的空间。
     * @param array
     * @param start
     * @param end
     */
    public static void MergeSort(Comparable[] array,int start ,int end){
        if (start < end){
            int mid = start+ (end-start)/2;
            MergeSort(array,start,mid);
            MergeSort(array,mid+1,end);
            merge(array,start,mid,end);
        }
    }

    /**
     * 归并排序
     * @param array
     * @param start
     * @param mid
     * @param end
     */
    public static void merge(Comparable[] array, int start, int mid, int end){
        int i = start;
        int j = mid+1;

        for (int k = start; k<end;k++){
            aux[k] = array[k];
        }
        for (int k = start;k<end;k++){
            if (i > mid){
                //左半边用完
                array[k] = aux[j++];
            }else if (j > end){
                //右半边用完
                array[k] = aux[i++];
            }else if (aux[j].compareTo(aux[i]) == -1){
                //右半边元素小于左半边元素
                array[k] = aux[j++];
            }else {
                //右半边元素大于等于左半边元素
                array[k] = aux[i++];
            }
        }
    }

    /*
    //堆排模版
    public static void heapSort(Comparable[] a){
        int N = a.length;
        for(int k = N/2;k>= 1;k--){
            //使用下沉方法从下到上构造堆
            sink(a,k,N);
        }
        while(N > 1){
            //将堆顶元素和数组尾交换，减小堆大小
            exch(a,1,N--);
            //下沉修复堆
            sink(a,1,N);
        }
    }
    */

    /**
     * 7: 堆排序
     * @param array
     */
    public static void heapSort(int[] array) {
        //1 构建大顶堆，从最后一个节点的父节点开始，向根节点方向调整堆。
        for (int i = array.length/2-1; i >= 0; i--){
            adjustHeap(array, i, array.length);
        }
        //2 调整堆结构 + 交换堆顶元素与末尾元素
        for (int j = array.length-1;j>0;j--){
            swap(array,0,j);
            adjustHeap(array,0,j);
        }
    }

    public static void adjustHeap(int[] array,int index,int length) {
        int temp = array[index];
        for (int child = index*2 + 1; child < length; child = child*2+1) {
            //从i节点的左子节点开始，也就是2i+1处开始
            if (child+1 < length && array[child] <array[child+1]) {
                //如果左子节点小于右子节点，child指向右子节点
                child++;
            }
            if (array[child] > temp) {
                //如果子节点大于父节点，将子节点赋给父节点（不用交换）
                array[index] = array[child];
                index = child;
            }
        }
        //将temp放到最终的位置
        array[index] = temp;
    }

    /**
     * 8：计数排序。计数排序是一个稳定的排序算法。
     * 当输入的元素是 n 个 0到 k 之间的整数时，时间复杂度是O(n+k)，空间复杂度也是O(n+k)，其排序速度快于任何比较排序算法。
     * 当k不是很大并且序列比较集中时，计数排序是一个很有效的排序算法。
     * 思路：
     * 1。找到当前序列的最大值和最小值。
     * 2。统计数组中每个值为i的元素出现的次数，存入数组c的第i项。
     * 3。反向填充数组。
     * @param arrays
     */
    public static void countingSort(int[] arrays) {
        //1
        int min = arrays[0];
        int max = arrays[0];
        for (int i = 0; i < arrays.length; i++) {
            min = arrays[i] < min ? arrays[i] : min;
            max = arrays[i] > max ? arrays[i] : max;
        }
        //2
        int[] count = new int[max - min + 1];
        for (int j = 0; j < arrays.length; j++) {
            count[arrays[j] - min]++;
        }
        //3
        int cur = 0;
        for (int k = 0; k < count.length;k++) {
            while (count[k] != 0) {
                arrays[cur++] = k + min;
                count[k]--;
            }
        }
    }

    public static void bucketSort(int[] arrays) {

    }

    public static void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }
}
