package myProblemList.slidingWindow;

public class _1004_Max_Consecutive_Ones_III {
    //    翻译：找出仅含有K个 0 的最长子数组
    //    Input: A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
    //    Output: 6
    // 滑动的这个窗口在符合要求时始终增大，当不符合要求时，i 和 j 同步向前，也就是不增大也不缩小，可以想成时保留最后一次符合要求的状态，当遇到 0，
    // K++，窗口有机会重新回到符合要求当状态。最后 j 走到 数组外，循环结束。窗口长度为 j - i + 1, 但是因为始终多出一个不符合要求的单位（多一个 0， 或者最后 越界一位）所以是 j - i。
    public int longestOnes(int[] A, int K) {
        int i = 0, j = 0, res = 0;
        for(; j < A.length; j++) {
            if(A[j] == 0) K--;
            if(K < 0 && A[i++] == 0) K++;
        }
        return j - i;
    }
}
