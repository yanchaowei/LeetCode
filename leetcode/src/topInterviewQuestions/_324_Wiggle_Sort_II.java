package topInterviewQuestions;

import java.util.Arrays;

public class _324_Wiggle_Sort_II {
    public void wiggleSort(int[] nums) {
        if(nums == null || nums.length == 0) return;
        int[] tmp = Arrays.copyOf(nums, nums.length);
        Arrays.sort(tmp);
        int end1 = (tmp.length - 1)/2;
        int end2 = (tmp.length - 1);
        for(int i = 0; i < nums.length; i++) {
            if((i & 1) == 0){
                nums[i] = tmp[end1--];
            }else{
                nums[i] = tmp[end2--];
            }
        }
        return;
    }

    public void wiggleSort2(int[] nums) {
        if(nums == null || nums.length < 2) {
            return;
        }
        int[] copy = Arrays.copyOf(nums, nums.length);

        Arrays.sort(copy);

        int endOf1stHalf = (nums.length - 1) / 2;
        int endOf2ndHalf = nums.length - 1;

        for(int i = 0; i < nums.length; i++) {
            if(i % 2 == 0) { // i = 0, 2, 4, 6...
                nums[i] = copy[endOf1stHalf--];
            } else {
                nums[i] = copy[endOf2ndHalf--];
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,2,1,2,2,1};
        _324_Wiggle_Sort_II wiggle_sort_ii = new _324_Wiggle_Sort_II();
        wiggle_sort_ii.wiggleSort2(nums);
        for(int num : nums) {
            System.out.print(num + " ");

        }
    }
}
