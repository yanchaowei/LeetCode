package myProblemList.slidingWindow;

public class _209_Minimum_Size_Subarray_Sum {
    public int minSubArrayLen(int s, int[] nums) {
        int i = 0;
        int n = nums.length;
        int res = n + 1;
        for(int j = 0; j < n; j++) {
            s -= nums[j];
            while(s <= 0) {
                res = Math.min(res, j - i + 1) ;
                s += nums[i++];
            }
        }
        return res == n + 1 ? 0 : res;
    }

    public int minSubArrayLen2(int s, int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int res = n + 1;
        int[] sums = new int[n + 1];
        for(int i = 0; i < n; i++) sums[i + 1] = sums[i] + nums[i];
        if(sums[n] < s) return 0;
        int l = 0;
        for(int i = 0; i < n + 1; i++) {
            while(l < i && sums[i] - sums[l] >= s) {
                res = Math.min(res, i - l);
                l++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {2,3,1,2,4,3};
        _209_Minimum_Size_Subarray_Sum minimum_size_subarray_sum = new _209_Minimum_Size_Subarray_Sum();
        int i = minimum_size_subarray_sum.minSubArrayLen2(7, nums);
        System.out.println(i);
    }
//    Count Number of Nice Subarrays
//Replace the Substring for Balanced String
//Max Consecutive Ones III
//Binary Subarrays With Sum
//Subarrays with K Different Integers
//Fruit Into Baskets
//Shortest Subarray with Sum at Least K
//Minimum Size Subarray Sum
}
