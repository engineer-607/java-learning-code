package com.nanxinda.tree.huffmantree.huffman_coding;

import java.io.*;
import java.util.*;
///在java.util.zip这个库中大量的类是用赫夫曼编码来实现的
/// 这些类可以在面对数据太大，传输太慢，内存不够的时候进行压缩

/**
 * 赫夫曼编码
 * 1）赫夫曼编码是赫夫曼树在电讯通信中的经典的应用之一
 * 2）赫夫曼编码广泛地应用于数据文件压缩，压缩率通常在20%-90%
 * 3）赫夫曼编码是可变字长编码（VLC）的一种
 * <p>
 * 通信领域中信息处理
 * 1.方式1-定长编码
 * 例如将英文字母根据对应的ASCII码翻译成八位二进制（保存完整，但长度长）
 * 2.方式2-变长编码
 * 根据各个字符出现的次数转成对应的二进制进行编码（长度变短，但是有多义性）
 * 3.方式3-赫夫曼编码
 * 1）先将各个字符出现的次数进行排序
 * 2）将排序结果转化为赫夫曼树，得到每个权值对应路径
 * 3）将该路径转化为二进制，这个就是该字符再在该编码方式下的存储方式
 */
public class HuffmanTreeCodingDemo {
    public static void main(String[] args) throws FileNotFoundException {
        HuffmanTree huffmanTree = new HuffmanTree();
        String nativeFile = "D:\\花.jpg";
        String destFile  = "D:\\花.zip";
        huffmanTree.zipFile(nativeFile,destFile);
        huffmanTree.uncompressFile(destFile,"D:\\flower.jpg");
//        HuffmanTree huffmanTree = new HuffmanTree("i like java");
//        huffmanTree.zip();
//        System.out.println(huffmanTree.uncompress());



    }
}
@SuppressWarnings({"all"})
class HuffmanTree{
    //记录需要进行压缩的字节数组
    private byte[] bytes;
    //用于构建赫夫曼树
    public ArrayList<Node> arrayList;
    //赫夫曼树的根节点
    private Node root;
//    private HashMap<Character,Object[]> hashMap = new HashMap<>();
    //用于存放每个字符对应的路径，及赫夫曼码表
    //记录的二进制
    /// 赫夫曼码表使用StringBuilder有意味修改的风险，应该改为String
    private HashMap<Byte,String> hashMap = new HashMap<>();
    //记录最后二进制数组最后一个元素对应二进制字符串的长度
    private int lastBitSize;
    private byte[] result;
    //通过传入字符串获取字节数组
    public HuffmanTree(String string) {
        this.bytes = string.getBytes();
    }
    public HuffmanTree(byte[] bytes){
        this.bytes = bytes;
    }
    public HuffmanTree(){}
    //压缩文件
    public void zipFile(String nativeFile,String destFile) {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream outputStream = null;
        try {
            fileInputStream = new FileInputStream(nativeFile);
            bytes = new byte[fileInputStream.available()];
            fileInputStream.read(bytes);
            result = zip();
            fileOutputStream = new FileOutputStream(destFile);
            outputStream = new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(result);
            outputStream.writeObject(hashMap);
        }catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                fileOutputStream.close();
                fileInputStream.close();
                outputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    //解压文件
    public void uncompressFile(String nativeFile,String destFile){
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream(nativeFile);
            fileOutputStream = new FileOutputStream(destFile);
            objectInputStream = new ObjectInputStream(fileInputStream);
            result = (byte[]) objectInputStream.readObject();
            hashMap = (HashMap<Byte, String>) objectInputStream.readObject();
            bytes = uncompress();
            fileOutputStream.write(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                fileOutputStream.close();
                fileInputStream.close();
                objectInputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public byte[] zip(){
        stringToCharNode();
        establishHuffmanTree();
        getPath();
        StringBuilder s = stringToString();
        byte[] bytes = stringToBytes(s);
        result = bytes;
        return bytes;
    }
    /// 多此一举，可以在在获取路径的时候就构建好赫夫曼表
//    private void getBytes(Node node){
//        if(node!=null&&node.getaChar()!=null){
//            hashMap.put(node.getaChar(), node.arrayList.toArray());
//        }
//        if(node==null){
//            return;
//        }
//        getBytes(node.getLeft());
//        getBytes(node.getRight());
//
//    }
    //将字符串拆分成一个个字符，统计出每个字符的出现的次数，将字符和次数用Node来记录
    //通过treeSet存储所有的node
    //根据赫夫曼表将字符串中的各个字符进行赫夫曼编码（并非最终结果，只是用字符串表示一下，还需后续处理）
    private StringBuilder stringToString(){
        byte[] bytes = this.bytes;
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(hashMap.get(b));
        }
        return stringBuilder;
    }
    //将字符串最终转成字节数组，中间使用二进制转化
    private byte[] stringToBytes(StringBuilder stringBuilder){
        int len;
        if(stringBuilder.length()%8==0){
            len = stringBuilder.length()/8;
        }else {
            len = stringBuilder.length()/8+1;
        }
        byte[] bytes = new byte[len];
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i+=8) {
            String s;
            if(i+8>stringBuilder.length()){
                s = stringBuilder.substring(i);
                lastBitSize = stringBuilder.length() - i;
            }else {
                s = stringBuilder.substring(i, i + 8);
            }
            bytes[index++] = (byte) Integer.parseInt(s,2);

        }
        return bytes;

    }

    private void stringToCharNode(){
        byte[] bytes= this.bytes;
        HashMap<Byte,Integer> hashMap = new HashMap<>();
        for (byte b : bytes) {
            if (hashMap.containsKey(b)) {
                hashMap.put(b, hashMap.get(b) + 1);
            } else {
                hashMap.put(b, 1);
            }
        }
         arrayList = new ArrayList<>();
        Iterator<Map.Entry<Byte, Integer>> iterator = hashMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Byte,Integer> entry = iterator.next();
            Node node = new Node(entry.getValue(),entry.getKey());
            arrayList.add(node);
        }
    }
    //根据得到关于每个值在字符串中出现的次数对应成相应的节点，再转化为赫夫曼树
    private void establishHuffmanTree(){
        while (arrayList.size()>1){
            Collections.sort(arrayList);
            Node right = arrayList.get(0);
            Node left = arrayList.get(1);
            Node parent = new Node(right.getAppearNum()+ left.getAppearNum(),null);
            parent.setRight(right);
            parent.setLeft(left);
            arrayList.remove(right);
            arrayList.remove(left);
            arrayList.add(parent);
        }
        root = arrayList.get(0);
    }
    private void infixOrder(Node node){
        if(node==null){
            return;
        }
        infixOrder(node.getLeft());
        System.out.println(node);
        infixOrder(node.getRight());
    }
/// 自己所写的缺点：1.所有地节点都共用一个存储路径地集合，这样需要频繁对该集合进行增删
///              2.误以为现在得到地路径就是转化为二进制的最终结果，实际上集合中每一个元素都是一个字节
//    private void getPath(){
//        Node root = arrayList.get(0);
//        getPath(root,1,new ArrayList<>());
//    }
//    private void getPath(Node node, int direction, ArrayList<Byte> Bytes){
//        //将路径加进去的前提
//        //1.已经进入到该节点
//        if(node!=arrayList.get(0)&&node.getaChar()==null) {
//            Bytes.add((byte) direction);
//        }
//        if(node.getaChar()!=null){
//            Bytes.add((byte) direction);
//            node.arrayList = new ArrayList<>(Bytes);
//            return;
//        }
//        getPath(node.getLeft(),1, Bytes);
//        Bytes.remove(Bytes.size()-1);
//
//        getPath(node.getRight(),0, Bytes);
//        Bytes.remove(Bytes.size()-1);
//    }
    /// 使用StringBuilder的理由：1.其能可使用于单线程2.其适合对字符串就是修改
    private void getPath(){
        if(root==null){
            return;
        }
        /// !!!需要学会考虑边界情况，如果只有一个节点，就需要进行特殊处理
        if(root.getaByte()!=null){
            //需要将这种情况单独列出来添加到赫夫曼码表中，不然赫夫曼码表中不会存在存在对应的编码
            hashMap.put(root.getaByte(),"0");
        }
        StringBuilder stringBuilder = new StringBuilder();
        getPath(stringBuilder,"",root);
    }
    /**
     * 用于获取各个叶子节点的权值和路径构成赫夫曼表
     * @param stringBuilder 记录的父节点的路径
     * @param direction 代表是父节点进入该节点所选的方向
     * @param node 即为当前节点
     */
    private void getPath(StringBuilder stringBuilder,String direction,Node node){
        //获取到达当前节点的路径
        StringBuilder stringBuilder1 = new StringBuilder(stringBuilder);
        stringBuilder1.append(direction);
        if(node.getaByte()==null){
            //1向左
            getPath(stringBuilder1,"1",node.getLeft());
            //0向右
            getPath(stringBuilder1,"0",node.getRight());
        }else {
            hashMap.put(node.getaByte(),stringBuilder1.toString());
        }
    }

    //进行解压缩的操作
    //1.将得到的字节数组还原成二进制式的字符串
    //2.将字符串跟据赫夫曼表进行配对解压缩成原文
    public String uncompressString(){
        return new String(uncompress());
    }
    private byte[] uncompress(){
        String s = bytesToBitString(result);
        return bitStringToString(s);
    }

    private String bytesToBitString(byte[] bytes){
        int temp;
        StringBuilder stringBuilder = new StringBuilder();
        String s;
        for (int i = 0; i < bytes.length; i++) {
            if(i!=bytes.length-1) {
                if (bytes[i] >= 0) {
                    temp = bytes[i]+256;
                    s = Integer.toBinaryString(temp);
                } else {
                    temp = bytes[i];
                    s = Integer.toBinaryString(temp);
                }
                s = s.substring(s.length()-8);
                stringBuilder.append(s);
            }else {
                if(bytes[i]>=0){
                    temp = bytes[i]+256;
                    s = Integer.toBinaryString(temp);
                }else {
                    temp = bytes[i];
                    s = Integer.toBinaryString(temp);
                }
                s = s.substring(s.length()-lastBitSize);
                stringBuilder.append(s);
            }

        }
        return stringBuilder.toString();
    }
    private byte[] bitStringToString(String s){
        //查赫夫曼表需要先将其颠倒
        HashMap<String,Byte> hashMap1 = new HashMap<>();
        for (Map.Entry<Byte,String> o : hashMap.entrySet()) {
            hashMap1.put(o.getValue(),o.getKey());
        }

        int count = 0;
        ArrayList<Byte> bytes = new ArrayList<>();
        for (int i = 0; i < s.length();) {
            while (true){
                String substring = s.substring(i, i + count);
                if(hashMap1.get(substring)!=null){
                    bytes.add( hashMap1.get(substring));
                    break;
                }
                count++;
            }
            i = i+count;
            count = 0;
        }
        byte[] bytes1 = new byte[bytes.size()];
        int index = 0;
        for (Byte b : bytes) {
            bytes1[index++] = (byte) b;
        }
        /// 在处理图片这种二进制文件时，这么操作会导致数据丢失
        /// 该方法结尾尝试将字节转化为字符串，而这过程中会将字节转化为字符，但是有的字节在UTF-8中没有对应的字符
        /// 就会出现乱码的情况，进而导致文件在解压缩的时候发生损坏
//        return new String(bytes1);
        return bytes1;
    }


}
@SuppressWarnings({"all"})
class Node implements Comparable<Node>{
    private int appearNum;
    private Byte aByte;
    private Node left;
    private Node right;
//    public ArrayList<Byte> arrayList;

    public Node(int appearNum,Byte b){
        this.appearNum = appearNum;
        this.aByte = b;
    }

    @Override
    public int compareTo(Node o) {
        int judge = appearNum - o.appearNum;
        if(judge!=0){
            return judge;
        }else {
            return 1;
        }
    }

//    @Override
//    public String toString() {
//        return "Node{" +
//                "arrayList=" + arrayList +
//                "appearNum=" + appearNum +
//                "char=" + aChar +
//                '}';
//    }


    @Override
    public String toString() {
        return "Node{" +
                "appearNum=" + appearNum +
                ", char=" + aByte +
                '}';
    }

    public int getAppearNum() {
        return appearNum;
    }

    public void setAppearNum(int appearNum) {
        this.appearNum = appearNum;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Byte getaByte() {
        return aByte;
    }

    public void setaByte(Byte aByte) {
        this.aByte = aByte;
    }
}

