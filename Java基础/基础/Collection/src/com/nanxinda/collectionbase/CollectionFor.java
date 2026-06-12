package com.nanxinda.collectionbase;

import java.util.ArrayList;
import java.util.List;

public class CollectionFor {
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        /*
        增强for循环，可以替代iterator迭代器，特点：增强for就是简化版的iterator
        本质一样，只能用于遍历集合或者数组
         */
        List list = new ArrayList();
        list.add(new Book("三国演义","罗贯中",10.1));
        list.add(new Book("小李飞刀","古龙",5.1));
        list.add(new Book("红楼梦","曹雪芹",34.6));
        //1.增强for底层仍然时迭代器
        //2.增强for可以理解为简化版的iterator
        //3.快捷键方式是I
        for (Object book :list) {
            System.out.println("book="+book);
        }
        //增强for，也可以在数组中使用

    }
}
