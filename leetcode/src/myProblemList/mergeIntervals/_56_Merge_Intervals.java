package myProblemList.mergeIntervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _56_Merge_Intervals {
    public int[][] merge(int[][] intervals) {
        if(intervals == null || intervals.length == 0) return new int[0][0];
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        int left = intervals[0][0];
        int right = intervals[0][1];
        for(int i = 1; i < intervals.length; i++) {
            if(right < intervals[i][0]) {
                List<Integer> list = new ArrayList<>();
                list.add(left);
                list.add(right);
                res.add(list);
                left = intervals[i][0];
                right = intervals[i][1];
            }else {
                right = Math.max(right, intervals[i][1]);
            }
        }
        List<Integer> list = new ArrayList<>();
        list.add(left);
        list.add(right);
        res.add(list);
        int[][] ans = new int[res.size()][2];
        for(int i = 0; i < res.size(); i++) {
            ans[i][0] = res.get(i).get(0);
            ans[i][1] = res.get(i).get(1);
        }
        return ans;
    }
}
