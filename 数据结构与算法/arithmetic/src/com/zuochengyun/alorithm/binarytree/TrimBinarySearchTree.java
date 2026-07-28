package com.zuochengyun.alorithm.binarytree;


@SuppressWarnings({"all"})
public class TrimBinarySearchTree {
    //Given the root of a binary search tree and the lowest
    //and highest boundaries as low and high,
    //trim the tree so that all its elements lies in [low, high].
    //Trimming the tree should not change the relative structure of the elements
    //that will remain in the tree (i.e., any node's descendant should remain a descendant).
    //It can be proven that there is a unique answer.
    //Return the root of the trimmed binary search tree.Note that
    //the root may change depending on the given bounds.

    //思路分析：
    //1.先递归获取头结点，然后再通过递归拼接头结点左子树、右子树
    private static int low;
    private static int high;
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if(root==null){
            return null;
        }
        this.low = low;
        this.high = high;
        TreeNode returnRoot = getRoot(root);
        return trim(returnRoot);

    }
    private TreeNode getRoot(TreeNode treeNode){
        if(treeNode==null){
            return null;
        }
        if(treeNode.val>=low&&treeNode.val<=high){
            return treeNode;
        }else if(treeNode.val<low){
            return getRoot(treeNode.right);
        }else {
            return getRoot(treeNode.left);
        }
    }
    private TreeNode trim(TreeNode treeNode){
        if(treeNode==null){
            return null;
        }
        if(treeNode.val<low){
            return trim(treeNode.right);
        }else if(treeNode.val>=low&&treeNode.val<=high){
            if(treeNode.right!=null) {
                treeNode.right = trim(treeNode.right);
            }
            if(treeNode.left!=null){
                treeNode.left = trim(treeNode.left);
            }
            return treeNode;
        }else {
            return trim(treeNode.left);
        }
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
    public void trimBST_emptyTree() {
        org.junit.Assert.assertNull(
                new TrimBinarySearchTree().trimBST(null, 1, 3));
    }

    @org.junit.Test(timeout = 1000)
    public void trimBST_exampleKeepsRootAndRightChild() {
        TrimBinarySearchTree trimBinarySearchTree = new TrimBinarySearchTree();
        TreeNode root = trimBinarySearchTree.new TreeNode(1);
        root.left = trimBinarySearchTree.new TreeNode(0);
        root.right = trimBinarySearchTree.new TreeNode(2);

        TreeNode actual = trimBinarySearchTree.trimBST(root, 1, 2);

        org.junit.Assert.assertEquals(1, actual.val);
        org.junit.Assert.assertNull(actual.left);
        org.junit.Assert.assertEquals(2, actual.right.val);
        org.junit.Assert.assertNull(actual.right.left);
        org.junit.Assert.assertNull(actual.right.right);
    }

    @org.junit.Test(timeout = 1000)
    public void trimBST_rootBelowLowPromotesRightChild() {
        TrimBinarySearchTree trimBinarySearchTree = new TrimBinarySearchTree();
        TreeNode root = trimBinarySearchTree.new TreeNode(1);
        root.right = trimBinarySearchTree.new TreeNode(2);

        TreeNode actual = trimBinarySearchTree.trimBST(root, 2, 4);

        org.junit.Assert.assertEquals(2, actual.val);
        org.junit.Assert.assertNull(actual.left);
        org.junit.Assert.assertNull(actual.right);
    }

    @org.junit.Test(timeout = 1000)
    public void trimBST_rootAboveHighPromotesLeftChild() {
        TrimBinarySearchTree trimBinarySearchTree = new TrimBinarySearchTree();
        TreeNode root = trimBinarySearchTree.new TreeNode(3);
        root.left = trimBinarySearchTree.new TreeNode(2);
        root.left.left = trimBinarySearchTree.new TreeNode(1);

        TreeNode actual = trimBinarySearchTree.trimBST(root, 1, 2);

        org.junit.Assert.assertEquals(2, actual.val);
        org.junit.Assert.assertEquals(1, actual.left.val);
        org.junit.Assert.assertNull(actual.left.left);
        org.junit.Assert.assertNull(actual.left.right);
        org.junit.Assert.assertNull(actual.right);
    }

    @org.junit.Test(timeout = 1000)
    public void trimBST_trimBelowLowInsideLeftSubtree() {
        TrimBinarySearchTree trimBinarySearchTree = new TrimBinarySearchTree();
        TreeNode root = trimBinarySearchTree.new TreeNode(3);
        root.left = trimBinarySearchTree.new TreeNode(0);
        root.right = trimBinarySearchTree.new TreeNode(4);
        root.left.right = trimBinarySearchTree.new TreeNode(2);
        root.left.right.left = trimBinarySearchTree.new TreeNode(1);

        TreeNode actual = trimBinarySearchTree.trimBST(root, 1, 3);

        org.junit.Assert.assertEquals(3, actual.val);
        org.junit.Assert.assertEquals(2, actual.left.val);
        org.junit.Assert.assertEquals(1, actual.left.left.val);
        org.junit.Assert.assertNull(actual.left.left.left);
        org.junit.Assert.assertNull(actual.left.left.right);
        org.junit.Assert.assertNull(actual.left.right);
        org.junit.Assert.assertNull(actual.right);
    }

    @org.junit.Test(timeout = 1000)
    public void trimBST_trimAboveHighInsideRightSubtree() {
        TrimBinarySearchTree trimBinarySearchTree = new TrimBinarySearchTree();
        TreeNode root = trimBinarySearchTree.new TreeNode(2);
        root.left = trimBinarySearchTree.new TreeNode(1);
        root.right = trimBinarySearchTree.new TreeNode(4);
        root.right.left = trimBinarySearchTree.new TreeNode(3);

        TreeNode actual = trimBinarySearchTree.trimBST(root, 1, 3);

        org.junit.Assert.assertEquals(2, actual.val);
        org.junit.Assert.assertEquals(1, actual.left.val);
        org.junit.Assert.assertNull(actual.left.left);
        org.junit.Assert.assertNull(actual.left.right);
        org.junit.Assert.assertEquals(3, actual.right.val);
        org.junit.Assert.assertNull(actual.right.left);
        org.junit.Assert.assertNull(actual.right.right);
    }

    @org.junit.Test(timeout = 1000)
    public void trimBST_allNodesInRangeRemain() {
        TrimBinarySearchTree trimBinarySearchTree = new TrimBinarySearchTree();
        TreeNode root = trimBinarySearchTree.new TreeNode(2);
        root.left = trimBinarySearchTree.new TreeNode(1);
        root.right = trimBinarySearchTree.new TreeNode(3);

        TreeNode actual = trimBinarySearchTree.trimBST(root, 1, 3);

        org.junit.Assert.assertEquals(2, actual.val);
        org.junit.Assert.assertEquals(1, actual.left.val);
        org.junit.Assert.assertEquals(3, actual.right.val);
        org.junit.Assert.assertNull(actual.left.left);
        org.junit.Assert.assertNull(actual.left.right);
        org.junit.Assert.assertNull(actual.right.left);
        org.junit.Assert.assertNull(actual.right.right);
    }
}
