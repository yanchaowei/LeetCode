package topInterviewQuestions;

import java.util.Stack;

public class _190_Reverse_Bits {
    // you need treat n as an unsigned value
    // 方法一：java build-in
    public int reverseBits(int n) {
        return Integer.reverse(n);
    }

    // 方法二
    public int reverseBits2(int n) {
        String str = String.format("%32s", Integer.toBinaryString(n)).replace(" ", "0");
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < str.length(); i++) stack.push(str.charAt(i));
        str = "";
        while (!stack.isEmpty()) str = str + Character.toString(stack.pop());
        Long reversed = Long.parseLong(str, 2);
        return reversed.intValue();
    }

    public static void main(String[] args) {
        _190_Reverse_Bits reverse_bits = new _190_Reverse_Bits();
        System.out.println(reverse_bits.reverseBits2(43261596));
    }
}

