package topInterviewQuestions;

import java.util.ArrayList;
import java.util.List;

public class _140_Word_Break_II {
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> res = new ArrayList<>();
        if(s == null || s.length() == 0 || wordDict.size() == 0) return res;
        if(!canBreak(s, wordDict)) return res;

        dfs(s, 0, "", res, wordDict);
        return res;
    }

    public void dfs(String s, int start, String str, List<String> res, List<String> wordDict) {
        if(start == s.length()) {
            res.add(str.trim());
            return;
        }
        for(int i = start; i < s.length(); i++) {
            String tmp = s.substring(start, i + 1);
            if(wordDict.contains(tmp))
                dfs(s, i + 1, str + " " + tmp, res, wordDict);
        }
    }


    public boolean canBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length()];
        for(int i = 0; i < s.length(); i++) {
            for(int j = 0; j < i + 1; j++) {
                String tmp = s.substring(j, i + 1);
                if(wordDict.contains(tmp) && (j == 0 || dp[j - 1])) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length() - 1];
    }
}
