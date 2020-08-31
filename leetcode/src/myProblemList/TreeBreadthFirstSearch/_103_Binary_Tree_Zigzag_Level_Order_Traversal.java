package myProblemList.TreeBreadthFirstSearch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _103_Binary_Tree_Zigzag_Level_Order_Traversal {
    public List<List<Integer>> zigzagLevelOrder(_102_Binary_Tree_Level_Order_Traversal.TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        Queue<_102_Binary_Tree_Level_Order_Traversal.TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int rev = 1;
        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for(int i = 0; i < size; i++) {
                _102_Binary_Tree_Level_Order_Traversal.TreeNode node = queue.poll();
                if(rev == 1){
                    list.add(node.val);
                }
                else {
                    list.add(0, node.val);
                }
                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
            }
            res.add(list);
            rev *= -1;
        }
        return res;
    }
}
