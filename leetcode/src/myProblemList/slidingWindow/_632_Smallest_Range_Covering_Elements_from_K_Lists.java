package myProblemList.slidingWindow;

import com.sun.tools.corba.se.idl.InterfaceGen;

import java.util.*;

public class _632_Smallest_Range_Covering_Elements_from_K_Lists {
    public int[] smallestRange2(List<List<Integer>> nums) {
        int n = 0;
        for(List list : nums) n += list.size();
        int[][] t = new int[n][2];
        int p = 0;
        for(int i = 0; i < nums.size(); i++) {
            for(int num : nums.get(i)) {
                t[p][0] = i;
                t[p][1] = num;
                p++;
            }
        }
        Arrays.sort(t, (a, b) -> (a[1] - b[1]));
        int i = 0, j, k = nums.size();
        int[] res = new int[2];
        int[] count = new int[nums.size()];
        for(j = 0; j < n; j++) {
            count[t[j][0]]++;
            if(count[t[j][0]] == 1) k--;
            while(k == 0) {
                if((res[0] == 0 && res[1] == 0) || (res[1] - res[0] > t[j][1] - t[i][1])) {
                    res[0] = t[i][1];
                    res[1] = t[j][1];
                }
                count[t[i][0]]--;
                if(count[t[i++][0]] == 0) k++;
            }
        }
        return res;
    }

    // 贪心 + 多指针 + 优先队列
    class Entry {
        int a;
        int b;
        public Entry (int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
    public int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<Entry> sh = new PriorityQueue<>(new Comparator<Entry>(){
            @Override
            public int compare(Entry o1, Entry o2) {
                return o1.a - o2.a;
            }
        });
        int end = -100001;
        int[] indexes = new int[nums.size()];
        for(int i = 0; i < nums.size(); i++) {
            if(nums.get(i).get(0) > end) end = nums.get(i).get(0);
            sh.offer(new Entry(nums.get(i).get(0), i));
            indexes[i]++;
        }
        int[] res = new int[2];
        res[0] = sh.peek().a;
        res[1] = end;
        while(indexes[sh.peek().b] < nums.get(sh.peek().b).size()) {
            Entry peek = sh.poll();
            int index = peek.b;
            int newNum = nums.get(index).get(indexes[index]);
            if(newNum > end) end = newNum;
            sh.offer(new Entry(newNum, index));
            indexes[index]++;
            if(res[1] - res[0] > end - sh.peek().a) {
                res[0] = sh.peek().a;
                res[1] = end;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<List<Integer>> nums = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        list1.add(4);
        list1.add(10);
        list1.add(15);
        list1.add(24);
        list1.add(26);
        List<Integer> list2 = new ArrayList<>();
        list2.add(0);
        list2.add(9);
        list2.add(12);
        list2.add(20);
        List<Integer> list3 = new ArrayList<>();
        list3.add(5);
        list3.add(18);
        list3.add(22);
        list3.add(30);
        nums.add(list1);
        nums.add(list2);
        nums.add(list3);
//        nums.add(list1);
        _632_Smallest_Range_Covering_Elements_from_K_Lists smallest_range_covering_elements_from_k_lists = new _632_Smallest_Range_Covering_Elements_from_K_Lists();
        int[] ints = smallest_range_covering_elements_from_k_lists.smallestRange(nums);

        System.out.println(ints.toString());

    }
}
