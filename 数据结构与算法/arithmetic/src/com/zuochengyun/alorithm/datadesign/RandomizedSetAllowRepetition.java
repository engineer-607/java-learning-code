package com.zuochengyun.alorithm.datadesign;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@SuppressWarnings({"all"})
public class RandomizedSetAllowRepetition {
    //RandomizedCollection 是一种包含数字集合(可能是重复的)的数据结构。
    //它应该支持插入和删除特定元素，以及删除随机元素。
    //实现 RandomizedCollection 类:
    //RandomizedCollection()初始化空的 RandomizedCollection 对象。
    //bool insert(int val) 将一个 val 项插入到集合中，即使该项已经存在。
    //如果该项不存在，则返回 true ，否则返回 false 。
    //bool remove(int val) 如果存在，从集合中移除一个 val 项。
    //如果该项存在，则返回 true ，否则返回 false 。注意，如果 val 在集合中出现多次，我们只删除其中一个。
    //int getRandom() 从当前的多个元素集合中返回一个随机元素。
    //每个元素被返回的概率与集合中包含的相同值的数量 线性相关 。
    //每个函数的 平均 时间复杂度为 O(1)

    //思路分析:
    //1.同样需要一个HashMap和动态数组ArrayList，但是HashMap的值为动态数组，一个键（动态数组中的值）对应多个索引
    //原因分析：动态数组主要作用是存储和删除，而哈希表的作用是查找，进而整个函数实现O（1）
    //2.插入，在HashMap中查找，如果存在返回false，并且在该键对应的动态数组添加ArrayList中的索引，如果不存在，就添加
    //插入过程ArrayList与哈希表同步，不过需要返回当前数组最后一个索引
    //3.删除：哈希表中查找，如果不存在，返回false，如果存在，就删除动态数组中的第一个元素，删该元素时需要分两种
    //情况，第一种该元素就是最后一个元素，ArrayList直接删除，如果该元素不是最后一个元素，就需要从动态数组中获取
    //最后一个元素，将最后一个索引移动到删除元素的位置上(动态数组最后一个位置要删除，删除位置元素要替换，哈希表
    //删除元素对应的索引需删除，最后一个元素的索引需改变），最后需要检查删除元素对应的动态数组元素个数如果为0
    //整个键直接删除
    //4.以动态数组的长度为随机取值的范围
    Map<Integer, List<Integer>> hashMap;
    ArrayList<Integer> arrayList;
    public RandomizedSetAllowRepetition(){
        hashMap = new HashMap<>();
        arrayList = new ArrayList<>();
    }
    public boolean insert(int val){
        ArrayList<Integer> list;
        arrayList.add(val);
        if(hashMap.containsKey(val)){
            list = (ArrayList<Integer>) hashMap.get(val);
            list.add(arrayList.size()-1);
            return false;
        }else {
            list = new ArrayList<>();
            list.add(arrayList.size()-1);
            hashMap.put(val,list);
            return true;
        }
    }

    public boolean remove(int val){
        if(hashMap.containsKey(val)){
            ArrayList<Integer> list = (ArrayList<Integer>) hashMap.get(val);
            int index = list.get(0);
            int lastIndex;
            if(index==(lastIndex = arrayList.size()-1)){
                list.remove(0);
                arrayList.remove(lastIndex);
            }else {
                int lastVal = arrayList.get(lastIndex);
                arrayList.remove(lastIndex);
                arrayList.set(index,lastVal);
                list.remove(0);
                ArrayList<Integer> list1 = (ArrayList<Integer>) hashMap.get(lastVal);
                list1.add(index);
                list1.remove((Integer) lastIndex);
            }
            if(list.isEmpty()){
                hashMap.remove(val);
            }
            return true;
        }
        return false;
    }
    public int getRandom(){
        return arrayList.get((int) (Math.random()*arrayList.size()));
    }
    @Test
    public void test(){
        RandomizedSetAllowRepetition allowRepetition = new RandomizedSetAllowRepetition();
        allowRepetition.insert(0);
        allowRepetition.insert(1);
        allowRepetition.remove(0);
        allowRepetition.insert(2);
        allowRepetition.remove(1);
        System.out.println(allowRepetition.getRandom());
    }
}
