package myProblemList.twoPoints;

import java.util.Arrays;

public class _16_3Sum_Closest {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int first = -1, second = -1, third = -1;
        int n = nums.length;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i + 2 < n; i++) {
            int j = i + 1, k = n - 1;
            while(j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if(Math.abs(target - sum) < min) {
                    min = Math.abs(target - sum);
                    first = i;
                    second = j;
                    third = k;
                }else if(sum < target) j++;
                else k--;
            }
        }
        return nums[first] + nums[second] + nums[third];
    }
}

