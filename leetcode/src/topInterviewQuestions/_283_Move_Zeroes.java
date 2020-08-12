package topInterviewQuestions;

public class _283_Move_Zeroes {
    public void moveZeroes(int[] nums) {
        if(nums == null || nums.length == 0) return;
        int wp = 0, rp = 0;
        while(rp < nums.length) {
            if(nums[rp] != 0) {
                nums[wp] = nums[rp];
                wp++;
            }
            rp++;
        }
        while(wp < nums.length) {
            nums[wp++] = 0;
        }
        return;
    }
}
