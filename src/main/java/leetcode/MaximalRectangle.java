package leetcode;

/**
 * https://leetcode.com/problems/maximal-rectangle/
 *
 *Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing all ones and return its area.
 *
 * 解题思路：
 * 遍历的过程中我们假设每一个节点都是“支持节点”
 * 即我们认为它的当前高度就是最大面积的高度，那么我们要做的就是计算它的长度
 * 长度分为左边和右边，得到这两个值后就可以相减即可
 * 左边的长度即为它的当前高度出现以来左边出现的最近的0的下一个位置
 * 右边的长度即为它的当前高度出现以来右边出现的最近的0的位置
 * 最后我们的众多假设中，必有一个是真正的支持节点，所有计算每一个的面积取最大值即可
 *
 * 注意：数组判null不行
 *
 * Created by xzbang on 2015/5/22.
 */
public class MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
//        if(matrix==null||matrix[0]==null)return 0;//这个地方判null竟然不行，涨知识
        int rowLen = matrix.length;
        if(rowLen==0)return 0;
        int lineLen = matrix[0].length;
        if(lineLen==0)return 0;
        /**
         * 遍历至今出现的高度、左边最近的0的下一个、右边最近的0 的位置
         */
        int[] height = new int[rowLen],left = new int[rowLen],right = new int[rowLen];
        for(int i = 0 ; i < rowLen ; i++){
            height[i]=0;
            left[i]=0;
            right[i]=rowLen;//注意右边的初值
        }
        int max = 0;
        for(int j = 0 ; j < lineLen ; j++){
            //当前这一行出现的最近的0的位置
            int curLeft=0,curRight=rowLen;
            for(int i = 0 ; i < rowLen ; i++){
                if(matrix[i][j]=='0')
                    height[i]=0;
                else
                    height[i]+=1;
            }
            for(int i = 0;i < rowLen ; i++){
                if(matrix[i][j]=='1'){
                    left[i]=Math.max(left[i],curLeft);
                }else{
                    left[i]=0;
                    curLeft=i+1;
                }
            }
            for(int i = rowLen-1;i>=0;i--){
                if(matrix[i][j]=='1'){
                    right[i] = Math.min(right[i],curRight);
                }else{
                    right[i]=rowLen;
                    curRight=i;
                }
            }
            for(int i = 0;i < rowLen ; i++){
                max = Math.max(max,(right[i]-left[i])*height[i]);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        MaximalRectangle mr = new MaximalRectangle();
//        char[] a = {'1','1','1'};
//        char[] b = {'1','1','1'};
//        char[] c = {'1','1','1'};
//        char[] d = {'1','1','1'};
//        char[][] matrix = {a,b,c,d};
        char[][] matrix = {};
        System.out.println(mr.maximalRectangle(matrix));
    }
}
