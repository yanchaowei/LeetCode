package test;

import java.util.HashMap;

/**
 * @author ycw
 */
public class FindSubString {

    // T76
    // ac
    public String minWindow(String s, String t) {
        if(s == null || s.length() < t.length()) return "";
        int head = 0, begain = 0, end = 0, counter = t.length();
        int d = Integer.MAX_VALUE;
        HashMap<Character, Integer> map = new HashMap<>();
        for(char c : t.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        while(end < s.length()){
            if(map.containsKey(s.charAt(end))){
                map.put(s.charAt(end), map.get(s.charAt(end)) - 1);
                if(map.get(s.charAt(end)) > -1)
                    counter--;
            }
            end++;
            while(counter == 0){
                if(d > end - begain) d = end - (head = begain);
                if (map.containsKey(s.charAt(begain))){
                    map.put(s.charAt(begain), map.get(s.charAt(begain)) + 1);
                    if(map.get(s.charAt(begain)) > 0) counter++;
                }
                begain++;
            }
        }
        if(d != Integer.MAX_VALUE) return s.substring(head, head + d);
        else return "";
    }

    // T76
    // ac
    // 与上面方法区别就是将不存在于字符串t的字符也存入map，但是通过判断map元素的value的正负辨别该字符是否存在于t中
    // 从而决定是否改变counter的值
    public String minWTindow2(String s, String t) {
        if(s == null || s.length() < t.length()) return "";
        int head = 0, begain = 0, end = 0, counter = t.length();
        int d = Integer.MAX_VALUE;
        HashMap<Character, Integer> map = new HashMap<>();
        for(char c : t.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        while(end < s.length()){
            map.put(s.charAt(end), map.getOrDefault(s.charAt(end), 0) - 1);
            if(map.get(s.charAt(end)) > -1)
                counter--;
            end++;
            while(counter == 0){
                if(d > end - begain) d = end - (head = begain);
                map.put(s.charAt(begain), map.get(s.charAt(begain)) + 1);
                if(map.get(s.charAt(begain)) > 0) counter++;
                begain++;
            }
        }
        if(d != Integer.MAX_VALUE) return s.substring(head, head + d);
        else return "";
    }

    /**
     * T3 最长不重复子字符串
     * @param s
     */
    public int lengthOfLongestSubstring(String s) {
        int begain = 0, end = 0, d = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        int counter = 0;
        while (end < s.length()) {
            map.put(s.charAt(end), map.getOrDefault(s.charAt(end), 0) + 1);
            if(map.get(s.charAt(end)) > 1) counter++;
            end++;
            while (counter > 0) {
                map.put(s.charAt(begain), map.get(s.charAt(begain)) - 1);
                if(map.get(s.charAt(begain)) >= 1) counter--;
                begain++;
            }
            d = Math.max(d, end - begain);
        }
        return d;
    }

    // 最长不重复子字符串
    public String longestSubstring(String s) {
        int began = 0, end = 0, d = Integer.MIN_VALUE;
        int head = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        int counter = 0;
        while (end < s.length()) {
            map.put(s.charAt(end), map.getOrDefault(s.charAt(end), 0) + 1);
            if(map.get(s.charAt(end)) > 1) counter++;
            end++;
            while (counter > 0) {
                map.put(s.charAt(began), map.get(s.charAt(began)) - 1);
                if(map.get(s.charAt(began)) >= 1) counter--;
                began++;
            }
            if(d < end - began) d = end - (head = began);
        }
        if(d != Integer.MIN_VALUE) return s.substring(head, head + d);
        else return "";
    }

    // **Longest Substring with At Most Two Distinct Characters**
    public String longestSubstring2(String s){
        int began = 0, end = 0, counter = 0;    // 记录窗口中不同的字符有多少个
        int head = 0, d = Integer.MIN_VALUE;    // 记录最优子字符串的起始位置和长度
        HashMap<Character, Integer> map = new HashMap<>();
        while (end < s.length()) {
            map.put(s.charAt(end), map.getOrDefault(s.charAt(end), 0) + 1);
            if (map.get(s.charAt(end)) == 1) counter++;     // 当新加入的字符计数为1，则表明这是个"新成员”，需要跟新counter
            end++;
            while (counter > 2){                // 当counter超过2，字符无效，就要滑动began使字符重新变得有效
                map.put(s.charAt(began), map.get(s.charAt(began)) - 1);
                if(map.get(s.charAt(began)) <= 0) counter--;
                began++;
            }
            // 这里需要注意！
            // 更新最优子字符串需要在内层while循环后，而不是内部，因为内部窗口还不是有效的子字符串（不同的字符超过2）
            if(d < end - began) d = end - (head = began);
        }
        if(d != Integer.MIN_VALUE) return s.substring(head, head + d);
        else return "";
    }

    public static void main(String[] args) {
        FindSubString findSubString = new FindSubString();
        String s = "abcabcbb";
        String ret = findSubString.longestSubstring2(s);
        System.out.println(ret);
    }
}
