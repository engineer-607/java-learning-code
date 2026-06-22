package com.zuochengyun.alorithm.datadesign;

public class AllOOneDataStructure {
    //Design a data structure to store the strings' count with the ability
    //to return the strings with minimum and maximum counts.
    //Implement the AllOne class:
    //AllOne() Initializes the object of the data structure.
    //inc(String key) Increments the count of the string key by 1.
    //If key does not exist in the data structure, insert it with count 1.
    //dec(String key) Decrements the count of the string key by 1.
    //If the count of key is 0 after the decrement,
    //remove it from the data structure.
    //It is guaranteed that key exists in the data structure before the decrement.
    //getMaxKey() Returns one of the keys with the maximal count.
    //If no element exists, return an empty string "".
    //getMinKey() Returns one of the keys with the minimum count.
    //If no element exists, return an empty string "".
    //that each function must run in O(1) average time complexity.
    //原本思路：
    //map1: key -> count
    //map2: count -> 一堆 key
    //maxCount
    //minCount
    //NOTE 核心问题：如果某个最小值一直删变为0，无法在保证达到O（1）的时间复杂度下
    // 得知下一个最小值为哪个字符串，如：a:1 b:100 c:200，dec("a")，a被移除


    public AllOOneDataStructure() {
    }

    public void inc(String key) {

    }

    public void dec(String key) {

    }

    public String getMaxKey() {

    }

    public String getMinKey() {

    }
}
