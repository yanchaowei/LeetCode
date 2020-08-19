package hard;

import java.util.Stack;

public class _316_Remove_Duplicate_Letters {
    public String removeDuplicateLetters(String s) {
        if(s.length() < 2) return s;
        int[] count = buildCount(s);
        Stack<Character> stack = new Stack<>();
        for(char c : s.toCharArray()) {
            count[c - 'a']--;
            if(stack.contains(c)) continue;
            while(!stack.isEmpty() && stack.peek() > c && count[stack.peek() - 'a'] > 0) stack.pop();
            stack.push(c);
        }
        String res = "";
        while(!stack.isEmpty()) res = stack.pop() + res;
        return res;
    }

    public int[] buildCount(String s) {
        int[] count = new int[26];
        for(char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        return count;
    }
}
