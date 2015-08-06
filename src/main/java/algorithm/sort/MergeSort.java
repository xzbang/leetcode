package algorithm.sort;

/**
 * 归并排序
 * 稳定性：稳定
 * 空间复杂度：链表：O(1)；数组：O(n)
 * 最坏时间复杂度：O(nlogn)
 * 平均时间复杂度：O(nlogn)
 * 描述：把数据分为两段，从两段中逐个选最小的元素移入新数据段的末尾。可从上到下或从下到上进行。
 * Created by xzbang on 2015/8/4.
 */
public class MergeSort {

    public static int[] mergingSort(int[] a){
        int len = a.length;
        if(len<=1)return a;
        int half = len/2;
        int[] a1 = new int[half];
        int[] a2 = new int[len-half];
        System.arraycopy(a,0,a1,0,half);
        System.arraycopy(a,half,a2,0,len-half);
        a1=mergingSort(a1);
        a2=mergingSort(a2);
        return mergingSortSub(a1,a2);
    }

    private static int[] mergingSortSub(int[] a1, int[] a2) {
        int len1 = a1.length,len2 = a2.length;
        int[] a = new int[len1+len2];
        int i = 0,j = 0,k = 0;
        while(i<len1&&j<len2){
                if(a1[i]>a2[j]){
                    a[k]=a2[j];
                    k++;j++;
                }else{
                    a[k]=a1[i];
                    k++;i++;
                }
        }
        while(i<len1){
            a[k] = a1[i];
            i++;
            k++;
        }
        while(j<len2) {
            a[k] = a2[j];
            j++;
            k++;
        }
        return a;
    }

    public static void main(String[] args) {
        int[] a = {5,4,3,2,6,9,7,8,0,1};
        int[] b=mergingSort(a);
        for(int i : b){
            System.out.println(i);
        }
    }
}
