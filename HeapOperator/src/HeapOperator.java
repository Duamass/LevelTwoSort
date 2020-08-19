import java.util.Arrays;

public class HeapOperator {
    /*
     对于二叉堆，有如下几种操作：
     1.插入节点：插入的位置是完全二叉树的最后一个位置。和父亲节点做比较，若小，则上浮。
     2.删除节点：所删除的是处于堆顶的节点，为了维持完全二叉树的结构，这时把堆的最后一个节点补到原本堆顶的位置。
               若比左右孩子中最小的一个小，则下沉。
     3.创建二叉堆：把一个无序的完全二叉树调整为二叉堆，本质上就是让所有非叶子节点依次下沉。
     */

    //上浮调整
    public static void upAdjust(int[] array){
        int childIndex = array.length-1;
        int parentIndex = (childIndex-1)/2;
        //temp保存插入的叶子节点值，用于最后的赋值
        int temp = array[childIndex];
        while (childIndex >0 && temp <array[parentIndex]){
            //无需真正交换，单向赋值即可
            array[childIndex] = array[parentIndex];
            childIndex = parentIndex;
            parentIndex = (parentIndex-1)/2;
        }
        array[childIndex] = temp;
    }

    //下沉调整
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
            if (temp >= array[childIndex]){
                break;
            }
            //无需真正交换，单向赋值即可
            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = 2*childIndex+1;
        }
        array[parentIndex] = temp;
    }

    //构建堆
    public static void buildHeap(int[] array){
        //从最后一个非叶子节点开始，依次下沉调整
        for (int i = array.length/2; i >= 0 ; i--) {
            downAdjust(array,i,array.length-1);
        }
    }

    public static void main(String[] args) {
        int[] array = new int[] {1,3,2,6,5,7,8,9,10,0};
        upAdjust(array);
        System.out.println(Arrays.toString(array));

        array = new int[] {7,1,3,10,5,2,8,9,6};
        buildHeap(array);
        System.out.println(Arrays.toString(array));

    }
}


