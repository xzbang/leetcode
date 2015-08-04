package leetcode;

/**
 * https://leetcode.com/problems/first-missing-positive/
 *
 Given an unsorted integer array, find the first missing positive integer.

 For example,
 Given [1,2,0] return 3,
 and [3,4,-1,1] return 2.

 Your algorithm should run in O(n) time and uses constant space.
 *
 * 解题思路：
 * 两种解法思路类似，但解法一不需要遍历第二遍，更有创意
 * 解法一：
 * 设置首尾标识，每次发现当前不在正确位置的数则将其交换到正确排序位置
 * 发现相同项或者超出数组范围的项时，用后一个数进行替换，并且end--，表示可表示范围减1
 * 无须遍历第二遍
 * 解法二：
 * 把每个数字都交换到它数值对应的位置，完成后遍历，第一个不是想要数字的位置偏移量+1即为所求
 *
 * Created by xzbang on 2015/5/27.
 */
public class FirstMissingPositive {
    /**
     * 解法一
     * 设置首尾标识，每次发现当前不在正确位置的数则将其交换到正确排序位置
     * 发现相同项或者超出数组范围的项时，用后一个数进行替换，并且end--，表示可表示范围减1
     * 无须遍历第二遍
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int index = nums[start] - 1;
            if (index == start)
                start++;
                //发现相同项或者超出数组范围的项时，用后一个数进行替换，并且end--，表示可表示范围减1
            else if (index < 0 || index > end || nums[start] == nums[index])
                nums[start] = nums[end--];
            else {
                //交换两个数值，使其处于正确排序的位置
                nums[start] = nums[index];
                nums[index] = index + 1;
            }
        }
        return start + 1;
    }

    /**
     * 解法二
     * 把每个数字都交换到它数值对应的位置，完成后遍历，第一个不是想要数字的位置偏移量+1即为所求
     * @param nums
     * @return
     */
    public int firstMissingPositive2(int[] nums) {
        int tmp=0;
        for(int i = 0;i<nums.length;i++)
            while(nums[i]>0&&nums[i]<=nums.length&&nums[i]!=nums[nums[i]-1]) {
                tmp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i]=tmp;
            }
        for(int i = 0;i < nums.length;i++)
            if(i!=nums[i]-1)return i+1;
        return nums.length+1;
    }

    public static void main(String[] args) {
        FirstMissingPositive fmp = new FirstMissingPositive();
        int[] nums = {3,4,-1,1};
        System.out.println(fmp.firstMissingPositive2(nums));
    }
}
