package com.nanxinda.map_;

import java.util.Hashtable;
@SuppressWarnings({"all"})
public class HashTableDetail {
    public static void main(String[] args) {
        /*
        HashTable:
        1)存放的元素是键值对：k-v
        2)hashtable的键和值都不能为null，否则会抛出NullPointerException
        3)hashtable使用方法基本上和HashMap一样
        4)hashtable是线程安全的（synchronized），hashMap是线程不安全的
         */
        //HashTable底层（简化）
        //1.底层有数组HashTable$Entry[] 初始化大小为11
        //2.临界值 threshold为8 ：11*0.75
        //3.扩容：按照自己的扩容机制来进行
        Hashtable hashtable = new Hashtable();
//        hashtable.put(null,1);NullPointerException
//        hashtable.put(1,null);NullPointerException
        for (int i = 1;i< 9;i++){
            hashtable.put("hello"+i,1);
            //4.执行方法 addEntry(hash, key, value, index);添加K-V封装到Entry
            //5.当if (count >= threshold) 满足时，就进行扩容
            //按照int newCapacity = (oldCapacity << 1) + 1;的大小进行扩容

        }
        /*
        hashtable和hashmap比较
        版本	   线程安全 （同步）	效率	 允许null键null值
       HashMap	 1.2	不安全	 高	 可以
       Hashtable 1.0    安全	    较低	 不可以
         */
    }
}
