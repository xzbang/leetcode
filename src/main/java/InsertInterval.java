import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/insert-interval/
 *
 Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

 You may assume that the intervals were initially sorted according to their start times.

 Example 1:
 Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].

 Example 2:
 Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].

 This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
 *
 * 解题思路：
 * 代码一目了然
 *
 * Created by xzbang on 2015/5/25.
 */
public class InsertInterval {
    //Definition for an interval.
    public class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }

    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> list = new ArrayList<Interval>();
        if(newInterval==null)return intervals;
        if(intervals==null||intervals.size()==0){
            list.add(newInterval);
            return list;
        }
        int i = 0;
        while(i<intervals.size()&&intervals.get(i).end<newInterval.start)
            list.add(intervals.get(i++));
        while(i<intervals.size()&&intervals.get(i).start<=newInterval.end)
            newInterval = new Interval(Math.min(newInterval.start,intervals.get(i).start),
                    Math.max(newInterval.end,intervals.get(i++).end));
        list.add(newInterval);
        while(i<intervals.size())
            list.add(intervals.get(i++));

        return list;
    }

    public static void main(String[] args) {
        InsertInterval insertInterval = new InsertInterval();
        //Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
        InsertInterval.Interval i1 = insertInterval.new Interval(1,2);
        InsertInterval.Interval i2 = insertInterval.new Interval(3,5);
        InsertInterval.Interval i3 = insertInterval.new Interval(6,7);
        InsertInterval.Interval i4 = insertInterval.new Interval(8,10);
        InsertInterval.Interval i5 = insertInterval.new Interval(12,16);
        InsertInterval.Interval ni = insertInterval.new Interval(4,9);
        List<Interval> intervals = new ArrayList<Interval>();
        intervals.add(i1);
        intervals.add(i2);
        intervals.add(i3);
        intervals.add(i4);
        intervals.add(i5);
        List<Interval> list = insertInterval.insert(intervals,ni);
        for(Interval interval : list){
            System.out.print(interval.start);
            System.out.print("    ");
            System.out.println(interval.end);
        }
    }
}
