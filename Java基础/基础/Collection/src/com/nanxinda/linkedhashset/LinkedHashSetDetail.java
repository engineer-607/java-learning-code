package com.nanxinda.linkedhashset;

import java.util.LinkedHashSet;
@SuppressWarnings({"all"})
public class LinkedHashSetDetail {
    public static void main(String[] args) {
        /*
        1）LinkedHashSet是HashSet的子类
        2）在LinkedHashSet中维护一个Hash表和双向链表
        3）每一个节点有before和after属性，这样可以形成双向链表，这样就可以形成双向链表
        4）在添加一个元素时，先求hash值，再求索引，确定该元素在table中的位置，然后将添加的元素
        加入到双向链表（如果已经存在，则不添加）
        tail.next = newElement;
        newElement.next = tail;
        tail = newElement;
        5）遍历LinkedHashSet的取出顺序与插入顺序一致
        6）第一次添加时，直接将数组table扩容到16，存放的节点类型是LinkedHashMap$Entry
        7）数组是HashMap$Node[] 存放的元素/数据是 LinkedHashMap$Entry
        //继承关系在内部类中完成
    static class Entry<K,V> extends HashMap.Node<K,V> {
        Entry<K,V> before, after;
        Entry(int hash, K key, V value, Node<K,V> next) {
            super(hash, key, value, next);
        }
    }
         */
        LinkedHashSet hashSet = new LinkedHashSet();
        hashSet.add("jack");
        hashSet.add(234);
        hashSet.add(0.56);
        System.out.println("hashSet="+hashSet);


    }
}
