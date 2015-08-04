package leetcode;

import java.util.*;

/**
 * https://leetcode.com/problems/word-ladder-ii/
 *
 Given two words (start and end), and a dictionary, find all shortest transformation sequence(s) from start to end, such that:

 Only one letter can be changed at a time
 Each intermediate word must exist in the dictionary
 For example,

 Given:
 start = "hit"
 end = "cog"
 dict = ["hot","dot","dog","lot","log"]
 Return
 [
 ["hit","hot","dot","dog","cog"],
 ["hit","hot","lot","log","cog"]
 ]
 Note:
 All words have the same length.
 All words contain only lowercase alphabetic characters.
 *
 * 解题思路：
 * 逐个计算每个词到start的最短距离，并保存每个节点的下一个节点的信息list
 * 用queue保存下一个可以用来搜索路径的节点，知道路径长度大于已知的最小长度
 * 递归输出路径，注意删除新加的节点
 *
 * Created by xzbang on 2015/5/19.
 */
public class WordLadder2 {
    HashMap<String,List<String>> map = new HashMap<String, List<String>>();//存储每个结点可以到的下一个结点
    List<List<String>> results = new ArrayList<List<String>>();
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        if(dict==null||dict.size()==0){
            return results;
        }
        HashMap<String,Integer> dicMap = new HashMap<String,Integer>();//存储每个节点到起点的距离
        Queue<String> queue = new ArrayDeque<String>();//可以用来搜索路径的下一个节点
        dict.add(end);
        for(String d : dict){
            dicMap.put(d,Integer.MAX_VALUE);
        }
        dicMap.put(start,0);
        queue.add(start);
        int minStep = Integer.MAX_VALUE;
        while(!queue.isEmpty()){
            String string = queue.poll();
            int step = dicMap.get(string)+1;
            if(step>minStep)
                break;

            int len = string.length();
            for(int i = 0;i < len;i++) {
                StringBuilder sb = new StringBuilder(string);
                for (char ch = 'a'; ch <= 'z'; ch++) {//替换字符，提高效率
                    sb.setCharAt(i,ch);
                    String new_string = sb.toString();
                    if(string.equals(new_string)||!dicMap.containsKey(new_string))
                        continue;
                    if(step<dicMap.get(new_string)) {
                        queue.add(new_string);
                        dicMap.put(new_string, step);
                    }else if(step>dicMap.get(new_string)){//超出长度的字符跳过，避免加入map中
                        continue;
                    }
                    if(map.containsKey(string)){
                        map.get(string).add(new_string);
                    }else{
                        List<String> dics = new ArrayList<String>();
                        dics.add(new_string);
                        map.put(string,dics);
                    }
                    if(new_string.equals(end))
                        minStep=step;
                }
            }
        }
        List<String> list = new ArrayList<String>();
        getResults(start,end,list);
        return results;
    }

    private void getResults(String start, String end, List<String> list) {
        list.add(start);
        if(start.equals(end)){
            results.add(new ArrayList<String>(list));
        }else{
            List<String> dics = map.get(start);
            if(dics!=null){
                for(String dic:dics){
                    getResults(dic,end,list);
                    list.remove(list.size()-1);//递归结束后，删除添加的字符
                }
            }
        }
    }

    public static void main(String[] args) {
        WordLadder2 wordLadder2 = new WordLadder2();
        String start = "hit";
        String end = "cog";
        String[] d = {"hot","dot","dog","lot","log"};
        Set<String> dict = new HashSet<String>();
        dict.add("hot");
        dict.add("dot");
        dict.add("dog");
        dict.add("lot");
        dict.add("log");
        System.out.println(wordLadder2.findLadders(start,end,dict));
    }
}
