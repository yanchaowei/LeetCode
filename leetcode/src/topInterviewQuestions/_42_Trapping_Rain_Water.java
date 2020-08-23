package topInterviewQuestions;

import java.util.Stack;

public class _42_Trapping_Rain_Water {
    // 双指针解法，没想明白
    public int trap3(int[] height) {
        int maxLeft = 0, maxRight = 0;
        int left = 1, right = height.length - 2, res = 0;
        while(left <= right) {
            if(height[left - 1] < height[right + 1]) {
                maxLeft = Math.max(maxLeft, height[left - 1]);
                int min = maxLeft;
                if(min > height[left]) res = res + min - height[left];
                left++;
            }else {
                maxRight = Math.max(maxRight, height[right + 1]);
                int min = maxRight;
                if(min > height[right]) res = res + min - height[right];
                right--;
            }
        }
        return res;
    }

    public int trap(int[] height) {
        if(height == null || height.length == 0) return 0;
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for(int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int bottom = stack.pop();
                if(stack.isEmpty()) break;
                int left = stack.peek();
                int s = (i - left - 1) * (Math.min(height[i], height[left]) - height[bottom]);
                res += s;
            }
            stack.push(i);
        }
        return res;
    }

    // 方法er
    public int trap2(int[] height) {
        int res = 0;
        int[] lm = new int[height.length], rm = new int[height.length];
        int maxFromLeft = 0;
        int maxFromRight = 0;
        for(int i = 0; i < height.length; i++) {
            if(height[i] > maxFromLeft) maxFromLeft = height[i];
            if(height[height.length - 1 - i] > maxFromRight) maxFromRight = height[height.length - 1 - i];
            lm[i] = maxFromLeft; rm[height.length - 1 - i] = maxFromRight;
        }
        for(int i = 0; i < height.length; i++) {
            res = Math.min(lm[i], rm[i]) - height[i] > 0 ? res + Math.min(lm[i], rm[i]) - height[i] : res;
        }
        return res;
    }
}
