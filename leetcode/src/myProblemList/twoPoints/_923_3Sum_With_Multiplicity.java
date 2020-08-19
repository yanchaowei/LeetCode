package myProblemList.twoPoints;

public class _923_3Sum_With_Multiplicity {
    public int threeSumMulti(int[] nums, int target) {
        long[] counts = new long[101];
        for(int num : nums) counts[num]++;
        long res = 0;
        for(int i = 0; i <= 100; i++) {
            for(int j = i; j <= 100; j++) {
                int k = target - i - j;
                if(k < 0 || k > 100) continue;
                if(i == j && j == k)
                    res += counts[i] * (counts[i] - 1) * (counts[i] - 2) / 6;
                else if(i == j && j != k)
                    res += counts[i] * (counts[i] - 1) * counts[k] / 2;
                else if(j < k)
                    res += counts[i] * counts[j] * counts[k];
            }
        }
        return (int)(res % (1e9 + 7));
    }
}
