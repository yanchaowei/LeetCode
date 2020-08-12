package topInterviewQuestions;

import java.util.ArrayList;

public class _210_Course_Schedule_II {
    int[] track;    // 0: 未访问， 1： 已经访问并且已经加入结果数组， 2：正在访问。
    ArrayList<ArrayList<Integer>> adj;
    int[] res;
    int c;
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        adj = new ArrayList<>();
        for(int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }
        for(int[] pre : prerequisites) {
            adj.get(pre[1]).add(pre[0]);
        }
        track = new int[numCourses];
        c = numCourses;
        res = new int[numCourses];
        for(int i = 0; i < numCourses; i++) {
            if(track[i] == 0 && !dfs(i)) return new int[0];
        }
        return res;
    }
    // 返回 false 表示发现循环
    public boolean dfs(int i) {
        track[i] = -1;
        for(int k : adj.get(i)) {
            if(track[k] == -1) return false;    // 发现循环
            if(track[k] == 0 && !dfs(k)) return false;
        }
        res[--c] = i;
        track[i] = 1;
        return true;
    }
}
