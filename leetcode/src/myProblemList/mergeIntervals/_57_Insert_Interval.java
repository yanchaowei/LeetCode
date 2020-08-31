package myProblemList.mergeIntervals;

import java.util.ArrayList;
import java.util.List;

public class _57_Insert_Interval {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if(intervals == null || intervals.length == 0) {
            return new int[][]{{newInterval[0], newInterval[1]}};
        }
        if(newInterval == null || newInterval.length == 0) return intervals;
        List<int[]> list = new ArrayList<>();
        int left = intervals[0][0];
        int right = intervals[0][1];
        boolean done = false;
        for(int i = 0; i < intervals.length; i++) {
            if(!done) {
                left = intervals[i][0];
                right = intervals[i][1];
                if(newInterval[1] < left) {
                    list.add(newInterval);
                    done = true;
                }else if(newInterval[0] > right) {
                    list.add(intervals[i]);
                }else{
                    left = Math.min(left, newInterval[0]);
                    right = Math.max(right, newInterval[1]);
                    done = true;
                }
            }else {
                if(right < intervals[i][0]) {
                    list.add(new int[]{left, right});
                    left = intervals[i][0];
                    right = intervals[i][1];
                }else {
                    right = Math.max(right, intervals[i][1]);
                }
            }
        }
        if(done) {
            list.add(new int[]{left, right});
        }else {
            list.add(newInterval);
        }

        int[][] res = new int[list.size()][2];
        for(int i = 0; i < list.size(); i++) {
            res[i][0] = list.get(i)[0];
            res[i][1] = list.get(i)[1];
        }
        return res;
    }
}
