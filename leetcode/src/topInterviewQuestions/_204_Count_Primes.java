package topInterviewQuestions;

public class _204_Count_Primes {
    public int countPrimes(int n) {
        if(n <= 0) return 0;
        int[] primes = new int[n];
        int cnt = 0;
        for(int i = 2; i < n; i++) {
            for(int j = 2; i * j > 0 && i * j < n; j++) {
                primes[i * j] = 1;
            }
            if(primes[i] != 1) cnt++;
        }
        return cnt;
    }
}
