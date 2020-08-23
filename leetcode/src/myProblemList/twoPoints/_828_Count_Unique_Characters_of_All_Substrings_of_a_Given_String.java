package myProblemList.twoPoints;

public class _828_Count_Unique_Characters_of_All_Substrings_of_a_Given_String {
    public int uniqueLetterString(String s) {
        if(s == null || s.length() == 0) return 0;
        long res = 0;
        int n = s.length();
        int[] pre = new int[26];
        int[] left = new int[n];
        int[] right = new int[n];
        for(int i = 0; i < 26; i++) {
            pre[i] = -1;
        }
        for(int i = 0; i < n; i++) {
            left[i] = pre[s.charAt(i) - 'A'];
            pre[s.charAt(i) - 'A'] = i;
        }
        for(int i = 0; i < 26; i++) pre[i] = n;
        for(int i = n - 1; i >= 0; i--) {
            right[i] = pre[s.charAt(i) - 'A'];
            pre[s.charAt(i) - 'A'] = i;
        }
        for(int i = 0; i < n; i++) {
            res += (i - left[i]) * (right[i] - i);
        }
        return (int) res % (1000000007);
    }
}
