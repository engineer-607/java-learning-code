package com.zuochengyun.alorithm.binarytree;

import java.util.Objects;

@SuppressWarnings({"all"})
public class CheckCompletenessOfBinaryTree {
    //Given the root of a binary tree, determine if it is a complete binary tree.
    //
    //In a complete binary tree, every level, except possibly the last, is completely filled,
    //and all nodes in the last level are as far left as possible.
    //It can have between 1 and 2h nodes inclusive at the last level h
    //思路分析：
    //1.使用层序遍历，每一个节点先检查左右节点是否都存在，都存在接着遍历，如果不满足就检查就检查左右节点
    // 如果是左节点为空，右节点不为空，直接返回false，如果是左节点不为空，右节点为空或者
    // 左右都为空，那么接下来的遍历检查就是检查每一个节点是否为叶子节点，如果发现不是叶子节点


    //具体实现：
    //1.自定义数组，左右指针模拟队列，布尔变量isCheck记录是否接下来检查节点为叶子节点（默认为false）
    TreeNode[] queue = new TreeNode[1001];
    //2.将头结点入队，接着根据左右指针之差出队，如果isCheck为false，就检查左右指针是否存在，都存在
    // 直接continue，不存在检查左节点是否存在，存在isCheck更改为true，直接continue，如果不存在，直接
    //返回false
    public boolean isCompleteTree(TreeNode root) {
        int r,l;
        r = l = 0;
        boolean isCheck = false;
        queue[r++] = root;
        while (r!=l){
            int size = r - l;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue[l++];
                if(!isCheck&&node.left!=null&&node.right!=null){
                    queue[r++] = node.left;
                    queue[r++] = node.right;
                    continue;
                }
                if(isCheck&&node.left==null&&node.right==null){
                    continue;
                }
                if(!isCheck&&node.left==null&&node.right==null){
                    isCheck = true;
                    continue;
                }
                if(!isCheck&&node.left!=null){
                    isCheck = true;
                    queue[r++] = node.left;
                    continue;
                }
                return false;

            }
        }
        return true;
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
    public void isCompleteTree_singleNode() {
        CheckCompletenessOfBinaryTree checker = new CheckCompletenessOfBinaryTree();
        TreeNode root = checker.new TreeNode(1);

        org.junit.Assert.assertTrue(checker.isCompleteTree(root));
    }

    @org.junit.Test(timeout = 1000)
    public void isCompleteTree_onlyLeftChild() {
        CheckCompletenessOfBinaryTree checker = new CheckCompletenessOfBinaryTree();
        TreeNode root = checker.new TreeNode(1);
        root.left = checker.new TreeNode(2);

        org.junit.Assert.assertTrue(checker.isCompleteTree(root));
    }

    @org.junit.Test(timeout = 1000)
    public void isCompleteTree_perfectThreeLevelTree() {
        CheckCompletenessOfBinaryTree checker = new CheckCompletenessOfBinaryTree();
        TreeNode root = checker.new TreeNode(1);
        root.left = checker.new TreeNode(2);
        root.right = checker.new TreeNode(3);
        root.left.left = checker.new TreeNode(4);
        root.left.right = checker.new TreeNode(5);
        root.right.left = checker.new TreeNode(6);
        root.right.right = checker.new TreeNode(7);

        org.junit.Assert.assertTrue(checker.isCompleteTree(root));
    }

    @org.junit.Test(timeout = 1000)
    public void isCompleteTree_lastLevelFilledFromLeft() {
        CheckCompletenessOfBinaryTree checker = new CheckCompletenessOfBinaryTree();
        TreeNode root = checker.new TreeNode(1);
        root.left = checker.new TreeNode(2);
        root.right = checker.new TreeNode(3);
        root.left.left = checker.new TreeNode(4);
        root.left.right = checker.new TreeNode(5);
        root.right.left = checker.new TreeNode(6);

        org.junit.Assert.assertTrue(checker.isCompleteTree(root));
    }

    @org.junit.Test(timeout = 1000)
    public void isCompleteTree_onlyRightChildIsNotComplete() {
        CheckCompletenessOfBinaryTree checker = new CheckCompletenessOfBinaryTree();
        TreeNode root = checker.new TreeNode(1);
        root.right = checker.new TreeNode(2);

        org.junit.Assert.assertFalse(checker.isCompleteTree(root));
    }

    @org.junit.Test(timeout = 1000)
    public void isCompleteTree_gapBeforeLaterNodeIsNotComplete() {
        CheckCompletenessOfBinaryTree checker = new CheckCompletenessOfBinaryTree();
        TreeNode root = checker.new TreeNode(1);
        root.left = checker.new TreeNode(2);
        root.right = checker.new TreeNode(3);
        root.left.left = checker.new TreeNode(4);
        root.right.left = checker.new TreeNode(6);

        org.junit.Assert.assertFalse(checker.isCompleteTree(root));
    }

    @org.junit.Test(timeout = 1000)
    public void isCompleteTree_levelOrderWithNullGapBeforeLaterNodesIsNotComplete() {
        CheckCompletenessOfBinaryTree checker = new CheckCompletenessOfBinaryTree();
        TreeNode root = checker.new TreeNode(1);
        root.left = checker.new TreeNode(2);
        root.right = checker.new TreeNode(3);
        root.left.left = checker.new TreeNode(5);
        root.right.left = checker.new TreeNode(7);
        root.right.right = checker.new TreeNode(8);

        org.junit.Assert.assertFalse(checker.isCompleteTree(root));
    }
}
