/**
 * Given an unsorted array, find the maximum difference between the successive elements in its sorted form.

 Try to solve it in linear time/space.

 Return 0 if the array contains less than 2 elements.

 You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
 *
 * 解题思路：最大间隙不小于gap=（最大值-最小值）/总数n，
 * 按gap设置n个桶，将所有数字映射到极大桶和极小桶，极大桶中的最小数减去极小桶中的最大数的最大值即为所求
 *
 * Created by xzbang on 2015/5/4.
 */
public class MaximumGap {
    public int maximumGap(int[] nums) {
        if(nums==null||nums.length<2){
            return 0;
        }
        int max = nums[0],min = nums[0]; //最大值，最小值
        double gap=0;//平均间隙
        int size = nums.length;
        for(int num : nums){
            if(num>max){
                max=num;
            }
            if(num<min){
                min=num;
            }
        }
        gap = (max-min)*1.0/size*1.0;

        int[] maximum = new int[size+1];//极大桶
        int[] minimum = new int[size+1];//极小桶
        for(int num : nums){
            int index = (int)((num-min)/gap);
            if(minimum[index]==0){
                minimum[index]=num;
                maximum[index]=num;
                continue;
            }
            if(num<minimum[index]){
                minimum[index]=num;
            }
            if(num>maximum[index]){
                maximum[index]=num;
            }
        }

        int maxGap = 0;
        min = minimum[0];//当前极小桶中的极小值
        max = maximum[0];//当前极大桶中的极大值
        for(int i = 1;i<=size;i++){
            if(minimum[i]==0){
                continue;
            }
            min = minimum[i];
            maxGap = ((maxGap>(min-max))?maxGap:(min-max));
            max = maximum[i];
        }
        return maxGap;
    }

    public static void main(String[] args) {
        MaximumGap maximumGap = new MaximumGap();
        int[] nums = {100,3,2,1};
        System.out.println(maximumGap.maximumGap(nums));
    }
}
