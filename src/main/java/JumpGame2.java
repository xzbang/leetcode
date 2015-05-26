/**
 *https://leetcode.com/problems/jump-game-ii/
 *
 Given an array of non-negative integers, you are initially positioned at the first index of the array.

 Each element in the array represents your maximum jump length at that position.

 Your goal is to reach the last index in the minimum number of jumps.

 For example:
 Given array A = [2,3,1,1,4]

 The minimum number of jumps to reach the last index is 2.
 (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
 *
 * 解题思路：贪心算法
 * 在当前跳的最远距离内，都具有相同的最小步长，对于每个点，计算下一个最远距离，当当前最远距离到达时，步长+1，直到队尾。
 *
 * Created by xzbang on 2015/5/26.
 */
public class JumpGame2 {
    public int jump(int[] nums) {
        if(nums==null||nums.length==0){
            return 0;
        }
        int nextMax = 0;
        int max=0;
        int count=0;
        //判定条件：i<=max,避免提前退出，i<nums.length-1，最后一步不用跳
        for(int i = 0 ; i <= max&&i<nums.length-1;i++){
            nextMax = Math.max(nextMax,i+nums[i]);
            if(i==max){
                max=nextMax;
                count++;
            }
        }
        return max >= nums.length-1?count:-1;
    }

    public static void main(String[] args) {
        JumpGame2 jumpGame2 = new JumpGame2();
        int[] nums = {2,3,1,1,4};
        System.out.println(jumpGame2.jump(nums));
    }
}
