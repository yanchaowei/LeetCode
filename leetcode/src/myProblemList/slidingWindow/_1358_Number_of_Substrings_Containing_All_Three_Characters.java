package myProblemList.slidingWindow;

public class _1358_Number_of_Substrings_Containing_All_Three_Characters {
    public int numberOfSubstrings(String s) {
        int count[] = {0, 0, 0}, i = 0, res = 0;
        int n = s.length();
        for(int j = 0; j < n; j++) {
            count[s.charAt(j) - 'a']++;
            while(count[0] > 0 && count[1] > 0 && count[2] > 0) {
                count[s.charAt(i++) - 'a']--;
            }
            res += i;
        }
        return res;
    }
}
