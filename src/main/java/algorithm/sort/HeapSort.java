package algorithm.sort;

/**
 * 堆排序
 * 稳定性：不稳定
 * 空间复杂度：O(1)
 * 最坏时间复杂度：O(nlogn)
 * 平均时间复杂度：O(nlogn)
 * 描述：（最大堆，有序区）。从堆顶把根卸出来放在有序区之前，再恢复堆。
 * Created by xzbang on 2015/8/5.
 */
public class HeapSort {
    public static void heapSort(int[] a){
        buildMaxHeap(a);
        sortHeap(a);
    }

    private static void sortHeap(int[] a) {
        for(int i = a.length-1;i>0;i--){
            int tmp = a[0];
            a[0] = a[i];
            a[i] = tmp;
            maxHeap(a,i,0);
        }
    }

    private static void buildMaxHeap(int[] a) {
        int parent = getParent(a.length-1);
        for(int i = parent;i>=0;i--){
            maxHeap(a,a.length,i);
        }
    }

    private static void maxHeap(int[] a, int heapSize, int parent) {
        int left = getLeftChild(parent);
        int right = getRightChild(parent);
        int max = parent;
        if(left<heapSize&&a[left]>a[max])max=left;
        if(right<heapSize&&a[right]>a[max])max=right;
        if(max!=parent){
            int tmp = a[parent];
            a[parent]=a[max];
            a[max]=tmp;
            maxHeap(a,heapSize,max);
        }
    }

    private static int getRightChild(int parent) {
        return (parent<<1)+2;
    }

    private static int getLeftChild(int parent) {
        return (parent<<1)+1;
    }

    private static int getParent(int i) {
        return (i-1)>>1;
    }

    public static void main(String[] args) {
        int[] a = {5,4,3,2,6,9,7,8,0,1};
        heapSort(a);
        for(int i : a){
            System.out.println(i);
        }
    }
}
