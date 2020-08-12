package topInterviewQuestions;

import java.util.Stack;

public class _84_Largest_Rectangle_in_Histogram {

    // 方法一，遍历

    // 方法二
    public int largestRectangleArea2(int[] heights) {
        if(heights == null || heights.length == 0) return 0;
        Stack<Integer> s = new Stack<>();
        int max = 0;
        int i = 0;
        while(i < heights.length) {
            while(!s.isEmpty() && heights[i] < heights[s.peek()]) {
                max = Math.max(max, heights[s.pop()] * (i - (s.isEmpty() ? 0 : s.peek() + 1)));
            }
            s.push(i++);
        }
        while(!s.isEmpty()) {
            max = Math.max(max, heights[s.pop()] * (heights.length - (s.isEmpty() ? 0 : s.peek() + 1)));
        }
        return max;
    }
}
