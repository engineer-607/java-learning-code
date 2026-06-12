package com.nanxinda.map_;

import java.util.*;

@SuppressWarnings({"all"})
public class MapFor {
    public static void main(String[] args) {
        /*
        Map接口遍历方法
        1）containsKey：查找键是否存在
        2）KeySet：获取所有键
        3）entrySet：获取所有关系
        4）values：获取所有的值
         */
        Map map = new HashMap();
        map.put("邓超",new Book("一千零一夜",100));
        map.put("王宝强","孙俪");
        map.put("宋喆","马蓉");
        map.put("刘令博",null);
        map.put(null,"刘亦菲");
        map.put("鹿晗","关晓彤");
        //第一组：先取出所有的key，通过key取出对应的value
        Set ksySet = map.keySet();
        //(1)增强for
        System.out.println("====第一种方式====");
        for (Object key :ksySet) {
            System.out.println(key+"-"+map.get(key));
        }
        //(2)迭代器
        System.out.println("====第二种方式====");
        Iterator iterator = ksySet.iterator();
        while (iterator.hasNext()) {
            Object key = iterator.next();
            System.out.println(key + "-" + map.get(key));
        }
        // 第二组：获取所有的values
        Collection values = map.values();

        // 这里可以使用所有的Collections遍历方法

        // (1) 增强for循环
        System.out.println("====取出所有的value 增强for====");
        for (Object value : values) {
            System.out.println(value);
        }

        // (2) 迭代器
        System.out.println("====取出所有的value 迭代器====");
        Iterator iterator2 = values.iterator();
        while (iterator2.hasNext()) {
            Object value = iterator2.next();
            System.out.println(value);
        }
        //通过EntrySet来获取k-v
        //(1)增强for
        System.out.println("====使用EntrySet的增强for====");
        Set sets = map.entrySet();
        for (Object object :sets) {
             Map.Entry entry = (Map.Entry) object;
            System.out.println(entry.getKey()+"-"+entry.getValue());
        }
        //(2)迭代器
        iterator2 = sets.iterator();
        System.out.println("====使用EntrySet的迭代器====");
        while (iterator2.hasNext()) {
            Object next =  iterator2.next();
            Map.Entry entry = (Map.Entry) next;
            System.out.println(entry.getKey()+"-"+entry.getValue());
        }
    }
}
