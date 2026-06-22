package com.zuochengyun.alorithm.datadesign;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class FreqStack {
    //Design a stack-like data structure to push elements to the stack
    //and pop the most frequent element from the stack.
    //Implement the FreqStack class:
    //FreqStack() constructs an empty frequency stack.
    //void push(int val) pushes an integer val onto the top of the stack.
    //int pop() removes and returns the most frequent element in the stack.
    //If there is a tie for the most frequent element,
    //the element closest to the stack's top is removed and returned.

    //思路分析：
    // 原先:打算用hash表为频次表+双向链表模拟栈，但是这种方式时间复杂度
    // 没有办法达到O（1），而且操作复杂
    // 更正：采用两个hash表，一个作为次频表，键为加入的数字，值为频次，
    // 一个模拟栈，键为频次，值为数组，数组记录该频次下的数字以及插入的顺序

    //实现分析：
    //1.push方法，先频次表检查有无该值，没有添加数字以及频次，同时检查
    // 栈中有无对应的频次，有就在数组后面添加，没有就创建并添加元素
    // 如果频次检查有该值，则频次++，栈同上，定义最大频次计数器每次添加都记得
    // 检查更新
    //2.pop方法：从最大频次计数器获取对应的数组，直接移除最后一个
    // 同时检查该数组是否为空，如果为空，最大计数器--,同时频次表中值对应的频次--，如果为1直接移除该值
    HashMap<Integer,Integer> mapFreq;
    HashMap<Integer, ArrayList<Integer>> stack;
    public static Integer maxFreq = 0;
    public FreqStack() {
        mapFreq = new HashMap<>();
        stack = new HashMap<>();
    }

    public void push(int val) {
        int freq = 1;
        if(mapFreq.containsKey(val)){
            mapFreq.replace(val,freq = (mapFreq.get(val))+1);
        }else {
            mapFreq.put(val,freq);
        }
        maxFreq = maxFreq>freq?maxFreq:freq;
        ArrayList<Integer> arrayList = stack.containsKey(freq)?stack.get(freq):new ArrayList<>();
        arrayList.add(val);
        stack.put(freq,arrayList);

    }

    public int pop() {
        ArrayList<Integer> arrayList = stack.get(maxFreq);
        int val = arrayList.get(arrayList.size()-1);
        if(maxFreq==1){
            mapFreq.remove(val);
        }else {
            mapFreq.replace(val,maxFreq-1);
        }
        arrayList.remove(arrayList.size()-1);
        if(arrayList.isEmpty()){
            stack.remove(maxFreq);
            maxFreq--;
        }
        return val;
    }
    @Test
    public void test(){
        FreqStack freqStack = new FreqStack();
        freqStack.push(5);
        freqStack.push(7);
        freqStack.push(5);
        freqStack.push(7);
        freqStack.push(4);
        freqStack.push(5);
        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());

    }
}
