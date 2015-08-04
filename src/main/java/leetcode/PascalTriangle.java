package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/pascals-triangle/
 *
 * Given numRows, generate the first numRows of Pascal's triangle.

 For example, given numRows = 5,
 Return

 [
 [1],
 [1,1],
 [1,2,1],
 [1,3,3,1],
 [1,4,6,4,1]
 ]
 *
 * Created by xzbang on 2015/4/13.
 */
public class PascalTriangle {
    public List<List<Integer>> generate(int numRows) {
        if(numRows<0){
            return null;
        }
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(numRows==0){
            return result;
        }
        List<Integer> prenode = new ArrayList<Integer>();
        prenode.add(1);
        for(int i=1;i<=numRows;i++){
            if(i==1){
                result.add(prenode);
                continue;
            }
            if(i==2){
                List<Integer> node = new ArrayList<Integer>();
                node.add(1);node.add(1);
                result.add(node);
                prenode=node;
                continue;
            }
            List<Integer> node = new ArrayList<Integer>();
            node.add(1);
//            node.set(i-1,1);
            for(int j=1;j<i-1;j++){
                node.add(prenode.get(j - 1) + prenode.get(j));
//                node.set(i-j-1,prenode.get(i-1)+prenode.get(i));
            }
            node.add(1);
            result.add(node);
            prenode=node;
        }
        return result;
    }

    public static void main(String[] args) {
        PascalTriangle pascalTriangle = new PascalTriangle();
        int n = 5;
        System.out.println(pascalTriangle.generate(n).get(n-1).get(n/2));
    }
}
