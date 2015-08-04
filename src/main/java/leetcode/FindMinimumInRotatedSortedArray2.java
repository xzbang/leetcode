package leetcode;

/**
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/
 *
 Suppose a sorted array is rotated at some pivot unknown to you beforehand.

 (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

 Find the minimum element.

 The array may contain duplicates.
 *
 * 解题思路：二分查找，首先获取最左边的数N0，和最右边的数Nn，当N0<Nn时，N0为最小值
 * 否则获取中间的数Nm，如果Nm>N0，则最小值在右边，对右边部分递归查找，如果Nm<=N0，对左边部分（包括Nm）递归查找
 *
 * Created by xzbang on 2015/5/13.
 */
public class FindMinimumInRotatedSortedArray2 {
    public int findMin(int[] nums) {
        if(nums == null||nums.length==0){
            return -1;
        }
        int size = nums.length;
        return findMinByIndex(nums, 0, size - 1);
    }

    private int findMinByIndex(int[] nums, int i, int j) {
        if(j==i){
            return nums[i];
        }
        int N0=nums[i],Nn=nums[j];
        int Nm = nums[(i + j) / 2];
        if(N0<Nn){
            return N0;
        }else if(N0>Nn) {
            if (Nm >= N0) {
                return findMinByIndex(nums, ((i + j) / 2) + 1, j);
            } else {
                return findMinByIndex(nums, i, (i + j) / 2);
            }
        }else{
            if(Nm==N0){//大片相等的数据中有个较小的数
                int Nl=findMinByIndex(nums, i, (i + j) / 2),Nr = findMinByIndex(nums, ((i + j) / 2) + 1, j);
                return Nl<Nr?Nl:Nr;
            }else if (Nm > N0) {
                return findMinByIndex(nums, ((i + j) / 2) + 1, j);
            } else {
                return findMinByIndex(nums, i, (i + j) / 2);
            }
        }
    }

    //另一种精炼的解法
    public int findMin2(int[] nums) {
        if(nums == null||nums.length==0)
            return -1;
        int first = 0;
        int last = nums.length-1;
        int middle;
        while(first<last){
            middle = first+(last-first)/2;//当first+last过大时可能会溢出，经典二叉查找bug
            if(nums[middle]<nums[last])
                last=middle;
            else if(nums[middle]>nums[last])
                first=middle+1;
            else
                last--;
        }
        return nums[first];
    }

    public static void main(String[] args) {
        FindMinimumInRotatedSortedArray2 findMinimumInRotatedSortedArray2 = new FindMinimumInRotatedSortedArray2();
        int[] nums = {3,1};
        System.out.println(findMinimumInRotatedSortedArray2.findMin2(nums));
    }
}
