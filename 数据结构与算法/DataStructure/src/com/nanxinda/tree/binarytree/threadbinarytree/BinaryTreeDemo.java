package com.nanxinda.tree.binarytree.threadbinarytree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        /*
        遍历
        1）前序遍历
        1.先输出当前节点（初始的时候时root节点）
        2.如果左子节点不为空，则递归继续前序遍历
        3.如果右子节点不为空，则递归继续前序遍历
        2）中序遍历
        1.如果当前节点的左子节点不为空，则递归中序遍历
        2.输出当前节点
        3.如果当前节点的右子节点不为空，则递归中序遍历
        3）后序遍历
        1.如果当前节点的左子节点不为空，则递归中序遍历
        2.如果当前节点的右子节点不为空，则递归中序遍历
        3.输出当前节点
         */
        BinaryTree binaryTree = new BinaryTree();
        HeroNode heroNode = new HeroNode(1,"宋江");
        HeroNode heroNode1 = new HeroNode(2,"吴用");
        HeroNode heroNode2 = new HeroNode(3,"卢俊义");
        HeroNode heroNode3 = new HeroNode(4,"林冲");
        HeroNode heroNode4 = new HeroNode(5,"关胜");
        binaryTree.root = heroNode;
        heroNode.setLeft(heroNode1);
        heroNode.setRight(heroNode2);
        heroNode2.setRight(heroNode3);
        heroNode2.setLeft(heroNode4);
        System.out.println("=======前序遍历=======");
        binaryTree.preOrder();//1，2，3，5，4
        System.out.println("=======后序遍历=======");
        binaryTree.postOrder();//2，5，4，3，1
        System.out.println("=======中序遍历=======");
        binaryTree.infixOrder();//2，1，5，3，4
        /*
        前序查找
        1.先判断当前节点当前节点的num是否等于要查找的
        2.如果相等，则返回当前节点
        3.如果不等，则判断当前节点的左子节点是否为空，如果不为空，则递归前序查找
        4.如果左递归前序查找，找到节点，则返回，否则继续判断，当前的节点的右子节点是否为空
        如果不为空，则继续右递归前序查找
        中序查找
        1.判断当前节点的左节点是否为空，如果不为1空，则递归中序查找
        2.如果找到，则返回，如果没有找到，就和当前节点比较，如果是则返回当前节点，否则继续
        进行右递归的中序查找
        3.如果右递归中序查找，找到就返回，否则就返回
        后续查找
        1.判断当前节点的左子节点是否为空，如果不为空，则递归后续查找
        2.如果找到，就返回，如果没有找到，就判断当前节点的右子节点是否为空，如果不为空
        则右递归进行后序查找，如果找到，就返回
        3.就和当前节点进行比较，如果是就返回，否则就返回null
         */
        System.out.println("=========查找=========");
        System.out.println(binaryTree.preSearch(4));
        System.out.println(binaryTree.infixSearch(3));
        System.out.println(binaryTree.postSearch(2));
        /*
        完成删除节点的操作
        规定：
        1.如果删除的节点是叶子节点，则删除该节点
        2.如果删除的节点是非叶子节点，则删除该子树
        思路：
        1.因为我们的二叉树是单向的，所以我们是判断当前结点的子节点是否需要删除节点，而不能判断当前节点是不是
        需要删除节点
        2.如果当前节点的左子节点不为空，并且左子节点就是要删除节点，就将this.left = null;并且就返回
        3.如果当前节点的右子节点不为空，并且右子节点就是删除节点，就将this.right = null;并且就返回
        4.如果第2步和第3步没有删除节点，那么我们就需要向左子节点进行递归删除
        5.如果第4步也没有删除节点，则应该向右子树进行递归删除
        6.考虑如果树是空树root，如果只有一个root节点，则等价将二叉树置空
         */
        System.out.println("=========删除=========");
        binaryTree.delNodeSecondWay(3);
        binaryTree.preOrder();
    }
}
class BinaryTree{
    public HeroNode root;
    /// 遍历
    public void preOrder(){
        if(root!=null) {
            root.preOrder();
        }
    }
    public void infixOrder(){
        if(root!=null){
            root.infixOrder();
        }
    }
    public void postOrder(){
        if(root!=null){
            root.postOrder();
        }
    }
    /// 查找
    public HeroNode preSearch(int num){
        if(root!=null){
            return root.preSearch(num);
        }
        return null;
    }
    public HeroNode infixSearch(int num){
        if(root!=null){
            return root.infixSearch(num);
        }
        return null;
    }
    public HeroNode postSearch(int num){
        if(root!=null){
            return root.postSearch(num);
        }
        return null;
    }
    /// 删除
    public void delNodeFirstWay(int no){
        if(root==null){
            System.out.println("该树为空树，无法删除");
            return;
        }
        if(root.getNum() == no){
            root = null;
            return;
        }
        root.deleteFirstWay(no);
    }

    public void delNodeSecondWay(int no){
        if(root==null){
            System.out.println("该树为空树，无法删除");
            return;
        }
        if(root.getNum() == no){
            if(root.getLeft()!=null){
                HeroNode right = root.getRight();
                root = root.getLeft();
                root.setLeft(null);
                root.setRight(right);
            }else {
                root = root.getRight();
            }
        }
        root.deleteSecondeWay(no);

    }

}
