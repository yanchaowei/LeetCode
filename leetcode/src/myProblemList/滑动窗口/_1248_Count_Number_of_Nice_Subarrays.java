package myProblemList.滑动窗口;

public class _1248_Count_Number_of_Nice_Subarrays {
    public int numberOfSubarrays(int[] nums, int k) {
        return atMost(nums, k) - atMost(nums, k - 1);
    }

    public int atMost(int[] nums, int k) {
        int n = nums.length, i = 0, res = 0;
        for(int j = 0; j < n; j++) {
            if((nums[j] & 1) == 1) k--;
            while(k < 0) {
                if((nums[i++] & 1) == 1) k++;
            }
            res += j - i + 1;
        }
        return res;
    }
}
