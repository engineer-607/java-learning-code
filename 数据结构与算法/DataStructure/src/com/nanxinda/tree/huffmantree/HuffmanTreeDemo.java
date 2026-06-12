package com.nanxinda.tree.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 赫尔曼夫树
 * 1）给定n个权值作为n个叶子节点，构成一棵二叉树，若该树的带权路径长度（wpl）达到最小，称这样的
 * 二叉树为最优二叉树，也称为哈夫曼树树
 * 2）赫夫曼树是带权路径长度最短的树，权值越大的节点离根节点的距离越近
 *
 * 重要概念
 * 1）路径和路径长度：在一棵树中，从一个节点往下可以达到的孩子或孙子节点之间的通路，称为路径。通路中分支数目
 * 称为路径长度。若规定根节点的层数为1，则从根节点到第L层节点的路径长度为L-1
 * 2）节点的权及带权路径长度：若将树中节点赋给一个有着某种含义的数值，则这个数值称为该节点的权。节点的带权
 * 路径长度为：从根节点到该节点之间的路径长度与该节点的权的乘机
 * 3）树的带权路径长度：树的带权路径长度规定为所有叶子节点的带权路径长度之和，记为WPL，权值越大的节点离根节点
 * 越近的二叉树才是最优二叉树
 * 4）WPL最小的就是赫夫曼树
 */
public class HuffmanTreeDemo {
    public static void main(String[] args) {
        int[] array = {13,7,8,3,29,6,1};
        HuffmanTree huffmanTree = new HuffmanTree(array);
        huffmanTree.establish().get(0).preOrder();
    }
}
class HuffmanTree{
    private int[] array;
    public HuffmanTree(int[] array){
        this.array = array;
    }
    public List<Node> establish(){
        List<Node> arrayList = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            arrayList.add(new Node(array[i]));
        }
        while (arrayList.size()>1) {
            Collections.sort(arrayList);
            Node right = arrayList.get(0);
            Node left = arrayList.get(1);
            Node parent = new Node(right.getValue() + left.getValue());
            parent.setLeft(left);
            parent.setRight(right);
            arrayList.remove(right);
            arrayList.remove(left);
            arrayList.add(parent);
        }
        return arrayList;
    }
}
class Node implements Comparable<Node>{
    private int value;
    private Node left;
    private Node right;
    public Node(int value){
        this.value = value;
    }
    //中序遍历
    public void preOrder(){
        preOrder(this);
    }
    private void preOrder(Node node){
        if(node==null){
            return;
        }
        System.out.println(node);
        preOrder(node.getRight());
        preOrder(node.getLeft());
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
    //从小到大排序
    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value+"}";
    }
}
