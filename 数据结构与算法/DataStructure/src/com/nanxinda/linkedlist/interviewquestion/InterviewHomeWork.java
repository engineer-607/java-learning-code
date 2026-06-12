package com.nanxinda.linkedlist.interviewquestion;

import com.nanxinda.linkedlist.singlelinkedlist.HeroNode;

public class InterviewHomeWork {
    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addHeroNodeByOrder(new HeroNode(5,"宋江","及时雨"));
        singleLinkedList.addHeroNodeByOrder(new HeroNode(3,"卢俊义","玉麒麟"));
        SingleLinkedList singleLinkedList1 = new SingleLinkedList();
        singleLinkedList1.addHeroNodeByOrder(new HeroNode(4,"林冲","豹子头"));
        singleLinkedList1.addHeroNodeByOrder(new HeroNode(2,"吴用","智多星"));
        singleLinkedList.CombineLinkedList(singleLinkedList1);
        singleLinkedList1.list();
    }
}
class SingleLinkedList{
    private HeroNode head = new HeroNode(0,"","");
    public void CombineLinkedList(SingleLinkedList singleLinkedList){
        HeroNode temp = this.head.next;
        HeroNode next = null;
        while (true){
            /// Java的访问控制权限是基于类的，而不是基于对象的
            HeroNode tempInner = singleLinkedList.head;
            next = temp.next;
            while (true){
                if(tempInner.next == null){
                    tempInner.next = temp;
                    break;
                }
                if(temp.getNo()>tempInner.getNo()&&temp.getNo()<tempInner.next.getNo()){
                    temp.next = tempInner.next;
                    tempInner.next = temp;
                    break;
                }
                tempInner = tempInner.next;
            }
            if(next == null){
                break;
            }
            temp = next;
        }
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
}