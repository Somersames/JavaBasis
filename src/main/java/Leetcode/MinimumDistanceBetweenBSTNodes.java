package Leetcode;

/**
 * @author szh
 * @create 2018-08-24 22:20
 **/
public class MinimumDistanceBetweenBSTNodes {
//    public static int difference =1000;
//    public static TreeNode node1;
//    public int minDiffInBST(TreeNode root) {
//        if(root != null){
//            compareNode(root);
//        }
//        return difference;
//    }
//    public void compareNode(TreeNode node){
//        if(node == null){
//            return  ;
//        }
//        compareNode(node.left);
//        if(node1 != null){
//            difference= Math.min(Math.abs(node.val - node1.val), difference);
//        }
//        node1 = node;
//        compareNode(node.right);
//    }
public static int difference1 =1000;
    public static TreeNode node1;
    public int minDiffInBST(TreeNode root) {
        if(root != null){
            compareNode(root);
        }
        return difference1;
    }
    public void compareNode(TreeNode node){
        if(node == null){
            return  ;
        }
        compareNode(node.left);
        if(node1 != null){
            difference1= Math.min(difference1 , Math.abs(node.val - node1.val));
        }
        node1 = node;
        compareNode(node.right);
    }
    public static void main(String[] args) {
        TreeNode  t =new TreeNode(27);
        TreeNode  t1 =new TreeNode(34);
        TreeNode  t2 =new TreeNode(58);
        TreeNode  t3 =new TreeNode(50);
        TreeNode  t4 =new TreeNode(44);
        t3.left = t4;
        t2.left=t3;
        t1.right=t2;
        t.right=t1;
        System.out.println(new MinimumDistanceBetweenBSTNodes().minDiffInBST(t));
    }
}
