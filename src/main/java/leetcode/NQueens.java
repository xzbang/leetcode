package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/n-queens/
 * https://leetcode.com/problems/n-queens-ii/
 *
 *The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.

 Given an integer n, return all distinct solutions to the n-queens puzzle.

 Each solution contains a distinct board configuration of the n-queens' placement,
 where 'Q' and '.' both indicate a queen and an empty space respectively.

 For example,
 There exist two distinct solutions to the 4-queens puzzle:

 [
 [".Q..",  // Solution 1
 "...Q",
 "Q...",
 "..Q."],

 ["..Q.",  // Solution 2
 "Q...",
 "...Q",
 ".Q.."]
 ]
 *
 * 解题思路：
 * 按行计算，保存当前列，两个对角线映射，并设置为true；
 * 以下为标记区分方法：
 * 行：由于按行计算，所以可以默认区分；
 * 列：用一个n维数组标记当前列即可；
 * “/”对角线：i+j为定值，又这个和最多有2n-1个，用2n维的数组映射（hash函数：2n-i-j-1）；
 * “\“对角线：i-j为定值，又这个值最多有2n-1个，用2n维的数组映射（hash函数：n-i+j）；
 * 最后递归遍历即可
 *
 * Created by xzbang on 2015/5/25.
 */
public class NQueens {
    public int totalNQueens(int n) {
        List<String[]> list = solveNQueens(n);
        return list.size();
    }

    public List<String[]> solveNQueens(int n) {
        if(n<=0)return new ArrayList<String[]>();
        List<String[]> list = new ArrayList<String[]>();
        getList(0,new boolean[n],new boolean[2*n],new boolean[2*n],new String[n],list);
        return list;
    }

    private void getList(int line,
                         boolean[] row,
                         boolean[] leftDiagonal,
                         boolean[] rightDiagonal,
                         String[] lineString,
                         List<String[]> list) {
        if(line==lineString.length){
            list.add(lineString.clone());//递归调用过程中的输出值要注意clone
            return;
        }
        int n = lineString.length;
        for(int i = 0;i<n;i++){
            if(row[i]||leftDiagonal[2*n-i-line-1]||rightDiagonal[n-line+i])continue;
            char[] chs = new char[n];
            Arrays.fill(chs,'.');
            chs[i]='Q';
            lineString[line] = new String(chs);
            row[i]=true;leftDiagonal[2*n-i-line-1]=true;rightDiagonal[n-line+i]=true;
            getList(line+1,row,leftDiagonal,rightDiagonal,lineString,list);
            row[i]=false;leftDiagonal[2*n-i-line-1]=false;rightDiagonal[n-line+i]=false;
        }
    }

    public static void main(String[] args) {
        NQueens nQueens = new NQueens();
        List<String[]> list = nQueens.solveNQueens(5);
        for(String[] strings : list){
            for(String s : strings){
                System.out.println(s);
            }
            System.out.println("-----------");
        }
        System.out.println(list.size());
    }
}
