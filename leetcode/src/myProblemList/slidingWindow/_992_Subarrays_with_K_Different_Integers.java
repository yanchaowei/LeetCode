package myProblemList.slidingWindow;

import java.util.HashMap;
import java.util.Map;

public class _992_Subarrays_with_K_Different_Integers {
    public int subarraysWithKDistinct(int[] A, int K) {
        return atMost(A, K) - atMost(A, K - 1);
    }

    int atMost(int[] A, int K) {
        Map<Integer, Integer> map = new HashMap<>();
        int i = 0, res = 0;
        for(int j = 0; j < A.length; j++) {
            if(map.getOrDefault(A[j], 0) == 0) K--;
            map.put(A[j], map.getOrDefault(A[j], 0) + 1);
            while(K < 0) {
                map.put(A[i], map.get(A[i]) - 1);
                if(map.get(A[i]) == 0) K++;
                i++;
            }
            res += j - i + 1;
        }
        return res;
    }
}
