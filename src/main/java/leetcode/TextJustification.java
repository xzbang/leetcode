package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/text-justification/
 *
 Given an array of words and a length L, format the text such that each line has exactly L characters and is fully
 (left and right) justified.

 You should pack your words in a greedy approach; that is, pack as many words as you can in each line.
 Pad extra spaces ' ' when necessary so that each line has exactly L characters.

 Extra spaces between words should be distributed as evenly as possible.
 If the number of spaces on a line do not divide evenly between words,
 the empty slots on the left will be assigned more spaces than the slots on the right.

 For the last line of text, it should be left justified and no extra space is inserted between words.

 For example,
 words: ["This", "is", "an", "example", "of", "text", "justification."]
 L: 16.

 Return the formatted lines as:
 [
 "This    is    an",
 "example  of text",
 "justification.  "
 ]
 Note: Each word is guaranteed not to exceed L in length.

 click to show corner cases.

 Corner Cases:
 A line other than the last line might contain only one word. What should you do in this case?
 In this case, that line should be left-justified.
 *
 * 解题思路：
 * 编码能力，字符串对齐
 *
 * Created by xzbang on 2015/5/25.
 */
public class TextJustification {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> list = new ArrayList<String>();
        if(words==null||words.length==0)
            return list;
        int nowWidth=0,evenSpaceNum=0,spusSpaceNum=0,wordNum=0;
        Queue<String> queue = new ArrayDeque<String>();
        for(String w : words){
            if(nowWidth+w.length()>maxWidth){
                if(wordNum==1){
                    evenSpaceNum = maxWidth-nowWidth+1;
                    String space1 = "";
                    for(int i = 0;i < evenSpaceNum;i++){
                        space1+=" ";
                    }
                    list.add(queue.poll()+space1);
                }else {
                    evenSpaceNum = (maxWidth - nowWidth + wordNum) / (wordNum - 1);
                    spusSpaceNum = (maxWidth - nowWidth + wordNum) % (wordNum - 1);
                    String space1 = "";
                    for (int i = 0; i < evenSpaceNum; i++) {
                        space1 += " ";
                    }
                    String space2 = space1 + " ";
                    StringBuilder newWord = new StringBuilder();
                    while (!queue.isEmpty()) {
                        newWord.append(queue.poll());
                        if (spusSpaceNum != 0) {
                            newWord.append(space2);
                            spusSpaceNum--;
                        } else {
                            newWord.append(space1);
                        }
                    }
                    list.add(newWord.toString().trim());
                }
                nowWidth=0;evenSpaceNum=0;spusSpaceNum=0;wordNum=0;
            }
            queue.add(w);
            nowWidth+=(w.length()+1);
            wordNum++;
        }

        //收尾
        if(wordNum==1){
            evenSpaceNum = maxWidth-nowWidth+1;
            String space1 = "";
            for(int i = 0;i < evenSpaceNum;i++){
                space1+=" ";
            }
            list.add(queue.poll()+space1);
        }else{
            evenSpaceNum = (maxWidth-nowWidth);
            String space1 = "";
            for(int i = 0;i < evenSpaceNum;i++){
                space1+=" ";
            }
            StringBuilder newWord = new StringBuilder();
            while(!queue.isEmpty()){
                newWord.append(queue.poll()).append(" ");
            }
            newWord.append(space1);
            list.add(newWord.toString());
        }

        return list;
    }

    public static void main(String[] args) {
        TextJustification textJustification = new TextJustification();
        String[] words = {"What","must","be","shall","be."};
        System.out.println(textJustification.fullJustify(words,16));
    }
}
