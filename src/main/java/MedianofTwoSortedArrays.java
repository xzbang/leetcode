/**
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 *
 * There are two sorted arrays A and B of size m and n respectively.
 * Find the median of the two sorted arrays.
 * The overall run time complexity should be O(log (m+n)).
 *
 * Created by xzbang on 2015/4/13.
 */
public class MedianofTwoSortedArrays {
    public double findMedianSortedArrays(int A[], int B[]) {
        if(A==null||B==null){
            return 0.0;
        }
        int k = (A.length+B.length+1)/2;//注意两个序列的长度和为奇数时需要+1才是中间那个数
        double v = findK(0,A.length-1,A,0,B.length-1,B,k);
        if((A.length+B.length)%2==0){
            double v2 = findK(0,A.length-1,A,0,B.length-1,B,k+1);//偶数需要再算一下右边那个数
            v=(v+v2)/2;
        }
        return v;
    }

    private double findK(int al,int ar,int A[],int bl,int br,int B[],int k){
        if(al>ar){
            return B[bl+k-1];
        }
        if(bl>br){
            return A[al+k-1];
        }
        int am = (al+ar)/2;//中间的那个数（奇数长度）或者是中间左边的那个数（偶数长度）的index
        int bm = (bl+br)/2;
        int amv = A[am];//那个数的值
        int bmv = B[bm];
        if(k<(bm+am-al-bl+2)){
            //k小于两个序列的各一半，删除大数右边的部分（包括这个数）
            if(amv>bmv){
                return findK(al,am-1,A,bl,br,B,k);
            }else{
                return findK(al,ar,A,bl,bm-1,B,k);
            }
        }else if(k>(bm+am-al-bl)){
            //k大于两个序列的各一半，删除小数左边的部分（包括这个数）
            if(amv>bmv){
                return findK(al,ar,A,bm+1,br,B,k-bm+bl-1);
            }else{
                return findK(am+1,ar,A,bl,br,B,k-am+al-1);
            }
        }else{
            //K等于两个序列的各一半时，删除小数左边和大数右边的部分（包括这两个数）
            if(amv<bmv){
                return findK(am+1,ar,A,bl,bm-1,B,k-am+al-1);
            }else if(amv>bmv){
                return findK(al,am-1,A,bm+1,br,B,k-bm+bl-1);
            }else{
                //这两个数相等时，直接输出
                return amv;
            }
        }
    }

    public static void main(String[] args) {
        MedianofTwoSortedArrays medianofTwoSortedArrays = new MedianofTwoSortedArrays();
        int[] a = {2,4,5,6,8};
        int[] b = {1,3,6,7,9};
        System.out.println(medianofTwoSortedArrays.findMedianSortedArrays(a,b));
    }
}
