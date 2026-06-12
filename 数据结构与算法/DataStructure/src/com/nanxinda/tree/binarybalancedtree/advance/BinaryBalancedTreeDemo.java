package com.nanxinda.tree.binarybalancedtree.advance;

public class BinaryBalancedTreeDemo {
    public static void main(String[] args) {
        int[] arr = {10,5,3,8,9,12,15,13};
        BalancedTree balancedTree = new BalancedTree();
        for (int j : arr) {
            balancedTree.add(new Node(j));
        }
        balancedTree.infixOrder();
    }
}
class BalancedTree{
    Node root;
    public void add(Node root){
        if(this.root==null){
            this.root = root;
        }else {
            this.root = this.root.add(root);
        }
    }
    public void infixOrder(){
        if(root==null){
            System.out.println("该树为空，无法遍历");
            return;
        }
        root.infixOrder(root);
    }

}
class Node{
    int value;
    Node left;
    Node right;
    int height;

    public Node(int value) {
        this.value = value;
    }

    public int getHeight() {
        return Math.max(this.left==null?0:left.height,this.right==null?0:right.height)+1;
    }
    public int getLeftHeight(){
        if(this.left==null){
            return 0;
        }
        return left.getHeight();
    }
    public int getRightHeight(){
        if(this.right==null){
            return 0;
        }
        return right.getHeight();
    }



    //添加
    public Node add(Node root){
        //添加新节点的时候，将路径上的节点切割成一个个子树，依次检查过平衡因子
        //没有问题之后再拼接上去，如果有问题，当前子树就进行调整，然后返回调整后子树的
        //根节点
        if(root==null){
            return this;
        }
        if(root.value>this.value){
            if(this.right==null){
                this.right = root;
            }else {
                this.right = this.right.add(root);
            }
        }else if(root.value<this.value){
            if(this.left==null){
                this.left = root;
            }else {
                this.left = this.left.add(root);
            }
        }else {
            //添加权值相等的情况暂时不考虑那么多
            if(this.right==null){
                this.right = root;
            }else if(this.left==null){
                this.left = root;
            }else if(this.right.value==root.value){
                this.right = this.right.add(root);
            }else if(this.left.value==root.value){
                this.left = this.left.add(root);
            }else {
                System.out.println("无法添加");
            }
        }
        root.height = root.getHeight();
        this.height = getHeight();
        int gap = getLeftHeight()-getRightHeight();

        if(gap>1){
            int balance = 0;
            //1. LL型：失衡节点的状态是2，失衡节点的左孩失衡因子是1 进行右旋即可
            //      8(this)
            //     / \
            //  6(y)  9
            //   / \
            //  4   7(B)
            // /
            //3

            //      6
            //     / \
            //    4   8
            //   /   / \
            //  3   7   9
            if((balance = this.left.getLeftHeight()-this.left.getRightHeight())==1||balance==0) {
                Node y = this.left;
                Node B = y.right;
                y.right = this;
                this.left = B;
                return y;
            }
            //2. LR型：失衡节点的状态为2，失衡节点的左孩子的平衡因子为-1 左旋左孩子，然后右旋
            //        10  （this)
            //       /
            //      5 (y)
            //       \
            //        8 (B)

            //        8
            //       / \
            //      5   10
            else {
                Node y = this.left;
                Node B = y.right;
                B.left = y;
                this.left = B;
                B.right = this;
                y.right = null;
                this.left = null;
                return B;
            }
        }else if(Math.abs(getLeftHeight()-getRightHeight())<=1){
            return this;
        }else {
            int balance = 0;
            //3. RR型：失衡节点的状态是-2，失衡节点的右孩子失衡因子是-1 进行左旋即可
            //      4(this)
            //     / \
            //    3   6(y)
            //       / \
            //    5(B)  7
            //           \
            //            8

            //        6
            //       / \
            //      4   7
            //     / \   \
            //    3  5    8
            if((balance = this.right.getLeftHeight() - this.right.getRightHeight())==-1||balance==0){
                Node y = this.right;
                Node B = y.left;
                y.left = this;
                this.right = B;
                return y;
            }
            //4. RL型：失衡节点的状态为-2，失衡节点的右孩子为平衡因子为1，右旋右孩子，然后左旋
            //          5(this)
            //           \
            //            9(y)
            //           /
            //        7(B)

            //        7
            //       / \
            //      5   9
            else {
                Node y = this.right;
                Node B = y.left;
                B.right = y;
                this.right = null;
                B.left = this;
                y.left = null;
                return B;
            }
        }
    }


    public Node searchPre(Node node){
        if((this.left!=null&&this.left==node)||(this.right!=null&&this.right==node)){
            return this;
        }else if(node.value>this.value&&this.left!=null){
            return this.left.searchPre(node);
        }else if(node.value<this.value&&this.right!=null){
            return this.right.searchPre(node);
        }else {
            return null;
        }
    }

    //查找
    private Node search(int num,Node node){
        if(node==null){
            return null;
        }
        if(node.value==num){
            return node;
        }
        if(num>node.value){
            return search(num,node.right);
        }
        return search(num, node.left);
    }
    //遍历
    public void infixOrder(Node node){
        if(node == null){
            return;
        }
        infixOrder(node.left);
        System.out.println(node);
        infixOrder(node.right);
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}