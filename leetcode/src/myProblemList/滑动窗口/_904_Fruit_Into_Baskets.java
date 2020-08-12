package myProblemList.滑动窗口;

import java.util.HashMap;
import java.util.Map;

public class _904_Fruit_Into_Baskets {
    public int totalFruit(int[] tree) {
        Map<Integer, Integer> count = new HashMap<>();
        int i = 0, j;
        for (j = 0; j < tree.length; ++j) {
            count.put(tree[j], count.getOrDefault(tree[j], 0) + 1);
            if (count.size() > 2) {
                count.put(tree[i], count.get(tree[i]) - 1);
                count.remove(tree[i++], 0);
            }
        }
        return j - i;
    }

    public int totalFruit2(int[] tree) {
        Map<Integer, Integer> count = new HashMap<>();
        int i = 0, j, max = 0;
        for (j = 0; j < tree.length; ++j) {
            count.put(tree[j], count.getOrDefault(tree[j], 0) + 1);
            while (count.size() > 2) {
                max = Math.max(max, j - i);
                count.put(tree[i], count.get(tree[i]) - 1);
                count.remove(tree[i++], 0);
            }
        }
        max = Math.max(max, j - i);
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {3,3,3,1,2,1,1,2,3,3,4};
        _904_Fruit_Into_Baskets fruit_into_baskets = new _904_Fruit_Into_Baskets();
        int i = fruit_into_baskets.totalFruit(nums);
        System.out.println(i);

    }
}
