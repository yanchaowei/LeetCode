import java.util.HashSet;
import java.util.Set;

public class _41_First_Missing_Positive {
    public int firstMissingPositive(int[] nums) {
        if(nums == null || nums.length == 0) return 1;
        Set<Integer> set = new HashSet<>();
        for(int num : nums) set.add(num);
        int i = 1;
        while(set.contains(i)) i++;
        return i;
    }

    // 方法二
    public int firstMissingPositive2(int[] nums) {
        if(nums == null || nums.length == 0) return 1;
        int i = 0;
        while(i < nums.length) {
            if(nums[i] == i + 1 || nums[i] <= 0 || nums[i] >= nums.length) i++;
            else if(nums[i] != nums[nums[i] - 1]) swap(nums, i, nums[i] - 1);
            else i++;
        }
        i = 0;
        while(i < nums.length && nums[i] == i + 1) i++;
        return i + 1;
    }
    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
// 数组值和下标存在相关性
// 0 1 2 3 4 5     下标
// 1 3 0 2 5 4     数组值

// 每次检查都会把一个值放到他该在的地方
// 3 1 0 2 5 4     1
// 2 1 0 3 5 4     3
// 0 1 2 3 5 4     2
// 0 1 2 3 4 5     4 5
}
