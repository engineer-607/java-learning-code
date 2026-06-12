package com.nanxinda.linkedlist.doublelinkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.addHeroNode(new HeroNode(2,"吴用","智多星"));
        doubleLinkedList.addByOrder(new HeroNode(5,"宋江","及时雨"));
        doubleLinkedList.addByOrder(new HeroNode(3,"林冲","豹子头"));
        System.out.println("========链表添加的情况========");
        doubleLinkedList.list();
        doubleLinkedList.update(new HeroNode(2,"卢俊义","玉麒麟"));
        System.out.println("========链表修改的情况=========");
        doubleLinkedList.list();
        doubleLinkedList.delete(2);
        System.out.println("========链表删除的情况=========");
        doubleLinkedList.list();

    }
}
class HeroNode{
    private int no;
    private String name;
    private String nikeName;
    public HeroNode next;
    public HeroNode pre;

    public HeroNode(int no, String name, String nikeName) {
        this.no = no;
        this.name = name;
        this.nikeName = nikeName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "nikeName='" + nikeName + '\'' +
                ", name='" + name + '\'' +
                ", no=" + no +
                '}';
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNikeName() {
        return nikeName;
    }

    public void setNikeName(String nikeName) {
        this.nikeName = nikeName;
    }

    public HeroNode getNext() {
        return next;
    }

    public void setNext(HeroNode next) {
        this.next = next;
    }

    public HeroNode getPre() {
        return pre;
    }

    public void setPre(HeroNode pre) {
        this.pre = pre;
    }
}
class DoubleLinkedList{
    private HeroNode head = new HeroNode(0,"","");
    //返回头节点
    public HeroNode getHead(){
        return head;
    }
    //遍历节点
    public void list(){
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head;
        while (true){
            if(temp.next==null){
                break;
            }
            temp = temp.next;
            System.out.println(temp);
        }
    }
    //添加节点
    public void addHeroNode(HeroNode heroNode){
        HeroNode temp = head;
        while (true){
            if(temp.next==null){
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;
        heroNode.pre = temp;
    }
    //修改节点的内容
    public void update(HeroNode newHeroNode){
        if(head.next == null){
            System.out.println("链表为空，不能修改");
            return;
        }
        HeroNode temp = head.next;
        while (true){
            if(temp.getNo()==newHeroNode.getNo()) {
                temp.setName(newHeroNode.getName());
                temp.setNikeName(newHeroNode.getNikeName());
                break;
            }
            if(temp.next == null){
                System.out.println("链表中无该编号");
                break;
            }
            temp = temp.next;
        }
    }
    //删除节点
    public void delete(int order) {
        if (head.next == null) {
            System.out.println("链表为空，不能删除数据");
        }
        HeroNode temp = head;
        boolean flag = false;//记录是否删除成功
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.getNo() == order) {
                temp.pre.next = temp.next;
                if (temp.next != null) {
                    temp.next.pre = temp.pre;
                }
                flag = true;
            }
            temp = temp.next;
        }
        if (!flag) {
            System.out.println("删除失败，该节点不存在");
        } else {
            System.out.println("删除成功");
        }
    }
    public void addByOrder(HeroNode heroNode){
        HeroNode temp = head;
        int order = heroNode.getNo();
        while (true){
            if(order!=0&&order == temp.getNo()){
                System.out.printf("%d节点已经存在，无法添加",order);
                break;
            }
            if(temp.next == null){
                heroNode.pre = temp;
                temp.next = heroNode;
                break;
            }
            if(temp.getNo()<order&&order<temp.next.getNo()){
                heroNode.pre = temp;
                heroNode.next = temp.next;
                temp.next.pre = heroNode;
                temp.next = heroNode;
                break;

            }
            temp = temp.next;
        }
    }


}