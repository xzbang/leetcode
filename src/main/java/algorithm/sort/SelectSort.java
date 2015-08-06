package algorithm.sort;

/**
 * 选择排序
 * 稳定性：不稳定
 * 空间复杂度：O(1)
 * 最坏时间复杂度：O(n^2)
 * 平均时间复杂度：O(n^2)
 * 描述：（有序区，无序区）。在无序区里找一个最小的元素跟在有序区的后面。对数组：比较得多，换得少。
 * 注：不稳定为每次将无序区的最小元素与有序区后面那个元素交换所导致。
 * Created by xzbang on 2015/8/5.
 */
public class SelectSort {
    public static void selectSort(int[] a){
        int len = a.length;
        for(int i = 0;i < len;i++){
            int min = i;
            for(int j = i+1;j < len;j++){
                if(a[j]<a[min])min=j;
            }
            if(min!=i){
                int tmp = a[i];
                a[i] = a[min];
                a[min] = tmp;
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {5,4,3,2,6,9,7,8,0,1};
        selectSort(a);
        for(int i : a){
            System.out.println(i);
        }
    }
}
