package com.nanxinda.tree.binarybalancedtree.primary;


import java.util.ArrayList;

public class BinaryBalancedTreeDemo {
    public static void main(String[] args) {
        //RR型添加没问题
        int[] arr = {4,3,6,5,7,8};
        BinaryBalancedTree binaryBalancedTree = new BinaryBalancedTree();
        for (int j : arr) {
            binaryBalancedTree.add(new Node(j));
        }
        binaryBalancedTree.infixOrder();
        System.out.println("=========");
        //LR型添加没问题
        int[] arr1 = {8, 6, 7, 5};
        BinaryBalancedTree binaryBalancedTree1 = new BinaryBalancedTree();
        for (int j : arr1) {
            binaryBalancedTree1.add(new Node(j));
        }
        binaryBalancedTree1.infixOrder();
        //LL型添加没有问题
        System.out.println("========");
        int[] arr2 = {8, 6, 9, 5, 7};
        BinaryBalancedTree binaryBalancedTree2 = new BinaryBalancedTree();
        for (int j : arr2) {
            binaryBalancedTree2.add(new Node(j));
        }
        binaryBalancedTree2.infixOrder();
        //RL型添加没问题
        System.out.println("========");
        int[] arr3 = {8,10,9};
        BinaryBalancedTree binaryBalancedTree3 = new BinaryBalancedTree();
        for (int j : arr3) {
            binaryBalancedTree3.add(new Node(j));
        }
        binaryBalancedTree3.infixOrder();
        //最终测试通过
        System.out.println("========");
        int[] arr4 = {1,2,3,4,5,6,7,8};
        BinaryBalancedTree binaryBalancedTree4 = new BinaryBalancedTree();
        for (int j : arr4) {
            binaryBalancedTree4.add(new Node(j));
        }
        binaryBalancedTree4.infixOrder();







    }
}

/**
 * 二叉平衡树是避免二叉排序树退化成类似链表的形式，即查找时间复杂度由O(log N)退化成O(n)
 * 1.前提：二叉搜索树
 * 2.特殊点：所有节点的（左子树高度-右子树高度）（平衡因子）的绝对值<=1
 * 3.查找、插入、构建、删除的过程和二叉搜索树一致，只是失衡的时候需要调整
 * 4.失衡时调整的操作：
 * 4.1左旋时，冲突的左孩变右孩
 * 4.2右旋时，冲突的右孩变左孩
 * 5.失衡的四种状态：
 * 5.1 LL型：失衡节点的状态是2，失衡节点的左孩失衡因子是1 进行右旋即可
 * （失衡节点的树是左子树高，以失衡节点的左孩子为根节点的子树高，所以直接右旋）
 * 5.2 RR型：失衡节点的状态是-2，失衡节点的右孩子失衡因子是-1 进行左旋即可
 * （失衡节点的树是右子树高，以失衡节点的右孩子为根节点的子树高，所以直接左旋）
 * 5.3 LR型：失衡节点的状态为2，失衡节点的左孩子的平衡因子为-1 左旋左孩子，然后右旋
 * （失衡节点的树是左子树高，以失衡节点的左孩子为根节点的右子树高，所以左旋左孩子，然后右旋）
 * 5.4 RL型：失衡节点的状态为-2，失衡节点的右孩子为平衡因子为1，右旋右孩子，然后左旋
 * （失衡节点的树是右子树高，以失衡节点的右孩子为根节点的左子树高，所以右旋右孩子，然后左旋）
 * 5.5 如果发现多个失衡节点，按最近的来
 *
 */
class BinaryBalancedTree{
    static Node root;
    static Node temp;
    static ArrayList<Node> unbalancedList;

    public BinaryBalancedTree(){
        root = null;
        temp = null;
        unbalancedList = new ArrayList<>();
    }
    //遍历
    public void infixOrder(){
        if(root==null){
            System.out.println("该树为空，无法遍历");
            return;
        }
        root.infixOrder(root);
    }
    //添加
    /// 待添加平衡机制
    public void add(Node node){
        if(root==null){
            root = node;
        }else {
            root.add(node);
        }
        adjustNode();
    }
    //删除
    /// 待添加平衡机制
    public void delNodeAdvanced(int num){
        if(root==null){
            return;
        }
        root = delNodeAdvanced(root,num);
        adjustNode();
    }
    private Node delNodeAdvanced(Node root, int num){
        //如果root等于null，其实代表删除失败
        if(root==null){
            return null;
        }
        if(root.value==num){
            //分三种情况
            //1.如果只是叶子节点，直接置空
            if(root.left==null&&root.right==null){
                return null;
            }//2.如果是非叶子节点，但为双叶子节点
            else if(root.left!=null&&root.right!=null) {
                //先获得右子树最小节点，将得到的值赋给当前节点
                //然后鸠占鹊巢删除最小节点
                root.value = findMin(root.right);
                root.right = delNodeAdvanced(root.right,root.value);
                return root;
            }//3.如果是非叶子节点，但为单叶子节点
            else {
                if(root.left!=null){
                    return root.left;
                }
                else{
                    return root.right;
                }
            }
        }else if(num>root.value){
            //大于该节点，向右查找，将查找得到的结果拼接到父节点
            root.right = delNodeAdvanced(root.right,num);
            return root;
        }else {
            //小于该节点，向左查找，将查找得到的结果拼接到父节点
            root.left = delNodeAdvanced(root.left,num);
            return root;
        }
    }
    private int findMin(Node node){
        while (node.left!=null){
            node = node.left;
        }
        return node.value;
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
    //平衡机制
    //1.递归探出每个节点的左右子树的深度，然后二者相减存入每个节点的平衡因子中
    private void countBalanceAPI(Node node){
        temp = node;
        countBalance(node);
    }
    private int countBalance(Node node){
        if(node==null){
            return 0;
        }
        int left = countBalance(node.left);
        int right = countBalance(node.right);
        node.balance = left-right;
        if(Math.abs(node.balance)>1){
            unbalancedList.add(node);
        }
        if(node==temp){
            return temp.balance;
        }
        return left>=right?left+1:right+1;
    }
    //2.找到每个节点的平衡因子之后，开始查找平衡因子绝对值大于1的节点
    //该失衡节点满足的条件，当前节点大于1，并且左右节点小于等于1，如果左右节点也大于1，那么先解决
    //左右节点
    private Node getUnbalanceStart(){
        //该集合存在的意义并不是说，调整好集合中的每一个失衡节点，而是为了找到本次调整的节点，以及如果该节点并不是根节点需要将该节点调整后的子树与其父失衡节点相连
        //(因为只要有一个失衡节点被调整好，其他失衡节点的状态很有可能发生改变，所以每个节点调整好之后需要对该集合进行重置)
        /// 没考虑全面，该集合并不能查找失衡节点的父节点，需要专门写方法查找
        for (Node node : unbalancedList) {
            if((node.left==null||Math.abs(node.left.balance)<=1)&&((node.right==null)||Math.abs(node.right.balance)<=1)){
                return node;
            }
        }
        return null;
    }
    private void adjustNode(){
        /// 因为每一次添加和删除都会进行调整，所以根本上只需要考虑三个节点的状态，对其进行调整即可（❌）
        /// 上面的说法是错误的，没有考虑全面
        //如果不为空的话就一直进行调整
        do {
            countBalanceAPI(root);
            Node node = getUnbalanceStart();
            if(node==null){
                break;
            }
            //1. LL型：失衡节点的状态是2，失衡节点的左孩失衡因子是1 进行右旋即可
            //      8(node)
            //     / \
            //  6(y)  9
            //   / \
            //  4   7(B)
            // /
            //3
            Node preNode = searchPre(node);
            Node resultRoot = null;
            if(node.balance==2&&node.left.balance==1){
                Node y = node.left;
                Node B = y.left;
                y.right = node;
                node.left = B;
                //      6
                //     / \
                //    4   8
                //   /   / \
                //  3   7   9
                resultRoot = y;
            }else if(node.balance==-2&&node.right.balance==-1){
                //2. RR型：失衡节点的状态是-2，失衡节点的右孩子失衡因子是-1 进行左旋即可
                //      4(node)
                //     / \
                //    3   6(y)
                //       / \
                //    5(B)  7
                //           \
                //            8
                Node y = node.right;
                Node B = y.left;
                y.left = node;
                node.right = B;
                //        6
                //       / \
                //      4   7
                //     / \   \
                //    3  5    8
                resultRoot = y;
            }else if(node.balance==2&&node.left.balance==-1) {
                //3. LR型：失衡节点的状态为2，失衡节点的左孩子的平衡因子为-1 左旋左孩子，然后右旋
                //        10  （node)
                //       /
                //      5 (y)
                //       \
                //        8 (B)
                Node y = node.left;
                Node B = y.right;
                B.left = y;
                node.left = B;
                B.right = node;
                y.right = null;
                node.left = null;
                //        8
                //       / \
                //      5   10
                resultRoot = B;
            }else if(node.balance==-2&&node.right.balance==1){
                //4. RL型：失衡节点的状态为-2，失衡节点的右孩子为平衡因子为1，右旋右孩子，然后左旋
                //          5(node)
                //           \
                //            9(x)
                //           /
                //        7(B)
                Node y = node.right;
                Node B = y.left;
                B.right = y;
                node.right = null;
                B.left = node;
                y.left = null;
                //        7
                //       / \
                //      5   9
                resultRoot = B;
            }
            if(preNode!=null) {
                if(resultRoot.value>preNode.value){
                    preNode.right = resultRoot;
                }else {
                    preNode.left = resultRoot;
                }
            }else {
                root = resultRoot;
            }
            unbalancedList.clear();
        }while (unbalancedList!=null);
    }
    private Node searchPre(Node node){
        if(node == root){
            return null;
        }
        return root.searchPre(node);

    }

}
class Node{
    int value;
    Node left;
    Node right;
    int balance;

    public Node(int value) {
        this.value = value;
    }

    //添加
    public void add(Node node){
        if(node==null){
            return;
        }
        if(node.value>this.value){
            if(this.right==null){
                this.right = node;
                return;
            }
            this.right.add(node);
        }else if(node.value<this.value){
            if(this.left==null){
                this.left = node;
                return;
            }
            this.left.add(node);
        }else {
            if(this.right!=null&&this.left!=null){
                this.right.add(node);
                return;
            }
            if(this.right==null){
                this.right = node;
                return;
            }
            this.left = node;
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