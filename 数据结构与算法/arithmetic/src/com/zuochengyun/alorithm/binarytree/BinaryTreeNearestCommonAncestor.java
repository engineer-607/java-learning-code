package com.zuochengyun.alorithm.binarytree;
@SuppressWarnings({"all"})
public class BinaryTreeNearestCommonAncestor {
    //对于有根树 T 的两个结点 p、q，
    //最近公共祖先表示为一个结点 x,
    //满足 x 是 p、q 的祖先且 x 的深度尽可能大
    //（一个节点也可以是它自己的祖先）

    //思路整理：
    //1.祖先分两种情况：一种为包含关系，即q为p的祖先或者p为q的祖先；一种为q、p分别位于两颗子树，有共同
    //的祖先节点
    //2.实现思路：每个节点获取左右子树中是否包含q或者p（钻下去判断左右节点是否为p、q，
    // 只要得到其中一个便停止钻直接返回）如果包含就返回找到的节点，没有就返回null
    // 并且将得到结果综合起来，如果都为空就返回空给当前节点的父节点，如果一个为空一个不为空，就把得到的
    //得到的节点返回，如果都不为空，说明当前节点就是祖先节点，将当前节点返回
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //递归遇到空或者p或者q节点直接返回
        if(root==null||root==q||root==p){
            return root;
        }
        //获取左子树的情况
        TreeNode l = lowestCommonAncestor(root.left,p,q);
        //获取右子树的情况
        TreeNode r = lowestCommonAncestor(root.right,p,q);
        //第二种情况
        if(l!=null&&r!=null){
            return root;
        }
        //第一种情况
        if(l==null&&r==null){
            return null;
        }
        return r!=null?r:l;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
