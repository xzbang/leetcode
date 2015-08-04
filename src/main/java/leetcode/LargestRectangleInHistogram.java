package leetcode;

import java.util.Stack;

/**
 * https://leetcode.com/problems/largest-rectangle-in-histogram/
 *
 Given n non-negative integers representing the histogram's bar height where the width of each bar is 1,
 find the area of largest rectangle in the histogram.

 Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
        6
      5---
     --| |
     | | |  3
  2  | | |2---
 ---1| | |-| |
 | |-| | | | |
 | | | | | | |
 -------------
 The largest rectangle is shown in the shaded area, which has area = 10 unit.
        6
      5---
     --|-|
     |/|/|  3
  2  |/|/|2---
 ---1|/|/|-| |
 | |-|/|/| | |
 | | |/|/| | |
 -------------
 For example,
 Given height = [2,1,5,6,2,3],
 return 10.
 *
 * 解题思路：栈
 * 我们假设直方图的每一维都是“支持维”
 * 即每一维的高度都是我们将要得到的最大面积的高度
 * 而我们为了找出每次的左边界和右边界，可以利用栈来进行存储，“高”的维总是存在“低”的维之上
 * 而当出现比栈顶元素小的维时，栈顶的“高”维就到了该结算的时候了
 * 对于众多假设，我们取最大者即为所求
 *
 * 这道题与MatrixRectangle那道题有共通之处，可以互相转化
 *
 * Created by xzbang on 2015/5/24.
 */
public class LargestRectangleInHistogram {
    public int largestRectangleArea(int[] height) {
        if(height==null||height.length==0)return 0;
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(-1);
        int max = 0;
        for(int i = 0;i<height.length;i++){
            while(stack.peek()>-1){
                int top = stack.peek();
                if(height[i]>=height[top]){
                    break;
                }else{
                    stack.pop();
                    //这里先将当前计算的节点出栈，然后用当前节点减去栈顶节点的偏移量再减1
                    //目的是计算低于当前节点的高度的上一个节点的位置，这样可以在直方图处于下降趋势时仍有效
                    //如测试用例：{2,1,2}
                    max = Math.max(max,(i-1-stack.peek())*height[top]);
                }
            }
            stack.push(i);
        }
        while(stack.peek()>-1){
            int top = stack.pop();
            max = Math.max(max,(height.length-1-stack.peek())*height[top]);
        }
        return max;
    }

    public static void main(String[] args) {
        LargestRectangleInHistogram lrih = new LargestRectangleInHistogram();
//        int[] h = {2,1,5,6,2,3};
        int[] h = {2,1,2};
        System.out.println(lrih.largestRectangleArea(h));
    }
}
