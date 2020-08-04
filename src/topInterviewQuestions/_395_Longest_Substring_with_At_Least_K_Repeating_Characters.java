public class _395_Longest_Substring_with_At_Least_K_Repeating_Characters {
    public int longestSubstring(String s, int k) {
        if(s == null || s.length() == 0) return 0;
        int[] cnts = new int[26];
        for(int i = 0; i < s.length(); i++) {
            cnts[s.charAt(i) - 'a'] += 1;
        }
        boolean pass = true;
        for(int i = 0; i < 26; i++) {
            if(cnts[i] != 0 && cnts[i] < k) pass = false;
        }
        if(pass) return s.length();
        int start = 0, res = 0;
        for(int i = 0; i < s.length(); i++) {
            if(cnts[s.charAt(i) - 'a'] < k) {
                res = Math.max(res, longestSubstring(s.substring(start, i), k));
                start = i + 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        _395_Longest_Substring_with_At_Least_K_Repeating_Characters longest_substring_with_at_least_k_repeating_characters = new _395_Longest_Substring_with_At_Least_K_Repeating_Characters();
        int bbaaacbd = longest_substring_with_at_least_k_repeating_characters.longestSubstring("bbaaacbd", 3);
        System.out.println(bbaaacbd);
    }
}
