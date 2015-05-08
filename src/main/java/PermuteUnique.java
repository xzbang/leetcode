import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 * For example,
 * [1,1,2] have the following unique permutations:
 * [1,1,2], [1,2,1], and [2,1,1].
 *
 *
 * 算法思想：
 * 首先对list排序，然后从第一个数到最后一个数依次与其他不同的数进行交换，每次交换之后都要对剩下的数进行排序，以递归调用
 *
 * Created by xzbang on 2015/4/7.
 */
public class PermuteUnique {
    public List<List<Integer>> permuteUnique(int[] num) {
        List<Integer> origlist = new ArrayList<Integer>();
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(num==null){
            return null;
        }
        //归并排序，避免同一次递归中和两个相同的数交换而得到相同的结果
        num = mergeSort(num);
        for(int i : num){
            origlist.add(i);
        }

        int size = origlist.size();
//        result.add(origlist);
        findNewResult(0, size, origlist, result);

        return result;
    }

    public static int[] mergeSort(int[] arr){//归并排序 --递归
        if(arr.length==1){
            return arr;
        }
        int half = arr.length/2;
        int[] arr1 = new int[half];
        int[] arr2 = new int[arr.length-half];
        System.arraycopy(arr, 0, arr1, 0, arr1.length);
        System.arraycopy(arr, half, arr2, 0, arr2.length);
        arr1 = mergeSort(arr1);
        arr2 = mergeSort(arr2);
        return mergeSortSub(arr1,arr2);
    }

    private static int[] mergeSortSub(int[] arr1,int[] arr2){//归并排序子程序
        int[] result = new int[arr1.length+arr2.length];
        int i = 0;
        int j = 0;
        int k = 0;
        while(true){
            if(arr1[i] < arr2[j]){
                result[k] = arr1[i];
                if(++i>arr1.length-1){
                    break;
                }
            }else{
                result[k] = arr2[j];
                if(++j>arr2.length-1){
                    break;
                }
            }
            k++;
        }
        for(;i<arr1.length;i++){
            result[++k] = arr1[i];
        }
        for(;j<arr2.length;j++){
            result[++k] = arr2[j];
        }
        return result;
    }

    private void findNewResult(int i, int j, List<Integer> origlist, List<List<Integer>> result) {

        if (i == j) {
            result.add(origlist);
            return;
        }
        int[] newNum = new int[j-i];
        for(int r=i;r<j;r++){
            newNum[r-i] = origlist.get(r);
        }
        newNum = mergeSort(newNum);//再次排序
        for(int r=i;r<j;r++){
            origlist.set(r,newNum[r-i]);
        }

        int s = origlist.get(i);
        int b = s;
        findNewResult(i+1,j,origlist,result);
        for(int k=i+1;k<j;k++){
            if(origlist.get(k)==s||origlist.get(k)==b){
                continue;
            }
            b=origlist.get(k);
            List<Integer> newList = new ArrayList<Integer>();
            newList.addAll(origlist);
            newList.set(i,b);//换完之后又会出现同样的序列
            newList.set(k,s);
            findNewResult(i+1,j,newList,result);
        }
    }

    public static void main(String[] args) {
//        int[] num = {-1,2,0,-1,1,0,1};
        int[] num = {1,2,3};
        PermuteUnique solution = new PermuteUnique();
        List<List<Integer>> result = solution.permuteUnique(num);
        for(List<Integer> list : result){
            for(int i : list){
                System.out.print(i);
            }
            System.out.println();
        }
    }
}
