package com.zuochengyun.alorithm.binarytree;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"all"})
public class BinaryTreeZigzagLevelOrderTraversal {
    //Given the root of a binary tree, return the zigzag level order traversal of its nodes' values.
    //(i.e., from left to right, then right to left for the next level and alternate between).
    //思路分析：1.采用自定义队列（数组+左右指针），二维动态数组存放最终结果
    //          2.依旧层序遍历但是每一层采取的遍历方式不同，第一层从左到右，第二层从右到左
    //            第三层从左到右，依次往下
    //          3.与之前写法类似，通过队列大小记录获取一层节点所需要的弹出次数，同时需要额外变量
    //            记录所处层数，不同点：不同的弹出遍历方式意味着需要将弹出和加入分为两个循环，不能在
    //            单个循环中完成
    //具体实现：1.定义数组，左右指针，二维动态数组，层数
    private static int MAX = 2001;
    private TreeNode queque[] = new TreeNode[MAX];
    private List<List<Integer>> list = new ArrayList<>();
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if(root==null){
            return list;
        }
        int size,level = 0,r,l;
        r = l = 0;
        ArrayList<Integer> arrayList;
        boolean isReserve = false;
        queque[r++] = root;
        while (r!=l){
            arrayList = new ArrayList<>();
            size = r - l;
            for (int start = isReserve ? r-1 : l, i = isReserve ? -1 : 1,k = 0;k<size; start += i,k++) {
                arrayList.add(queque[start].val);
            }
            list.add(level++,arrayList);
            int lastL = l;
            l = r;
            for (int i = 0; i <size ; i++) {
                TreeNode node = queque[lastL+i];
                if(node.left!=null){
                    queque[r++] = node.left;
                }
                if(node.right!=null){
                    queque[r++] = node.right;
                }
            }
            isReserve = !isReserve;
        }
        return list;
    }

    //          2.如果头节点为空，返回空的二维数组，如果不为空，加入队列中
    //            外循环：队列为空时结束
    //            第一个内循环：获取队列大小，定义isReserve，为false时就从左向右进行遍历
    //                          为true时，从右向左进行遍历，将弹出元素依次放到二维数组对应的层数
    //            第二个内循环：再从左向右遍历该层，有左加左，有右加右
    @org.junit.Test(timeout = 1000)
    public void zigzagLevelOrder_emptyTree() {
        org.junit.Assert.assertEquals(
                java.util.Collections.emptyList(),
                new BinaryTreeZigzagLevelOrderTraversal().zigzagLevelOrder(null));
    }

    @org.junit.Test(timeout = 1000)
    public void zigzagLevelOrder_singleNode() {
        BinaryTreeZigzagLevelOrderTraversal traversal = new BinaryTreeZigzagLevelOrderTraversal();
        TreeNode root = traversal.new TreeNode(1);

        org.junit.Assert.assertEquals(
                java.util.Arrays.asList(java.util.Arrays.asList(1)),
                traversal.zigzagLevelOrder(root));
    }

    @org.junit.Test(timeout = 1000)
    public void zigzagLevelOrder_exampleWithMissingChildren() {
        BinaryTreeZigzagLevelOrderTraversal traversal = new BinaryTreeZigzagLevelOrderTraversal();
        TreeNode root = traversal.new TreeNode(3);
        root.left = traversal.new TreeNode(9);
        root.right = traversal.new TreeNode(20);
        root.right.left = traversal.new TreeNode(15);
        root.right.right = traversal.new TreeNode(7);

        org.junit.Assert.assertEquals(
                java.util.Arrays.asList(
                        java.util.Arrays.asList(3),
                        java.util.Arrays.asList(20, 9),
                        java.util.Arrays.asList(15, 7)),
                traversal.zigzagLevelOrder(root));
    }

    @org.junit.Test(timeout = 1000)
    public void zigzagLevelOrder_completeThreeLevelTree() {
        BinaryTreeZigzagLevelOrderTraversal traversal = new BinaryTreeZigzagLevelOrderTraversal();
        TreeNode root = traversal.new TreeNode(1);
        root.left = traversal.new TreeNode(2);
        root.right = traversal.new TreeNode(3);
        root.left.left = traversal.new TreeNode(4);
        root.left.right = traversal.new TreeNode(5);
        root.right.left = traversal.new TreeNode(6);
        root.right.right = traversal.new TreeNode(7);

        org.junit.Assert.assertEquals(
                java.util.Arrays.asList(
                        java.util.Arrays.asList(1),
                        java.util.Arrays.asList(3, 2),
                        java.util.Arrays.asList(4, 5, 6, 7)),
                traversal.zigzagLevelOrder(root));
    }

    @org.junit.Test(timeout = 1000)
    public void zigzagLevelOrder_unbalancedTree() {
        BinaryTreeZigzagLevelOrderTraversal traversal = new BinaryTreeZigzagLevelOrderTraversal();
        TreeNode root = traversal.new TreeNode(1);
        root.left = traversal.new TreeNode(2);
        root.left.right = traversal.new TreeNode(3);
        root.left.right.left = traversal.new TreeNode(4);

        org.junit.Assert.assertEquals(
                java.util.Arrays.asList(
                        java.util.Arrays.asList(1),
                        java.util.Arrays.asList(2),
                        java.util.Arrays.asList(3),
                        java.util.Arrays.asList(4)),
                traversal.zigzagLevelOrder(root));
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
