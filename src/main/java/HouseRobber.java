/**
 *https://leetcode.com/problems/house-robber/
 *
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed,
 * the only constraint stopping you from robbing each of them is that adjacent houses have security system connected
 * and it will automatically contact the police if two adjacent houses were broken into on the same night.

 Given a list of non-negative integers representing the amount of money of each house,
 determine the maximum amount of money you can rob tonight without alerting the police.
 * Created by xzbang on 2015/5/8.
 */
public class HouseRobber {
    public int rob(int[] num) {
        int size = num.length;
        int[] max = new int[size];
        if(size==0){
            return 0;
        }
        else if(size==1){
            max[0]=num[0];
            return num[0];
        }else{
            max[0]=num[0];
            max[1]=Math.max(max[0],num[1]);
        }
        int i=2;
        while(i<size){
            max[i]=Math.max(max[i-1],max[i-2]+num[i]);
            i++;
        }
        return Math.max(max[size-1],max[size-2]);
    }
}
