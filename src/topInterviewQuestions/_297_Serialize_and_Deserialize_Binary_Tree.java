public class _297_Serialize_and_Deserialize_Binary_Tree {


    // Definition for a binary tree node.
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) return "null";

            String res = root.val + "," + serialize(root.left) + "," + serialize(root.right);
            System.out.println(res);
            return res;
        }

        // Decodes your encoded data to tree.
        int offset;
        public TreeNode deserialize(String data) {
            if (data == null || data.length() == 0) return null;
            String[] strs = data.split(",");
            offset = 0;
            return deserializeHelper(strs);
        }

        public TreeNode deserializeHelper(String[] strs) {
            if (strs[offset].equals("null")) {
                offset++;
                return null;
            }
            TreeNode root = new TreeNode(Integer.valueOf(strs[offset]));
            offset++;
            root.left = deserializeHelper(strs);
            root.right = deserializeHelper(strs);
            return root;
        }
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        n1.left = n2;
        n1.right = n3;
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        n3.left = n4;
        n3.right = n5;
        Codec codec = new Codec();
        String serialize = codec.serialize(n1);
        TreeNode deserialize = codec.deserialize(serialize);
    }
}
// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
// 1, 2, null, null, 3, 4, null, null, 5, null, null