package com.nanxinda.tree.binarysorttree;
@SuppressWarnings({"all"})
/**
 * 二叉排序树：对于二叉排序树的任何一个非叶子节点，要求左子节点的值比当前节点的值小，右子节点的值比当前节点
 * 的值大（特别说明：如果有相同的值，可以将该节点放在左子节点或者右子节点）
 */
/**
 * 二叉排序树实现 - 学习笔记
 *
 * 关于二叉排序树删除操作的两种实现策略总结：
 *
 * ============================================================================
 * 一、两种删除策略对比
 * ============================================================================
 *
 * 【策略1：值替换法】（对应 delNodeAdvanced 方法）
 *
 * 原理：
 *   - 删除双子树节点时，找到右子树最小节点，将其 value 复制到当前节点
 *   - 然后递归删除那个最小节点（它必然是叶子或单子树）
 *   - 本质是：替换值，保留原节点对象
 *
 * 优点：
 *   - 代码简洁，利用递归自动处理父子关系
 *   - 不需要记录父节点（pre），不需要方向标识
 *   - 逻辑清晰，易于理解和维护
 *
 * 缺点：
 *   - 只适用于基本类型或简单值对象
 *   - 如果节点包含复杂数据（如 User 对象有 name、age 等），只复制比较值会丢失其他数据
 *   - 如果节点有其他业务数据，需要整体深拷贝，增加开销
 *
 * 适用场景：
 *   - 节点数据简单，只有比较值一个字段
 *   - 内存中的树结构（如 Java TreeMap）
 *   - 不需要保留原节点对象身份的场景
 *
 *
 * 【策略2：节点替换法】（对应原版 del_DoubleLeaf_Tree 方法）
 *
 * 原理：
 *   - 找到右子树最小节点（temp）
 *   - 修改父节点指针，让父节点指向 temp
 *   - 将原节点的左右子树接到 temp 上
 *   - 本质是：替换节点引用，不复制数据
 *
 * 优点：
 *   - 数据完整，节点所有字段都保留
 *   - 适用于任何类型，包括复杂引用类型
 *   - 不需要深拷贝，性能稳定
 *
 * 缺点：
 *   - 代码复杂，需要手动处理指针
 *   - 需要记录父节点（pre）和方向标识
 *   - 容易出错，调试困难
 *
 * 适用场景：
 *   - 节点包含复杂业务数据
 *   - 磁盘上的树结构（如数据库 B+树）
 *   - 需要保留节点对象身份的场景
 *
 * ============================================================================
 * 二、实际类库中的应用
 * ============================================================================
 *
 * 【Java TreeMap】- 使用值替换法
 *
 * 原因：
 *   - TreeMap 存储在内存中，复制 Entry 对象（key + value）成本很低
 *   - 内存操作速度快，复制几十字节无影响
 *   - 代码简洁，易于维护
 *
 * 源码简化版：
 *   private void deleteEntry(Entry<K,V> p) {
 *       if (p.left != null && p.right != null) {
 *           Entry<K,V> s = successor(p);  // 找后继节点
 *           p.key = s.key;                // 复制 key
 *           p.value = s.value;            // 复制 value
 *           p = s;                        // 转而去删除后继节点
 *       }
 *       // 此时 p 最多只有一个子节点，直接删除
 *   }
 *
 *
 * 【数据库 B+树】（如 MySQL InnoDB）- 使用节点替换法
 *
 * 原因：
 *   - B+树存储在磁盘上，每个节点对应一个磁盘块（如 16KB）
 *   - 复制整个节点数据意味着读写磁盘，成本极高
 *   - 更高效的方式是修改节点间的引用（指针），只操作少量数据
 *   - 叶子节点间有链表指针，节点替换时需要维护这些指针
 *
 * 操作示意：
 *   - 删除内部节点的键 → 从右子节点借最小的键
 *   - 修改父节点指针指向借来的节点
 *   - 如果子节点下溢，合并兄弟节点（调整引用关系）
 *
 *
 * 【Redis 跳表】- 混合使用
 *
 * 原因：
 *   - Redis 的有序集合（zset）底层用跳表实现
 *   - 删除时，找到目标节点后直接修改前后节点的指针
 *   - 跳表是多层结构，需要同时修改多层指针
 *   - 本质上是节点替换法，因为节点包含 score 和 member 等业务数据
 *
 * ============================================================================
 * 三、个人总结
 * ============================================================================
 *
 * 今天完成的重构让我认识到：
 *
 * 1. 两种删除策略没有绝对的优劣，选择取决于：
 *    - 数据存储位置（内存 vs 磁盘）
 *    - 数据复杂度（简单值 vs 复杂对象）
 *    - 性能要求（复制成本 vs 指针操作复杂度）
 *
 * 2. 值替换法适用于：
 *    - 内存数据结构（TreeMap、HashSet 等）
 *    - 节点数据简单或可快速复制
 *
 * 3. 节点替换法适用于：
 *    - 磁盘数据结构（B+树、LSM树等）
 *    - 节点包含复杂业务数据
 *    - 需要保持对象身份的场景
 *
 * 4. 作为程序员，两种方法都要掌握，在实际开发中根据场景选择合适的方式
 *
 * @author [庞绍祥]
 * @date 2026-03-29
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7,2,3,10,12,13,1,9,8,5,6};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }
        binarySortTree.infixOrder();
        binarySortTree.delNodeAdvanced(4);
        binarySortTree.infixOrder();
//        System.out.println(binarySortTree.delNode(7));
//        binarySortTree.infixOrder();
    }
}
class BinarySortTree{
    static Node root;
    public void add(Node node){
        if(root==null){
            root = node;
        }else {
            root.add(node);
        }
    }
    public void infixOrder(){
        if(root==null){
            System.out.println("该树为空，无法遍历");
            return;
        }
        root.infixOrder(root);
    }
    //先只考虑删除叶子节点
    public boolean delNode(int num){
        Node node = search(num,root);
        if(node.left==null&&node.right==null){
            return delLeafNode(num);
        }else if(node.left!=null&&node.right!=null){
            return del_DoubleLeaf_Tree(num);
        }else {
            return del_SingleLeaf_Tree(num);
        }
    }
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
    private boolean delLeafNode(int num){
        if(root.value==num){
            root = null;
            return true;
        }else if(num>root.value){
            if(root.right==null){
                return false;
            }
            Node.pre = root;
            return root.right.delLeafNode(num,"0");
        }else {
            if(root.left==null){
                return false;
            }
            Node.pre = root;
            return root.left.delLeafNode(num,"1");
        }
    }

    private boolean del_SingleLeaf_Tree(int num){
        if(root.value ==num){
            if(root.left!=null){
                root = root.left;
                return true;
            }else if(root.right!=null){
                root = root.right;
                return true;
            }else {
                return false;
            }
        }else if(num>root.value){
            if(root.right==null){
                return false;
            }
            Node.pre = root;
            return root.right.del_SingleLeaf_Tree(num,"0");
        }else {
            if(root.left==null){
                return false;
            }
            Node.pre = root;
            return root.left.del_SingleLeaf_Tree(num,"1");
        }
    }

    private boolean del_DoubleLeaf_Tree(int num){
        if(root.value==num){
            return root.del_DoubleLeaf_Tree(num,"");
        }else if(num>root.value){
            if(root.right==null){
                return false;
            }
            Node.pre = root;
            return root.right.del_DoubleLeaf_Tree(num,"0");
        }else {
            if(root.left==null){
                return false;
            }
            Node.pre = root;
            return root.left.del_DoubleLeaf_Tree(num,"1");
        }
    }
    /// 将所有的删除方法统一到一起，使用递归进行删除
    //1.找到同时进行删除，删除操作：
    //1）如果只是叶子节点，直接置空，返回null给父节点
    //2）如果是非叶子节点，但为单叶子节点，直接返回当前节点的下一个节点
    //3）如果是非叶子节点，但为双叶子节点，在其右子树找到最小的节点，然后通过1.1方式将其删除返回，将原本的节点的权值进行修改
    //再将其返回拼接到父节点上
    //2.删除之后把该节点视为一个子树的根节点，拼接到父节点
    public void delNodeAdvanced(int num){
        if(root==null){
            return;
        }
        root = delNodeAdvanced(root,num);
    }
    private Node delNodeAdvanced(Node root,int num){
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
}
@SuppressWarnings({"all"})
class Node{
    int value;
    Node left;
    Node right;
    static Node pre = null;


    public Node(int value) {
        this.value = value;
    }

    public void infixOrder(Node node){
        if(node == null){
            return;
        }
        infixOrder(node.left);
        System.out.println(node);
        infixOrder(node.right);
    }

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
            if(this.left==null){
                this.left = node;
            }
        }
    }
    //删除
    //1.删除叶子节点

    /**
     * 删除叶子节点，并返回是否删除成功
     * @param num 需要删除的数字
     * @param direction 需要删除的节点是父节点的右节点（0）还是左子节点（1）
     * @return 是否成功删除
     */
    public boolean delLeafNode(int num,String direction){
        if(num==this.value){
            if(direction.equals("0")){
                pre.right = null;
                return true;
            }else if(direction.equals("1")){
                pre.left = null;
                return true;
            }else {
                return false;
            }
        }else if(num> this.value){
            if(this.right==null){
                return false;
            }
            pre = this;
            return this.right.delLeafNode(num,"0");
        }else {
            if(this.left==null){
                return false;
            }
            pre = this;
            return this.left.delLeafNode(num,"1");
        }
    }

    /**
     * 删除非叶子节点但为单叶子的子树
     * @param num 需要删除的数字
     * @param direction 该节点为父节点的左子树还是右子树
     * @return 返回是否正确删除
     */
    public boolean del_SingleLeaf_Tree(int num,String direction){
        if(this.value == num){
            if(direction.equals("0")){
                if(this.right!=null){
                    pre.right = this.right;
                    return true;
                }else if(this.left!=null){
                    pre.right = this.left;
                    return true;
                }
                return false;
            }else if(direction.equals("1")){
                if(this.right!=null){
                    pre.left = this.right;
                    return true;
                }else if(this.left!=null){
                    pre.left = this.left;
                    return true;
                }
                return false;
            }else {
                return false;
            }
        }else if(num>this.value){
            if(this.right==null){
                return false;
            }
            pre = this;
            return this.right.del_SingleLeaf_Tree(num,"0");
        }else {
            if(this.left==null){
                return false;
            }
            pre = this;
            return this.left.del_SingleLeaf_Tree(num,"1");
        }
    }

    /**
     * 删除有双叶子节点的子树，方法：找到要删除的节点，进入其右子树，然后一直向左
     * @param num 需要删除的数字
     * @param direction 该节点为父节点的左子树还是右子树
     * @return 返回是否正确删除
     */
    public boolean del_DoubleLeaf_Tree(int num,String direction){
        if(this.value==num){
            Node temp = this.right;
            Node preNode = this;
            while (true){
                if(temp.left==null){
                    break;
                }
                preNode = temp;
                temp = temp.left;
            }
            if(direction.equals("0")){
                pre.right = temp;
            }else if(direction.equals("1")){
                pre.left = temp;
            }else {
                if(this==BinarySortTree.root){
                    BinarySortTree.root = temp;
                }
            }
            Node right = this.right;
            Node left = this.left;
            preNode.left = null;
            temp.left = left;
            if(temp!=right) {
                temp.right = right;
            }
            return true;
        }else if(num>this.value){
            if(this.right==null){
                return false;
            }
            pre = this;
            return this.right.del_DoubleLeaf_Tree(num,"0");
        }else {
            if(this.left==null){
                return false;
            }
            pre = this;
            return this.left.del_DoubleLeaf_Tree(num,"1");
        }
    }
    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}