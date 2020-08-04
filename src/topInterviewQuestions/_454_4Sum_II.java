import java.util.HashMap;
import java.util.Map;

public class _454_4Sum_II {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for(int a : A) {
            for(int b : B) {
                int key = a + b;
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
        }
        for(int c : C) {
            for(int d : D) {
                int sum = -(c + d);
                res += map.getOrDefault(sum , 0);
            }
        }
        return res;
    }
}
