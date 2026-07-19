package com.zuochengyun.alorithm.binarytree;
@SuppressWarnings({"ALL"})
public class SerializeAndDeserializeAdvanced {
    //按层进行序列化
    //思路分析：
    // 1.需要队列来进行层序遍历
    // 2.序列化在遍历时看节点是否为空，为空#入字符串，不为空节点值入字符串
    // 同时用,进行分割
    // 3.反序列化对字符串按照,进行分割，同样如果元素不为#（即该节点不为空）入队
    // 如果该节点为空，便不入队，节点在弹出的时候依次遍历数组元素，如果为#直接父节点
    // 连接空指针，如果不为#，则根据值直接建立节点进行连接

    //具体实现
    //定义队列（数组直接赋值，只需要每次使用重置左右指针相当于刷新队列）
    TreeNode[] queue = new TreeNode[10001];
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        //定义序列化存储对象
        StringBuilder stringBuilder = new StringBuilder();
        //边界：根节点为空
        if(root!=null) {
            int r, l;
            r = l = 0;
            //根节点入队，并且存储对象记录根节点
            queue[r++] = root;
            stringBuilder.append(root.val+",");
            while (r!=l){
                //弹出元素
                //并且将弹出元素的左右节点入队,并且将弹出元素左右节点记录进入
                //存储对象
                int size = r - l;
                for (int i = 0; i < size; i++) {
                    TreeNode node = queue[l++];
                    if(node.left!=null){
                        queue[r++] = node.left;
                        stringBuilder.append(node.left.val+",");
                    }else {
                        stringBuilder.append("#,");
                    }
                    if(node.right!=null){
                        queue[r++] = node.right;
                        stringBuilder.append(node.right.val+",");
                    }else {
                        stringBuilder.append("#,");
                    }
                }
            }
            stringBuilder.deleteCharAt(stringBuilder.toString().length() - 1);
        }
        return stringBuilder.toString();

    }
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        //具体实现：
        //边界：字符串是否为空，为空直接返回
        if(data == null || data.isEmpty()){
            return null;
        }
        //将字符串分割得到数据源
        String[] strings = data.split(",");
        //同样先将头节点还原入队，接着循环弹出将弹出元素根据值进行还原
        int r,l,i;
        r = l = i = 0;
        TreeNode root  = new TreeNode(Integer.parseInt(strings[i++]));
        queue[r++] = root;
        while (r!=l){
            int size = r - l;
            for (int j = 0; j < size; j++) {
                TreeNode node = queue[l++];
                node.left = generate(strings,i++);
                node.right = generate(strings,i++);
                if(node.left!=null){
                    queue[r++] = node.left;
                }
                if(node.right!=null){
                    queue[r++] = node.right;
                }
            }
        }
        return root;
    }

    private TreeNode generate(String[] strings,int i){
        return strings[i].equals("#")?null:new TreeNode(Integer.parseInt(strings[i]));
    }

    @org.junit.Test(timeout = 1000)
    public void serialize_emptyTree_levelOrder() {
        org.junit.Assert.assertEquals(
                "",
                new SerializeAndDeserializeAdvanced().serialize(null));
    }

    @org.junit.Test(timeout = 1000)
    public void serialize_singleNode_levelOrder() {
        SerializeAndDeserializeAdvanced serializeAndDeserializeAdvanced = new SerializeAndDeserializeAdvanced();
        TreeNode root = serializeAndDeserializeAdvanced.new TreeNode(1);

        org.junit.Assert.assertEquals(
                "1,#,#",
                serializeAndDeserializeAdvanced.serialize(root));
    }

    @org.junit.Test(timeout = 1000)
    public void serialize_completeTree_levelOrder() {
        SerializeAndDeserializeAdvanced serializeAndDeserializeAdvanced = new SerializeAndDeserializeAdvanced();
        TreeNode root = serializeAndDeserializeAdvanced.new TreeNode(1);
        root.left = serializeAndDeserializeAdvanced.new TreeNode(2);
        root.right = serializeAndDeserializeAdvanced.new TreeNode(3);
        root.left.left = serializeAndDeserializeAdvanced.new TreeNode(4);
        root.left.right = serializeAndDeserializeAdvanced.new TreeNode(5);
        root.right.left = serializeAndDeserializeAdvanced.new TreeNode(6);
        root.right.right = serializeAndDeserializeAdvanced.new TreeNode(7);

        org.junit.Assert.assertEquals(
                "1,2,3,4,5,6,7,#,#,#,#,#,#,#,#",
                serializeAndDeserializeAdvanced.serialize(root));
    }

    @org.junit.Test(timeout = 1000)
    public void serialize_treeWithMissingChildren_levelOrder() {
        SerializeAndDeserializeAdvanced serializeAndDeserializeAdvanced = new SerializeAndDeserializeAdvanced();
        TreeNode root = serializeAndDeserializeAdvanced.new TreeNode(1);
        root.left = serializeAndDeserializeAdvanced.new TreeNode(2);
        root.right = serializeAndDeserializeAdvanced.new TreeNode(3);
        root.right.left = serializeAndDeserializeAdvanced.new TreeNode(4);
        root.right.right = serializeAndDeserializeAdvanced.new TreeNode(5);

        org.junit.Assert.assertEquals(
                "1,2,3,#,#,4,5,#,#,#,#",
                serializeAndDeserializeAdvanced.serialize(root));
    }

    @org.junit.Test(timeout = 1000)
    public void serialize_rightOnlyTree_levelOrder() {
        SerializeAndDeserializeAdvanced serializeAndDeserializeAdvanced = new SerializeAndDeserializeAdvanced();
        TreeNode root = serializeAndDeserializeAdvanced.new TreeNode(2);
        root.right = serializeAndDeserializeAdvanced.new TreeNode(3);
        root.right.right = serializeAndDeserializeAdvanced.new TreeNode(4);
        root.right.right.right = serializeAndDeserializeAdvanced.new TreeNode(5);

        org.junit.Assert.assertEquals(
                "2,#,3,#,4,#,5,#,#",
                serializeAndDeserializeAdvanced.serialize(root));
    }

    @org.junit.Test(timeout = 1000)
    public void deserialize_emptyTree_levelOrder() {
        org.junit.Assert.assertNull(
                new SerializeAndDeserializeAdvanced().deserialize(""));
    }

    @org.junit.Test(timeout = 1000)
    public void deserialize_singleNode_levelOrder() {
        SerializeAndDeserializeAdvanced serializeAndDeserializeAdvanced = new SerializeAndDeserializeAdvanced();

        TreeNode root = serializeAndDeserializeAdvanced.deserialize("1,#,#");

        org.junit.Assert.assertEquals(1, root.val);
        org.junit.Assert.assertNull(root.left);
        org.junit.Assert.assertNull(root.right);
    }

    @org.junit.Test(timeout = 1000)
    public void deserialize_treeWithMissingChildren_levelOrder() {
        SerializeAndDeserializeAdvanced serializeAndDeserializeAdvanced = new SerializeAndDeserializeAdvanced();

        TreeNode root = serializeAndDeserializeAdvanced.deserialize("1,2,3,#,#,4,5,#,#,#,#");

        org.junit.Assert.assertEquals(1, root.val);
        org.junit.Assert.assertEquals(2, root.left.val);
        org.junit.Assert.assertNull(root.left.left);
        org.junit.Assert.assertNull(root.left.right);
        org.junit.Assert.assertEquals(3, root.right.val);
        org.junit.Assert.assertEquals(4, root.right.left.val);
        org.junit.Assert.assertEquals(5, root.right.right.val);
        org.junit.Assert.assertNull(root.right.left.left);
        org.junit.Assert.assertNull(root.right.left.right);
        org.junit.Assert.assertNull(root.right.right.left);
        org.junit.Assert.assertNull(root.right.right.right);
    }

    @org.junit.Test(timeout = 1000)
    public void deserialize_rightOnlyTree_levelOrder() {
        SerializeAndDeserializeAdvanced serializeAndDeserializeAdvanced = new SerializeAndDeserializeAdvanced();

        TreeNode root = serializeAndDeserializeAdvanced.deserialize("2,#,3,#,4,#,5,#,#");

        org.junit.Assert.assertEquals(2, root.val);
        org.junit.Assert.assertNull(root.left);
        org.junit.Assert.assertEquals(3, root.right.val);
        org.junit.Assert.assertNull(root.right.left);
        org.junit.Assert.assertEquals(4, root.right.right.val);
        org.junit.Assert.assertNull(root.right.right.left);
        org.junit.Assert.assertEquals(5, root.right.right.right.val);
        org.junit.Assert.assertNull(root.right.right.right.left);
        org.junit.Assert.assertNull(root.right.right.right.right);
    }

    @org.junit.Test(timeout = 1000)
    public void serializeDeserialize_roundTrip_levelOrder() {
        SerializeAndDeserializeAdvanced serializeAndDeserializeAdvanced = new SerializeAndDeserializeAdvanced();
        TreeNode root = serializeAndDeserializeAdvanced.new TreeNode(1);
        root.left = serializeAndDeserializeAdvanced.new TreeNode(2);
        root.right = serializeAndDeserializeAdvanced.new TreeNode(3);
        root.right.left = serializeAndDeserializeAdvanced.new TreeNode(4);
        root.right.right = serializeAndDeserializeAdvanced.new TreeNode(5);

        String data = serializeAndDeserializeAdvanced.serialize(root);
        TreeNode actual = new SerializeAndDeserializeAdvanced().deserialize(data);

        org.junit.Assert.assertEquals(1, actual.val);
        org.junit.Assert.assertEquals(2, actual.left.val);
        org.junit.Assert.assertEquals(3, actual.right.val);
        org.junit.Assert.assertEquals(4, actual.right.left.val);
        org.junit.Assert.assertEquals(5, actual.right.right.val);
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
