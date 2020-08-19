import java.awt.image.renderable.RenderableImage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

// 快速排序是从冒泡排序演变而来的，使用了分治法，平均时间复杂度是o(nlogn)，最坏是o(n^2)。
/* 冒泡排序在每一轮只把一个元素冒泡到数列的一端，而快速排序在每一轮挑选一个基准元素，
   并让其他比它大的元素移动到数列一边，比它小的元素移动到数列的另一边，从而把数列拆解成俩部分。
*/
public class QuickSort {
    public static void quickSort(int[] array, int startIndex, int endIndex) {
        //递归结束条件：startIndex >= endIndex的时候
        if (startIndex >= endIndex){
            return;
        }
        //得到基准元素位置
        int pivotIndex = partition(array, startIndex, endIndex);
        //用分治法递归数列的两部分
        quickSort(array,startIndex,pivotIndex - 1);
        quickSort(array,pivotIndex + 1, endIndex);
    }

    //挖坑法
    private static int partition(int[] array, int startIndex, int endIndex){
        // 取第一个位置的元素为基准元素
        int pivot = array[startIndex];
        int left = startIndex;
        int right = endIndex;
        //坑的位置，初始等于pivot的位置
        int index = startIndex;

        // 大循环在左右指针重合或者交错时结束
        while (right >= left){
            //right指针从右向左进行比较
            while (right >= left){
                if (array[right] < pivot){
                    array[left] = array[right];
                    index = right;
                    left++;
                    break;
                }
                right--;
            }
            //left指针从左向右进行比较
            while(right >= left){
                if (array[left] > pivot){
                    array[right] = array[left];
                    index = left;
                    right--;
                    break;
                }
                left++;
            }
        }
        array[index] = pivot;
        return index;
    }

    // 指针交换法-区别在于partition函数的实现,在partition方法中进行的元素交换次数更少。
    public static void quickSort2(int[] array, int startIndex, int endIndex){
        //递归结束条件，startIndex大于等于endIndex的时候
        if (startIndex >= endIndex){
            return;
        }
        // 得到基准元素位置
        int pivotIndex = partition2(array,startIndex,endIndex);
        // 根据基准元素，分成两部分递归排序
        quickSort2(array,startIndex,pivotIndex - 1);
        quickSort2(array,pivotIndex + 1, endIndex);
    }

    private static int partition2(int[] array, int startIndex, int endIndex){
        // 取第一个位置的元素作为基准元素
        int pivot = array[startIndex];
        int left = startIndex;
        int right = endIndex;
        while (left!=right){
            // 控制right指针比较并左移
            while(left < right && array[right] > pivot){
                right--;
            }
            // 控制left指针比较并右移
            while(left < right && array[left] <= pivot){
                left++;
            }
            // 交换left和right指向的元素
            if (left < right){
                int temp = array[left];
                array[left] = array[right];
                array[right] = temp;
            }
        }

        //pivot和指针重合点交换
        int temp = array[left];
        array[left] = array[startIndex];
        array[startIndex] = temp;

        return left;

    }

    // 非递归实现-绝大多数用递归来实现的问题，都可以用【栈】的方式来替代。
    /* 因为我们代码中一层一层的方法调用，本身就是一个函数栈。每次进入一个新方法，就相当于入栈；
       每次有方法返回，就相当于出栈。
    */

    public static void quickSortWithStack(int[] arr, int startIndex, int endIndex){
        // 用一个集合栈来代替递归的函数栈
        Stack<Map<String,Integer>> quickSortStack = new Stack<Map<String, Integer>>();
        // 整个数列的起止下标，以哈希的形式入栈
        Map rootParam = new HashMap();
        rootParam.put("startIndex",startIndex);
        rootParam.put("endIndex",endIndex);
        quickSortStack.push(rootParam);

        // 循环结束条件：栈为空时结束
        while(!quickSortStack.isEmpty()){
            // 栈顶元素出栈，得到起止下标
            Map<String, Integer> param = quickSortStack.pop();
            // 得到基准元素位置
            int pivotIndex = partition(arr, param.get("startIndex"),param.get("endIndex"));
            //根据基准元素分成两部分，把每一部分的起止下标入栈
            if (param.get("startIndex") < pivotIndex -1){
                Map<String,Integer> leftParam = new HashMap<String, Integer>();
                leftParam.put("startIndex", param.get("startIndex"));
                leftParam.put("endIndex", pivotIndex-1);
                quickSortStack.push(leftParam);
            }
            if (pivotIndex+1 < param.get("endIndex")){
                Map<String,Integer> rightParam = new HashMap<String, Integer>();
                rightParam.put("startIndex", pivotIndex+1);
                rightParam.put("endIndex", param.get("endIndex"));
                quickSortStack.push(rightParam);
            }
        }
    }

    private static int partition3(int[] arr, int startIndex, int endIndex){
        // 取第一个位置的元素作为基准元素
        int pivot = arr[startIndex];
        int left = startIndex;
        int right = endIndex;
        while (left != right){
            //控制right指针比较并左移
            while(left < right && arr[right] > pivot){
                right--;
            }
            //控制left指针比较并右移
            while (left < right && arr[left] <= pivot){
                left++;
            }
            //交换left和right指向的元素
            if (left < right){
                int p = arr[left];
                arr[left] = arr[right];
                arr[right] = p;
            }
        }
        //pivot和指针重合点交换
        int p = arr[left];
        arr[left] = arr[startIndex];
        arr[startIndex] = p;

        return left;
    }
    /*
    和递归相比，代码的变动仅在quickSortWithStack方法中。该方法引入了一个存储Map类型元素的栈，
    用于存储每一次交换时的起始下标和结束下标。

    每一次循环，都会让栈顶元素出栈，进行排序，并且按照基准元素的位置分成左右两部分，左右两部分再分别入栈。
    当栈为空时，说明排序已经完毕，退出循环。
    */

    public static void main(String[] args) {
        int[] arr = {4,7,6,5,3,2,8,1};
        int[] arr2 = {4,7,6,5,3,2,8,1};
        int[] arr3 = {4,7,6,5,3,2,8,1};

        quickSort(arr, 0,arr.length-1);
        quickSort2(arr2, 0,arr2.length-1);
        quickSortWithStack(arr3, 0,arr3.length-1);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(arr2));
        System.out.println(Arrays.toString(arr3));
    }




}

