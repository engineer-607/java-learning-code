package com.nanxinda.linkedlist;

import java.util.LinkedList;
@SuppressWarnings({"all"})
public class LinkedListSource {
    public static void main(String[] args) {
        /*
        LinkedList全面说明：
        1）LinkedList底层实现了双向链表和双端队列特点
        2）可以添加任意元素（元素可以重复），包括null
        3）线程不安全，没有实现同步
         */
        /*
        LinkedList底层操作机制
        1）LinkedList底层维护一个双向链表
        2）LinkedList中维护两个属性first和last分别指向首节点和尾节点
        3）每个节点（Node对象），里面又维护prev、next、item三个属性
        其中通过prev指向前一个，通过next指向后一个节点，最终实现双向链表
        4）LinkedList的元素的添加和删除，不是通过数组完成的，相对来说效率高
         */
        LinkedList linkedList = new LinkedList();
        /*
            1）public LinkedList() {
            }
            //这时候LinkedList对象属性first=null，last=null

         */
        linkedList.add(1);
        linkedList.add(2);
        /*
        //执行添加
            2）1.
                public boolean add(E e) {
                    linkLast(e);
                    return true;
                 }
            2）2.
                    void linkLast(E e) {
                        final Node<E> l = last;//last变量一会会发生改变，所以需要l变量来存储上一次
                        //添加留下的last变量
                        final Node<E> newNode = new Node<>(l, e, null);
                        //传入prev=l（只有一个元素时l=null），数据e，next=null
                        //新的结点已经创建，last变量指向新节点
                        last = newNode;
                        添加第二个节点时：
                        ————————————————          ————————————————
                        |   pre:null    |<—————————|————pre       |
                        |item:Integer(1)|         |item:Integer(2)|
                        |   next:null   |         |   next :null  |
                        |———————————————          —————————————————
                        if (l == null)
                             first = newNode;
                        else
                             l.next = newNode;
                        ————————————————          ————————————————
                        |   pre:null    |<—————————|————pre       |
                        |item:Integer(1)|         |item:Integer(2)|
                        |   next————————|—————————>|   next :null  |
                        |———————————————          —————————————————
                        size++;
                        modCount++;
                     }
                     //定义last属性
                         transient Node<E> last;
                     //内部定义Node类
                         private static class Node<E> {
                              E item;
                              Node<E> next;
                              Node<E> prev;

                              Node(Node<E> prev, E element, Node<E> next) {
                                       this.item = element;
                                       this.next = next;
                                       this.prev = prev;
                              }
                         }

         */
        System.out.println("linkedList="+linkedList);
        linkedList.remove();//这里默认删除第一个节点
        System.out.println("linkedList="+linkedList);
        /*
            public E remove() {
                return removeFirst();
            }//返回删除结点的数据原因：1）可以确认删除的是什么2）将删除的数据进行转移3）有可能直接调用
            public E removeFirst() {
                final Node<E> f = first;
                if (f == null)
                    throw new NoSuchElementException();
                return unlinkFirst(f);
            }
    private E unlinkFirst(Node<E> f) {
        // assert f == first && f != null;
        final E element = f.item;
        //存储删除节点的数据
        final Node<E> next = f.next;
        //获取即将删除结点的下一个结点
        f.item = null;
        f.next = null; // help GC
        //切断第一个结点到第二个的连接，并删除第一个结点的数据
        first = next;
        //将下一个结点设为第一个节点
        if (next == null)//先判断如果下一个结点是否为null
            last = null;//如果为null，代表整个链表已为null，将last置空
        else
            next.prev = null;//如果不为空，就切断现在的first和过往first的连接
        size--;
        modCount++;
        return element;
        //返回删除的结点的数据
    }
         */
        //修改某个结点对象
        linkedList.set(0,999);
        //得到某个结点对象
        Object o = linkedList.get(0);
        System.out.println("linkedList="+linkedList);


        //ArrayList和LinkedList的比较
        /*
               | 底层结构      |  增删的效率         | 改查的效率    |
               |-------------|---------------|---------------|
| ArrayList   |   可变数组      | 较低              | 较高          |
|             |               |  数组扩容          |               |
| LinkedList  |   双向链表      | 较高，通过链表追加。 | 较低          |
         */
        //1.如果改查多，选择ArrayList
        //2.如果增删多，选择LinkedList
        //3.一般来说，在程序中，80%-90%都是查询，因此大部分情况下会选择人ArrayList
        //4.在一个项目中，可以根据业务灵活进行选择，可能在一个模块中使用ArrayList
        //另一个模块中使用LinkedList，根据业务来进行选择

    }
}
