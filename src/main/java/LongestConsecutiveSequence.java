import java.util.HashMap;

/**
 * https://leetcode.com/problems/longest-consecutive-sequence/
 *
 Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

 For example,
 Given [100, 4, 200, 1, 3, 2],
 The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

 Your algorithm should run in O(n) complexity.
 *
 * 解题思路：
 * 用hashmap存储每个数的连续长度
 * 利用连续的特性，每次加入一个数时，判断左右两边的数的连续长度并相加，同时更新序列首位
 * 记录每次计算得到的最大连续长度
 *
 * Created by xzbang on 2015/5/19.
 */
public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        HashMap<Integer,Integer> hashMap = new HashMap<Integer, Integer>();
        int lcs = 0;
        for(int n : nums){
            if(hashMap.containsKey(n))
                continue;
            int left = (hashMap.containsKey(n-1))?(hashMap.get(n-1)):0;
            int right = (hashMap.containsKey(n+1))?(hashMap.get(n+1)):0;
            int nlcs = left+right+1;
//            hashMap.put(n,nlcs);
            hashMap.put(n-left,nlcs);
            hashMap.put(n+right,nlcs);
            lcs = Math.max(lcs,nlcs);
        }
        return lcs;
    }

    public static void main(String[] args) {
        LongestConsecutiveSequence longestConsecutiveSequence = new LongestConsecutiveSequence();
        int[] nums = {200,4,100,2,3,1};
        System.out.println(longestConsecutiveSequence.longestConsecutive(nums));
    }
}
