/**
 * https://leetcode.com/problems/interleaving-string/
 *
 Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

 For example,
 Given:
 s1 = "aabcc",
 s2 = "dbbca",

 When s3 = "aadbbcbcac", return true.
 When s3 = "aadbbbaccc", return false.
 *
 * 解题思路：动态规划
 * 如果用递归，而不借助数据结构存储，复杂度为m的n次方
 * 分析之后可以发现，有大量重复工作
 * 之后问题就变成了如何去存储中间结果避免重复工作：二维表
 * 对于二分问题，大部分时候可以通过二维表来解决，从而将复杂度降为m*n
 * 一次计算每一项，与前一项或后一项求与后求或，即可得到最终结果
 *
 * Created by xzbang on 2015/5/22.
 */
public class InterleavingString {
    public boolean isInterleave(String s1, String s2, String s3) {
        if((s1.length()+s2.length())!=s3.length())
            return false;
        int m = s1.length(),n=s2.length();
        boolean[][] z = new boolean[m+1][n+1];
        z[0][0]=true;
        for(int i = 1; i <= m; i++)
            z[i][0] = z[i-1][0]&&(s1.charAt(i-1)==s3.charAt(i-1));
        for(int j = 1; j <= n; j++)
            z[0][j] = z[0][j-1]&&(s2.charAt(j-1)==s3.charAt(j-1));
        for(int i = 1; i <= m; i++)
            for(int j = 1; j <= n; j++)
                z[i][j] = (z[i-1][j]&&(s1.charAt(i-1)==s3.charAt(i+j-1)))
                        ||(z[i][j-1]&&(s2.charAt(j-1)==s3.charAt(i+j-1)));
        return z[m][n];
    }

    public static void main(String[] args) {
        InterleavingString interleavingString = new InterleavingString();
        //When s3 = "aadbbcbcac", return true.
        //When s3 = "aadbbbaccc", return false.
        String s1 = "aabcc",s2 = "dbbca",s3 = "aadbbbaccc";
        System.out.println(interleavingString.isInterleave(s1,s2,s3));
    }
}
