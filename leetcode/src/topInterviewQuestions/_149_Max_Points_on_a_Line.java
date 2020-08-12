package topInterviewQuestions;

import java.util.HashMap;
import java.util.Map;

public class _149_Max_Points_on_a_Line {
    public int maxPoints(int[][] points) {
        if(points == null || points.length == 0 || points[0].length == 0) return 0;
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        int result = 0;
        for(int i = 0; i < points.length; i++) {
            map.clear();
            int max = 0, overLap = 1;
            for(int j = i + 1; j < points.length; j++) {
                int x = points[i][0] - points[j][0];
                int y = points[i][1] - points[j][1];
                if(x == 0 && y == 0) {
                    overLap++;
                    continue;
                }
                int gcd = getGCD(x, y);
                if(gcd != 0) {
                    x /= gcd;
                    y /= gcd;
                }
                if(map.containsKey(x)) {
                    map.get(x).put(y, map.get(x).getOrDefault(y, 0) + 1);
                } else {
                    Map<Integer, Integer> map_ = new HashMap<>();
                    map_.put(y, 1);
                    map.put(x, map_);
                }
                max = Math.max(max, map.get(x).get(y));
            }
            result = Math.max(result, max + overLap);
        }
        return  result;
    }
    public int getGCD(int a, int b) {
        if(b == 0) return a;
        return getGCD(b, a%b);
    }
}
