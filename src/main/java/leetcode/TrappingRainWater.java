package leetcode;

import java.util.Stack;

/**
 * https://leetcode.com/problems/trapping-rain-water/
 *
 Given n non-negative integers representing an elevation map where the width of each bar is 1,
 compute how much water it is able to trap after raining.

 For example,
 Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.

 The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
 In this case, 6 units of rain water (blue section) are being trapped.
 *
 * 解题思路：两种解法
 * 解法一：O(n)时间复杂度，O(n)空间复杂度
 * 用栈存储遍历过程中下降阶段的点的偏移量，当再次处于上升阶段时，则与栈内的点的高度进行比较，加水至较低的一个高度
 *
 * 解法二：O(n)时间复杂度，O(1)空间复杂度
 * 利用两边低中间高的特点，从两边向中间收缩，计算遇到的凹处即可
 * 具体为定义一个平台，他表示当前收缩的海拔高度，此后低于平台的地方都需要蓄水，平台只升不降
 * 通过判断i、j两处的海拔判断平台是否需要提升，计算蓄水量时从更矮的一段计算并将矮的一边往中间收缩
 *
 * Created by xzbang on 2015/5/27.
 */
public class TrappingRainWater {

    /**
     * 解法一
     * 我自己写的AC代码，需要用到栈，额外存储，最坏O(n)空间复杂度
     * @param height
     * @return
     */
    public int trap(int[] height) {
        if(height==null||height.length==0)return 0;
        Stack<Integer> stack = new Stack<Integer>();//存储比当前点高的点的偏移量
        int prev = 0,len=height.length;
        int water = 0;
        for(int i = 0;i<len;i++){
            if(height[i]>=prev) {//当前处于上升阶段
                while(!stack.isEmpty()){//对栈进行判断，如果已经有数据，需要加水
                    int j = stack.peek();
                    if(height[i]<height[j]){//上一个高度比当前高度高
                        water+=((height[i]-prev)*(i-j-1));//加水至当前高度
                        break;
                    }else{//上一个高度不大于当前高度
                        water+=((height[j]-prev)*(i-j-1));//加水至上一个高度
                        stack.pop();//上一个高度出栈
                        prev = height[j];//记录当前水高
                    }
                }
            }else{//当前处于下降阶段
                stack.push(i-1);//将上一个节点入栈
            }
            prev = height[i];
        }

        return water;
    }

    /**
     * 解法二
     * O(n)时间复杂度，O(1)空间复杂度
     * @param A
     * @return
     */
    public int trapNB(int[] A) {
        /**
         * i：从左边开始往右递推标记
         * j：从右边开始往左递推标记
         * plant：当前平台海拔高度，此后低于平台的地方都需要蓄水，平台只升不降
         * result：总蓄水量
         */
        int i = 0, j = A.length - 1, result = 0, plank = 0;
        while(i <= j){
            //通过判断i、j两处的海拔判断平台是否需要提升
            plank = plank < Math.min(A[i], A[j]) ? Math.min(A[i], A[j]) : plank;
            //计算蓄水量，从更矮的一段计算并将矮的一边往中间收缩
            result = A[i] >= A[j] ? result + (plank - A[j--]) : result + (plank - A[i++]);
        }
        return result;
    }

    public static void main(String[] args) {
        TrappingRainWater trappingRainWater = new TrappingRainWater();
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trappingRainWater.trapNB(height));
    }
}
