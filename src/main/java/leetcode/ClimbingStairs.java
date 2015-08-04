package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * You are climbing a stair case. It takes n steps to reach to the top.

 Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

 * Created by xzbang on 2015/4/13.
 */
public class ClimbingStairs {
    Map<Integer,Integer> result = new HashMap<Integer, Integer>();
    public int climbStairs(int n) {
        if(n<=0){
            return 0;
        }
        if(n==1){
            return 1;
        }
        if(n==2){
            return 2;
        }
        if(result.containsKey(n)) {
            return result.get(n);
        }
        int m = climbStairs(n-1)+climbStairs(n-2);
        result.put(n,m);
        return m;
    }

    public static void main(String[] args) {
        ClimbingStairs cstarts = new ClimbingStairs();
        int n = 5;
        System.out.println(cstarts.climbStairs(n));
    }
}
