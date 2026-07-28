package com.zuochengyun.alorithm.binarytree;
@SuppressWarnings({"all"})
public class JudgeBalancedTree {
    //输入一棵二叉树的根节点，判断该树是不是平衡二叉树。
    //如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
    //思路分析：
    //1.定义全局变量isBalanced记录该树是否为平衡二叉树
    //2.用递归探寻每个节点的左子树和右子树的高度，如果二者之差大于1，将全局变量isBalanced改为false
    private static boolean isBalanced;

    public boolean isBalanced(TreeNode root) {
        isBalanced = true;
        getHeight(root);
        return isBalanced;
    }

    private int getHeight(TreeNode treeNode){
        if(!isBalanced||treeNode==null){
            return 0;
        }
        int leftHeight = getHeight(treeNode.left);
        int rightHeight = getHeight(treeNode.right);
        if(Math.abs(leftHeight-rightHeight)>1){
            isBalanced = false;
        }
        return Math.max(leftHeight,rightHeight) + 1;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
