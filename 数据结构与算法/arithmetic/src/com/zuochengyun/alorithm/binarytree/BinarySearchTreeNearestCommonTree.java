package com.zuochengyun.alorithm.binarytree;

public class BinarySearchTreeNearestCommonTree {
    //思路分析：
    //1.利用搜索二叉树的特性：左子树的值全部都小于当前节点的值
    // 右子树的值全部都大于当前节点的值
    //2.当前节点值如果比p、q都大，那么只获取左子树传上来的信息
    // 如果比p、q都小，那么只获取右子树传上来的信息
    // 如果在p和q之间，那么直接返回当前节点
    // 如果等于p或q，就将相等的节点直接返回
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root.val>p.val&&root.val>q.val){
            return lowestCommonAncestor(root.left,p,q);
        }else if(root.val<p.val&&root.val<q.val){
            return lowestCommonAncestor(root.right,p,q);
        }else if(root.val==q.val){
            return q;
        }else if(root.val==p.val){
            return p;
        }else {
            return root;
        }
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
