package com.nanxinda.tree.binarytree.threadbinarytree;

public class HeroNode{
    private String name;
    private int num;
    private HeroNode left;
    private HeroNode right;

    private int leftType;
    private int rightType;
    /// 1表示左子节点为前驱节点，0表示左子节点为左子树

    public HeroNode(int num,String name) {
        this.num = num;
        this.name = name;
    }
    /// 遍历
    public void preOrder(){
        System.out.println(this);
        if(this.left!=null){
            this.left.preOrder();
        }
        if(this.right!=null){
            this.right.preOrder();
        }
    }
    public void infixOrder(){
        if(this.left!=null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if(this.right!=null){
            this.right.infixOrder();
        }
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public void postOrder(){
        if(this.left!=null){
            this.left.postOrder();
        }
        if(this.right!=null){
            this.right.postOrder();
        }
        System.out.println(this);
    }
    /// 查找
    public HeroNode preSearch(int num){
        if(this.num==num){
            return this;
        }
        HeroNode resNode = null;
        if(this.left!=null){
            resNode =  this.left.preSearch(num);
        }
        if(resNode!=null){
            return resNode;
        }
        if(this.right!=null){
            resNode =  this.right.preSearch(num);
        }
        return resNode;
    }
    public HeroNode infixSearch(int num){
        HeroNode resNode = null;
        if(this.left!=null){
             resNode = this.left.infixSearch(num);
        }
        if(resNode!=null){
            return resNode;
        }
        if(this.num==num){
            return this;
        }
        if(this.right!=null){
            resNode = this.right.infixSearch(num);
        }
        return resNode;
    }
    public HeroNode postSearch(int num){
        HeroNode resNode = null;
        if(this.left!=null) {
            resNode = this.left.postSearch(num);
        }
        if(resNode!=null){
            return resNode;
        }
        if(this.right!=null){
            resNode = this.right.postSearch(num);
        }
        if (this.num == num) {
            return this;
        }
        return resNode;
    }
    /// 删除
    public void deleteFirstWay(int no){
        if(this.left!=null&&this.left.num==no){
            this.left=null;
            return;
        }
        if(this.right!=null&&this.right.num==no){
            this.right=null;
            return;
        }
        if(this.right!=null){
            this.right.deleteFirstWay(no);
        }
        if(this.left!=null){
            this.left.deleteFirstWay(no);
        }
    }
    public void deleteSecondeWay(int no){
        if(this.left!=null&&this.left.num==no){
            HeroNode node = this.left;
            if(node.left!=null) {
                HeroNode right = node.getRight();
                this.left = node.getLeft();
                node = this.left;
                node.setLeft(null);
                node.setRight(right);
            }else {
                this.left = node.getRight();
            }
            return;
        }
        if(this.right!=null&&this.right.num==no){
            HeroNode node = this.right;
            if(this.right.left!=null) {
                HeroNode right = node.getRight();
                this.right = node.getLeft();
                node = this.right;
                node.setLeft(null);
                node.setRight(right);
            }else {
                this.right = node.getRight();
            }
            return;
        }
        if(this.right!=null){
            this.right.deleteFirstWay(no);
        }
        if(this.left!=null){
            this.left.deleteFirstWay(no);
        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "num=" + num +
                ", name='" + name + '\'' +
                '}';
    }
}
