package myProblemList.TreeBreadthFirstSearch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _107_Binary_Tree_Level_Order_Traversal_II {
    public List<List<Integer>> levelOrderBottom(_102_Binary_Tree_Level_Order_Traversal.TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        Queue<_102_Binary_Tree_Level_Order_Traversal.TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for(int i = 0; i < size; i++) {
                _102_Binary_Tree_Level_Order_Traversal.TreeNode node = queue.poll();
                list.add(node.val);
                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
            }
            res.add(0, list);
        }
        return res;
    }
}

