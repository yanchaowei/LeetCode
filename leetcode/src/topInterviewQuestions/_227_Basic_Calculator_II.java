package topInterviewQuestions;

import java.util.Stack;

public class _227_Basic_Calculator_II {

    // 此方法失败：
    int l;
    int r;
    public int calculate(String s) {
        if(s == null || s.length() == 0) return 0;
        StringBuffer sb = new StringBuffer(s);
        for(int i = 0; i < sb.length();) {
            if(sb.charAt(i) == ' ') sb.delete(i, i + 1);
            else i++;
        }
        int res = 0;

        while(sb.indexOf("*") != -1 || sb.indexOf("/") != -1) {
            int cheng = sb.indexOf("*");
            int chu = sb.indexOf("/");
            if(chu == -1 || (cheng != -1 && cheng < chu)) {
                int a = findPreInt(sb, cheng);
                int b = findPostInt(sb, cheng);
                sb.replace(l + 1, r, a * b + "");
            }
            else{
                int a = findPreInt(sb, chu);
                int b = findPostInt(sb, chu);
                sb.replace(l + 1, r, a / b + "");
            }
        }
        while(sb.indexOf("+") != -1 || sb.indexOf("-") != -1) {
            int jia = sb.indexOf("+");
            int jian = sb.indexOf("-");
            if(jian == -1 || (jia != -1 && jia < jian) || (sb.lastIndexOf("-") == 0 && jia > jian)) {
                int a = findPreInt(sb, jia);
                int b = findPostInt(sb, jia);
                sb.replace(l + 1, r, a + b + "");
            }
            else{
                if(jia == -1 && sb.lastIndexOf("-") == 0) break;
                int a = findPreInt(sb, jian);
                int b = findPostInt(sb, jian);
                sb.replace(l + 1, r, a - b + "");
            }
        }
        return Integer.valueOf(new String(sb));

    }
    public int findPreInt(StringBuffer sb, int offset) {
        l = offset - 1;
        while(l >= 0 && sb.charAt(l) - '0' >= 0 && sb.charAt(l) - '0' < 10) l--;
        return Integer.valueOf(sb.substring(l + 1, offset));
    }
    public int findPostInt(StringBuffer sb, int offset) {
        r = offset + 1;
        while(r < sb.length() && sb.charAt(r) - '0' >= 0 && sb.charAt(r) - '0' < 10) r++;
        return Integer.valueOf(sb.substring(offset + 1, r));
    }


    // 方法二：
    public int calculate2(String s) {
        s = s.trim().replaceAll(" ", "");
        String[] nums = s.split("[\\+\\-\\*\\/]+");
        String[] opts = s.split("[\\d]+");
        Stack<Integer> stack = new Stack<>();
        // 这里无法保证s第一个字母为运算符的情况
        int i = 0, j = 1;
        if(nums[0] != null && nums[0].length() != 0)
        {
            stack.push(Integer.valueOf(nums[0]));
            i = 1;
        }
        for(; i < opts.length; i++) {
            int cur = Integer.valueOf(nums[j]);
            if(opts[i].equals("*")) {
                stack.push(stack.pop() * cur);
            } else if(opts[i].equals("/")) {
                stack.push(stack.pop() / cur);
            } else if(opts[i].equals("-")) {
                stack.push(-1 * cur);
            } else {
                stack.push(cur);
            }
            j++;
        }

        int res = 0;
        while(!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }

    public static void main(String[] args) {
        _227_Basic_Calculator_II basic_calculator_ii = new _227_Basic_Calculator_II();
        System.out.println(basic_calculator_ii.calculate2(" 3/2"));
    }
}
