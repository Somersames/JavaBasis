package Leetcode;

/**
 * @author szh
 * @create 2018-07-25 22:41
 **/
public class MinimumAbsoluteDifferenceinBST {

    public int getMinimumDifference(TreeNode root) {
        if(root != null){
            return  compareTree(Integer.MAX_VALUE,root);
        }
        return 0;
    }
    public int compareTree(int min ,TreeNode node){

        if(node.left != null){

            TreeNode leftNode =node.left;
            min=Math.min(min,compareTree(min,leftNode));
            int leftVal =Math.abs(node.val- node.left.val);
            if(min > leftVal){
                min= leftVal;
            }
//            return compareTree(min,leftNode);
        }
        if(node.right != null){
            int rightVal =Math.abs(node.val- node.right.val);
            if(min > rightVal){
                min= rightVal;
            }
            TreeNode rightNode =node.right;
            min =Math.min(min,compareTree(min,rightNode));
        }
        return min;
    }

//    public int compareTreeV2(int min ,TreeNode root,TreeNode node){
//
//        int value = Math.abs(root.val - node.val);
//        if(root.left != null){
////            int leftVal =Math.abs(node.val- node.left.val);
////            if(min > leftVal){
////                min= leftVal;
////            }
//            TreeNode leftNode =node.left;
////            return compareTree(min,leftNode);
//        }
//        if(node.right != null){
//            int rightVal =Math.abs(node.val- node.right.val);
//            if(min > rightVal){
//                min= rightVal;
//            }
//            TreeNode rightNode =node.right;
////            return compareTree(min,rightNode);
//        }
//        return min;
//    }
    int minDiff = Integer.MAX_VALUE;
    TreeNode prev;

    public int getMinimum(TreeNode root) {
        inorder(root);
        return minDiff;
    }

    public void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        if (prev != null) minDiff = Math.min(minDiff, root.val - prev.val);
        prev = root;
        inorder(root.right);
    }


    public static void main(String[] args) {
//        TreeNode  t =new TreeNode(1);
//        TreeNode t1 =new TreeNode(3);
//        TreeNode t2 =new TreeNode(2);
//        t1.left=t2;
//        t.right=t1;

        TreeNode  t =new TreeNode(236);
        TreeNode  t1 =new TreeNode(104);
        TreeNode  t2 =new TreeNode(227);
        TreeNode  t3 =new TreeNode(701);
        TreeNode  t4 =new TreeNode(911);
        t1.right=t2;
        t3.right=t4;
        t.left=t1;
        t.right=t3;
//        System.out.println(new MinimumAbsoluteDifferenceinBST().getMinimumDifference(t));
        System.out.println(new MinimumAbsoluteDifferenceinBST().getMinimum(t));
    }

}
 class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
