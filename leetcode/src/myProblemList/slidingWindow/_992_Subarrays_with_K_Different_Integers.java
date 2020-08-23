package myProblemList.slidingWindow;

import java.util.HashMap;
import java.util.Map;

public class _992_Subarrays_with_K_Different_Integers {
    public int subarraysWithKDistinct(int[] A, int K) {
        return atMost2(A, K) - atMost2(A, K - 1);
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

    public int atMost2(int[] A, int K) {
        if(K <= 0) return 0;
        Map<Integer, Integer> map = new HashMap<>();
        int i = 0, j = 0, n = A.length, res = 0;
        for(; j < n; j++) {
            map.put(A[j], map.getOrDefault(A[j], 0) + 1);
            while(map.size() > K) {
                map.put(A[i], map.get(A[i]) - 1);
                if(map.get(A[i]) == 0)
                    map.remove(A[i]);
                i++;
            }
            res += j - i + 1;
        }
        return res;
    }

    public static void main(String[] args) {
        _992_Subarrays_with_K_Different_Integers subarrays_with_k_different_integers = new _992_Subarrays_with_K_Different_Integers();
        int[] nums = new int[]{1,2,1,2,3};
        int i = subarrays_with_k_different_integers.subarraysWithKDistinct(nums, 2);
        System.out.println(i);
//        Map<Integer, Integer> map = new HashMap<>();
//        map.put(1, 1);
//        map.put(2, 2);
//        map.remove(1);
//        map.remove(2, 2);
//        System.out.println(map.get(1));
//        System.out.println(map.get(2));
    }
}
