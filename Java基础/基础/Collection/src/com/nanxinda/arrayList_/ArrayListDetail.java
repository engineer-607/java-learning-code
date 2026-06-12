package com.nanxinda.arrayList_;

import java.util.ArrayList;
@SuppressWarnings({"all"})
public class ArrayListDetail {
    public static void main(String[] args) {
    //1）permits all elements ,including null,ArrayList可以加入null,并且是多个
    ArrayList list = new ArrayList();
    list.add(null);
    //2）ArrayList是由数组来实现数据存储
        // 3）ArrayList基本等同于Vactor，除了ArrayList是线程不安全（执行效率高）
        /*没有synchronized
            public boolean add(E e) {
               ensureCapacityInternal(size + 1);  // Increments modCount!!
               elementData[size++] = e;
               return true;
            }
         */
        //在多线程下不建议使用ArrayList
    }
}
