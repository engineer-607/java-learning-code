package com.nanxinda.map_;

import java.util.Comparator;
import java.util.TreeMap;
@SuppressWarnings({"all"})
public class TreeMapDetail {
    public static void main(String[] args) {
        //1.使用默认的构造器，创建TreeMap是无序的
//        TreeMap treeMap = new TreeMap();
        //2.使用可以传入比较器的构造器，实现排序
        TreeMap treeMap = new TreeMap(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((String)o1).compareTo((String) o2);
            }
        });
        /*1.构造器，把传入的实现了Comperator接口的匿名内部类（对象），传给TreeMap的comparator
    public TreeMap(Comparator<? super K> comparator) {
        this.comparator = comparator;
    }
          2.调用put方法
          2.1第一次添加，把k-v封装到Entry对象，放入root
        Entry<K,V> t = root;
        if (t == null) {
            compare(key, key); // type (and possibly null) check

            root = new Entry<>(key, value, null);
            size = 1;
            modCount++;
            return null;
        }
        2.2以后添加
        Comparator<? super K> cpr = comparator;
        if (cpr != null) {//cpr是我们的匿名内部类（对象）
            do {//遍历所有的Key，给当前Key找到合适的位置
                parent = t;
                //动态绑定到我们匿名内部类（对象）compare
                cmp = cpr.compare(key, t.key);
                if (cmp < 0)
                    t = t.left;
                else if (cmp > 0)
                    t = t.right;
                else//如果遍历过程中，发现准备添加Key和当前已有的Key
                    return t.setValue(value);
            } while (t != null);
        }
         */
        treeMap.put("jack","杰克");
        treeMap.put("tom","汤姆");
        treeMap.put("smith","斯密斯");
        System.out.println("treeMap="+treeMap);
    }
}
