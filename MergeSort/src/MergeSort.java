import java.util.Arrays;

public class MergeSort {
    /*
    归并排序就像是元素之间的比武大会，分为两个阶段：
    1.分组
    假设集合内有n个元素，算法将会对集合进行逐层的折半分组。
    第一层两个大组，每组n/2个元素；第二层四个小组，每组n/4个元素；... 直到每组只有一个元素。
    2.归并
    与擂台赛不同的是，擂台赛只需要决定谁是老大，而归并排序更复杂一些，需要确定每一个元素的排列位置。

    核心：如何把两个有序的小集合归并成一个有序的大集合。
    需要三个步骤：
    1.创建一个额外大集合用于存储归并结果，长度是两个小集合之和。（三个辅助指针用于记录当前操作的位置）
    2.从左到右逐一比较两个小集合中的元素，把较小的元素优先放入大集合。
    3.若一个小集合已经没有元素，将另一个集合中的元素按顺序复制到大集合尾部。
     */
    public static void mergeSort(int[] array, int start, int end){
        if (start < end){
            //折半成两个小集合，分别进行递归
            int mid = (start + end)/2;
            mergeSort(array, start, mid);
            mergeSort(array, mid+1, end);
            //把两个有序小集合，归并成一个大集合。
            merge(array, start, mid, end);
        }
    }
    private static void merge(int[] array, int start, int mid, int end){
        //开辟额外大集合，设置指针
        int[] tempArray = new int[end-start+1];
        int p1 = start, p2 = mid+1, p = 0;
        //比较两个小集合的元素，依次放入大集合
        while (p1 <= mid && p2 <= end){
            if (array[p1] <= array[p2])
                tempArray[p++] = array[p1++];
            else
                tempArray[p++] = array[p2++];
        }
        //左侧小集合还有剩余，依次放入大集合尾部
        while (p1<=mid){
            tempArray[p++] = array[p1++];
        }
        //右侧小集合还有剩余，依次放入大集合尾部
        while(p2<=end){
            tempArray[p++] = array[p2++];
        }
        //把大集合的元素复制回原数组
        for (int i = 0; i < tempArray.length; i++) {
            array[i+start] = tempArray[i];
        }
    }

    public static void main(String[] args) {
        int[] arr = {5,8,6,3,9,2,1,7};
        mergeSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
    /*
    归并排序是稳定排序。
    空间复杂度：
    如果集合长度是n，把集合进行折半分组，那么折半的层数是logn，每一层进行归并操作的运算量是n，即o(nlogn)。
    空间复杂度：
    每次归并所创建的额外集合都会随着方法的结束而被释放，因此这部分空间不应该累加计算。
    单次归并操作的最大空间是n，所以空间复杂度是o(n)。
     */

}