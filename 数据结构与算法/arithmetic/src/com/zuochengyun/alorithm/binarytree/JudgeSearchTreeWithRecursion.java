package com.zuochengyun.alorithm.binarytree;
@SuppressWarnings({"all"})
public class JudgeSearchTreeWithRecursion {
    //Given the root of a binary tree, determine
    // if it is a valid binary search tree (BST).
    //
    //A valid BST is defined as follows:
    //    The left of a node contains only nodes with keys strictly less than the node's key.
    //    The right subtree of a node contains only nodes
    //    with keys strictly greater than the node's key.
    //    Both the left and right subtrees must also be binary search trees.
    //    思路分析：
    //    1.通过递归获取左子树中的最大值和最小值、右子树的最大值和最小值
    //    将左子树的最大值、当前节点值、右子树的最小值三者进行比较
    //    如果不遵循搜索二叉树的规律，就直接返回false，如果遵循就将该树的最小和最大
    //    值返回给父节点

    //具体实现：
    //1.定义全局变量max、min记录获取每棵子树的最大值、最小值,布尔值记录是否
    //为搜索二叉树
    private static long max;
    private static long min;
    private static boolean isValidBST;
    public boolean isValidBST(TreeNode root) {
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
        isValidBST = true;
        judge(root);
        return isValidBST;
    }

    private void judge(TreeNode root){
        if(!isValidBST||root==null){
            return;
        }
        if(root.left==null&&root.right==null){
            max = root.val;
            min = root.val;
            return;
        }
        long LMin = Long.MAX_VALUE;
        long LMax = Long.MIN_VALUE;
        if(root.left!=null) {
            judge(root.left);
            LMin = min;
            LMax = max;
        }
        long RMin = Long.MAX_VALUE;
        long RMax = Long.MIN_VALUE;
        if(root.right!=null) {
            judge(root.right);
            RMin = min;
            RMax = max;
        }
        if(LMax>=root.val||RMin<=root.val){
            isValidBST = false;
            return;
        }
        min = Math.min(Math.min(LMin,RMin),root.val);
        max = Math.max(Math.max(RMax,LMax), root.val);

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

    @org.junit.Test(timeout = 1000)
    public void isValidBST_emptyTree() {
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
        isValidBST = true;

        org.junit.Assert.assertTrue(
                new JudgeSearchTreeWithRecursion().isValidBST(null));
    }

    @org.junit.Test(timeout = 1000)
    public void isValidBST_singleNode() {
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
        isValidBST = true;
        JudgeSearchTreeWithRecursion judge = new JudgeSearchTreeWithRecursion();
        TreeNode root = judge.new TreeNode(1);

        org.junit.Assert.assertTrue(judge.isValidBST(root));
    }

    @org.junit.Test(timeout = 1000)
    public void isValidBST_simpleValidTree() {
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
        isValidBST = true;
        JudgeSearchTreeWithRecursion judge = new JudgeSearchTreeWithRecursion();
        TreeNode root = judge.new TreeNode(2);
        root.left = judge.new TreeNode(1);
        root.right = judge.new TreeNode(3);

        org.junit.Assert.assertTrue(judge.isValidBST(root));
    }

    @org.junit.Test(timeout = 1000)
    public void isValidBST_duplicateValueIsInvalid() {
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
        isValidBST = true;
        JudgeSearchTreeWithRecursion judge = new JudgeSearchTreeWithRecursion();
        TreeNode root = judge.new TreeNode(2);
        root.left = judge.new TreeNode(2);
        root.right = judge.new TreeNode(3);

        org.junit.Assert.assertFalse(judge.isValidBST(root));
    }

    @org.junit.Test(timeout = 1000)
    public void isValidBST_rightChildLessThanRootIsInvalid() {
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
        isValidBST = true;
        JudgeSearchTreeWithRecursion judge = new JudgeSearchTreeWithRecursion();
        TreeNode root = judge.new TreeNode(0);
        root.left = judge.new TreeNode(-1);

        org.junit.Assert.assertTrue(judge.isValidBST(root));
    }

    @org.junit.Test(timeout = 1000)
    public void isValidBST_deepNodeViolatesAncestorBoundary() {
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
        isValidBST = true;
        JudgeSearchTreeWithRecursion judge = new JudgeSearchTreeWithRecursion();
        TreeNode root = judge.new TreeNode(5);
        root.left = judge.new TreeNode(4);
        root.right = judge.new TreeNode(6);
        root.right.left = judge.new TreeNode(3);
        root.right.right = judge.new TreeNode(7);

        org.junit.Assert.assertFalse(judge.isValidBST(root));
    }

    @org.junit.Test(timeout = 1000)
    public void isValidBST_validTreeWithIntegerBoundaries() {
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
        isValidBST = true;
        JudgeSearchTreeWithRecursion judge = new JudgeSearchTreeWithRecursion();
        TreeNode root = judge.new TreeNode(0);
        root.left = judge.new TreeNode(Integer.MIN_VALUE);
        root.right = judge.new TreeNode(Integer.MAX_VALUE);

        org.junit.Assert.assertTrue(judge.isValidBST(root));
    }

    @org.junit.Test(timeout = 1000)
    public void isValidBST_leftSubtreeNodeGreaterThanRootIsInvalid() {
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
        isValidBST = true;
        JudgeSearchTreeWithRecursion judge = new JudgeSearchTreeWithRecursion();
        TreeNode root = judge.new TreeNode(5);
        root.left = judge.new TreeNode(14);
        root.left.left = judge.new TreeNode(1);

        org.junit.Assert.assertFalse(judge.isValidBST(root));
    }

    @org.junit.Test(timeout = 1000)
    public void isValidBST_minValueRootWithMaxValueRightChild() {
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
        isValidBST = true;
        JudgeSearchTreeWithRecursion judge = new JudgeSearchTreeWithRecursion();
        TreeNode root = judge.new TreeNode(Integer.MIN_VALUE);
        root.right = judge.new TreeNode(Integer.MAX_VALUE);

        org.junit.Assert.assertTrue(judge.isValidBST(root));
    }
}
