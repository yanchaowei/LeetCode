import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class _315_Count_of_Smaller_Numbers_After_Self {
    static class Node {
        Node right;
        Node left;
        int leftCount;
        int val;

        public Node(int val) {
            this.val = val;
        }
    }
    static class Solution {
        public List<Integer> countSmaller(int[] nums) {
            if(nums == null || nums.length == 0) return new ArrayList<>();
            List<Integer> res = new ArrayList<>();
            Node root = null;
            for(int i = nums.length - 1; i >= 0; i--) {
                root = buildBinaryTree(root, nums[i], 0, res);
            }
            Collections.reverse(res);
            return res;
        }

        public Node buildBinaryTree(Node root, int val, int sum, List<Integer> res) {
            if(root == null) {
                res.add(sum);
                return new Node(val);
            }
            if(root.val < val) {
                sum += root.leftCount + 1;
                root.right = buildBinaryTree(root.right, val, sum, res);
            } else {
                root.left = buildBinaryTree(root.left, val, sum, res);
                root.leftCount += root.leftCount + 1;
            }
            return root;
        }
    }

    public static void main(String[] args) {
        int[] nums = {26,78,27,100,33,67,90,23,66,5,38,7,35,23,52,22,83,51,98,69,81,32,78,28,94,13,2,97,3,76,99,51,9,21,84,66,65,36,100,41};
        Solution solution = new Solution();
        List<Integer> integers = solution.countSmaller(nums);
        System.out.println(integers);
    }
}
