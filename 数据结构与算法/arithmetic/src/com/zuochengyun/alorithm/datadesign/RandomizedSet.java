package com.zuochengyun.alorithm.datadesign;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

public class RandomizedSet {
    //设计一个支持在平均 时间复杂度 O(1) 下，执行以下操作的数据结构:
    //    insert(val)：当元素 val 不存在时返回 true ，并向集合中插入该项，否则返回 false 。
    //    remove(val)：当元素 val 存在时返回 true ，并从集合中移除该项，否则返回 false 。
    //    getRandom：随机返回现有集合中的一项。每个元素应该有 相同的概率 被返回。

    //思路分析：
    //1.数据结构：一个HashMap，一个ArrayList动态数组，HashMap负责增删，ArrayList可以通过连续的下标
    //随机返回一个索引，负责随机返回集合中一项
    //2.增：HashMap先检查有无该元素，如果没有，
    // 动态数组添加该元素，并返回该元素的索引，然后将元素和索引以键值对的形式存入hashset
    //3.删：同样hashmap先检查有无该元素a，没有返回false，有则获取a索引，然后分两种情况
    // 如果获取的索引是最后一个，动态数组直接删除，然后HashMap同样也删除，如果索引并不是最后一个
    // 需要在动态数组中先获取最后一个元素b，然后在hashmap中将b对应的value修改为要a的索引，同时删除a
    // 之后在动态数组中删除b元素，并且将a修改为b（原因：只有通过把最后一个元素填到被删除元素的坑上
    // 才能确保正常执行随机返回一个元素，因为随机返回的前提是需要一个连续的索引范围)

    Map<Integer,Integer> map;
    List<Integer> list;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(map.containsKey(val)){
            return false;
        }else {
            list.add(val);
            map.put(val,list.size()-1);
            return true;
        }
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(map.containsKey(val)){
            Integer lastVal;
            int removeIndex;
            int lastIndex;
            if(val==(lastVal = list.get((lastIndex = list.size()-1)))){
                list.remove(lastIndex);
                map.remove(val);
            }else {
                map.replace(lastVal, (removeIndex = map.get(val)));
//                list.add(removeIndex,lastVal);
//                list.remove(lastIndex);
                //NOTE 两个坑：第一个，add并不是修改而是在原来的位置插入元素，原来的元素整体向后移
                // 如果想要修改需要使用set方法；第二个，remove方法有两种
                // remove(int index)      按索引删
                // remove(Object o)       按元素值删
                // 如果索引的类型Integer，remove就会把该值当成元素而不是索引
                list.set(removeIndex, lastVal);
                list.remove(lastIndex);
                map.remove(val);
            }
            return true;
        }else {
            return false;
        }
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return list.get((int)(Math.random()*list.size()));
    }

    @Test
    public void testRemoveIndexOutOfBounds() {
        RandomizedSet randomizedSet = new RandomizedSet();
        randomizedSet.insert(1);
        randomizedSet.insert(10);
        randomizedSet.insert(20);
        randomizedSet.insert(30);

        boolean get1 = false;
        boolean get10 = false;
        boolean get20 = false;
        boolean get30 = false;
        for (int i = 0; i < 200; i++) {
            int random = randomizedSet.getRandom();
            if (random == 1) {
                get1 = true;
            } else if (random == 10) {
                get10 = true;
            } else if (random == 20) {
                get20 = true;
            } else if (random == 30) {
                get30 = true;
            }
        }

        if (!get1 || !get10 || !get20 || !get30) {
            throw new AssertionError("getRandom did not return all inserted values");
        }
    }
}
