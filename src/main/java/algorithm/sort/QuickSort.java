package algorithm.sort;

/**
 * 快速排序
 * 稳定性：不稳定
 * 空间复杂度：O(logn)
 * 最坏时间复杂度：O(n^2)
 * 平均时间复杂度：O(nlogn)
 * 描述：递归选择一个数，将所有小的数分到该数的左边，所有大的数分到该数的右边
 * Created by xzbang on 2015/8/6.
 */
public class QuickSort {
    public static void quickSort(int[] a){
        int len = a.length;
        sort(a,0,len-1);
    }

    private static void sort(int[] a, int start, int last) {
        int mid = start,left=start,right=last;
        while(left!=right){
            if(mid==left){
                if(a[right]<a[left]){
                    int tmp = a[left];
                    a[left] = a[right];
                    a[right] = tmp;
                    mid = right;
                    left++;
                }else{
                    right--;
                }
            }else{
                if(a[left]>a[right]){
                    int tmp = a[left];
                    a[left] = a[right];
                    a[right] = tmp;
                    mid = left;
                    right--;
                }else{
                    left++;
                }
            }
        }
        if(mid>start)sort(a,start,mid-1);
        if(mid<last)sort(a,mid+1,last);
    }

    public static void main(String[] args) {
        int[] a = {5,4,3,2,6,9,7,8,0,1};
        quickSort(a);
        for(int i : a){
            System.out.println(i);
        }
    }
}
