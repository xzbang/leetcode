package leetcode;

/**
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 *
 Suppose a sorted array is rotated at some pivot unknown to you beforehand.

 (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

 You are given a target value to search. If found in the array return its index, otherwise return -1.

 You may assume no duplicate exists in the array.
 *
 * 解题思路：
 * 与FindMinimumInRotatedSortedArray2类类似
 * 二分查找，关键是理清每一个判断条件处的逻辑关系
 *
 * 也可以先二分查找获取最小值点，然后对剩下的数运用已排序数组的二分查找即可
 *
 * Created by xzbang on 2015/5/29.
 */
public class SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        if(nums==null || nums.length==0)return -1;
        int i = 0,j = nums.length;
        while(i<j){
            if(target>nums[j-1]&&target<nums[i])return -1;
            if(target==nums[j-1])return j-1;
            if(target==nums[i])return i;
            if(j==i+1||j==i+2)return -1;
            int k = i+(j-i)/2;
            if(target>nums[i]){
                if(target>nums[k]){
                    if(nums[k]>=nums[i])i=k;
                    else j=k+1;
                }else{
                    j=k+1;
                }
            }else{
                if(target>nums[k]){
                    i=k;
                }else{
                    if(nums[k]>=nums[i])i=k;
                    else j=k+1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        SearchInRotatedSortedArray srsa = new SearchInRotatedSortedArray();
        int[] nums = {4,5,6,7,0,1,2};
        System.out.println(srsa.search(nums,7));
    }
}
