package com.zuochengyun.alorithm.datadesign;

import java.util.HashMap;
import java.util.Map;

//LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
//int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，
// 否则返回 -1 。
//void put(int key, int value) 如果关键字已经存在，则变更其数据值；
// 如果关键字不存在，则插入该组「关键字-值」。
// 当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
@SuppressWarnings("all")
public class LRUCache {
    //思路分析：
    //1.结构分析：hashmap结构和双向链表
    //hashmap中存着双向链表中的节点（节点包含4个属性，last、next、val、key）
    //双向链表需要一个尾节点、一个头结点便于后续节点的调整，不至于每次都遍历
    //2.方法分析：需要一个属性limit记录hashmap和链表的最大容量，一个计数器记录当前容器中的数量
    //当计数器值不等于limit时，put正常添加，只改变尾指针指向即可，
    //当相等时，需要调节头指针并移除头指针前一个节点，添加节点后调节尾指针指向该节点
    //get方法不仅要返回值，也需要调整节点，将被查的节点移动到尾指针后面，并且调节尾指针
    //3.get方法需要分三种情况讨论，第一种get节点为头结点，这时候需要将头结点移动到下一个节点
    //将node与现在的node断掉联系，然后将node移动到tail后面，并调整tail指针
    //第二种情况，get节点为尾指针，什么都不需要操作，直接返回值
    //第三种情况get节点在头节点和尾节点之间，这时候就需要将node前后节点相连
    //然后将node节点移动到尾节点后面
    //4.put方法也需要分三大类，第一种容量只为一，这种对应get方法第二种情况，这种情况无需考虑节点连接问题
    //有节点就替换无节点就添加；第二种情况put的key在map中有对应，这种需要替换，并且将该节点移动到尾节点后面
    //第三种情况，就是纯添加，至于节点的移动可以跟第二种情况进行合并
    private static int limit;
    private int counter;
    private Map<Integer,Node> hashMap;
    private  class Node{
        Node last;
        Node next;
        int val;
        int key;
        Node(int key,int val){
            this.key = key;
            this.val = val;
        }

    }
    private Node head;
    private Node tail;
    public LRUCache(int capacity) {
        limit = capacity;
        counter = 0;
        hashMap = new HashMap<>();
    }

    public int get(int key) {
        Node node;
        if((node = hashMap.get(key))==null){
            return -1;
        }else {
            if(node==tail){
                return node.val;
            }else if(node==head){
                moveHead(node);
                return tail.val;
            }else {
                moveInnerNode(node);
                return tail.val;
            }
        }
    }

    public void put(int key, int value) {
        if(limit==1){
            if(counter==0){
                counter++;
            }else {
                hashMap.remove(head.key);
            }
            head = new Node(key,value);
            tail = head;
            hashMap.put(key,head);
            return;
        }
        Node node ;
        if((node = hashMap.get(key))!=null){
            hashMap.get(key).val = value;
            if(node==tail){
                return;
            }else if(node==head){
                moveHead(node);
            }else {
                moveInnerNode(node);
            }
        }else {
            node = new Node(key,value);
            hashMap.put(key,node);
            if(counter==0){
                head = node;
                tail = head;
                counter++;
            }else if(counter!=limit){
                tail.next = node;
                tail.next.last = tail;
                tail = tail.next;
                counter++;
            }else {
                head = head.next;
                hashMap.remove(head.last.key);
                head.last.next = null;
                head.last = null;
                tail.next = node;
                tail.next.last = tail;
                tail = tail.next;
            }
        }
    }
    private void moveHead(Node node){
        head = head.next;
        node.next = null;
        head.last = null;
        node.last = tail;
        tail.next = node;
        tail = tail.next;
    }
    private void moveInnerNode(Node node){
        node.last.next = node.next;
        node.next.last = node.last;
        node.last = tail;
        tail.next = node;
        node.next = null;
        tail = tail.next;
    }

    public static void main(String[] args) {
        testLRUCache();
    }

    public static void testLRUCache() {
        LRUCache cache = new LRUCache(4);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(2, 20);
        cache.put(1, 10);
        cache.put(3, 3);
        cache.put(2, 200);
    }

    private static void checkGet(LRUCache cache, int key, int expected) {
        try {
            int actual = cache.get(key);
            if (actual == expected) {
                System.out.println("PASS: get(" + key + ") = " + actual);
            } else {
                System.out.println(
                        "FAIL: get(" + key + "), expected " + expected + ", actual " + actual
                );
            }
        } catch (Exception e) {
            System.out.println(
                    "FAIL: get(" + key + "), expected " + expected
                            + ", but threw " + e.getClass().getSimpleName()
            );
        }
    }
}
