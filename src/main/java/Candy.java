import java.util.Stack;

/**
 * https://leetcode.com/problems/candy/
 *
 There are N children standing in a line. Each child is assigned a rating value.

 You are giving candies to these children subjected to the following requirements:

 Each child must have at least one candy.
 Children with a higher rating get more candies than their neighbors.
 What is the minimum candies you must give?
 *
 * 解题思路：
 * 解法1：
 * 初始化一个长度相同，全为1的数组存储结果
 * 顺序遍历，将升序序列的值设为前一个值加1
 * 逆序遍历，将降序序列的值设为后一个值加1或当前的较大值
 * 累加结果
 *
 * 解法2：
 * 第一个人的糖果数为1
 * 如果序列升序，该人的糖果数为前一个的糖果数加1
 * 如果序列等值，糖果数为1
 * 如果序列降序，存储当次降序序列的长度，降序结束时累加求和将糖果数加回来，并且补足降序序列首部的糖果数
 * 结束时对剩余的降序序列同上操作
 * 原作者解析如下：
 This solution picks each element from the input array only once.
 First, we give a candy to the first child. Then for each child we have three cases:

 -His/her rating is equal to the previous one -> give 1 candy.
 -His/her rating is greater than the previous one -> give him (previous + 1) candies.
 -His/her rating is less than the previous one -> don't know what to do yet,let's just count the number of such consequent cases.
 When we enter 1 or 2 condition we can check our count from 3.
 If it's not zero then we know that we were descending before and we have everything to update our total candies amount:
 number of children in descending sequence of raitings - coundDown,
 number of candies given at peak - prev (we don't update prev when descending).
 Total number of candies for "descending" children can be found through arithmetic progression formula (1+2+...+countDown).
 Plus we need to update our peak child if his number of candies is less then or equal to countDown.
 *
 * Created by xzbang on 2015/5/16.
 */
public class Candy {

    /**
     * 连续的孩子中如果相等的级别得到相同数量的糖果
     * @param ratings
     * @return
     */
    public int candy0(int[] ratings) {
        if(ratings==null||ratings.length==0){
            return 0;
        }
        int len = ratings.length;
        if(len==1){
            return 1;
        }
        Stack<Integer> stack = new Stack<Integer>();//保存非递增序列
        int min = 0;//最少糖果数
        int left = 0;//递增时左边的糖果数

        for(int i=0;i<len;i++){
            if(i==len-1||ratings[i]>=ratings[i+1]){//非递增阶段入栈
                stack.push(i);
                continue;
            }else if(!stack.isEmpty()){//递增且栈内非空，则当前为1并保存，清空栈并递推出栈
                min+=1;
                int value = 1;
                while(stack.size()!=1){
                    int j = stack.pop();
                    if(ratings[j]!=ratings[j+1]) {
                        value++;
                    }
                    min+=value;
                }
                int j = stack.pop();//栈内最后一个数需要与其左边的数进行比较
                if(ratings[j]!=ratings[j+1]) {
                    value++;
                }
                value = (value>=left+1)?value:(left+1);
                min+=value;
                left=1;
            }else{//递增且栈内为空，值比左边多1并保存
                left++;
                min+=left;

            }
        }

        //遍历结束，清空栈,注意最后一个数已入栈
        int value=0;
        while(stack.size()!=1){
            int j = stack.pop();
            if(j==len-1||ratings[j]!=ratings[j+1]) {
                value++;
            }
            min+=value;
        }
        int j = stack.pop();//栈内最后一个数需要与其左边的数进行比较
        if(j==len-1||ratings[j]!=ratings[j+1]) {
            value++;
        }
        value = (value>=left+1)?value:(left+1);
        min+=value;
        return min;
    }

    /**
     * 【原题正解1】
     *
     * @param ratings
     * @return
     */
    public int candy1(int[] ratings) {
        int size=ratings.length;
        if(size<=1)
            return size;
        int[] num = new int[size];
        for(int i = 0; i < size; i++)//初始化
            num[i]=1;
        for (int i = 1; i < size; i++)//解决升序
        {
            if(ratings[i]>ratings[i-1])
                num[i]=num[i-1]+1;
        }
        for (int i= size-1; i>0 ; i--)//解决降序
        {
            if(ratings[i-1]>ratings[i])
                num[i-1]=Math.max(num[i] + 1, num[i - 1]);
        }
        int result=0;
        for (int i = 0; i < size; i++)//组合结果
        {
            result+=num[i];
        }
        return result;
    }

    /**
     * 【原题正解2】
     *
     * @param ratings
     * @return
     */
    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) return 0;
        int total = 1, prev = 1, countDown = 0;
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] >= ratings[i-1]) {
                if (countDown > 0) {
                    total += countDown*(countDown+1)/2; // 对原来降序部分累加求和
                    if (countDown >= prev) total += countDown - prev + 1;//恢复降序时第一个数的值，加至比累减数多1
                    countDown = 0;
                    prev = 1;
                }
                prev = ratings[i] == ratings[i-1] ? 1 : prev+1;
                total += prev;
            } else countDown++;
        }
        if (countDown > 0) { // if we were descending at the end
            total += countDown*(countDown+1)/2;
            if (countDown >= prev) total += countDown - prev + 1;
        }
        return total;
    }

    public static void main(String[] args) {

//        Input:	[2,2,1]
//        Output:	5
//        Expected:	4

        Candy can = new Candy();
        int[] ratings = {1,3,5,5,2,1,2,1,4};
//        int[] ratings = {2,1};
        System.out.println(can.candy(ratings));
    }
}
