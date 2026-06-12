package com.nanxinda.set_;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
@SuppressWarnings({"all"})
public class SetDetail {
    public static void main(String[] args) {
        /*
        Set接口
        1）无序（添加和取出顺序不一致），没有索引
        2）不允许有重复元素，最多包含一个null
        3）和List接口一样，Set接口也是Collection子接口，因此，常用方法和Collection接口一样

        Set接口遍历方式
        同Collection的遍历方式一样，因为Set接口是Collection接口的子接口
        1.可以使用迭代器
        2.增强for
        3.不能使用索引的方式来获取
         */
        //Set接口实现类HashSet的常见方法
        Set set = new HashSet();
        set.add("john");
        set.add("lucy");
        set.add("john");//重复
        set.add("jack");
        set.add(null);
        System.out.println("set="+set);
        //遍历方式
        //方式一：迭代器
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Object next =  iterator.next();
            System.out.println(next);
        }
        //方式二：增强for
        System.out.println("====增强for====");
        for (Object object :set) {
            System.out.println(object);
        }


    }
}
