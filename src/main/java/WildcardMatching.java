/**
 * https://leetcode.com/problems/wildcard-matching/
 *
 Implement wildcard pattern matching with support for '?' and '*'.

 '?' Matches any single character.
 '*' Matches any sequence of characters (including the empty sequence).

 The matching should cover the entire input string (not partial).

 The function prototype should be:
 bool isMatch(const char *s, const char *p)

 Some examples:
 isMatch("aa","a") → false
 isMatch("aa","aa") → true
 isMatch("aaa","aa") → false
 isMatch("aa", "*") → true
 isMatch("aa", "a*") → true
 isMatch("ab", "?*") → true
 isMatch("aab", "c*a*b") → false
 *
 * 解题思路：
 * 解法一：动态规划
 * 用一个数组保存带匹配字符串s中每一个字符处当前正确匹配的最远字符
 * 遇到“*”号前向遍历，否则后向遍历
 *
 * 解法二：回溯
 * 用两个变量保存两个回溯点，分别标记s上的指针和p上的指针
 * 每当遇到“*”更新回溯点；
 * 每当无法匹配，即回溯至回溯点，并将s上的回溯点+1；
 * 当最后回溯点无效（超出字符串长度）时失败，最后p中完全匹配s是成功
 *
 * Created by xzbang on 2015/5/26.
 */
public class WildcardMatching {
    public boolean isMatch1(String s, String p) {
        if(s.equals(p))return true;
        if(s.length()==0||p.length()==0)return false;

        int slen = s.length();
        int plen = p.length();
        char[] chars = s.toCharArray();
        char[] charp = p.toCharArray();

        int[] intr = new int[slen];

        for(int k = 0;k < slen;k++){
            intr[k] = -1;
        }

        if(charp[0]=='*'){
            for(int k = 0;k < slen;k++){
                intr[k] = 0;
            }
        }else if(charp[0]=='?'){
            intr[0] = 0;
        }else if(charp[0]==chars[0]){
            intr[0] = 0;
        }else{
            return false;
        }

        for(int i = 1;i < plen;i++){
            if(charp[i]=='*'){
                int start = 0;
                for(int j = 1;j < slen;j++){
                    if(intr[j - 1] == i - 1)
                        start=j;
                }
                for(int k = start;k < slen;k++){
                    intr[k] = i;
                }
            }else {
                for (int j = slen - 1; j > 0; j--) {
                    if (intr[j - 1] == i - 1) {
                        if(charp[i]=='?'){
                            intr[j] = i;
                        }else if(charp[i]==chars[j]){
                            intr[j] = i;
                        }
                    }
                }
            }
        }

        if(intr[slen-1]==plen-1) {
            return true;
        }else
            return false;
    }

    public boolean isMatch(String str, String pattern) {
        int s = 0, p = 0, match = 0, starIdx = -1;
        while (s < str.length()){
            // advancing both pointers
            if (p < pattern.length()  && (pattern.charAt(p) == '?' || str.charAt(s) == pattern.charAt(p))){
                s++;
                p++;
            }
            // * found, only advancing pattern pointer
            else if (p < pattern.length() && pattern.charAt(p) == '*'){
                starIdx = p;
                match = s;
                p++;
            }
            // last pattern pointer was *, advancing string pointer
            else if (starIdx != -1){
                p = starIdx + 1;
                match++;
                s = match;
            }
            //current pattern pointer is not star, last patter pointer was not *
            //characters do not match
            else return false;
        }

        //check for remaining characters in pattern
        while (p < pattern.length() && pattern.charAt(p) == '*')
            p++;

        return p == pattern.length();
    }

    public static void main(String[] args) {
        WildcardMatching wildcardMatching = new WildcardMatching();
//        isMatch("aa","a") → false
//        isMatch("aa","aa") → true
//        isMatch("aaa","aa") → false
//        isMatch("aa", "*") → true
//        isMatch("aa", "a*") → true
//        isMatch("ab", "?*") → true
//        isMatch("aab", "c*a*b") → false
        System.out.println(wildcardMatching.isMatch("aa", "a"));
        System.out.println(wildcardMatching.isMatch("aa","aa"));
        System.out.println(wildcardMatching.isMatch("aaa","aa"));
        System.out.println(wildcardMatching.isMatch("aa","*"));
        System.out.println(wildcardMatching.isMatch("aa","a*"));
        System.out.println(wildcardMatching.isMatch("ab","?*"));
        System.out.println(wildcardMatching.isMatch("aab","c*a*b"));
    }
}
