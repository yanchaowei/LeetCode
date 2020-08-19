package myProblemList.twoPoints;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _15_3Sum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length < 3) return res;
        Arrays.sort(nums);
        int n = nums.length;
        for(int i = 0; i + 2 < n; i++) {
            if(i != 0 && nums[i] == nums[i - 1]) continue;
            int l = i + 1, r = n - 1;
            while(l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if(sum == 0) {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[l]);
                    list.add(nums[r]);
                    res.add(list);
                    l++;
                    while(l < r && nums[l] == nums[l - 1]) l++;
                    r--;
                    while(l < r && nums[r] == nums[r + 1]) r--;
                }else if(sum < 0) l++;
                else r--;
            }
        }
        return res;
    }
}
