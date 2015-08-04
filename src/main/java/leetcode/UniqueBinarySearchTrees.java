package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/unique-binary-search-trees/
 *
 Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

 For example,
 Given n = 3, there are a total of 5 unique BST's.

 1         3     3      2      1
  \       /     /      / \      \
   3     2     1      1   3      2
  /     /       \                 \
 2     1         2                 3
 *
 * Created by xzbang on 2015/4/13.
 */
public class UniqueBinarySearchTrees {
    Map<Integer,Integer> sums = new HashMap<Integer, Integer>();//保存每次的记录，避免重复计算
    public int numTrees(int n) {
        if(n<1) {
            return 0;
        }else if(n==1){
            return 1;
        }
        if(sums.containsKey(n)){
            return sums.get(n);
        }
        int sum = 0;
        //每种根节点的每个子树的组合方式之积之和
        for(int i=1;i<=n;i++){
            if(i==1||i==n){
                sum+=numTrees(n-1);
            }else{
                sum+=(numTrees(i-1)*numTrees(n-i));
            }
        }
        sums.put(n,sum);
        return sum;
    }

    public static void main(String[] args) {
        UniqueBinarySearchTrees uniqueBinarySearchTrees = new UniqueBinarySearchTrees();
        int n = 4;
        System.out.println(uniqueBinarySearchTrees.numTrees(n));
    }
}
