package com.zuochengyun.alorithm.binarytree;
@SuppressWarnings({"all"})
public class SerializeAndDeserialize {



    //采用先序遍历（或者后序遍历）
    //Encodes a tree to a single string.
    //思路分析：1.采用前序遍历，遇到空节点，字符串加入#，非空就把节点的值
    //加入字符串
    private StringBuilder stringBuilder;
    public String serialize(TreeNode root) {
        stringBuilder = new StringBuilder();
        preOrder(root);
        stringBuilder.deleteCharAt(stringBuilder.toString().length()-1);
        return stringBuilder.toString();
    }
    private void preOrder(TreeNode root){
        if(root==null){
            stringBuilder.append("#,");
            return;
        }
        stringBuilder.append(root.val+",");
        preOrder(root.left);
        preOrder(root.right);
    }



    // Decodes your encoded data to tree.
    //反序列化：同样通过递归将字符串数组变为对应的节点
    String[] strings;
    int i;
    public TreeNode deserialize(String data) {
        i = 0;
        strings = data.split(",");
        if(strings[i].equals("#")){
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(strings[i]));
        restoreNode(root);
        return root;
    }
    private void restoreNode(TreeNode root){
        if(i>=strings.length||root==null){
            return;
        }
        TreeNode treeNode = null;
        if(!strings[++i].equals("#")){
            treeNode = new TreeNode(Integer.parseInt(strings[i]));
        }
        root.left = treeNode;
        restoreNode(treeNode);
        treeNode = null;
        if(!strings[++i].equals("#")){
            treeNode = new TreeNode(Integer.parseInt(strings[i]));
        }
        root.right = treeNode;
        restoreNode(treeNode);

    }

    @org.junit.Test(timeout = 1000)
    public void serialize_emptyTree_preOrder() {
        org.junit.Assert.assertEquals(
                "#",
                new SerializeAndDeserialize().serialize(null));
    }

    @org.junit.Test(timeout = 1000)
    public void serialize_singleNode_preOrder() {
        SerializeAndDeserialize serializeAndDeserialize = new SerializeAndDeserialize();
        TreeNode root = serializeAndDeserialize.new TreeNode(1);

        org.junit.Assert.assertEquals(
                "1,#,#",
                serializeAndDeserialize.serialize(root));
    }

    @org.junit.Test(timeout = 1000)
    public void serialize_completeTree_preOrder() {
        SerializeAndDeserialize serializeAndDeserialize = new SerializeAndDeserialize();
        TreeNode root = serializeAndDeserialize.new TreeNode(1);
        root.left = serializeAndDeserialize.new TreeNode(2);
        root.right = serializeAndDeserialize.new TreeNode(3);
        root.left.left = serializeAndDeserialize.new TreeNode(4);
        root.left.right = serializeAndDeserialize.new TreeNode(5);
        root.right.left = serializeAndDeserialize.new TreeNode(6);
        root.right.right = serializeAndDeserialize.new TreeNode(7);

        org.junit.Assert.assertEquals(
                "1,2,4,#,#,5,#,#,3,6,#,#,7,#,#",
                serializeAndDeserialize.serialize(root));
    }

    @org.junit.Test(timeout = 1000)
    public void serialize_treeWithMissingChildren_preOrder() {
        SerializeAndDeserialize serializeAndDeserialize = new SerializeAndDeserialize();
        TreeNode root = serializeAndDeserialize.new TreeNode(1);
        root.left = serializeAndDeserialize.new TreeNode(2);
        root.right = serializeAndDeserialize.new TreeNode(3);
        root.right.left = serializeAndDeserialize.new TreeNode(4);
        root.right.right = serializeAndDeserialize.new TreeNode(5);

        org.junit.Assert.assertEquals(
                "1,2,#,#,3,4,#,#,5,#,#",
                serializeAndDeserialize.serialize(root));
    }

    @org.junit.Test(timeout = 1000)
    public void serialize_rightOnlyTree_preOrder() {
        SerializeAndDeserialize serializeAndDeserialize = new SerializeAndDeserialize();
        TreeNode root = serializeAndDeserialize.new TreeNode(2);
        root.right = serializeAndDeserialize.new TreeNode(3);
        root.right.right = serializeAndDeserialize.new TreeNode(4);
        root.right.right.right = serializeAndDeserialize.new TreeNode(5);

        org.junit.Assert.assertEquals(
                "2,#,3,#,4,#,5,#,#",
                serializeAndDeserialize.serialize(root));
    }

    @org.junit.Test(timeout = 1000)
    public void deserialize_emptyTree_preOrder() {
        org.junit.Assert.assertNull(
                new SerializeAndDeserialize().deserialize("#"));
    }


    @org.junit.Test(timeout = 1000)
    public void deserialize_treeWithMissingChildren_preOrder() {
        SerializeAndDeserialize serializeAndDeserialize = new SerializeAndDeserialize();

        TreeNode root = serializeAndDeserialize.deserialize("1,2,#,#,3,4,#,#,5,#,#");

        org.junit.Assert.assertEquals(1, root.val);
        org.junit.Assert.assertEquals(2, root.left.val);
        org.junit.Assert.assertNull(root.left.left);
        org.junit.Assert.assertNull(root.left.right);
        org.junit.Assert.assertEquals(3, root.right.val);
        org.junit.Assert.assertEquals(4, root.right.left.val);
        org.junit.Assert.assertNull(root.right.left.left);
        org.junit.Assert.assertNull(root.right.left.right);
        org.junit.Assert.assertEquals(5, root.right.right.val);
        org.junit.Assert.assertNull(root.right.right.left);
        org.junit.Assert.assertNull(root.right.right.right);
    }

    @org.junit.Test(timeout = 1000)
    public void deserialize_rightOnlyTree_preOrder() {
        SerializeAndDeserialize serializeAndDeserialize = new SerializeAndDeserialize();

        TreeNode root = serializeAndDeserialize.deserialize("2,#,3,#,4,#,5,#,#");

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
    public void serializeDeserialize_roundTrip_preOrder() {
        SerializeAndDeserialize serializeAndDeserialize = new SerializeAndDeserialize();
        TreeNode root = serializeAndDeserialize.new TreeNode(1);
        root.left = serializeAndDeserialize.new TreeNode(2);
        root.right = serializeAndDeserialize.new TreeNode(3);
        root.right.left = serializeAndDeserialize.new TreeNode(4);
        root.right.right = serializeAndDeserialize.new TreeNode(5);

        String data = serializeAndDeserialize.serialize(root);
        TreeNode actual = new SerializeAndDeserialize().deserialize(data);

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
