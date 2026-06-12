package com.nanxinda.set_;

import java.util.HashSet;

@SuppressWarnings({"all"})
public class HashSetSource {
    public static void main(String[] args) {
        /*
        HashSet底层机制说明
        1.HashSet底层是HashMap
        2.添加一个元素时，先得到hash值，将该值转换成索引值
        3.找到存储数据表table，检查这个索引位置是否已经存放元素
        4.如果没有，会直接加入
        5.如果有，调用equals比较，如果相同，就放弃添加，如果不相同，则添加到最后
        6.在Java8中，如果一条链表的元素个数到达TREEIFY_THRESHOLD（默认是8）
        并且table大小>=MIN_TREEIFY（默认是64），就会进行树化（红黑树）
         */
        HashSet hashSet = new HashSet();
        /*1.执行HashSet构造器
    public HashSet() {
        map = new HashMap<>();
    }
         */
        hashSet.add("java");
        /*2.执行add()
    public boolean add(E e) {//e="java"
        return map.put(e, PRESENT)==null;//为了使用Map，PRESENT起到占位的作用
        //private static final Object PRESENT = new Object();
    }
         3.执行 put()，该方法会执行hash(key) 得到key对应的hash值
         算法(h = key.hashCode()) ^ (h >>> 16)
    public V put(K key, V value) {
        return putVal(hash(key), key, value, false, true);
    }
        4.执行putVal
    final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
        Node<K,V>[] tab; Node<K,V> p; int n, i;//定义辅助变量
        //if语句表示如果table为null或者大小=0
        //就通过resize方法进行一次扩容，第一次扩容为16
        if ((tab = table) == null || (n = tab.length) == 0)
        //table数组是HashMap的一个属性，类型是Node[]
            n = (tab = resize()).length;
            //(1)根据key，得到hash去计算key应该存放到table表的哪个索引位置
            //并把这个位置的对象，赋给p
            //(2)判断p是否为null
            //(2.1)如果p为null，表示还没有存放元素，就创建一个Node(key = "java",value = PRESENT)
            //(2.2)讲其放在位置tab[i]上
        if ((p = tab[i = (n - 1) & hash]) == null)
            tab[i] = newNode(hash, key, value, null);
        else {
            Node<K,V> e; K k;
            if (p.hash == hash &&
                ((k = p.key) == key || (key != null && key.equals(k))))
                e = p;
            else if (p instanceof TreeNode)
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
            else {
                  .....................
            }
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                afterNodeAccess(e);
                return oldValue;
            }
        }
        ++modCount;
        //size就是我们每加入一个结点Node(k,v,h,next),size都会自增（不管这个结点在索引位置，还是在链表上）
        if (++size > threshold)//判断是否到达临近点
            resize();//如果达到临近点，就进行扩容
        afterNodeInsertion(evict);
        return null;
        //返回null，则put()返回空，则add()中map.put(e, PRESENT)==null;返回T
    }
         */
        /*
    final Node<K,V>[] resize() {
        Node<K,V>[] oldTab = table;//记录旧数组
        //transient Node<K,V>[] table;
        int oldCap = (oldTab == null) ? 0 : oldTab.length;//记录旧数组的长度
        int oldThr = threshold;//记录上一次扩容的临界点
        //    int threshold;   //记录执行扩容的临界点
        int newCap, newThr = 0;
        if (oldCap > 0) {
              .........
        }
        else if (oldThr > 0)
              ..........
        else { //在oldThr=0的情况下              // zero initial threshold signifies using defaults
            newCap = DEFAULT_INITIAL_CAPACITY;//记录扩容数组的新长度
            //    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;//二进制左移4位为16
            newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
            //    static final float DEFAULT_LOAD_FACTOR = 0.75f;//临界点常量为0.75
            //记录新的临界点
        }
            ...............
        threshold = newThr;
        @SuppressWarnings({"rawtypes","unchecked"})
        Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
        table = newTab;
        //将属性table进行赋值，此时table不再为空（外面的putVal在后续中会再次使用）
        ....................
        return newTab;
    }
         */
        hashSet.add("php");
        /*
        第二次执行add()方法分析（从putVal开始）
    final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
        Node<K,V>[] tab; Node<K,V> p; int n, i;
        if ((tab = table) == null || (n = tab.length) == 0)
        //if语句执行三件事：1）将属性table赋值给局部变量tab、将tab.length赋值给n
        2）判断tab是否为空3）判断n是否为0
            n = (tab = resize()).length;
        if ((p = tab[i = (n - 1) & hash]) == null)
        //执行该if语句发现i = (n - 1) & hash为9，p最终为空，创建一个新的结点
            tab[i] = newNode(hash, key, value, null);
        else {
            Node<K,V> e; K k;
            if (p.hash == hash &&
                ((k = p.key) == key || (key != null && key.equals(k))))
                e = p;
            else if (p instanceof TreeNode)
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
            else {
              .................
        }
        ++modCount;
        if (++size > threshold)
            resize();
        afterNodeInsertion(evict);
        return null;
    }
         */
        hashSet.add("java");
        /*
        第三执行add()（分析putVal中else）
            Node<K,V> e; K k;//提示：局部变量（辅助变量）需要时再进行定义
            if (p.hash == hash &&
                ((k = p.key) == key || (key != null && key.equals(k))))
                //if语句分析：
                //(1)判断当前索引位置的第一个Node对象的hash值和准备加入的Node对象的hash值是否相等
                //(2)并且满足以下两个条件之一：
                  1.准备加入的Node对象的key值和p指向的Node对象的key值相等
                  2.准备加入的Node对象的key值不为空并且调用其动态绑定的equals方法与p指向的Node对象的key值
                  进行比较相等
                e = p;
            else if (p instanceof TreeNode)
            //再判断p是否时一棵红黑树
            //如果是就用方法putTreeVal进行调用
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
            else {//在与该索引第一个Node对象比较不相等且p不是红黑树的情况下
                for (int binCount = 0; ; ++binCount) {//遍历链表
                    if ((e = p.next) == null) {//如果到链表的最后，就将准备加入的Node对象添加到最后
                        p.next = newNode(hash, key, value, null);
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                        //判断该链表是否已经达到8个结点
                            treeifyBin(tab, hash);
                        //如果已经达到8个结点，就调用treeifyBin()，对当前这个链表进行树化
                        //注意，在转化为红黑树时，还需要进行判断
                        //        if (tab == null || (n = tab.length) < MIN_TREEIFY_CAPACITY)
                        //            resize();
                        //    static final int MIN_TREEIFY_CAPACITY = 64;
                        //如果上面的条件成立，才进行转成红黑树
                        break;
                    }
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        //与链表每个Node对象进行比较，如果相同就break跳出循环
                        break;
                    p = e;
                }
            }
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                afterNodeAccess(e);
                return oldValue;
            }
         */

        System.out.println("set="+hashSet);


    }
}
