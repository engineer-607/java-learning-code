package com.nanxinda.map_;

import java.util.HashMap;
@SuppressWarnings({"all"})
public class MapSourceDeep {
    public static void main(String[] args) {
        /*
结论：
1) HashMap底层维护了Node类型的数组table，默认为null
2) 当创建对象时，将加载因子（loadFactor）初始化为0.75。
3) 当添加key-val时，通过key的哈希值得到在table的索引。
然后判断该索引处是否有元素，如果没有元素直接添加。如果该索引处有元素，
继续判断该元素的key和准备加入的key是否相等，如果相等，则直接替换val；
如果不相等需要判断是树结构还是链表结构，做出相应处理。如果添加时发现容量不够，则需要扩容。
4) 第1次添加，则需要扩容table容量为16，临界值（threshold）为12（16*0.75）
5) 以后再扩容，则需要扩容table容量为原来的2倍（32），临界值为原来的2倍，即24，依次类推。
6) 在Java8中，如果一条链表的元素个数超过TREEIFY_THRESHOLD（默认是8），
并且table的大小 >= MIN_TREEIFY_CAPACITY（默认64），就会进行树化（红黑树）。
         */
        HashMap map = new HashMap();
        map.put("java",10);
        map.put("php",20);
        map.put("java",20);
        /*
        1.执行构造器
    public HashMap() {
        this.loadFactor = DEFAULT_LOAD_FACTOR; // all other fields defaulted
    }
         初始化加载因子loadFactor = 0.75
         HashMap$Node[] table = null;
         2.执行put 调用hash方法 计算key的hash值 (h = key.hashCode()) ^ (h >>> 16)
    public V put(K key, V value) {//K = "java" value = 10
        return putVal(hash(key), key, value, false, true);
    }
         3.执行putVal
    final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
        Node<K,V>[] tab; Node<K,V> p; int n, i;
        //如果底层的table数组为空，或者length为0，就扩容到16
        if ((tab = table) == null || (n = tab.length) == 0)
            n = (tab = resize()).length;
        //取出hash值对应的table的索引位置的Node，如果为null，就直接把加入的k-v
        //，创建成一个Node，加入到该位置
        if ((p = tab[i = (n - 1) & hash]) == null)
            tab[i] = newNode(hash, key, value, null);
        else {
            Node<K,V> e; K k;
            if (p.hash == hash &&
                ((k = p.key) == key || (key != null && key.equals(k))))
                //如果满足table的索引位置的key的hash和新的key的hash值相同
                //并满足（现有的节点的key和准备添加的key是同一个对象||equals返回真）
                //就认为不能加入新的k-v
                e = p;
            else if (p instanceof TreeNode)
            //如果当前的table的已有的Node是红黑树，就按照红黑树的方式处理
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
            else {
            //如果找到的节点后面是链表，就循环比较
                for (int binCount = 0; ; ++binCount) {
                    if ((e = p.next) == null) {//如果整个链表，没有和他相同，就添加到该链表的最后
                        p.next = newNode(hash, key, value, null);
                        //加入后，判断当前链表的个数，是否已经到8个，到8个后
                        //就调用treeifyBin方法进行红黑树的转换
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                            treeifyBin(tab, hash);
                        break;
                    }
                    if (e.hash == hash &&//如果在循环比较过程中，发现有相同，就break，就只是替换
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    p = e;
                }
            }
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;//替换，key对应value
                afterNodeAccess(e);
                return oldValue;
            }
        }
        ++modCount;
        if (++size > threshold)//如果大于临界值就进行2倍扩容（临界值也扩容）
            resize();
        afterNodeInsertion(evict);
        return null;
    }
         */

    }
}
