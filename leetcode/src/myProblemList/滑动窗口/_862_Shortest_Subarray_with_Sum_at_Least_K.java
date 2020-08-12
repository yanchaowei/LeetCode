package myProblemList.滑动窗口;

import java.util.ArrayDeque;
import java.util.Deque;

public class _862_Shortest_Subarray_with_Sum_at_Least_K {
    public int shortestSubarray(int[] nums, int s) {
        if(nums == null || nums.length == 0) return 0;
        int l = 0, r = 0;
        int sum = 0;
        int res = Integer.MAX_VALUE;
        while(r < nums.length) {
            sum += nums[r];
            if(sum <= 0) {
                r++;
                l = r;
                sum = 0;
                continue;
            }
            while(l <= r && sum >= s) {
                res = Math.min(res, r - l + 1);
                sum -= nums[l];
                l++;
                while(l < nums.length && nums[l] <= 0) {
                    sum -= nums[l];
                    l++;
                }
            }
            r++;
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    // 方法二
    public int shortestSubarray2(int[] nums, int s) {
        if(nums == null || nums.length == 0) return 0;
        int n = nums.length, res = n + 1;
        int[] sums = new int[n + 1];
        for(int i = 0; i < n; i++) sums[i + 1] = sums[i] + nums[i];
        Deque<Integer> q = new ArrayDeque<>();
        for(int i = 0; i < n + 1; i++) {
            while(q.size() > 0 && sums[i] - sums[q.getFirst()] >= s)
                res = Math.min(res, i - q.pollFirst());
            while(q.size() > 0 && sums[i] <= sums[q.getLast()])
                q.pollLast();
            q.addLast(i);
        }
        return res == n + 1 ? -1 : res;
    }

    public static void main(String[] args) {
        int[] nums = {1};
        _862_Shortest_Subarray_with_Sum_at_Least_K shortest_subarray_with_sum_at_least_k = new _862_Shortest_Subarray_with_Sum_at_Least_K();
        int i = shortest_subarray_with_sum_at_least_k.shortestSubarray2(nums, 1);
        System.out.println(i);
    }
}
