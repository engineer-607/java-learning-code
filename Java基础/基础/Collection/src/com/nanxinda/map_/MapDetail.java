package com.nanxinda.map_;

import java.util.HashMap;
import java.util.Map;
@SuppressWarnings({"all"})
public class MapDetail {
    public static void main(String[] args) {
        //Map接口实现类的特点：
        //1）Map与Collection并列存在，用于保存具有映射关系的数据Key-Value
        //2）Map中的key和value可以是任何引用类型的数据，会封装到HashMap$Node对象中
        Map map = new HashMap();
        map.put("no1","jack");
        //3）Map中的key不允许重复，原因和HashSet一样
        map.put("no1","john");//当有相同的key，等价于进行替换
        //4）Map中的value可以重复
        map.put("no3","john");
        //5）Map的key可以为null，value也可以为null（注意：key为null，只能有一个，但是value可以有多个null）
        map.put(null,null);
        map.put(null,"abc");//等价替换
        map.put("no4",null);
        //6）常用String作为Map的key
        System.out.println("map="+map);
        //7）key和value之间存在单一的一对一关系，即通过指定的key总能找到对应的value
        System.out.println(map.get("no1"));
        /*
        Map体系继承图
 *                  Iterable (接口)
 *                      |
 *                  Collection (接口)
 *                      |
 *                 ┌────┴────┐
 *                 ↓         ↓
 *              List        Set       Queue
 *            (接口)       (接口)      (接口)
 *
 *              Map (接口) ← 独立于Collection的另一体系
 *                 │
 *      ┌──────────┼──────────┬────────────┐
 *      ↓          ↓          ↓            ↓
 *   HashMap    Hashtable   SortedMap    Properties
 *      │          │          (接口)         │
 *      │          │            │          (继承自Hashtable)
 *      ↓          ↓            ↓
 *   LinkedHashMap          TreeMap
         */




    }
}
