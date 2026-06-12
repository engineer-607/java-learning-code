package com.nanxinda.linkedlist.interviewquestion;

import com.nanxinda.linkedlist.singlelinkedlist.HeroNode;

import java.util.Stack;

public class Interview {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.addHeroNodeByOrder(new HeroNode(1,"宋江","及时雨"));
        list.addHeroNodeByOrder(new HeroNode(3,"卢俊义","玉麒麟"));
        list.addHeroNodeByOrder(new HeroNode(2,"吴用","智多星"));
        //第一题
        System.out.println("========获取有效节点数========");
        System.out.println(list.getNodeCount());
        //第二题
        System.out.println("========获取倒数第k个节点========");
        System.out.println(list.getNodeInLast(2));
        //第四题
        //方式一：
        System.out.println("========从尾到头打印链表========");
        list.ReserveTraverse();
        System.out.println("========链表现在的顺序=========");
        list.list();
        //方式二（老韩）：
        //利用栈的数据结构，将各个节点压入栈中，然后利用栈的先进后出的特点，实现逆序打印的效果
        System.out.println("=========从尾到头打印链表（老韩）========");
        Stack<HeroNode> stack = new Stack<>();
        for (int i =1; i <= list.getNodeCount(); i++) {
            stack.add(list.getNode(i));
        }
        while (stack.size()>0){
            System.out.println(stack.pop());
        }
        //第三题
        System.out.println("========将链表反转（自己所写）=========");
        LinkedList list1 = list.getReserve();
        list1.list();
        System.out.println("========原来的链表========");
        list.list();
        System.out.println("========将链表反转（老韩所写）========");
        LinkedList list2 = list.getReserve(list.getHead());
        list2.list();
        System.out.println("========原来的链表========");
        list.list();

        //第四题




    }
}
@SuppressWarnings({"all"})
class LinkedList{
    private HeroNode head = new HeroNode(0,"","");
    public int getNodeCount(){
        HeroNode temp = head;
        int count = 0;
        while (true){
            if(temp.next == null){
                break;
            }
            temp = temp.next;
            count++;
        }
        return count;
    }
    public HeroNode getNode(int k){
        if(k<=0||k>this.getNodeCount()){
            return null;
        }
        int i = 0;
        HeroNode temp = head;
        while (true){
            if(i == k){
                return temp;
            }
            temp = temp.next;
            i++;
        }
    }
    public HeroNode getNodeInLast(int k){
        /// 还需要考虑k为负数的情况
        if(k<0){
            return null;
        }
        int num = this.getNodeCount() - k + 1;
        if(num<=0){
            System.out.printf("倒数%d个节点不存在",k);
            return null;
        }
        int i = 0;
        HeroNode temp = head;
        while (true){
            if(i==num){
                return temp;
            }
            temp = temp.next;
            i++;
        }
    }
    //个人所写：
    public LinkedList getReserve(){
        int count = this.getNodeCount();
        HeroNode newHead = new HeroNode(0,"","");
        HeroNode temp = null;
        for (int i = 1; i <= count; i++) {
            if(i == 1){
                newHead.next = this.getNodeInLast(i).copy();
                temp = newHead.next;
                continue;
            }
            temp.next = this.getNodeInLast(i).copy();
            temp = temp.next;
        }
        LinkedList list = new LinkedList();
        list.setHead(newHead);
        return list;
    }
    /// 可以将编号在后的节点插入到编号在前的节点（会破坏原有的链表）
    public LinkedList getReserve(HeroNode head){
        HeroNode temp = head.next;
        HeroNode next = null;
        HeroNode reserveHead = new HeroNode(0,"","");
        while (true){
            if(temp == null){
                break;
            }
            next = temp.next;
            temp.next = reserveHead.next;
            reserveHead.next = temp;
            temp = next;
        }
        head.next = reserveHead.next;
        LinkedList list = new LinkedList();
        list.setHead(reserveHead);
        return list;
    }


    public void addHeroNodeByOrder(HeroNode heroNode){
        int order = heroNode.getNo();
        HeroNode temp = head;
        boolean flag = false;///记录该编号是否已存在
        while (true){
            if(temp.getNo() ==order){/// 考虑该编号已存在
                flag = true;
                break;
            }
            /// 考虑在链表的最后插入
            if(temp.next == null){
                break;
            }
            /// 考虑在链表的中间插入
            if(order> temp.getNo()&&order< temp.next.getNo()){
                break;
            }
            temp = temp.next;
        }
        if(flag){
            System.out.printf("%d编号已存在，不能加入\n",order);
        }else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }
    public void list(){
        HeroNode temp = head;
        while (true){
            if(temp.next==null){
                break;
            }
            temp = temp.next;
            System.out.println(temp);
        }
    }
    public void ReserveTraverse(){
        int count = this.getNodeCount();
        for (int i = 1; i <= count; i++) {
            System.out.println(this.getNodeInLast(i));
        }
    }

    public HeroNode getHead() {
        return head;
    }

    public void setHead(HeroNode head) {
        this.head = head;
    }
}