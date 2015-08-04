package leetcode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * https://leetcode.com/problems/minimum-window-substring/
 *
 Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

 For example,
 S = "ADOBECODEBANC"
 T = "ABC"
 Minimum window is "BANC".

 Note:
 If there is no such window in S that covers all characters in T, return the emtpy string "".

 If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
 *
 *解题思路：
 * 依次遍历s字符串，当t中的字符全部出现过之后（包括重复），记录当前的偏移量end
 * 然后收缩start，知道不能完全包括t中的字符，计算此过程中的最小子串，若s还未遍历完成，回到上一步
 *
 * 用Queue保存遍历过程中遇到的t中的字符的偏移量，在之后收缩start时可以节约时间
 * 本题默认ascall编码，采用两个128维数组分别保存t中字符出现在s中的次数和t中每个字符当前出现的次数
 * 另外还需要保存t中的字符出现的总次数
 *
 * Created by xzbang on 2015/5/25.
 */
public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        if(s==null||t==null||s.isEmpty()||t.isEmpty()){
            return "";
        }
        int lens = s.length(),lent = t.length();
        int[] count = new int[128];//s每个字符在t中出现的次数
        int[] found = new int[128];//t中每个字符当前出现的次数
        Queue<Integer> index = new ArrayDeque<Integer>();//t中的字符出现的地点
        for(int i = 0;i<lent;i++){
            count[t.charAt(i)]++;
        }
        int hasFound = 0;//当前已经找到了多少个t中的字符
        int min = 0;//最小窗口长度
        int minStart=0,minEnd=Integer.MAX_VALUE;//最小窗口的起始和结尾
        for(int i = 0;i<lens;i++){
            if(count[s.charAt(i)]==0)
                continue;
            found[s.charAt(i)]++;
            index.add(i);
            //注意这一步，只有当前字符还没达到count中大小时，才对hasFound递增，否则只对found数组中相应递增
            if(count[s.charAt(i)]>=found[s.charAt(i)])hasFound++;
            while(hasFound==lent){//根据队列收缩起始位置
                int start = index.poll();
                found[s.charAt(start)]--;
                if(count[s.charAt(start)]>found[s.charAt(start)]){
                    hasFound--;
                    if((minEnd-minStart)>(i-start)){
                        minEnd=i;minStart=start;
                    }
                    break;
                }
            }
        }
        return (minEnd==Integer.MAX_VALUE)?"":s.substring(minStart,minEnd+1);
    }

    public static void main(String[] args) {
//        char ch = 'A';
//        int i = 95;
//        int[] m = new int[3];
//        System.out.println((int)ch);
//        System.out.println((char)i);
//        System.out.println(m[0]);

        MinimumWindowSubstring mws = new MinimumWindowSubstring();
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(mws.minWindow(s,t));
    }
}
