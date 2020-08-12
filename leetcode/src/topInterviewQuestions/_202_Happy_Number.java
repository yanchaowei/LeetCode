package topInterviewQuestions;

import java.util.HashSet;
import java.util.Set;

public class _202_Happy_Number {
    public boolean isHappy(int n) {
        if(n <= 0) return false;
        Set<Integer> set = new HashSet<>();
        set.add(n);
        while(true) {
            int sum = 0;
            while(n > 0) {
                sum = sum + (n % 10) * (n % 10);
                n = n/10;
            }
            System.out.println(sum);
            if(sum == 1) return true;
            if(!set.add(sum)) return false;
            n = sum;
        }
    }

    public static void main(String[] args) {
        _202_Happy_Number happy_number = new _202_Happy_Number();
        System.out.println(happy_number.isHappy(2));
    }
}
