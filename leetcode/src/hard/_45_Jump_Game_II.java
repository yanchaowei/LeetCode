package hard;

import java.util.Arrays;

public class _45_Jump_Game_II {
    // 此方法超时
    int mj;
    public int jump(int[] nums) {
        if(nums == null || nums.length < 2) return 0;
        int n = nums.length;
        mj = nums.length;
        for(int i = 1; i <= nums[0] && i < nums.length; i++) {
            helper(nums, i, 1);
        }
        return mj;
    }
    public void helper(int[] nums, int pos, int jump) {
        if(pos == nums.length - 1) {
            mj = Math.min(mj, jump);
            return;
        }
        for(int i = pos + 1; i <= pos + nums[pos] && i < nums.length; i++) {
            helper(nums, i, jump + 1);
        }
    }

    // 方法二：dp
    public int jump2(int[] nums) {
        int[] cache = new int[nums.length];
        Arrays.fill(cache, Integer.MAX_VALUE);
        cache[0] = 0;

        for (int i = 0; i < nums.length; i++) {
            int reachIndex = i + nums[i];
            for (int j = reachIndex; j > i; j--) {
                if (j >= nums.length) continue;
                cache[j] = Math.min(cache[j], cache[i] + 1);
            }
        }

        return cache[nums.length - 1];
    }

    // 贪心算法，最优解法
    public int jump3(int[] nums) {
        if(nums == null || nums.length < 2) return 0;
        int maxstep = nums[0]; int maxpos = nums[0];
        int jump = 1;
        for(int i = 0; i < nums.length; i++) {
            if(maxstep < i) {
                jump++;
                maxstep = maxpos;
            }
            maxpos = Math.max(maxpos, i + nums[i]);
        }
        return jump;
    }
}
