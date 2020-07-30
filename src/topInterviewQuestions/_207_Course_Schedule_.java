import java.util.*;

/**
 * 值得看看
 */
public class _207_Course_Schedule_ {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, ArrayList<Integer>> map;
        map = buildMap(numCourses, prerequisites);

        int[] countArr;
        countArr = buildArr(numCourses, prerequisites);

        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < numCourses; i++) {
            if(countArr[i] == 0)  queue.offer(i);
        }

        int counter = 0;
        while(!queue.isEmpty()) {
            int num = queue.poll();
            counter++;
            for(int i : map.get(num)) {
                countArr[i]--;
                if(countArr[i] == 0)
                    queue.offer(i);
            }
        }
        return counter == numCourses;
    }

    public int[] buildArr(int numCourses, int[][] prerequisites) {
        int[] countArr = new int[numCourses];
        for(int[] arr : prerequisites) {
            countArr[arr[0]]++;
        }
        return countArr;
    }

    public Map<Integer, ArrayList<Integer>> buildMap(int numCourses, int[][] prerequisites) {
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        for(int i = 0; i < numCourses; i++) {
            map.put(i, new ArrayList<Integer>());
        }
        for(int[] arr : prerequisites) {
            map.get(arr[1]).add(arr[0]);
        }
        return map;
    }

    // 方法二
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        boolean res = createGraph(numCourses, prerequisites);
        return res == false ? true : false;
    }

    public boolean createGraph(int numCourses, int[][] prerequisites) {
        ArrayList<Integer>[] adj = new ArrayList[numCourses];
        for(int i = 0; i < numCourses; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int i = 0; i < prerequisites.length; i++) {
            adj[prerequisites[i][0]].add(prerequisites[i][1]);
        }

        return hasCycle(adj);
    }

    public boolean hasCycle(ArrayList<Integer>[] adj) {
        Set<Integer> whiteSet = new HashSet<>();
        Set<Integer> graySet = new HashSet<>();
        Set<Integer> blackSet = new HashSet<>();

        for(int i = 0; i < adj.length; i++) {
            Iterator<Integer> iterator = adj[i].iterator();
            while(iterator.hasNext()) {
                whiteSet.add(iterator.next());
            }
        }

        while(!whiteSet.isEmpty()) {
            int current = whiteSet.iterator().next();
            if(dfs(current, whiteSet, graySet, blackSet, adj)) return true;
        }
        return false;
    }

    public boolean dfs(int current, Set<Integer> whiteSet, Set<Integer> graySet, Set<Integer> blackSet, ArrayList<Integer>[] adj) {
        move(current, whiteSet, graySet);

        Iterator<Integer> iterator = adj[current].iterator();
        while(iterator.hasNext()) {
            int n = iterator.next();
            if(blackSet.contains(n)) continue;

            if(graySet.contains(n)) return true;

            if(dfs(n, whiteSet, graySet, blackSet, adj)) return true;
        }
        move(current, graySet, blackSet);
        return false;
    }

    public void move(int num, Set<Integer> source, Set<Integer> destination) {
        source.remove(num);
        destination.add(num);
    }

    public static void main(String[] args) {
        _207_Course_Schedule_ course_schedule_ = new _207_Course_Schedule_();
        int[][] prerequisites = {{1, 0}};
        System.out.println(course_schedule_.canFinish2(2, prerequisites));
    }


    // 方法三
    int[] track;
    ArrayList<ArrayList<Integer>> adj;
    int[] res;
    int c;
    public boolean canFinish3(int numCourses, int[][] prerequisites) {
        adj = new ArrayList<>();
        for(int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }
        for(int[] pre : prerequisites) {
            adj.get(pre[1]).add(pre[0]);
        }
        track = new int[numCourses];
        c = numCourses;
        res = new int[numCourses];
        for(int i = 0; i < numCourses; i++) {
            if(track[i] == 0 && !dfs(i)) return false;
        }
        return true;
    }
    public boolean dfs(int i) {
        track[i] = -1;
        for(int k : adj.get(i)) {
            if(track[k] == -1) return false;
            if(track[k] == 0 && !dfs(k)) return false;
        }
        res[--c] = i;
        track[i] = 1;
        return true;
    }
}
