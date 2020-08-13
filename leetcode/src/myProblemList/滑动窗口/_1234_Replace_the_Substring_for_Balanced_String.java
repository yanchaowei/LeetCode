package myProblemList.滑动窗口;

public class _1234_Replace_the_Substring_for_Balanced_String {
    public int balancedString(String s) {
        int n = s.length(), k = n/4, i = 0, res = n;
        int[] count = new int[128];
        for(int j = 0; j < n; j++) {
            count[s.charAt(j)]++;
        }
        for(int j = 0; j < n; j++) {
            count[s.charAt(j)]--;
            while(i < n && count['Q'] <= k && count['W'] <= k && count['E'] <= k && count['R'] <= k) {
                res = Math.min(res, j - i + 1);
                count[s.charAt(i++)]++;
            }
        }
        return res;
    }
}
