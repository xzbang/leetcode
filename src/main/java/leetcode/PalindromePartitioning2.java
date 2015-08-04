package leetcode;

/**
 * https://leetcode.com/problems/palindrome-partitioning-ii/
 *
 Given a string s, partition s such that every substring of the partition is a palindrome.

 Return the minimum cuts needed for a palindrome partitioning of s.

 For example, given s = "aab",
 Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
 *
 * 解题思路：动态规划
 * 设置一个len+1大小的数组，用于存储从尾部到这个字符的最优分割数
 * 一次遍历字符串，然后以i为中心（奇数）或中心左侧（偶数）进行判断，找出最长的对称子串，标记当前串的起始点分割数
 * 第一个字符处的分割数即为所得
 *
 * Created by xzbang on 2015/5/18.
 */
public class PalindromePartitioning2 {
    public int minCut(String s) {
        if(s==null||s.length()==0){
            return 0;
        }
        int len = s.length();
        int[] cut = new int[len+1];
        for(int i = 0;i < len;i++)
            cut[i]=Integer.MAX_VALUE;
        cut[len]=-1;
        for(int i = len-1;i>=0;i--){
            for(int a=i,b=i;a>=0&&b<len&&s.charAt(a)==s.charAt(b);a--,b++)
                cut[a]=Math.min(cut[a],1+cut[b+1]);
            for(int a=i,b=i+1;a>=0&&b<len&&s.charAt(a)==s.charAt(b);a--,b++)
                cut[a]=Math.min(cut[a],1+cut[b+1]);
        }
        return cut[0];
    }

    public static void main(String[] args) {
        PalindromePartitioning2 palindromePartitioning2 = new PalindromePartitioning2();
        String s = "aabaaaba";
        System.out.println(palindromePartitioning2.minCut(s));
    }
}
