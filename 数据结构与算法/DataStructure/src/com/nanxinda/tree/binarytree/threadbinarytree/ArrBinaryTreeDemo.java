package com.nanxinda.tree.binarytree.threadbinarytree;

public class ArrBinaryTreeDemo {
    /// 1)顺序二叉树通常只考虑完全二叉树
    /// 2)第n个元素的左子节点为2*n+1
    /// 3)第n个元素的右子节点为2*n+2
    /// 4)第n个元素的父节点为(n-1)/2
    public static void main(String[] args) {
        ///前序，中序，后序其实指的是父节点是第一个、第二个还是最后一个被遍历
        /// 前序（根-》左-》右）
        /// 中序（左-》根-》右）
        /// 后序（左-》右-》根）
        int[] array = {1,2,3,4,5,6,7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(array);
        arrBinaryTree.preOrder();//1,2,4,5,3,6,7
        System.out.println("===========");
        arrBinaryTree.infixOrder();//4,2,5,1,6,3,7
        System.out.println("===========");
        arrBinaryTree.postOrder();//4,5,2,6,7,3,1
    }
}
class ArrBinaryTree {
    private int[] array;
    public ArrBinaryTree(int[] array){
        this.array = array;
    }
    /// 前序遍历
    public void preOrder(){
        this.preOrder(0);
    }
    private void preOrder(int index){
        if(array==null||array.length==0){
            System.out.println("二叉树为空，无法遍历");
            return;
        }
        System.out.println(array[index]);
        if((index*2+1)<array.length){
            preOrder(index*2+1);
        }
        if((index*2+2)<array.length){
            preOrder(index*2+2);
        }
    }
    /// 中序遍历
    public void infixOrder(){
        infixOrder(0);
    }
    private void infixOrder(int index){
        if(array==null||array.length==0){
            System.out.println("二叉树为空，无法遍历");
            return;
        }
        if((index*2+1)<array.length){
            infixOrder(index*2+1);
        }
        System.out.println(array[index]);
        if((index*2+2)<array.length){
            infixOrder(index*2+2);
        }
    }
    /// 后序遍历
    public void postOrder(){
        this.postOrder(0);
    }
    private void postOrder(int index){
        if(array==null||array.length==0){
            System.out.println("二叉树为空，无法遍历");
            return;
        }
        if((index*2+1)<array.length){
            postOrder(index*2+1);
        }
        if((index*2+2)<array.length){
            postOrder(index*2+2);
        }
        System.out.println(array[index]);
    }

}
