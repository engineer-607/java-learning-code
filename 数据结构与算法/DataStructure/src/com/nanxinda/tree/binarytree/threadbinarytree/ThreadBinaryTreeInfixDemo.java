package com.nanxinda.tree.binarytree.threadbinarytree;


/// 线索二叉树基本介绍
/// 1.n个结点的二叉链表中含有n+1【公式2n-（n+1）=n+1】个空指针，利用二叉链表中的空指针域，存放指向该节点
/// 在某种遍历次序下的前驱和后继节点的指针（这种附加的指针称为“线索”）
/// 2.这种加上了线索的二叉链表称为线索链表，相应的二叉树称为线索二叉树。根据二叉树的性质不同可以分为
/// 前序线索二叉树、中序线索二叉树和后序线索二叉树
/// 3.在遍历过程中，一个节点的前一个节点被称为前驱节点
/// 4.在遍历过程中，一个节点的后一个节点被称为后继节点
public class ThreadBinaryTreeInfixDemo {
    public static void main(String[] args) {
        ThreadBinaryTreeInfix tree = new ThreadBinaryTreeInfix();
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
        tree.traverseInfix();
    }
}
class ThreadBinaryTreeInfix {
    private HeroNode root;
    private HeroNode pre = null;
    public void threadBinaryTree(){
        this.threadBinaryTree(root);
    }
    private void threadBinaryTree(HeroNode heroNode){
        /// 递归的结束条件
        if(heroNode==null){
            return;
        }
        /// 中序线索二叉树
        threadBinaryTree(heroNode.getLeft());
        /// 设置前驱节点
        if(heroNode.getLeft()==null){
            heroNode.setLeft(pre);
            heroNode.setLeftType(1);
        }
        /// 设置后驱节点(从当前节点的角度来看)
        if(pre!=null&&pre.getRight()==null){
            pre.setRight(heroNode);
            pre.setRightType(1);
        }
        /// 最重要的一步，将当前节点设置为pre
        pre = heroNode;
        threadBinaryTree(heroNode.getRight());

    }
    public void traverseInfix(){
        HeroNode node = root;
        while (node!=null){
            /// 找到中序遍历的开始节点
            node = getStart(node);
            /// 根据后驱指向向下遍历
            while (node.getRightType()==1){
                System.out.println(node);
                node = node.getRight();
            }
            System.out.println(node);///这个是用来打印那些右节点不为前驱节点的节点
            node = node.getRight();
            /// 当发现得到的节点的右节点并不是前驱节点，意味着需要再次一个节点作为下一个阶段的开始节点
        /// 即再次进入循环
        }
    }
    private HeroNode getStart(HeroNode node){
        HeroNode temp = node;
        while (true) {
            if (temp.getLeftType() == 1) {
                return temp;
            }
            temp = temp.getLeft();
        }
    }

    public HeroNode getRoot() {
        return root;
    }

    public void setRoot(HeroNode root) {
        this.root = root;
    }
}