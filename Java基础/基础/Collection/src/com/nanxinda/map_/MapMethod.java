package com.nanxinda.map_;

import java.util.HashMap;
import java.util.Map;
@SuppressWarnings("all")
public class MapMethod {
    public static void main(String[] args) {
        Map map = new HashMap();
        //1.put：添加
        map.put("邓超",new Book("一千零一夜",100));
        map.put("王宝强","孙俪");
        map.put("宋喆","马蓉");
        map.put("刘令博",null);
        map.put(null,"刘亦菲");
        map.put("鹿晗","关晓彤");
        //2.get：根据键获取值
        Object o = map.get("邓超");
        System.out.println(o);
        //3.remove：根据键删除映射关系
        map.remove("鹿晗");
        System.out.println(map);
        //4.查找键是否存在
        boolean containsKey = map.containsKey(null);
        System.out.println(containsKey);
        //5.size：获取元素个数
        System.out.println("k-v="+map.size());
        //6.clear：清除
        map.clear();
        //7.isEmpty()：判断个数是否为0
        System.out.println(map.isEmpty());


    }
}
class Book{
    private String name;
    private double price;
    public Book(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}