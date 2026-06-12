package com.nanxinda.map_;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"all"})
public class MapSource {
    public static void main(String[] args) {
        Map map = new HashMap();
        map.put("no1","jack");
        //1.k-v 最后是HashMap$Node node = newNode(hash,key,value,null)
        //2.k-v为了方便程序员的遍历，还会创建EntrySet集合，该集合存放的元素的类型Entry，而一个
        //Entry对象就有k,v Entry<Entry<K,V>>——transient Set<Map.Entry<K,V>> entrySet;
        //3.entrySet中，定义的类型是Map.Entry，但是实际上集合存放的元素类型Node（Node实现接口Entry）
        //4.当把HashMap$Node 对象存放到entrySet就方便我们的遍历，因为Map.Entry提供重要方法
        // K getKey(); V getValue();
        Set set = map.entrySet();//返回EntrySet对象（EntrySet继承AbstractSet，而AbstractSet实现接口Set）
        System.out.println(set.getClass());//HashMap$EntrySet
        for (Object object :set) {
//            System.out.println(object.getClass());//HashMap$Node
            Map.Entry entry = (Map.Entry)object;
            System.out.println(entry.getKey()+"-"+entry.getValue());
        }
        //KeySet和Values这两个内部类分别存储的是HashMap的键引用、值引用，而不是直接存储数据
        Set set1 = map.keySet();//final class KeySet extends AbstractSet<K> {}
        //为了确保键的唯一性需要实现Set接口
        System.out.println(set1.getClass());
        Collection values = map.values();//final class Values extends AbstractCollection<V> {}
        //值可以不唯一所以使用Collection
        System.out.println(values.getClass());

    }
}
