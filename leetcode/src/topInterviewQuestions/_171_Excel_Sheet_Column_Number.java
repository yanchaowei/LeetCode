package topInterviewQuestions;

/**
 * 26 进制转化为 十进制
 */
public class _171_Excel_Sheet_Column_Number {
    public int titleToNumber(String s) {
        if(s == null || s.length() == 0) return -1;
        int res = 0;
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c - 'A' < 0 || c - 'A' > 25) return -1;
            res = res * 26 + (c - 'A' + 1);
        }
        return res;
    }
}
