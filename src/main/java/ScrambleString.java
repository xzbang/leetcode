import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/scramble-string/
 *
 Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.

 Below is one possible representation of s1 = "great":

        great
       /    \
      gr    eat
     / \    /  \
    g   r  e   at
              / \
             a   t
 To scramble the string, we may choose any non-leaf node and swap its two children.

 For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".

        rgeat
       /    \
      rg    eat
     / \    /  \
    r   g  e   at
              / \
             a   t
 We say that "rgeat" is a scrambled string of "great".

 Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".

        rgtae
       /    \
      rg    tae
     / \    /  \
    r   g  ta  e
          / \
         t   a
 We say that "rgtae" is a scrambled string of "great".

 Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
 *
 * 解题思路：动态规划
 * Arrays的方法不错
 * subString的边界条件需要注意
 * 思想为递归非平均二分，可否更简单或效率更高？
 *
 * 可行思路：空间换时间
 * 将每次比较过的两个字符串按一定格式组合后存入hashmap，value为其判断结果
 * 每次判断之前先查表
 *
 * Created by xzbang on 2015/5/22.
 */
public class ScrambleString {

    /**
     *二分时，并且如果字符串为奇数时左边比右边少1时，有效
     * @param s1
     * @param s2
     * @return
     */
    public boolean isScramble_HalfDivision(String s1, String s2) {
        if(s1.length()!=s2.length())return false;
        if(s1.length()==1) {
            if (s1.equals(s2))
                return true;
            else
                return false;
        }
        Set<String> chs = new HashSet<String>();
        int len = s1.length();
        for(int i = 0;i < len/2;i++)
            chs.add(String.valueOf(s1.charAt(i)));
        boolean resultTag = false;
        boolean tag = true;
        for(int i = 0;i < len/2;i++)
            if(!chs.contains(String.valueOf(s2.charAt(i)))) {
                tag = false;
                break;
            }
        if(tag==true){
            resultTag = resultTag||(isScramble(s1.substring(0,len/2),s2.substring(0,len/2))&&isScramble(s1.substring(len/2),s2.substring(len/2)));
        }

        tag = true;
        for(int i = len-len/2;i < len;i++)
            if(!chs.contains(String.valueOf(s2.charAt(i)))) {
                tag = false;
                break;
            }
        if(tag==true){
            int mid = len/2+1;
            if((len/2)*2==len) mid=len/2;//奇数长度和偶数长度两边子串长度不一样
            resultTag = resultTag||(isScramble(s1.substring(0,len/2),s2.substring(mid))&&isScramble(s1.substring(len/2),s2.substring(0,mid)));
        }
        return resultTag;
    }

    /**
     * 【原题正解】
     * 乱切，但切完后子树就确定了
     * @param s1
     * @param s2
     * @return
     */
    public boolean isScramble(String s1, String s2) {
        if(s1.length()!=s2.length())return false;
        if(s1.equals(s2)) return true;
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        Arrays.sort(c1);//这些Arrays的方法不错，之前竟然没有用过
        Arrays.sort(c2);
        if(!Arrays.equals(c1,c2))return false;
        int len = s1.length();
        if(len<=3) return true;
        for(int i = 1;i<len;i++) {//此处应该是i<len,substring(0，i)的结果不包含第i个字符
            if ((isScramble(s1.substring(0, i), s2.substring(0, i))
                    && isScramble(s1.substring(i), s2.substring(i))))
                return true;
            if((isScramble(s1.substring(0, i), s2.substring(len - i))
                    && isScramble(s1.substring(i), s2.substring(0, len - i))))
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        ScrambleString scrambleString = new ScrambleString();
        String s2 = "tqxpxeknttgwoppemjkivrulaflayn";
        String s1 = "afaylnlurvikjmeppowgttnkexpxqt";
//        Set<String> chs = new HashSet<String>();
//        chs.add("a");
//        System.out.println(chs.contains("a"));
        System.out.println(scrambleString.isScramble(s1, s2));
    }
}
