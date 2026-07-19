package com.zuochengyun.alorithm.binarytree;

public class DepthOfBinaryTree {
    //Given the root of a binary tree, return its maximum depth.
    //A binary tree's maximum depth is the number of nodes
    //along the longest path from the root node down to the farthest leaf node.
    //获取最大高度思路分析：
    // 使用递归获取左右子树中的最大高度并加上一返回给父节点
    public int maxDepth(TreeNode root) {
        return root==null?0:Math.max(maxDepth(root.left),maxDepth(root.right))+1;
    }
    //Given a binary tree, find its minimum depth.
    //The minimum depth is the number of nodes along the shortest path from the root node
    //down to the nearest leaf node.
    //A leaf is a node with no children.
    //获取最小高度思路分析：
    //1.如果传进来的节点是空，就返回0
    //2.同样递归获取左右子树的最小高度，再加一把结果返回父节点
    //3.递归结束条件为左右节点均为空返回1
    public int minDepth(TreeNode root) {
        if(root==null){
            return 0;
        }
        if(root.left==null&&root.right==null){
            return 1;
        }
        int rDeep = Integer.MAX_VALUE;
        int lDeep = Integer.MAX_VALUE;
        //需要确保递归传进去的子节点不为空，否则返回0取最小时会覆盖真正最小值
        //（这个零代表某一个节点为空，但是不是左右节点同时为空）
        //解决方案：传进去的就是非空节点，如果没有返回默认为整型最大值
        if(root.left!=null){
            lDeep = minDepth(root.left);
        }
        if(root.right!=null){
            rDeep = minDepth(root.right);
        }
        return Math.min(lDeep,rDeep)+1;
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
