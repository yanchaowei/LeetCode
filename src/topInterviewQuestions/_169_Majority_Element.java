public class _169_Majority_Element {
    public int majorityElement(int[] nums) {
        int cnt = 0;
        int maxEle = nums[0];
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == maxEle) {
                cnt++;
            }else{
                cnt--;
            }
            if(cnt == 0) {
                cnt = 1;
                maxEle = nums[i];
            }
        }
        cnt = 0;
        for(int ele : nums) {
            if(ele == maxEle) cnt++;
        }
        if(cnt > nums.length/2) return maxEle;
        return -1;
    }
}
