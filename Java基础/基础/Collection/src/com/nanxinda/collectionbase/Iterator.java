package com.nanxinda.collectionbase;

import java.util.ArrayList;
import java.util.List;

public class Iterator {
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        /*
        Collection接口遍历元素方式1-使用Iterator（迭代器）
        1）Iterator对象称为迭代器，主要用于遍历Collection集合中的元素
        2）所有实现Collection接口的集合类都有一个iterator()方法，用以
        返回一个实现Iterator接口的对象，即返回一个迭代器
         */
        //迭代器的常用方法
        //1.hasNext()：判断是否还有下一个元素
        //2.next()作用：1）下移 2）将下移以后集合位置上的元素返回
        /*
        注意点:在调用iterator.newt()方法之前必须要调用iterator.hasNext()
        进行检测；若不调用，且下一条记录无效，直接调用iterator.next()会抛出异常
        NoSuchElementException
         */
        //案例
        List list = new ArrayList();
        list.add(new Book("三国演义","罗贯中",10.1));
        list.add(new Book("小李飞刀","古龙",5.1));
        list.add(new Book("红楼梦","曹雪芹",34.6));
        //先得到list对应的迭代器
        java.util.Iterator iterator = list.iterator();
        //使用while循环遍历
        //使用itit快捷键可以快速生成迭代器的while循环
        //ctrl+j快捷键可以查看常用快捷键
        while (iterator.hasNext()) {//判断是否还有数据
            //返回下一个数据，类型是Object
            Object next =  iterator.next();
            System.out.println("next="+next);
        }
        //退出while循环时，迭代器指向最后一个元素
        //如果想要重置：
        iterator = list.iterator();
    }
}
class Book{
    private String name;
    private String author;
    private double price;

    public Book(String name, String author, double price) {
        this.name = name;
        this.author = author;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                '}';
    }
}