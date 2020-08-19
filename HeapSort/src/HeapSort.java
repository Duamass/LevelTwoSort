import java.util.Arrays;

public class HeapSort {
    /*
       堆排序算法的步骤；
       1.把无序数组构建成二叉堆
       2.循环删除堆顶元素，移到集合尾部，调节堆产生新的堆顶
     */
    public static void downAdjust(int[] array, int parentIndex, int length){
        //temp保存父节点值，用于最后的赋值
        int temp = array[parentIndex];
        int childIndex = 2 * parentIndex+1;
        while (childIndex<length){
            //如果有右孩子，且右孩子小于左孩子的值，则定位到右孩子
            if (childIndex+1 < length && array[childIndex+1] < array[childIndex]){
                childIndex++;
            }
            //如果父节点小于任何一个孩子的值，直接跳出
            if (temp <= array[childIndex]){
                break;
            }
            //无需真正交换，单向赋值即可
            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = 2*childIndex+1;
        }
        array[parentIndex] = temp;
    }

    public static void heapSort(int[] array){
        //1.把无序数组构建成二叉堆
        for (int i = (array.length-2)/2; i >= 0; i--) {
            downAdjust(array,i,array.length);
        }
        System.out.println(Arrays.toString(array));
        //2.循环删除堆顶元素，移动到集合尾部，调节堆产生新的堆顶
        for (int i = array.length-1; i > 0; i--) {
            //最后一个元素和第一个元素进行交换
            int temp = array[i];
            array[i] = array[0];
            array[0] = temp;
            // 下沉调整最大堆
            downAdjust(array,0,i);
        }
    }

    public static void main(String[] args) {
        int[] arr = {1,3,2,6,5,7,8,9,10,0};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
/*
 空间复杂度：因为没有开辟额外的集合空间，所以是o(1)。
 时间复杂度：若二叉堆共有n个元素，那么向下调整的最坏时间复杂度就等同于二叉堆的高度也就是o(logn)。

           第一步，把无序数组构建成二叉堆，需要进行n/2次循环。每次循环调用一次downAdjust方法，
           所以第一步的计算规模是n/2*logn，时间复杂度为o(nlogn)。
           第二步，需进行n-1次循环，每次循环调用一次downAdjust方法，所以第二步的计算规模是(n-1)*logn,
           时间复杂度为o(nlogn)。
           两个步骤是并列关系，所以整体时间复杂度同样是o(nlogn)。
 */

/*
 堆排序和快速排序的区别和联系
 相同点：平均时间复杂度都是o(nlogn),并且都是不稳定排序。
 不同点：快速排序的最坏时间复杂度是o(n^2)，而堆排序最坏时间复杂度稳定o(nlogn)。
        快速排序的递归和非递归方法空间复杂度都是o(n),而堆排序是o(1)。
 */
