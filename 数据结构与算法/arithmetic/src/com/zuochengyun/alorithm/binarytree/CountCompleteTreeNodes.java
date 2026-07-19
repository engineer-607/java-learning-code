package com.zuochengyun.alorithm.binarytree;

@SuppressWarnings({"all"})
public class CountCompleteTreeNodes {
    //Given the root of a complete binary tree, return the number of the nodes in the tree.
    //According to Wikipedia, every level, except possibly the last,
    //is completely filled in a complete binary tree,
    //and all nodes in the last level are as far left as possible.
    //It can have between 1 and 2h nodes inclusive at the last level h.
    //Design an algorithm that runs in less than O(n) time complexity.
    //思路分析：
    //1.先一直向左钻获取该树的高度，然后从右子树的头结点开始一直向左扎，获取右子树的高度
    //如果右子树的高度等于左子树的高度，则左子树为完全二叉树直接返回左子树的节点个数，再计算右子树
    //如果右子树的高度小于左子树的高度，说明右子树为完全二叉树，返回右子树节点个数，再计算左子树的
    //节点个数，而计算左子树或者右子树的节点个数可以同样用如上类似方法获取当前树的左子树、右子树高度
    //由此形成递归，递归结束条件就是当该节点为叶子节点时，直接返回1
    public int countNodes(TreeNode root) {
        if(root==null){
            return 0;
        }
        if(root.left==null&&root.right==null){
            return 1;
        }
        TreeNode node = root;
        int leftHight = getHight(root.left);
        int rightHight = getHight(root.right);
        //左子树为完全二叉树
        if(leftHight==rightHight){
            return countNodes(root.right)+(int)Math.pow(2,leftHight);
        }else {
            //右子树为完全二叉树
            return countNodes(root.left)+(int)Math.pow(2,rightHight);
        }
    }

    private int getHight(TreeNode root){
        int leftCounter = 0;
        while (root!=null){
            leftCounter++;
            root = root.left;
        }
        return leftCounter;
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
    public void countNodes_emptyTree() {
        org.junit.Assert.assertEquals(
                0,
                new CountCompleteTreeNodes().countNodes(null));
    }

    @org.junit.Test(timeout = 1000)
    public void countNodes_singleNode() {
        CountCompleteTreeNodes counter = new CountCompleteTreeNodes();
        TreeNode root = counter.new TreeNode(1);

        org.junit.Assert.assertEquals(
                1,
                counter.countNodes(root));
    }

    @org.junit.Test(timeout = 1000)
    public void countNodes_twoNodesOnlyLeftChild() {
        CountCompleteTreeNodes counter = new CountCompleteTreeNodes();
        TreeNode root = counter.new TreeNode(1);
        root.left = counter.new TreeNode(2);

        org.junit.Assert.assertEquals(
                2,
                counter.countNodes(root));
    }

    @org.junit.Test(timeout = 1000)
    public void countNodes_perfectThreeLevelTree() {
        CountCompleteTreeNodes counter = new CountCompleteTreeNodes();
        TreeNode root = counter.new TreeNode(1);
        root.left = counter.new TreeNode(2);
        root.right = counter.new TreeNode(3);
        root.left.left = counter.new TreeNode(4);
        root.left.right = counter.new TreeNode(5);
        root.right.left = counter.new TreeNode(6);
        root.right.right = counter.new TreeNode(7);

        org.junit.Assert.assertEquals(
                7,
                counter.countNodes(root));
    }

    @org.junit.Test(timeout = 1000)
    public void countNodes_lastLevelHasOneNode() {
        CountCompleteTreeNodes counter = new CountCompleteTreeNodes();
        TreeNode root = counter.new TreeNode(1);
        root.left = counter.new TreeNode(2);
        root.right = counter.new TreeNode(3);
        root.left.left = counter.new TreeNode(4);

        org.junit.Assert.assertEquals(
                4,
                counter.countNodes(root));
    }

    @org.junit.Test(timeout = 1000)
    public void countNodes_lastLevelFilledFromLeft() {
        CountCompleteTreeNodes counter = new CountCompleteTreeNodes();
        TreeNode root = counter.new TreeNode(1);
        root.left = counter.new TreeNode(2);
        root.right = counter.new TreeNode(3);
        root.left.left = counter.new TreeNode(4);
        root.left.right = counter.new TreeNode(5);
        root.right.left = counter.new TreeNode(6);

        org.junit.Assert.assertEquals(
                6,
                counter.countNodes(root));
    }

    @org.junit.Test(timeout = 1000)
    public void countNodes_fourLevelCompleteTreeWithPartialLastLevel() {
        CountCompleteTreeNodes counter = new CountCompleteTreeNodes();
        TreeNode root = counter.new TreeNode(1);
        root.left = counter.new TreeNode(2);
        root.right = counter.new TreeNode(3);
        root.left.left = counter.new TreeNode(4);
        root.left.right = counter.new TreeNode(5);
        root.right.left = counter.new TreeNode(6);
        root.right.right = counter.new TreeNode(7);
        root.left.left.left = counter.new TreeNode(8);
        root.left.left.right = counter.new TreeNode(9);
        root.left.right.left = counter.new TreeNode(10);

        org.junit.Assert.assertEquals(
                10,
                counter.countNodes(root));
    }
}
