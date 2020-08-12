package topInterviewQuestions;

import java.util.*;
public class _150_Evaluate_ {

    public static int evalRPN(String[] tokens) {
        Set<String> set = new HashSet<>();
        set.add("+");
        set.add("-");
        set.add("*");
        set.add("/");
        if(tokens == null || tokens.length == 0) return 0;
        if(tokens.length == 1) return Integer.valueOf(tokens[0]);
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < tokens.length; i++) {
            if(set.contains(tokens[i])) {
                int a = stack.pop();
                int b = stack.pop();
                if(tokens[i] == "+") stack.push(a + b);
                if(tokens[i] == "-") stack.push(a - b);
                if(tokens[i] == "*") stack.push(a * b);
                if(tokens[i] == "/") stack.push(b / a);
            }else{
                stack.push(Integer.valueOf(tokens[i]));
            }
        }
        return stack.peek();
    }

    public static void main(String[] args) {
        String[] tokens = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        System.out.println(evalRPN(tokens));
    }
}
