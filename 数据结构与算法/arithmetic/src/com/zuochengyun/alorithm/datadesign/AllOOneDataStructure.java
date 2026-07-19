package com.zuochengyun.alorithm.datadesign;

import org.junit.Test;
import org.junit.Assert;

import java.util.HashMap;
import java.util.HashSet;
@SuppressWarnings({"all"})
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

    //现在思路：1.保留map1，但是值不是频次，而是桶节点，map2舍弃，用双向链表桶节点来记录频次和对应的键（按照顺序）
    // 这种方式就可以解决最小值被删除找不到下一个最小值的问题
    //具体实现：1.定义内部类桶，属性前指针、后指针以及整数频次，hashset，set表存储该频次下的字符串，
    // 同时定义头节点频次为0，尾节点指针为int类型最大数，再定义一个hash表，键为字符串，值为对应的桶
    public class Bucket{
        public Bucket(Integer freq){
            this.freq = freq;
        }
        Bucket pre;
        Bucket next;
        Integer freq;
        HashSet<String> hashSet = new HashSet<>();
    }
    private Bucket head;
    private Bucket tail;
    private HashMap<String,Bucket> hashMap;
    public AllOOneDataStructure() {
        head = new Bucket(0);
        tail = new Bucket(Integer.MAX_VALUE);
        head.next = tail;
        tail.pre = head;
        hashMap = new HashMap<>();
    }
    //2.inc方法：
    // 检查hash表中有无对应的键，如果有，就获取对应的桶节点，删除hashset中对应的字符串（注意hashset删完之后是否为空）
    // ，然后查找该节点next节点频次是否是当前节点频次+1，如果是，键直接加入next节点的hashset中，如果不是创建一个新的桶节点
    // 设置该节点的频次，前后指针，以及将该字符串加入其hashset中；如果没有，就查找有无频次为1的桶（头指针next）
    // 如果有，则字符串直接加入其hashset，如果没有就创建新的桶节点，频次为1，调好前后指针，hashset加入字符串
    // 最后，上述步骤完结后将值指向对应的桶节点
    public void inc(String key) {
        Bucket pre, now;
        HashSet<String> hashSetPre, hashSetNow;
        if(hashMap.containsKey(key)){
            pre = hashMap.get(key);
            hashSetPre = pre.hashSet;
            hashSetPre.remove(key);
            now = pre.next;
            if(now.freq == pre.freq+1){
                hashSetNow = now.hashSet;
                hashSetNow.add(key);
            }else {
                now = new Bucket(pre.freq+1);
                now.hashSet.add(key);
                now.pre = pre;
                now.next = pre.next;
                pre.next.pre = now;
                pre.next = now;
            }
            if(hashSetPre.isEmpty()){
                pre.pre.next = now;
                now.pre = pre.pre;
            }
            hashMap.replace(key,now);
        }else {
            if(head.next.freq==1){
                now = head.next;
                now.hashSet.add(key);
            }else {
                now = new Bucket(1);
                now.hashSet.add(key);
                now.pre = head;
                now.next = head.next;
                now.next.pre = now;
                head.next = now;
            }
            hashMap.put(key,now);
        }
    }
    //3.dec方法：
    //直接获取该字符串的对应的桶节点，检查该节点频次是否为1，如果为1，直接hash表删除该键，并且hashset移除该
    //该字符串，并检查移除之后hashset是否为空，如果为空移除该节点；如果不为1，获取该桶节点，移除该字符串
    //查找该桶节点的pre节点，如果pre节点的频次为当前频次-1，字符串直接添加进pre节点的hashset，如果频次并非如此
    // 就创建一个新的节点，然后调整前后节点（调整时看看原节点的hashset是否为空，为空顺手移除他），设置频次以及hashset
    // 最后，将hash表中值进行调整
    public void dec(String key) {
        Bucket now = hashMap.get(key),next;
        HashSet<String> hashSetNow = now.hashSet;
        hashSetNow.remove(key);
        if(now.freq==1){
            hashMap.remove(key);
            now = head;
        }else {
            if(now.pre.freq==now.freq-1){
                now = now.pre;
                now.hashSet.add(key);
            }else {
                next = now;
                now = new Bucket(next.freq-1);
                now.hashSet.add(key);
                now.next = next;
                now.pre = next.pre;
                now.pre.next = now;
                next.pre = now;
            }
            hashMap.put(key,now);
        }
        if(hashSetNow.isEmpty()){
            now.next = now.next.next;
            now.next.pre = now;
        }

    }
    //4.检查尾节点的pre节点，如果为头结点，就返回""，如果不为空，返回节点hashset的任意字符串
    public String getMaxKey() {
        if(tail.pre==head){
            return "";
        }
        return tail.pre.hashSet.iterator().next();
    }
    //5.检查头结点的next节点，如果为尾节点，就返回""，如果不为空，返回hashset中的任意字符串
    public String getMinKey() {
        if(head.next==tail){
            return "";
        }
        return head.next.hashSet.iterator().next();
    }

    @Test
    public void testLeetcodeCaseFromScreenshot(){
        AllOOneDataStructure allOne = new AllOOneDataStructure();

        allOne.inc("a");
        allOne.inc("b");
        allOne.inc("b");
        allOne.inc("b");
        allOne.inc("c");
        allOne.inc("c");
        allOne.inc("c");
        allOne.inc("c");
        allOne.inc("c");
        allOne.inc("c");
        allOne.inc("d");
        allOne.inc("d");
        allOne.inc("d");
        allOne.inc("d");
        allOne.inc("d");
        allOne.inc("d");
        allOne.inc("d");

        Assert.assertEquals("d", allOne.getMaxKey());
        Assert.assertEquals("a", allOne.getMinKey());

        allOne.inc("b");
        allOne.dec("d");

        String maxKey = allOne.getMaxKey();
        Assert.assertTrue(maxKey.equals("c") || maxKey.equals("d"));
        Assert.assertEquals("a", allOne.getMinKey());
    }

    @Test
    public void testEmptyStructure(){
        AllOOneDataStructure allOne = new AllOOneDataStructure();

        Assert.assertEquals("", allOne.getMaxKey());
        Assert.assertEquals("", allOne.getMinKey());
    }

    @Test
    public void testLeetcodeCaseDeleteLastKeyThenAddAgain(){
        AllOOneDataStructure allOne = new AllOOneDataStructure();

        allOne.inc("hello");
        allOne.inc("hello");
        Assert.assertEquals("hello", allOne.getMaxKey());
        Assert.assertEquals("hello", allOne.getMinKey());

        allOne.dec("hello");
        allOne.dec("hello");
        Assert.assertEquals("", allOne.getMaxKey());

        allOne.inc("hello");
        Assert.assertEquals("hello", allOne.getMinKey());
    }
}
