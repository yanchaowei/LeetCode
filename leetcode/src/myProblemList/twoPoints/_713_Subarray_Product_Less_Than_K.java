package myProblemList.twoPoints;

public class _713_Subarray_Product_Less_Than_K {
    // 虽然划在了双指针类型之中，但感觉划在滑动窗口类型也许更合适
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if(k <= 1) return 0;
        int res = 0;
        int i, j, prod = 1;
        for(i = 0, j = 0; j < nums.length; j++) {
            prod = prod * nums[j];
            while(prod >= k) {
                prod = prod/nums[i++];
            }
            res += j - i + 1;
        }
        return res;
    }
}
