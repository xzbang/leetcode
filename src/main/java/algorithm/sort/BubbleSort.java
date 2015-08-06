package algorithm.sort;

/**
 * 冒泡排序
 * 稳定性：稳定
 * 空间复杂度：O(1)
 * 最坏时间复杂度：O(n^2)
 * 平均时间复杂度：O(n^2)
 * 描述：（无序区，有序区）。无序区两两比较，通过交换找出最大元素放到有序区前端。
 * Created by xzbang on 2015/8/4.
 */
public class BubbleSort {

    public static void bubbleSort(int[] a){
        int len = a.length;
        for(int i = 0;i < len-1;i ++){
            for(int j = 0;j < len-1-i;j ++){
                if(a[j]>a[j+1]){
                    int tmp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = tmp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {5,4,3,2,6,9,7,8,0,1};
        bubbleSort(a);
        for(int i : a){
            System.out.println(i);
        }
    }

}
