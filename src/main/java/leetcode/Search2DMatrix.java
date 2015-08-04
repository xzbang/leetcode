package leetcode;

/**
 * https://leetcode.com/problems/search-a-2d-matrix/
 *
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

 Integers in each row are sorted from left to right.
 The first integer of each row is greater than the last integer of the previous row.
 For example,

 Consider the following matrix:

 [
 [1,   3,  5,  7],
 [10, 11, 16, 20],
 [23, 30, 34, 50]
 ]
 Given target = 3, return true.
 *
 * Created by xzbang on 2015/4/13.
 */
public class Search2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix==null||matrix[0]==null){
            return false;
        }
        int[] ma = null;
        int rowsize = matrix.length;
        int linesize = matrix[0].length;
        for(int i = 0;i < rowsize;i++){
            if(target<=matrix[i][linesize-1]){
                ma=matrix[i];
                break;
            }
        }
        if(ma==null){
            return false;
        }
        rowsize=0;
        linesize = linesize-1;
        while(rowsize<=linesize){
            int index = (rowsize+linesize)/2;
            if(ma[index]==target){
                return true;
            }else if(ma[index]>target){
                linesize=index-1;
            }else{
                rowsize = index+1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Search2DMatrix search2DMatrix = new Search2DMatrix();
//        int[] a = {1,   3,  5,  7};
//        int[] b = {10, 11, 16, 20};
//        int[] c = {23, 30, 34, 50};
        int[] d = {1,3};
        int[][] t = {d};
        System.out.println(search2DMatrix.searchMatrix(t,3));
    }
}
