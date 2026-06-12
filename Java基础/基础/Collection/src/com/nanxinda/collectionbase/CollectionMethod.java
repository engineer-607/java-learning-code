package com.nanxinda.collectionbase;

import java.util.ArrayList;
import java.util.List;

public class CollectionMethod {
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        /*
        Collection接口实现类特点：
        1）collection实现子类可以存放多个元素，每个元素可以是Object
        2）collection实现类，可以存放重复的元素，有些不可以
        3）collection实现类，可以是有序的（List），可以是无序的（Set）
        4）collection接口没有直接的实现子类，是通过它的子接口Set和List来实现的
         */
        //Collection常见方法（以其实现子类ArrayList来演示）
        List list = new ArrayList();
        //add添加单个元素
        list.add("jack");
        list.add(10);//有自动装箱的过程(int->Integer)
        list.add(true);
        System.out.println("list="+list);
        //remove：删除指定元素
        list.remove(0);//删除第一个元素
        list.remove(Integer.valueOf(10));//删除某个指定元素
        System.out.println("list="+list);
        //contains：查找某个元素是否存在
        System.out.println(list.contains(true));
        //size：获取集合中的元素
        System.out.println(list.size());//1
        //isEmpty：判断是否为空
        System.out.println(list.isEmpty());//false
        //clear：清空
        list.clear();
        System.out.println("list="+list);
        //addAll：添加多个元素（实际是添加集合）
        List list1 = new ArrayList();
        list1.add("红楼梦");
        list1.add("三国演义");
        list.addAll(list1);
        System.out.println("list="+list);
        //conmtainsAll
        System.out.println(list.containsAll(list1));
        //removeAll：删除多个元素
        list.add("聊斋");
        list.removeAll(list1);
        System.out.println("list"+list);


    }
}
