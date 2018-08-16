package Leetcode;

/**
 * @author szh
 * @create 2018-08-12 20:48
 **/
public class SearchinaBinarySearchTree {
    public TreeNode searchBST(TreeNode root, int val) {
        if(root == null){
            return null;
        }
        if(root.val == val){
            return root;
        }
        if(root.val > val){
            return compareAndSelect(root.left,val);
        }else{
            return compareAndSelect(root.right,val);
        }
    }
    public TreeNode compareAndSelect(TreeNode node , int val){
        if(node == null){
            return null;
        }
        if(node.val == val){
            return node;
        }
        if(node.val >val){
            return compareAndSelect(node.left,val);
        }else{
            return compareAndSelect(node.right,val);
        }
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
