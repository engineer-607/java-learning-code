package com.zuochengyun.alorithm.binarytree;

import com.zuochengyun.alorithm.merge.TransactionReserveSequence;

import java.util.Arrays;
import java.util.HashMap;

@SuppressWarnings({"all"})
public class ConstructBinaryTreeFromPreOrderAndInorderTraversal {

    //Given two integer arrays preorder and inorder where preorder is
    //the preorder traversal of a binary tree and inorder is the inorder traversal of the same tree,
    //construct and return the binary tree.
    //思路分析：
    //1.先序遍历是中左右，中序遍历是左中右，因此头结点是先序数组范围的0索引的数，而左右节点
    // 则需要通过找到中序数组中头结点的索引位置，然后得出左子树和右子树的数组范围，接着通过
    // 递归参照上述过程得到左子树范围中的头结点后建立自己的左子树和右子树，直到当前范围只有头结点
    // 递归结束

    //具体实现：1.需要一个map集合记录节点值-中序数组索引（避免每次中序数组查找头结点都遍历）
    private HashMap<Integer,Integer> hashMap = new HashMap<>();
    //2.具体计算：先序数组索引范围L1——R1，中序数组范围L2——R2，则头结点为L1，假设头结点在中序数组
    //中的索引是K，则左子树在先序数组范围是L1+1——L1+（K-L2）在中序数组范围是L2——K-1，右子树在
    //先序数组范围是L1+K-L2+1——R1，在中序数组的范围K+1——R2
    private TreeNode getRoot(int[] preorder,int l1,int r1,int[] inorder,int l2,int r2){
        if(l1>= preorder.length||r1>=preorder.length||l1<0||r1<0||l2>=inorder.length||r2>=inorder.length||l2<0||r2<0||l1>r1||l2>r2){
            return null;
        }
        TreeNode root = new TreeNode(preorder[l1]);
        //终止条件：范围的元素个数只有一个
        if(l1==r1){
            return root;
        }
        //直接查表获取头结点在中序数组的位置
        int k = hashMap.get(preorder[l1]);
        root.left = getRoot(preorder,l1+1,l1+k-l2,inorder,l2,k-1);
        root.right = getRoot(preorder,l1+k-l2+1,r1,inorder,k+1,r2);
        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            hashMap.put(inorder[i],i);
        }
        if(preorder.length==0||preorder==null||inorder.length==0||inorder==null){
            return null;
        }
        return getRoot(preorder,0, preorder.length-1,inorder,0,inorder.length-1);
    }

    @org.junit.Test(timeout = 1000)
    public void buildTree_emptyTree() {
        ConstructBinaryTreeFromPreOrderAndInorderTraversal construct = new ConstructBinaryTreeFromPreOrderAndInorderTraversal();

        org.junit.Assert.assertNull(
                construct.buildTree(new int[]{}, new int[]{}));
    }

    @org.junit.Test(timeout = 1000)
    public void buildTree_singleNode() {
        ConstructBinaryTreeFromPreOrderAndInorderTraversal construct = new ConstructBinaryTreeFromPreOrderAndInorderTraversal();

        TreeNode root = construct.buildTree(new int[]{1}, new int[]{1});

        org.junit.Assert.assertEquals(1, root.val);
        org.junit.Assert.assertNull(root.left);
        org.junit.Assert.assertNull(root.right);
    }

    @org.junit.Test(timeout = 1000)
    public void buildTree_leetcodeExample() {
        ConstructBinaryTreeFromPreOrderAndInorderTraversal construct = new ConstructBinaryTreeFromPreOrderAndInorderTraversal();

        TreeNode root = construct.buildTree(
                new int[]{3, 9, 20, 15, 7},
                new int[]{9, 3, 15, 20, 7});

        org.junit.Assert.assertEquals(3, root.val);
        org.junit.Assert.assertEquals(9, root.left.val);
        org.junit.Assert.assertNull(root.left.left);
        org.junit.Assert.assertNull(root.left.right);
        org.junit.Assert.assertEquals(20, root.right.val);
        org.junit.Assert.assertEquals(15, root.right.left.val);
        org.junit.Assert.assertNull(root.right.left.left);
        org.junit.Assert.assertNull(root.right.left.right);
        org.junit.Assert.assertEquals(7, root.right.right.val);
        org.junit.Assert.assertNull(root.right.right.left);
        org.junit.Assert.assertNull(root.right.right.right);
    }

    @org.junit.Test(timeout = 1000)
    public void buildTree_leftOnlyTree() {
        ConstructBinaryTreeFromPreOrderAndInorderTraversal construct = new ConstructBinaryTreeFromPreOrderAndInorderTraversal();

        TreeNode root = construct.buildTree(
                new int[]{1, 2, 3},
                new int[]{3, 2, 1});

        org.junit.Assert.assertEquals(1, root.val);
        org.junit.Assert.assertEquals(2, root.left.val);
        org.junit.Assert.assertEquals(3, root.left.left.val);
        org.junit.Assert.assertNull(root.left.left.left);
        org.junit.Assert.assertNull(root.left.left.right);
        org.junit.Assert.assertNull(root.left.right);
        org.junit.Assert.assertNull(root.right);
    }

    @org.junit.Test(timeout = 1000)
    public void buildTree_rightOnlyTree() {
        ConstructBinaryTreeFromPreOrderAndInorderTraversal construct = new ConstructBinaryTreeFromPreOrderAndInorderTraversal();

        TreeNode root = construct.buildTree(
                new int[]{1, 2, 3},
                new int[]{1, 2, 3});

        org.junit.Assert.assertEquals(1, root.val);
        org.junit.Assert.assertNull(root.left);
        org.junit.Assert.assertEquals(2, root.right.val);
        org.junit.Assert.assertNull(root.right.left);
        org.junit.Assert.assertEquals(3, root.right.right.val);
        org.junit.Assert.assertNull(root.right.right.left);
        org.junit.Assert.assertNull(root.right.right.right);
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
