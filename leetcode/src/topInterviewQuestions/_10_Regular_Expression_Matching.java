package topInterviewQuestions;

public class _10_Regular_Expression_Matching {
    public boolean isMatch(String s, String p) {
        return matcher(s, p, 0, 0);
    }

    public boolean matcher(String s, String p, int i, int j) {
        if(i == s.length() && j == p.length()) return true;
        if(j == p.length()) return false;
        if(j < p.length() - 1 && p.charAt(j + 1) == '*') {
            if(i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.'))
                return matcher(s, p, i + 1, j) || matcher(s, p, i + 1, j + 2) || matcher(s, p, i, j + 2);
            else
                return matcher(s, p, i, j + 2);
        }else {
            if(i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.')) return matcher(s, p, i + 1, j + 1);
            else return false;
        }
    }

    public static void main(String[] args) {
        String s = "";
        String p = ".*";
        _10_Regular_Expression_Matching regular_expression_matching = new _10_Regular_Expression_Matching();
        boolean match = regular_expression_matching.isMatch(s, p);
        System.out.println(match);
    }

    // 2D dp
    public boolean isMatch2(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for(int i = 0; i < p.length(); i++) {
            if(p.charAt(i) == '*' && dp[0][i - 1]) {
                dp[0][i + 1] = true;
            }
        }
        for(int i = 0; i < s.length(); i++) {
            for(int j = 0; j < p.length(); j++) {
                if(p.charAt(j) == s.charAt(i) || p.charAt(j) == '.')
                    dp[i + 1][j + 1] = dp[i][j];
                if(p.charAt(j) ==  '*')
                    if(j > 0 && (p.charAt(j - 1) == s.charAt(i) || p.charAt(j - 1) == '.'))
                        dp[i + 1][j + 1] = (dp[i + 1][j] || dp[i + 1][j - 1] || dp[i][j + 1]);
                    else dp[i + 1][j + 1] = dp[i + 1][j - 1];
            }
        }
        return dp[s.length()][p.length()];
    }
}
