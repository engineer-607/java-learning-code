package com.nanxinda.list;

import java.util.ArrayList;
import java.util.List;

public class ListMethod {
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        //1.void add(int index,Object ele)：在index位置插入ele元素
        List list = new ArrayList();
        list.add("张三丰");
        list.add("贾宝玉");
        list.add(1,"hsp");
        System.out.println("list="+list);
        //2.void addAll(int index,Collection eles)：从index位置开始将eles中所有元素添加进来
        List list1 = new ArrayList();
        list1.add("jack");
        list1.add("tom");
        list.addAll(1,list1);
        System.out.println("list="+list);
        //3.Object get(int index)：获取指定index位置的元素
        //4.int index(Objec obj：返回obj在当前集合中首次出现的位置
        //5.int lastIndexOf(Object obj)：返回obj在当前集合中末次出现的位置
        //6.Object remove(int index)：移除指定index位置的元素，并返回此元素
        list.remove(0);
        System.out.println("list="+list);
        //7.Object set(int index,Object ele)：设置指定index位置的元素为ele，相当于是替换
        list.set(1,"玛丽");
        System.out.println("list="+list);
        //8.List subList(int fromIndex,int toIndex)：返回从fromIndex到toIndex位置的子集合
        //注意返回的子集合 fromIndex<= subList <toIndex
        List returnList = list.subList(0,2);
        System.out.println("returnList="+returnList);

    }
}
