package topInterviewQuestions;

public class _334_Increasing_Triplet_Subsequence {
    public boolean increasingTriplet(int[] nums) {
        if(nums == null || nums.length < 3) return false;
        int first = 0, second = -1, p = 1;
        while(p < nums.length) {
            if(nums[p] < nums[first]) first = p;
            if(nums[p] > nums[first] && (second == -1 || nums[p] < nums[second])) second = p;
            if(second != -1 && nums[p] > nums[second]) return true;
            p++;
        }
        return false;
    }

    public boolean increasingTriplet2(int[] nums) {
        if(nums == null || nums.length < 3) return false;
        int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
        for(int num : nums) {
            if(num < first) first = num;
            if(num > first) second = Math.min(second, num);
            if(num > second) return true;
        }
        return false;
    }
}
