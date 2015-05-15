import java.util.HashMap;

/**
 * https://leetcode.com/problems/max-points-on-a-line/
 *
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 *
 * 解题思路：以一个点为基准，计算每两点间的斜率，统计同一斜率上的点个数
 * 注意统计相同的点个数,正零和负零有区别
 *
 * Created by xzbang on 2015/5/14.
 */
public class MaxPointsOnALine {

    public int maxPoints(Point[] points) {
        if(points==null||points.length==0)
            return 0;
        int size = points.length;
        int result = 0;
        for(int i = 0;i<size;i++){
            int samePoint = 1;
            HashMap<Double,Integer> slope = new HashMap<Double, Integer>();//需要区分正零和负零
            slope.put(Double.MAX_VALUE,0);
            for(int j=i+1;j<size;j++){
                if(points[i].x==points[j].x&&points[i].y==points[j].y)
                    samePoint++;
                else if(points[i].x==points[j].x)
                    slope.put(Double.MAX_VALUE,slope.get(Double.MAX_VALUE)+1);
                else{
                    double sl = (points[i].y-points[j].y)*1.0/(points[i].x-points[j].x);
                    if(sl==0){//0.0;  -0,0
                        sl=0.0;
                    }
                    if(slope.containsKey(sl))
                        slope.put(sl,slope.get(sl)+1);
                    else
                        slope.put(sl,1);
                }
            }
            int localMax = 0;
            for(double sl:slope.keySet())
                localMax = slope.get(sl)>localMax?slope.get(sl):localMax;
            localMax += samePoint;
            result = (localMax>result)?localMax:result;
        }
        return result;
    }

    public static void main(String[] args) {
        MaxPointsOnALine maxPointsOnALine = new MaxPointsOnALine();
        Point p1 = new Point(2,3);
        Point p2 = new Point(3,3);
        Point p3 = new Point(-5,3);
        Point[] points = {p1,p2,p3};
        System.out.println(maxPointsOnALine.maxPoints(points));
    }
}

//Definition for a point.
class Point {
    int x;
    int y;
    Point() { x = 0; y = 0; }
    Point(int a, int b) { x = a; y = b; }
}