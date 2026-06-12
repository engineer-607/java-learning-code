package com.nanxinda.collection_;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.Callable;

@SuppressWarnings({"all"})
public class CollectionsDetail {
    public static void main(String[] args) {
        /*
        Collections工具类
        1）Collections是一个操作Set、List和Map等集合的工具类
        2）Collections中提供一系列静态的方法对集合元素进行排序、查询和修改等操作
         */
        ArrayList list = new ArrayList();
        list.add("tom");
        list.add("smith");
        list.add("king");
        list.add("milian");
        //1)reserve(List)：反转List中元素的顺序
        Collections.reverse(list);
        System.out.println("反转元素的顺序");
        System.out.println("list="+list+"\n");
        //2)shuffle(List)：对List集合元素进行随机排序
        Collections.shuffle(list);
        System.out.println("对元素进行随机排序");
        System.out.println("list="+list+"\n");
        //3)sort(List)：对List集合元素进行排序
        Collections.sort(list);
        System.out.println("对元素进行排序");
        System.out.println("list="+list+"\n");
        //4)sort(List,Comperator)：根据指定的Comperator产生的顺序对List集合元素进行排序
        Collections.sort(list, new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((String)o1).compareTo((String) o2);
            }
        });
        System.out.println("按字符串大小进行排序");
        System.out.println("list="+list+"\n");
        //5)swap(List,int,int)：将指定List集合中的i处元素和j处元素进行交换
        System.out.println("第一处和第二处元素进行交换");
        Collections.swap(list,0,1);
        System.out.println("list="+list);
        //6)Object max(Collection)：根据元素的自然顺序，返回该集合中的最大元素
        System.out.println("自然顺序中的最大元素"+Collections.max(list)+"\n");
        //7)Object max(Collection,Comparator)：根据Comparator指定的顺序，返回给定集合中
        //的最大元素
        //返回长度最大的元素
        Object maxObject = Collections.max(list, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((String)o1).length()-((String)o2).length();
            }
        });
        System.out.println("长度最大的元素"+maxObject+"\n");
        //Object min(Collection)
        //Object min(Collection,Comparator)
        //参考max
        //8)int frequency(Collection,Object)：返回集合中指定元素出现的次数
        System.out.println("tom出现的次数="+Collections.frequency(list,"tom"));
        //9)void copy(List dest,List src)：将src复制到dest中（要求：确保dest的大小大于等于src
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            arrayList.add("");
        }
        Collections.copy(arrayList,list);
        System.out.println("arrayList="+arrayList);
        //10)boolean repalceAll(List list,Object oldVal,Object newVal)：使用新值
        //替换List对象的所有旧值
        Collections.replaceAll(list,"tom","汤姆");



    }
}
