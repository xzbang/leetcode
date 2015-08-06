package algorithm.sort;

/**
 * 桶排序
 * 稳定性：稳定
 * 空间复杂度：O(n*k)
 * 最坏时间复杂度：O(n^2)
 * 平均时间复杂度：O(n*k)
 * 描述：将数组分到有限数量的桶子里。每个桶子再个别排序（有可能再使用别的排序算法或是以递归方式继续使用桶排序进行排序）。
 * 注：本实现针对无重复数组，当存在重复数据时，各个桶内还需要再排序
 * Created by xzbang on 2015/8/6.
 */
public class BucketSort {
    public static void bucketSort(int[] a){
        int min = getMin(a);
        int max = getMax(a);
        int len = a.length;
        int[] bucket = new int[max-min+1];
        for(int i = 0;i < max-min+1;i++)
            bucket[i]=Integer.MIN_VALUE;
        for(int i = 0;i < len;i++)
            bucket[a[i]-min]=a[i];
        int k = 0;
        for(int i = 0;i < max-min+1;i++)
            if(bucket[i]!=Integer.MIN_VALUE)
                a[k++]=bucket[i];
    }

    private static int getMax(int[] a) {
        int max=a[0],len = a.length;
        for(int i = 1;i < len;i++)
            if(a[i]>max)
                max=a[i];
        return max;
    }

    private static int getMin(int[] a) {
        int min = a[0],len=a.length;
        for(int i = 1;i < len;i++)
            if(a[i]<min)
                min=a[i];
        return min;
    }

    public static void main(String[] args) {
        int[] a = {5,4,3,2,6,9,7,8,0,1};
        bucketSort(a);
        for(int i : a){
            System.out.println(i);
        }
    }
}
