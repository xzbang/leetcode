import java.util.*;

/**
 * https://leetcode.com/problems/merge-intervals/
 *
 Given a collection of intervals, merge all overlapping intervals.

 For example,
 Given [1,3],[2,6],[8,10],[15,18],
 return [1,6],[8,10],[15,18].
 *
 * 解题思路：重载Collections.sort
 *
 * Created by xzbang on 2015/5/25.
 */
public class MergeIntervals {
    //Definition for an interval.
    public class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }

    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> list = new ArrayList<Interval>();
        if(intervals==null||intervals.size()==0)
            return list;

        //重载list的排序方式
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval i1, Interval i2) {
                return Integer.compare(i1.start, i2.start);
            }
        });

        int start = intervals.get(0).start,end = intervals.get(0).end;
        for(Interval interval : intervals){
            if(interval.start<=end){
                end = Math.max(end,interval.end);
            }else{
                list.add(new Interval(start,end));
                start = interval.start;
                end = interval.end;
            }
        }
        list.add(new Interval(start,end));
        return list;
    }

    public static void main(String[] args) {
        MergeIntervals mergeIntervals = new MergeIntervals();
        MergeIntervals.Interval i1 = mergeIntervals.new Interval(1,2);
        MergeIntervals.Interval i2 = mergeIntervals.new Interval(3,5);
        MergeIntervals.Interval i3 = mergeIntervals.new Interval(2,7);
        MergeIntervals.Interval i4 = mergeIntervals.new Interval(3,10);
        MergeIntervals.Interval i5 = mergeIntervals.new Interval(12,16);
        List<Interval> intervals = new ArrayList<Interval>();
        intervals.add(i1);
        intervals.add(i2);
        intervals.add(i3);
        intervals.add(i4);
        intervals.add(i5);
        List<Interval> list = mergeIntervals.merge(intervals);
        for(Interval interval : list){
            System.out.print(interval.start);
            System.out.print("    ");
            System.out.println(interval.end);
        }
    }
}
