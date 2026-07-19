package com.zuochengyun.alorithm.binarytree;
@SuppressWarnings({"all"})
public class MaximumOfBinaryTree {
    //Given the root of a binary tree, return the maximum width of the given tree.
    //The maximum width of a tree is the maximum width among all levels.
    //The width of one level is defined as the length between the end-nodes
    //(the leftmost and rightmost non-null nodes),
    //where the null nodes between the end-nodes
    //that would be present in a complete binary tree extending down to
    //that level are also counted into the length calculation.
    //It is guaranteed that the answer will in the range of a 32-bit signed integer.

    //我的思路分析：1.依旧采用队列的层序遍历出
    // 元素的个数，但是在加入的时候无论左右是否为空都加入，并且弹出的时候也弹出
    // 将弹出结果记录到一个数组中，最后记录数组的最大值并返回

    //改进的思路：核心由将null也记录到队列转变通过索引来记录父节点、左右子节点，通过
    //获取最右侧节点的索引和最左侧索引之差获取一层中的宽度
    private static final int MAX = 3001;
    private static TreeNode[] queue = new TreeNode[MAX];
    private static int[] index = new int[MAX];
    //具体实现：定义一个节点数组，左右指针作为队列，同时定义一个索引数组，存储每个节点对应的
    //索引（两个数组相同的位置一个存节点，一个存索引），并且两个数组同步增长、同步变化
    //具体方法实现：先检验头结点是否为空，为空直接返回0，不为空入队并在索引数组记录头结点
    //的索引为1，并定义最大宽度计数器为1，
    //然后获取队列大小size，如下操作循环size遍：弹出节点，有右节点时队列和索引数组
    //同时记录，左节点同理，并且将队列最右侧索引和最左侧索引之差和最大宽度计数器进行比较
    //如此往复直至队列为空
    public int widthOfBinaryTree(TreeNode root) {
        if(root==null){
            return 0;
        }
        int r,l;
        r = l = 0;
        queue[r] = root;
        index[r++] = 1;
        int maxCounter = 1;
        while (true){
            int size = r - l;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue[l];
                int fatherIndex = index[l++];
                if(node.left!=null){
                    queue[r] = node.left;
                    index[r++] = fatherIndex*2;
                }
                if(node.right!=null){
                    queue[r] = node.right;
                    index[r++] = fatherIndex*2+1;
                }
            }
            if(r==l){
                break;
            }
            maxCounter = (index[r-1] - index[l] + 1)>maxCounter?(index[r-1] - index[l] + 1):maxCounter;
        }
        return maxCounter;
    }

    @org.junit.Test(timeout = 1000)
    public void widthOfBinaryTree_emptyTree() {
        org.junit.Assert.assertEquals(
                0,
                new MaximumOfBinaryTree().widthOfBinaryTree(null));
    }

    @org.junit.Test(timeout = 1000)
    public void widthOfBinaryTree_singleNode() {
        MaximumOfBinaryTree maximumOfBinaryTree = new MaximumOfBinaryTree();
        TreeNode root = maximumOfBinaryTree.new TreeNode(1);

        org.junit.Assert.assertEquals(
                1,
                maximumOfBinaryTree.widthOfBinaryTree(root));
    }

    @org.junit.Test(timeout = 1000)
    public void widthOfBinaryTree_exampleWithNullBetweenEndNodes() {
        MaximumOfBinaryTree maximumOfBinaryTree = new MaximumOfBinaryTree();
        TreeNode root = maximumOfBinaryTree.new TreeNode(1);
        root.left = maximumOfBinaryTree.new TreeNode(3);
        root.right = maximumOfBinaryTree.new TreeNode(2);
        root.left.left = maximumOfBinaryTree.new TreeNode(5);
        root.left.right = maximumOfBinaryTree.new TreeNode(3);
        root.right.right = maximumOfBinaryTree.new TreeNode(9);

        org.junit.Assert.assertEquals(
                4,
                maximumOfBinaryTree.widthOfBinaryTree(root));
    }

    @org.junit.Test(timeout = 1000)
    public void widthOfBinaryTree_leftAndRightSparseDeepLevel() {
        MaximumOfBinaryTree maximumOfBinaryTree = new MaximumOfBinaryTree();
        TreeNode root = maximumOfBinaryTree.new TreeNode(1);
        root.left = maximumOfBinaryTree.new TreeNode(3);
        root.right = maximumOfBinaryTree.new TreeNode(2);
        root.left.left = maximumOfBinaryTree.new TreeNode(5);
        root.right.right = maximumOfBinaryTree.new TreeNode(9);
        root.left.left.left = maximumOfBinaryTree.new TreeNode(6);
        root.right.right.right = maximumOfBinaryTree.new TreeNode(7);

        org.junit.Assert.assertEquals(
                8,
                maximumOfBinaryTree.widthOfBinaryTree(root));
    }

    @org.junit.Test(timeout = 1000)
    public void widthOfBinaryTree_unbalancedTree() {
        MaximumOfBinaryTree maximumOfBinaryTree = new MaximumOfBinaryTree();
        TreeNode root = maximumOfBinaryTree.new TreeNode(1);
        root.left = maximumOfBinaryTree.new TreeNode(2);
        root.left.right = maximumOfBinaryTree.new TreeNode(3);
        root.left.right.left = maximumOfBinaryTree.new TreeNode(4);

        org.junit.Assert.assertEquals(
                1,
                maximumOfBinaryTree.widthOfBinaryTree(root));
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
