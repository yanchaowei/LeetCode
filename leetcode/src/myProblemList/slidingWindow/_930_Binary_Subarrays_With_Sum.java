package myProblemList.slidingWindow;

public class _930_Binary_Subarrays_With_Sum {

    // 滑动窗口：和为一定值 / 出现互不相同数字的个数
    public int numSubarraysWithSum(int[] A, int S) {
        return atMost(A, S) - atMost(A, S - 1);
    }

    public int atMost(int[] A, int S) {
        if(S < 0) return 0;
        int i = 0, res = 0;
        for(int j = 0; j < A.length; j++) {
            S -= A[j];
            while(S < 0) {
                S += A[i++];
            }
            res += j - i + 1;
        }
        return res;
    }

    // 方法二： 前缀和。具有通用性。注意：count数组第一个值要为1。
    public int numSubarraysWithSum2(int[] A, int S) {
        int[] count = new int[A.length + 1];
        count[0] = 1;
        int preSum = 0, res = 0;
        for(int num : A) {
            preSum += num;
            if(preSum >= S) {
                res += count[preSum - S];
            }
            count[preSum]++;
        }
        return res;
    }
}
