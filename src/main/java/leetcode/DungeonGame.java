package leetcode;

/**
 * https://leetcode.com/problems/dungeon-game/
 *
 * The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.

 The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.

 Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms; other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).

 In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.


 Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.

 For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.

 -2 (K) 	-3	    3
 -5	        -10	    1
 10	        30	    -5 (P)

 Notes:

 The knight's health has no upper bound.
 Any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.
 *
 * Created by xzbang on 2015/5/4.
 */
public class DungeonGame {
    /**
     * 逆向推导
     * 把每个数值都改为到达当前这一步之后剩余的最少体力值
     * @param dungeon
     * @return
     */
    public int calculateMinimumHP(int[][] dungeon) {
        if(dungeon == null||dungeon[0]==null){
            return 0;
        }
        int len = dungeon.length;
        int size = dungeon[0].length;
        if(dungeon[len-1][size-1] < 0) {
            dungeon[len-1][size-1] = 1 - dungeon[len-1][size-1];
        }else{
            dungeon[len-1][size-1] = 1;
        }

        //计算最后一行
        for(int i = size-2;i>=0;i--){
            dungeon[len-1][i]=dungeon[len-1][i+1]-dungeon[len-1][i];
            if(dungeon[len-1][i] <= 0) {//注意边界条件，王子最少要有一滴血
                dungeon[len-1][i] = 1;
            }
        }

        //计算最后一列
        for(int i = len-2;i>=0;i--){
            dungeon[i][size-1]=dungeon[i+1][size-1]-dungeon[i][size-1];
            if(dungeon[i][size-1] <= 0) {
                dungeon[i][size-1] = 1;
            }
        }

        //依次向上取最优路径
        for(int i=len-2;i>=0;i--){
            for(int j=size-2;j>=0;j--){
                int num1 = dungeon[i][j+1]-dungeon[i][j];
                int num2 = dungeon[i+1][j]-dungeon[i][j];
                dungeon[i][j] = Math.min(num1,num2);
                if(dungeon[i][j] <= 0) {
                    dungeon[i][j] = 1;
                }
            }
        }

        return dungeon[0][0];
    }

    public static void main(String[] args) {
        DungeonGame dungeonGame = new DungeonGame();
        int[] a = {1,   -3,  5,  -7};
        int[] b = {-10, 11, -16, 20};
        int[] c = {23, -30, 14, -5};
//        int[] d = {1,3};
        int[][] t = {a,b,c};
        System.out.println(dungeonGame.calculateMinimumHP(t));
    }
}
