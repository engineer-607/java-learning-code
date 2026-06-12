package com.nanxinda.set_;
@SuppressWarnings({"all"})
public class HashSetStructure {
    public static void main(String[] args) {
        //模拟HashSet的底层(HashMap的底层结构)
        //数组+链表
        //1.创建一个数组，数组的类型是Node[]
        //2.有时会将Node[]数组称为表
        Node[] table = new Node[16];
        //3.创建结点
        Node jack = new Node("jack", null);
        table[2] = jack;
        Node john = new Node("john", null);
        jack.next = john;//将john挂载到jack
        System.out.println("table="+table);
        Node rose = new Node("Rose", null);
        john.next = rose;//将rose结点挂载到john
        System.out.println("table="+table);
        Node lucy = new Node("lucy", null);
        table[3] = lucy;
    }
}
class Node{
    Object item;//存放数据
    Node next;//指向下一个结点

    public Node(Object item, Node next) {
        this.item = item;
        this.next = next;
    }
}