package com.nanxinda.set_;

import java.util.Comparator;
import java.util.TreeSet;
@SuppressWarnings({"all"})
public class TreeSetDetail {
    public static void main(String[] args) {
        //1.当使用无参构造器时，创建TreeSet时，仍然是无序的
//        TreeSet treeSet = new TreeSet();
        //2.使用TreeSet提供的一个构造器，可以传入一个比较器（匿名内部类）
        //并指定排序规则
        TreeSet treeSet = new TreeSet(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
//                return ((String)o1).compareTo((String) o2);
                //比较两个字符串的长度
                return ((String)o1).length()-((String)o2).length();
            }
        });
        /*1.构造器把传入的比较器对象，赋给TreeSet的底层的TreeMap的属性this.comperator
    public TreeSet(Comparator<? super E> comparator) {
        this(new TreeMap<>(comparator));
    }
    public TreeMap(Comparator<? super K> comparator) {
        this.comparator = comparator;
    }
         2.在调用treeSet.add("tom")，在底层会执行
        Comparator<? super K> cpr = comparator;
        if (cpr != null) {//cpr是我们的匿名内部类（对象）
            do {
                parent = t;
                //动态绑定到我们匿名内部类（对象）compare
                cmp = cpr.compare(key, t.key);
                if (cmp < 0)
                    t = t.left;
                else if (cmp > 0)
                    t = t.right;
                else//如果相等，即返回0，这个Key就没有加入
                    return t.setValue(value);
            } while (t != null);
        }
         */
        treeSet.add("jack");
        treeSet.add("tom");//3
        treeSet.add("sp");
        treeSet.add("a");
        treeSet.add("abc");//3
        //无法添加，与集合各个元素比较，发现有返回0的
        System.out.println("treeSet="+treeSet);

    }
}
