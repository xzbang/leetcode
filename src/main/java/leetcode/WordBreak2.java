package leetcode;

import java.util.*;

/**
 *https://leetcode.com/problems/word-break-ii/
 *
 Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.

 Return all such possible sentences.

 For example, given
 s = "catsanddog",
 dict = ["cat", "cats", "and", "sand", "dog"].

 A solution is ["cats and dog", "cat sand dog"].
 *
 * 解题思路：动态规划
 * 对s逐个字符分割为s1，s2；
 * s1不在dict中时跳过此分割；否则对s2递归求解
 * 计算过程中保存已经计算过得s对应的list，防止重复计算
 * 最后将子问题结果拼接即为最后答案
 *
 * Created by xzbang on 2015/5/16.
 */
public class WordBreak2 {
    //构造字典，防止重复计算
    private final Map<String, List<String>> cache = new HashMap<String, List<String>>();

    public List<String> wordBreak(String s, Set<String> wordDict) {
        List<String> sentences = new ArrayList<String>();
        if(s==null||s.equals("")||wordDict==null||wordDict.size()==0){
            return sentences;
        }

        if(wordDict.contains(s))//字符本身就在dict中
            sentences.add(s);

        int len = s.length();
        if(len==1){
            return sentences;
        }
        for(int i = 1;i<=len-1;i++){
            String s1 = s.substring(0,i);
            String s2 = s.substring(i);
            if(!wordDict.contains(s1)){
                continue;
            }
            List<String> subSentences= cache.get(s2);
            if(subSentences==null) {
                subSentences = wordBreak(s2, wordDict);
                cache.put(s2,subSentences);
            }
            if(subSentences.size()==0)
                continue;
            for(String ss:subSentences){
                sentences.add(s1+" "+ss);
            }
        }

        return sentences;
    }

    public static void main(String[] args) {
        WordBreak2 wordBreak2 = new WordBreak2();
//        String s = "catsanddog";
        String s = "ab";
        Set<String> dict = new HashSet<String>();//["cat", "cats", "and", "sand", "dog"]
//        dict.add("cat");
//        dict.add("cats");
//        dict.add("and");
//        dict.add("sand");
//        dict.add("dog");
        dict.add("a");
        dict.add("b");
        System.out.println(wordBreak2.wordBreak(s,dict));
    }
}
