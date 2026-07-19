package com.zuochengyun.alorithm.binarytree;

import java.util.*;
import java.util.concurrent.SynchronousQueue;
@SuppressWarnings({"all"})
public class BinaryTreeOrderTraversal {
    //Given the root of a binary tree,
    // return the level order traversal of its nodes' values.
    // (i.e., from left to right, level by level).

    //思路1分析：
    // 按层序进行遍历，需要队列操作头结点出，左右孩子入，然后如此
    // 往复达到遍历是按照层序进行遍历，但是问题是不清楚每个节点
    // 是来自哪一层，这时候就需要一个hash表在节点进入队列时来进行记录，
    // 这样在队列弹出该节点可以从hash表中获悉层数，进而根据层数将其放到
    // 对应索引的动态数组之中
    // 具体实现:
    // 1.需要一个队列，一个hash表（键：节点，值：层数），一个真正返回的结果的
    // 二维动态数组
    private Queue<TreeNode> queue = new LinkedList<>();
    private Map<TreeNode,Integer> map = new HashMap<>();
    private List<List<Integer>> list = new ArrayList<>();
    // 2.头结点特殊需要单独拎出来，先判断头结点是否为空，如果为空直接返回
    // 如果不为空，需要先加入hash表（值设为0），然后再加入队列
    // 3.接下来，就是以队列是否空为结束条件的循环，循环体中先弹出队列的头元素
    // 然后根据hash表获取该元素所处的层数，如果发现二维数组的大小等于获取层数
    // 就先创建该层数的动态数组，然后再添加，之后再判断弹出节点的左右节点，如果有
    // 就添加（队列和hash表均添加）
    public List<List<Integer>> levelOrder_Base(TreeNode root) {
        if(root!=null){
            map.put(root,0);
            queue.add(root);
            while (!queue.isEmpty()){
                TreeNode node = queue.poll();
                int level = map.get(node);
                if(level==list.size()) {
                    ArrayList arrayList = new ArrayList();
                    list.add(level, arrayList);
                }
                list.get(level).add(node.val);
                if(node.left!=null){
                    map.put(node.left,level+1);
                    queue.add(node.left);
                }
                if(node.right!=null){
                    map.put(node.right,level+1);
                    queue.add(node.right);
                }
            }
        }
        return list;
    }

    //思路2（优化版）分析：
    //1.队列可以自己模拟（定义左右指针，左指针用来弹出，右指针用来元素入队）
    //2.不需要用一个额外的hash表来记录这个节点来自哪个层
    // 可以定义一个内循环而这一次循环弹出的为一层的节点，具体操作：
    // 2.1判断头结点是否为空，为空直接返回空的动态数组
    // 2.2头结点不为空，先将头结点入队，然后记录此时队列大小size，意味着需要弹出元素个数（即该层元素个数）
    // 然后如下操作重复size遍：弹出元素，然后判断该元素是否有右元素或者左元素，有则加
    // 在将弹出元素收集到动态数组（需要一个全局变量来记录层数，方便添加）
    // 2.3上述是内循环，外循环即为队列为空时循环结束
    private static int MAX = 2001;

    private static TreeNode[] queueNode = new TreeNode[MAX];

    public List<List<Integer>> levelOrder_Advanced(TreeNode root){
        int left = 0,right = 0;
        if(root==null){
            return list;
        }
        queueNode[right++] = root;
        int size,layer = 0;
        ArrayList<Integer> arrayList;
        TreeNode node;
        while (left!=right){
            size = right - left;
            arrayList = new ArrayList();
            for (int i = 0; i < size; i++) {
                node = queueNode[left++];
                arrayList.add(node.val);
                if(node.left!=null){
                    queueNode[right++] = node.left;
                }
                if(node.right!=null){
                    queueNode[right++] = node.right;
                }
            }
            list.add(layer++,arrayList);
        }
        return list;
    }

    @org.junit.Test(timeout = 1000)
    public void levelOrderAdvanced_emptyTree() {
        org.junit.Assert.assertEquals(
                Collections.emptyList(),
                new BinaryTreeOrderTraversal().levelOrder_Advanced(null));
    }

    @org.junit.Test(timeout = 1000)
    public void levelOrderAdvanced_singleNode() {
        BinaryTreeOrderTraversal traversal = new BinaryTreeOrderTraversal();
        TreeNode root = traversal.new TreeNode(1);

        org.junit.Assert.assertEquals(
                Arrays.asList(Arrays.asList(1)),
                traversal.levelOrder_Advanced(root));
    }

    @org.junit.Test(timeout = 1000)
    public void levelOrderAdvanced_completeTreeWithMissingChildren() {
        BinaryTreeOrderTraversal traversal = new BinaryTreeOrderTraversal();
        TreeNode root = traversal.new TreeNode(3);
        root.left = traversal.new TreeNode(9);
        root.right = traversal.new TreeNode(20);
        root.right.left = traversal.new TreeNode(15);
        root.right.right = traversal.new TreeNode(7);

        org.junit.Assert.assertEquals(
                Arrays.asList(
                        Arrays.asList(3),
                        Arrays.asList(9, 20),
                        Arrays.asList(15, 7)),
                traversal.levelOrder_Advanced(root));
    }

    @org.junit.Test(timeout = 1000)
    public void levelOrderAdvanced_unbalancedTree() {
        BinaryTreeOrderTraversal traversal = new BinaryTreeOrderTraversal();
        TreeNode root = traversal.new TreeNode(1);
        root.left = traversal.new TreeNode(2);
        root.left.right = traversal.new TreeNode(3);
        root.left.right.left = traversal.new TreeNode(4);

        org.junit.Assert.assertEquals(
                Arrays.asList(
                        Arrays.asList(1),
                        Arrays.asList(2),
                        Arrays.asList(3),
                        Arrays.asList(4)),
                traversal.levelOrder_Advanced(root));
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
