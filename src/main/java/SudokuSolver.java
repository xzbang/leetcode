/**
 * https://leetcode.com/problems/sudoku-solver/
 *
 Write a program to solve a Sudoku puzzle by filling the empty cells.

 Empty cells are indicated by the character '.'.

 You may assume that there will be only one unique solution.

 Example:

 5 3   |   7   |
 6     | 1 9 5 |
   9 8 |       |   6
 - - - + - - - + - - -
 8     |   6   |     3
 4     | 8   3 |     1
 7     |   2   |     6
 - - - + - - - + - - -
   6   |       | 2 8
       | 4 1 9 |     5
       |   8   |   7 9

 A sudoku puzzle...

 5 3 4 | 6 7 8 | 9 1 2
 6 7 2 | 1 9 5 | 3 4 8
 1 9 8 | 3 4 2 | 5 6 7
 - - - + - - - + - - -
 8 5 9 | 7 6 1 | 4 2 3
 4 2 6 | 8 5 3 | 7 9 1
 7 1 3 | 9 2 4 | 8 5 6
 - - - + - - - + - - -
 9 6 1 | 5 3 7 | 2 8 4
 2 8 7 | 4 1 9 | 6 3 5
 3 4 5 | 2 8 6 | 1 7 9

 *
 * 解题思路：回溯
 * 挨个寻找空位，然后将1-9挨个代入测试，如果行、列、9宫格内都没有相同的，递归
 *
 * Created by xzbang on 2015/5/28.
 */
public class SudokuSolver {
    public void solveSudoku(char[][] board) {
        if(board==null||board.length!=9||board[0].length!=9)return;
        solve(board);
    }

    private boolean solve(char[][] board){
        for(int i = 0;i < 9;i++){
            for(int j = 0;j < 9;j++){
                if(board[i][j] == '.'){
                    for(char k = '1';k <= '9';k++){
                        if(accept(board,i,j,k)){
                            board[i][j]=k;
                            if(solve(board))
                                return true;
                            else
                                board[i][j]='.';
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean accept(char[][] board, int i, int j, char k) {
        for(int r = 0;r < 9;r++){
            if(board[i][r] == k || board[r][j] == k)
                return false;
        }
        for(int m = (i/3)*3;m < (i/3)*3+3;m++) {
            for (int n = (j / 3) * 3; n < (j / 3) * 3 + 3; n++) {
                if(board[m][n] == k)
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        SudokuSolver sudokuSolver = new SudokuSolver();
        char[] l1 = "53..7....".toCharArray();
        char[] l2 = "6..195...".toCharArray();
        char[] l3 = ".98....6.".toCharArray();
        char[] l4 = "8...6...3".toCharArray();
        char[] l5 = "4..8.3..1".toCharArray();
        char[] l6 = "7...2...6".toCharArray();
        char[] l7 = ".6....28.".toCharArray();
        char[] l8 = "...419..5".toCharArray();
        char[] l9 = "....8..79".toCharArray();
        char[][] board = {l1,l2,l3,l4,l5,l6,l7,l8,l9};
        sudokuSolver.solveSudoku(board);
        for(int i = 0;i < 9;i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }
}
