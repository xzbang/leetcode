package leetcode;

/**
 * https://leetcode.com/problems/edit-distance/
 *
 Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2.
 (each operation is counted as 1 step.)

 You have the following 3 operations permitted on a word:

 a) Insert a character
 b) Delete a character
 c) Replace a character
 *
 * 解题思路：动态规划
 * 用一个数组int[][] ed = new int[l1+1][l2+1]存储word1的前i个字符和word2的前j个字符的编辑距离
 * 每次比较时的计算规则：
 * 1、如果word1的第i个字符与word2的第j个字符相等：ed[i][j]=ed[i-1][j-1];
 * 2、如果word1的第i个字符与word2的第j个字符不相等（replace）：ed[i][j]=ed[i-1][j-1]+1;  \
 * 3、如果word1的第i个字符为之前比较完成后多出来的字符（insert）：ed[i][j]=ed[i-1][j]+1; --对这三种情况求最小
 * 4、如果word2的第j个字符为之前比较完成后多出来的字符（delete）：ed[i][j]=ed[i][j-1]+1; /
 *
 * Created by xzbang on 2015/5/25.
 */
public class EditDistance {
    public int minDistance(String word1, String word2) {
        if(word1.equals(null))return 0;
        if(word1.length()==0||word2.length()==0)
            return Math.abs(word1.length()-word2.length());
        int l1 = word1.length(),l2 = word2.length();
        int[][] ed = new int[l1+1][l2+1];//用于存储word1的前i个字符和word2的前j个字符的编辑距离
        //初始化未匹配上的上界
        for(int i = 0;i <= l1;i++)
            ed[i][0] = i;
        for(int j = 0;j <= l2;j++)
            ed[0][j] = j;
        for(int i = 1;i <= l1;i++){
            for(int j = 1;j <= l2;j++){
                if(word1.charAt(i-1)==word2.charAt(j-1))
                    ed[i][j] = ed[i-1][j-1];
                else
                    ed[i][j] = Math.min(ed[i-1][j-1],Math.min(ed[i][j-1],ed[i-1][j]))+1;
            }
        }
        return ed[l1][l2];
    }

    public static void main(String[] args) {
        EditDistance editDistance = new EditDistance();
        String s1 = "abcde";
        String s2 = "acbed";
        System.out.println(editDistance.minDistance(s1,s2));
    }
}
