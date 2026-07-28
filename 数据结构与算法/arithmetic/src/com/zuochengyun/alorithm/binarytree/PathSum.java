package com.zuochengyun.alorithm.binarytree;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"all"})
public class PathSum {
    //给你二叉树的根节点 root 和一个整数目标和 targetSum ，
    //找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
    //思路分析：
    //1.定义全局变量answerList记录所有答案
    //2.采用递归回溯方式探查每一条路径是否为正确答案：定义返回布尔类型递归方法，传入当前节点、目标值、以及记录路径
    //的动态数组
    //具体实现：
    //1.定义递归方法，传入当前节点、当前值、目标值、以及记录路径的动态数组
    //2.方法内先判断当前节点是否为叶子节点，如果不是叶子节点就分左右子树分别探索，同时分别跟新当前值、动态数组
    // 两个方向探索之后需要分别将动态数组当前元素剔除掉，如果是叶子节点，就判断跟新后的当前值是否等于目标值
    // 如果相等，跟新后的动态数组复制一份添加到全局变量中，但是无论是否相等当前数组都必须剔除当前元素
    private static List<List<Integer>> answerList;

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        answerList = new ArrayList<>();
        if(root!=null){
            List<Integer> list = new ArrayList<>();
            list.add(root.val);
            tryPath(root,root.val,targetSum,list);
        }
        return answerList;
    }

    private void tryPath(TreeNode treeNode,int now,int aim,List<Integer> list){
        if(treeNode.left==null&&treeNode.right==null&&now==aim){
            answerList.add(new ArrayList<>(list));
        }else {
            TreeNode node;
            if((node = treeNode.left)!=null){
                list.add(node.val);
                tryPath(node,now + node.val,aim,list);
                list.remove(list.size() - 1);
            }
            if((node = treeNode.right)!=null){
                list.add(node.val);
                tryPath(node, now + node.val,aim,list);
                list.remove(list.size() - 1);
            }
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
    public void pathSum_emptyTree() {


        org.junit.Assert.assertEquals(
                java.util.Collections.emptyList(),
                new PathSum().pathSum(null, 0));
    }

    @org.junit.Test(timeout = 1000)
    public void pathSum_singleNodeEqualsTarget() {

        PathSum pathSum = new PathSum();
        TreeNode root = pathSum.new TreeNode(1);

        org.junit.Assert.assertEquals(
                java.util.Arrays.asList(java.util.Arrays.asList(1)),
                pathSum.pathSum(root, 1));
    }

    @org.junit.Test(timeout = 1000)
    public void pathSum_singleNodeDoesNotEqualTarget() {

        PathSum pathSum = new PathSum();
        TreeNode root = pathSum.new TreeNode(1);

        org.junit.Assert.assertEquals(
                java.util.Collections.emptyList(),
                pathSum.pathSum(root, 2));
    }

    @org.junit.Test(timeout = 1000)
    public void pathSum_exampleTreeHasTwoPaths() {

        PathSum pathSum = new PathSum();
        TreeNode root = pathSum.new TreeNode(5);
        root.left = pathSum.new TreeNode(4);
        root.right = pathSum.new TreeNode(8);
        root.left.left = pathSum.new TreeNode(11);
        root.left.left.left = pathSum.new TreeNode(7);
        root.left.left.right = pathSum.new TreeNode(2);
        root.right.left = pathSum.new TreeNode(13);
        root.right.right = pathSum.new TreeNode(4);
        root.right.right.left = pathSum.new TreeNode(5);
        root.right.right.right = pathSum.new TreeNode(1);

        org.junit.Assert.assertEquals(
                java.util.Arrays.asList(
                        java.util.Arrays.asList(5, 4, 11, 2),
                        java.util.Arrays.asList(5, 8, 4, 5)),
                pathSum.pathSum(root, 22));
    }

    @org.junit.Test(timeout = 1000)
    public void pathSum_noRootToLeafPathEqualsTarget() {

        PathSum pathSum = new PathSum();
        TreeNode root = pathSum.new TreeNode(1);
        root.left = pathSum.new TreeNode(2);
        root.right = pathSum.new TreeNode(3);

        org.junit.Assert.assertEquals(
                java.util.Collections.emptyList(),
                pathSum.pathSum(root, 5));
    }

    @org.junit.Test(timeout = 1000)
    public void pathSum_negativeValuesCanFormTarget() {

        PathSum pathSum = new PathSum();
        TreeNode root = pathSum.new TreeNode(-2);
        root.right = pathSum.new TreeNode(-3);

        org.junit.Assert.assertEquals(
                java.util.Arrays.asList(java.util.Arrays.asList(-2, -3)),
                pathSum.pathSum(root, -5));
    }

    @org.junit.Test(timeout = 1000)
    public void pathSum_duplicateValuesShouldKeepRootToLeafOrder() {
        PathSum pathSum = new PathSum();
        TreeNode root = pathSum.new TreeNode(1);
        root.left = pathSum.new TreeNode(-2);
        root.right = pathSum.new TreeNode(-3);
        root.left.left = pathSum.new TreeNode(1);
        root.left.right = pathSum.new TreeNode(3);
        root.right.left = pathSum.new TreeNode(-2);
        root.left.left.left = pathSum.new TreeNode(-1);

        org.junit.Assert.assertEquals(
                java.util.Arrays.asList(java.util.Arrays.asList(1, -2, 3)),
                pathSum.pathSum(root, 2));
    }
}
