package sort;

import java.util.Stack;

/**
 * 各种排序算法汇总
 */
public class SortUtils {
    private static Comparable[] aux;
    /**
     * 快排,基准分组排序。
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

    /**
     * 归并排序
     * @param a
     */
    public static void mergeSort(Comparable[] a){
        //一次性分配空间
        aux = new Comparable[a.length];

        MergeSort(a,0,a.length-1);
    }

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
        for (int child = index*2 + 1; child < length; child = child*2+1){
            //从i节点的左子节点开始，也就是2i+1处开始
            if (child+1 < length && array[child] <array[child+1]){
                //如果左子节点小于右子节点，child指向右子节点
                child++;
            }
            if (array[child] > temp){
                //如果子节点大于父节点，将子节点赋给父节点（不用交换）
                array[index] = array[child];
                index = child;
            }
        }
        //将temp放到最终的位置
        array[index] = temp;
    }

    public static void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }
}
