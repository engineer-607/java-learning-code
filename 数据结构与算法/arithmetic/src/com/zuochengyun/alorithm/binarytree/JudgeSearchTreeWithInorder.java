package com.zuochengyun.alorithm.binarytree;
@SuppressWarnings({"all"})
public class JudgeSearchTreeWithInorder {
    //Given the root of a binary tree, determine if it is a valid binary search tree (BST).
    //A valid BST is defined as follows:
    //The left of a node contains only nodes with keys strictly less than the node's key.
    //The right subtree of a node contains only nodes with
    //keys strictly greater than the node's key.
    //Both the left and right subtrees must also be binary search trees.
    public boolean isValidBST(TreeNode root) {
        // 思路分析：
        // 采用非递归中序遍历方式检查当前节点和前一节点值比大小

        TreeNode[] treeNodes = new TreeNode[10001];
        int ponit = 0;
        TreeNode pre = null;
        TreeNode head = root;
        while (head!=null || ponit>0){
            if(head!=null){
                treeNodes[ponit++] = head;
                head = head.left;
            }else {
                head = treeNodes[--ponit];
                if(pre!=null&&pre.val>=head.val){
                    return false;
                }
                pre = head;
                head = head.right;
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
}
