package myProblemList.twoPoints;

import java.util.HashMap;
import java.util.Map;

public class _76_Minimum_Window_Substring {
    public String minWindow(String s, String t) {
        if(s == null || s.length() == 0 || t == null || t.length() > s.length()) return "";
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < t.length(); i++)
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) + 1);
        int i = 0, j = 0, k = map.size(), min = Integer.MAX_VALUE;
        String res = "";
        for(; j < s.length(); j++) {
            if(map.containsKey(s.charAt(j))) {
                map.put(s.charAt(j), map.get(s.charAt(j)) - 1);
                if(map.get(s.charAt(j)) == 0) k--;
            }
            while(k <= 0) {
                if(min > j - i + 1) {
                    min = j - i + 1;
                    res = s.substring(i, j + 1);
                }
                if(map.containsKey(s.charAt(i))) {
                    map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
                    if(map.get(s.charAt(i)) == 1) k++;
                }
                i++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        _76_Minimum_Window_Substring minimum_window_substring = new _76_Minimum_Window_Substring();
        String s = minimum_window_substring.minWindow("ADOBECODEBANC", "ABC");
        System.out.println(s);

    }
}
