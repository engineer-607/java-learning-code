package com.nanxinda.linkedlist;
@SuppressWarnings({"all"})
public class LinkedList01 {
    public static void main(String[] args) {
        Node jack = new Node("jack");
        Node tom = new Node("tom");
        Node hsp = new Node("hsp");

        //连接三个节点，形成双向链表
        //jack->tom->hsp
        jack.next = tom;
        tom.next = hsp;
        //hsp->tom->jack
        hsp.pre = tom;
        tom.pre = jack;

        Node first = jack;//让fist引用指向jack，就是双向链表的头节点
        Node last = hsp;//让last引用指向hsp，就是链表的尾节点

        //从头到尾进行遍历
        System.out.println("===从头到尾进行遍历===");
        Node a = first;
        while(true){
            if(a==null){
                break;
            }
            System.out.println(a);
            a = a.next;
        }
        //从尾到头进行遍历
        System.out.println("===从尾到头进行遍历===");
        Node b = last;
        while(true){
            if(b==null){
                break;
            }
            System.out.println(b);
            b = b.pre;
        }
        //链表添加数据
        //1.先创建一个Node结点，name
        Node smith = new Node("smith");
        tom.next = smith;
        smith.pre = tom;
        smith.next = hsp;
        hsp.pre = smith;
        Node c = first;
        System.out.println("=========");
        while(true){
            if(c==null){
                break;
            }
            System.out.println(c);
            c = c.next;
        }



    }

}
//定义一个Node对象，Node 对象表示双向链表的一个节点
class Node{
    public Object item;//真正存放数据
    public Node next;//指向下一个节点
    public Node pre;//指向上一个节点

    public Node(Object item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "Node name=" + item;
    }
}