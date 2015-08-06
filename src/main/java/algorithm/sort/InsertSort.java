package algorithm.sort;

/**
 * 插入排序
 * 稳定性：稳定
 * 空间复杂度：O(1)
 * 最坏时间复杂度：O(n^2)
 * 平均时间复杂度：O(n^2)
 * 描述：（有序区，无序区）。把无序区的第一个元素插入到有序区的合适的位置。对数组：比较得少，换得多。
 * Created by xzbang on 2015/8/5.
 */
public class InsertSort {
    public static void insertSort(int[] a){
        int len = a.length;
        for(int i = 1;i < len;i++){
            int index = find(a,i);
            insert(a,index,i);
        }
    }

    private static void insert(int[] a, int index, int i) {
        if(index==i)return;
        int tmp = a[i];
        for(int j = i-1;j >= index;j--){
            a[j+1]=a[j];
        }
        a[index] = tmp;
    }

    private static int find(int[] a, int i) {
        for(int j = 0;j < i;j++){
            if(a[j]>a[i])return j;
        }
        return i;
    }

    public static void main(String[] args) {
        int[] a = {5,4,3,2,6,9,7,8,0,1};
        insertSort(a);
        for(int i : a){
            System.out.println(i);
        }
    }
}
