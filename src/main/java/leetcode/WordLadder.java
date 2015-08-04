package leetcode;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * https://leetcode.com/problems/word-ladder/
 *
 * Given two words (beginWord and endWord), and a dictionary, find the length of shortest transformation sequence from beginWord to endWord, such that:

 Only one letter can be changed at a time
 Each intermediate word must exist in the dictionary
 For example,

 Given:
 start = "hit"
 end = "cog"
 dict = ["hot","dot","dog","lot","log"]
 As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 return its length 5.

 Note:
 Return 0 if there is no such transformation sequence.
 All words have the same length.
 All words contain only lowercase alphabetic characters.
 *
 * Created by xzbang on 2015/5/8.
 */
public class WordLadder {
    public int ladderLength(String start, String end, Set<String> dict) {
        if(dict==null||dict.size()==0||start==null||end==null||start.length()!=end.length()||start.length()==0||end.length()==0||start.equals(end)){
            return 0;
        }
        if(getDis(start, end)==1){
            return 2;
        }
        int size = dict.size();
        int len = start.length();
        Set<String> nearStart = new HashSet<String>();
        for(int i = 0;i<26;i++){
            char ch = (char)('a'+i);
            for(int j=0;j<len;j++){
                String nowString = start.substring(0,j)+ch+start.substring(j+1);
                if(dict.contains(nowString)){
                    dict.remove(nowString);
                    nearStart.add(nowString);
                }
            }
        }

        Set<String> nearChild = new HashSet<String>();
        int nowDistance = 1;
        while(nowDistance-1<size&&nearStart.size()!=0){
            Iterator iterator1 = nearStart.iterator();
            while(iterator1.hasNext()){
                String dic = (String)iterator1.next();
                if(getDis(dic,end)==1){
                    return nowDistance+2;
                }else{
                    for(int i = 0;i<26;i++){//只用26个字母变换，减少复杂度
                        char ch = (char)('a'+i);
                        for(int j=0;j<len;j++){
                            String nowString = dic.substring(0,j)+ch+dic.substring(j+1);
                            if(dict.contains(nowString)){
                                dict.remove(nowString);
                                nearChild.add(nowString);
                            }
                        }
                    }
                }
            }
            nowDistance++;
            nearStart=nearChild;
            nearChild=new HashSet<String>();
        }
        return 0;
    }

    private int getDis(String start, String end) {
        int len = start.length();
        int dis = 0;
        for(int i=0;i<len;i++){
            if(start.charAt(i)!=end.charAt(i)){
                dis++;
            }
        }
        return dis;
    }
}
