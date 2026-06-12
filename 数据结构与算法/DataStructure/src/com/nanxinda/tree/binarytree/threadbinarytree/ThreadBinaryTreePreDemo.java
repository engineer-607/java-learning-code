package com.nanxinda.tree.binarytree.threadbinarytree;

public class ThreadBinaryTreePreDemo {
    public static void main(String[] args) {
        ThreadBinaryTreePre tree = new ThreadBinaryTreePre();
        HeroNode heroNode = new HeroNode(1,"宋江");
        HeroNode heroNode1 = new HeroNode(2,"吴用");
        HeroNode heroNode2 = new HeroNode(3,"卢俊义");
        HeroNode heroNode3 = new HeroNode(4,"林冲");
        HeroNode heroNode4 = new HeroNode(5,"关胜");
        tree.setRoot(heroNode);
        heroNode.setLeft(heroNode1);
        heroNode.setRight(heroNode2);
        heroNode2.setRight(heroNode3);
        heroNode2.setLeft(heroNode4);
        tree.threadBinaryTree();
        tree.traversePre();

    }
}
class ThreadBinaryTreePre{
    HeroNode root;
    HeroNode pre = null;
    public void threadBinaryTree(){
        threadBinaryTree(root);
    }
    public void threadBinaryTree(HeroNode node){
        if(node==null){
            return;
        }
        if(node.getRight()!=null&&node.getLeft()!=null){
            node.setLeftType(1);
        }
        if(pre!=null&&pre.getRight()==null){
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;
        threadBinaryTree(node.getLeft());
        threadBinaryTree(node.getRight());
    }
    public void traversePre(){
        HeroNode node = root;
        while (node!=null){
            while (node.getLeftType()==1){
                System.out.println(node);
                node = node.getLeft();///打印第一个
            }
            System.out.println(node);///打印第二个
            node = node.getRight();
        }
    }


    public HeroNode getRoot() {
        return root;
    }

    public void setRoot(HeroNode root) {
        this.root = root;
    }
}
