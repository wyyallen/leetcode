二分查找就是将查找的键和子数组的中间键做比较，  
如果被查找的键小于中间键，就在左子数组继续查找；如果大于中间键，就在右子数组中查找，否则中间键就是要找的元素。  

# 标准二分查找

    public static int binarySearch(int[] array, int key){
        int left = 0;
        int right = array.length - 1;
        //这里left必须是 <= ,否则判断条件不完整（比如left = right）
        while(left <= right) {
            int mid = left + (right - left)/2;
            if(array[mid] < key) {
                left = mid + 1;
            }else if (array[mid] > key){
                right = mid - 1;
            }else{
                return mid;
            }
        }
        return -1;
    }
    
# 二分查找的变种
原理类似，主要就是变换判断条件（也就是边界条件）。我们需要理解的是跳出循环时候的边界值，判断需要返回的是left还是right。  
最后跳出循环时的情况应该是 left = right + 1;也就是left和right分别是边界值右边和左边的位置。