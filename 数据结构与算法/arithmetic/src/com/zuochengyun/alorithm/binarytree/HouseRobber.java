package com.zuochengyun.alorithm.binarytree;
@SuppressWarnings({"all"})
public class HouseRobber {
    //The thief has found himself a new place for his thievery again.
    //There is only one entrance to this area, called root.
    //Besides the root, each house has one and only one parent house.
    //After a tour, the smart thief realized
    //that all houses in this place form a binary tree.
    //It will automatically contact the police
    //if two directly-linked houses were broken into on the same night.
    //Given the root of the binary tree, return the maximum amount of money
    //the thief can rob without alerting the police.

    //思路分析：
    //1.使用递归，每个节点只需要获取两棵子树汇报上来的
    //算上自己和不算上自己两种情况总和，并把情况返回给父节点
    //具体实施：
    //1.定义全局变量yes、no，no记录算上子树头结点时的总和，yes记录不算上子树头结点的总和
    //2.获取LYse，LNo，RYse，RNo，最后综合一下将算上当前节点和不算上当前节点赋值给yes、no
    //错误分析：
    //1.没有考虑到空
    //2.不算当前节点应该分为两种情况，一种是当前节点和子树的头结点不算的情况，一种是不算当前
    //节点但子树头结点算的情况，更加细分应该有4种情况，右算+左算，右算+左不算，右不算+左算
    //右不算+左不算

    //算上当前节点总和
    private static int yes;
    //不算当前节点总和
    private static int no;
    public int rob(TreeNode root) {
        if(root==null){
            return 0;
        }
        yes = 0;
        no = 0;
        getChildrenCondition(root);
        return Math.max(yes,no);
    }
    private void getChildrenCondition(TreeNode treeNode){
        if(treeNode==null){
            yes = 0;
            no = 0;
            return;
        }
        if(treeNode.left==null&&treeNode.right==null){
            yes = treeNode.val;
            no = 0;
            return;
        }
        getChildrenCondition(treeNode.left);
        //左算
        int LYes = yes;
        //左不算
        int LNo = no;
        getChildrenCondition(treeNode.right);
        //右算
        int RYes = yes;
        //右不算
        int RNo = no;
        //当前节点算上
        yes = treeNode.val + LNo + RNo;
        //当前节点不算
        no = Math.max(Math.max(LYes+RYes,LNo+RYes),Math.max(LNo+RNo,LYes+RNo));

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
    public void rob_emptyTree() {

        org.junit.Assert.assertEquals(
                0,
                new HouseRobber().rob(null));
    }

    @org.junit.Test(timeout = 1000)
    public void rob_singleNode() {
        HouseRobber houseRobber = new HouseRobber();
        TreeNode root = houseRobber.new TreeNode(3);

        org.junit.Assert.assertEquals(
                3,
                houseRobber.rob(root));
    }

    @org.junit.Test(timeout = 1000)
    public void rob_exampleChooseRootAndGrandchildren() {
        HouseRobber houseRobber = new HouseRobber();
        TreeNode root = houseRobber.new TreeNode(3);
        root.left = houseRobber.new TreeNode(2);
        root.right = houseRobber.new TreeNode(3);
        root.left.right = houseRobber.new TreeNode(3);
        root.right.right = houseRobber.new TreeNode(1);

        org.junit.Assert.assertEquals(
                7,
                houseRobber.rob(root));
    }

    @org.junit.Test(timeout = 1000)
    public void rob_exampleChooseChildrenInsteadOfRoot() {
        HouseRobber houseRobber = new HouseRobber();
        TreeNode root = houseRobber.new TreeNode(3);
        root.left = houseRobber.new TreeNode(4);
        root.right = houseRobber.new TreeNode(5);
        root.left.left = houseRobber.new TreeNode(1);
        root.left.right = houseRobber.new TreeNode(3);
        root.right.right = houseRobber.new TreeNode(1);

        org.junit.Assert.assertEquals(
                9,
                houseRobber.rob(root));
    }

    @org.junit.Test(timeout = 1000)
    public void rob_leftSkewedTree() {
        HouseRobber houseRobber = new HouseRobber();
        TreeNode root = houseRobber.new TreeNode(2);
        root.left = houseRobber.new TreeNode(1);
        root.left.left = houseRobber.new TreeNode(3);

        org.junit.Assert.assertEquals(
                5,
                houseRobber.rob(root));
    }

    @org.junit.Test(timeout = 1000)
    public void rob_fullTreeWithZeroValues() {
        HouseRobber houseRobber = new HouseRobber();
        TreeNode root = houseRobber.new TreeNode(0);
        root.left = houseRobber.new TreeNode(0);
        root.right = houseRobber.new TreeNode(0);

        org.junit.Assert.assertEquals(
                0,
                houseRobber.rob(root));
    }

    @org.junit.Test(timeout = 1000)
    public void rob_largerValuesPreferGrandchildren() {
        HouseRobber houseRobber = new HouseRobber();
        TreeNode root = houseRobber.new TreeNode(10);
        root.left = houseRobber.new TreeNode(1);
        root.right = houseRobber.new TreeNode(2);
        root.left.left = houseRobber.new TreeNode(20);
        root.left.right = houseRobber.new TreeNode(30);
        root.right.left = houseRobber.new TreeNode(40);
        root.right.right = houseRobber.new TreeNode(50);

        org.junit.Assert.assertEquals(
                150,
                houseRobber.rob(root));
    }

    @org.junit.Test(timeout = 1000)
    public void rob_leftChainChooseRootAndDeepLeaf() {
        HouseRobber houseRobber = new HouseRobber();
        TreeNode root = houseRobber.new TreeNode(4);
        root.left = houseRobber.new TreeNode(1);
        root.left.left = houseRobber.new TreeNode(2);
        root.left.left.left = houseRobber.new TreeNode(3);

        org.junit.Assert.assertEquals(
                7,
                houseRobber.rob(root));
    }
}
