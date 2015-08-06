package algorithm.sort;

/**
 * 希尔排序
 * 稳定性：不稳定
 * 空间复杂度：O(1)
 * 最坏时间复杂度：O(n^2)
 * 平均时间复杂度：O(n(logn)^2)
 * 描述：每一轮按照事先决定的间隔进行插入排序，间隔会依次缩小，最后一次一定要是1。
 * Created by xzbang on 2015/8/5.
 */
public class ShellSort {
    public static void shellSort(int[] a){
        int len=a.length,h = 0;
        while(h<len/3){
            h=h*3+1;//步长为3的倍数+1
        }
        for(;h>=1;h/=3){
            for(int i = 0;i < h;i++){
                for(int j = i+h;j < len;j+=h){
                    for(int k = j;k>=h&&a[k]<a[k-h];k-=h){
                        int tmp = a[k];
                        a[k]=a[k-h];
                        a[k-h]=tmp;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {5,4,3,2,6,9,7,8,0,1};
        shellSort(a);
        for(int i : a){
            System.out.println(i);
        }
    }
}
