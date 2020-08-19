package myProblemList.twoPoints;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class _373_Find_K_Pairs_with_Smallest_Sums {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();
        int len1 = nums1.length;
        int len2 = nums2.length;
        if(nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) return res;
        PriorityQueue<List<Integer>> q = new PriorityQueue<>((a, b) -> Integer.compare(nums1[a.get(0)] + nums2[a.get(1)], nums1[b.get(0)] + nums2[b.get(1)]));
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(0);
        q.offer(list);
        while(k-- > 0 && !q.isEmpty()) {
            List<Integer> pre = q.poll();
            ArrayList<Integer> tmp = new ArrayList<>();
            tmp.add(nums1[pre.get(0)]);
            tmp.add(nums2[pre.get(1)]);
            res.add(tmp);
            if(pre.get(0) + 1 < len1) {
                List<Integer> newList = new ArrayList<>();
                newList.add(pre.get(0) + 1);
                newList.add(pre.get(1));
                q.offer(newList);
            }
            if(pre.get(0) == 0 && pre.get(1) + 1 < len2){
                List<Integer> newList = new ArrayList<>();
                newList.add(pre.get(0));
                newList.add(pre.get(1) + 1);
                q.offer(newList);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3};
        int[] nums2 = {4,5,6};
        _373_Find_K_Pairs_with_Smallest_Sums find_k_pairs_with_smallest_sums = new _373_Find_K_Pairs_with_Smallest_Sums();
        List<List<Integer>> lists = find_k_pairs_with_smallest_sums.kSmallestPairs(nums1, nums2, 3);
        System.out.println(lists.toArray());

    }
}
