package com.nanxinda.linkedlist.singlelinkedlist;

public class LinkedList {
    public static void main(String[] args) {
        //1)链表是以节点的方式存储
        //2)每个节点包含data域，next域：指向下一个节点
        //3)链表的各个节点不一定是连续存储的
        //4)链表分带头节点的链表和没有头节点的链表，根据实际的需求来确定
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addHeroNodeByOrder(new HeroNode(1,"宋江","及时雨"));
        singleLinkedList.addHeroNodeByOrder(new HeroNode(3,"卢俊义","玉麒麟"));
        singleLinkedList.addHeroNodeByOrder(new HeroNode(2,"吴用","智多星"));
        singleLinkedList.list();
        singleLinkedList.update(new HeroNode(2,"林冲","豹子头"));
        singleLinkedList.list();
        singleLinkedList.delete(3);
        singleLinkedList.list();
    }
}
class SingleLinkedList{
    private HeroNode head = new HeroNode(0,"","");
    public void delete(int order){
        if(head.next == null){
            System.out.println("链表为空，不能删除数据");
        }
        HeroNode temp = head.next;
        HeroNode oldTemp = head;
        while (true){
            /// 可以找到编号的上一个节点，通过temp.next.getNo()查找真正对应的节点，然后通过当前temp
            ///对编号对应的节点进行删除(temp.next = temp.next.next)
            if(order == temp.getNo()){
                oldTemp.next = temp.next;
                temp.next = null;
                break;
            }
            if(temp.next==null){
                System.out.printf("%d编号不存在，无法删除\n",order);
                break;
            }
            oldTemp = temp;
            temp = temp.next;

        }
    }
    public void addHeroNode(HeroNode heroNode){
        HeroNode temp = head;
        while (true){
            if(temp.next==null){
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;
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
}
