import java.util.*;

public class _218_The_Skyline_Problem {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> bars = new ArrayList<>();
        List<List<Integer>> sky = new ArrayList<>();
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);

        for(int[] bud : buildings) {
            bars.add(Arrays.asList(bud[0], bud[2]));
            bars.add(Arrays.asList(bud[1], -bud[2]));
        }

        Collections.sort(bars, (b1, b2) -> b1.get(0) == b2.get(0) ? b2.get(1) - b2.get(1) : b1.get(0) - b2.get(0));

        List<Integer> prev = null;
        for(List<Integer> bar : bars) {
            int x = bar.get(0), hi = bar.get(1);
            int skyLine = prev == null ? 0 : prev.get(1);
            if(hi < 0) {
                queue.remove(-hi);
                int drop = queue.isEmpty() ? 0 : queue.peek();
                if(drop == skyLine) continue;
                if(x == prev.get(0) && drop < skyLine) prev.set(0, drop);
                else sky.add(prev = Arrays.asList(x, drop));
            } else {
                queue.add(hi);
                if(hi > skyLine) sky.add(prev = bar);
            }
        }
        return sky;
    }

    public static void main(String[] args) {
        int[][] buildings = {{0,2,3},{2,5,3}};
        _218_The_Skyline_Problem the_skyline_problem = new _218_The_Skyline_Problem();
        List<List<Integer>> skyline = the_skyline_problem.getSkyline(buildings);
        System.out.println(skyline);
    }
}
