package leetcode;

/**
 * https://leetcode.com/problems/distinct-subsequences/
 *
 Given a string S and a string T, count the number of distinct subsequences of T in S.

 A subsequence of a string is a new string which is formed from the original string by deleting some (can be none)
 of the characters without disturbing the relative positions of the remaining characters.
 (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

 Here is an example:
 S = "rabbbit", T = "rabbit"

 Return 3.
 *
 * 解题思路:
 * 建立一个字符串t长度加1的数组，数组中第k个数表示遍历s到目前为止前k个字符的Distinct Subsequences数
 * 数组第0位存储1，表示第一个遍历到一次就累加1
 * s字符串由前往后遍历，保证字符顺序
 * t字符串由后往前，保证后面的字符只能累加前面的字符的出现次数
 * 当遍历到第j个时，就将前j个字符在s中前i个字符的Distinct Subsequences数累加上去
 *
 * Created by xzbang on 2015/5/21.
 */
public class DistinctSubsequences {
    public int numDistinct(String s, String t) {
        int sl = s.length(),tl=t.length();
        int[] tp = new int[tl+1];//数组中第k个数表示遍历s到目前为止前k个字符的Distinct Subsequences数
        tp[0]=1;
        for(int i = 0;i<sl;i++)
            for(int j = tl-1;j>=0;j--)
                if(s.charAt(i)==t.charAt(j))//遍历到第j个，就将前j个字符在s中前i个字符的Distinct Subsequences数累加上去
                    tp[j+1]+=tp[j];
        return tp[tl];
    }

    public static void main(String[] args) {
        DistinctSubsequences distinctSubsequences = new DistinctSubsequences();
        String s = "abccdade";
        String t = "acae";
        System.out.println(distinctSubsequences.numDistinct(s,t));
    }
}
