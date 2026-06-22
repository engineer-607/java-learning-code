package com.zuochengyun.alorithm.datadesign;

import java.util.PriorityQueue;

public class FindMedianFromDataStream{
//The median is the middle value in an ordered integer list.
//If the size of the list is even, there is no middle value,
//and the median is the mean of the two middle value
//For example, for arr = [2,3,4], the median is 3
//For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5
//Implement the MedianFinder class
//MedianFinder() initializes the MedianFinder object
//void addNum(int num) adds the integer num from the data stream to the data structure
//double findMedian() returns the median of all elements so far
//Answers within 10-5 of the actual answer will be accepted

    //思路分析：
    //1.需要持续获取中间数或者中间两个数，可以构造一个大根堆一个小根堆并且保持两个堆元素个数之差小于等于1
    //这种数据结构就可以保持中位数或者中间两个数从两个堆的堆顶获得
    //具体实现：1.定义一个大根堆large，一个小根堆small（用PropertiesQueue模拟堆结构，内部结构可能不是堆结构但是堆顶一定是最大或者
    // 最小，peak可以看到堆顶，pop弹出堆顶）
    // 2.addNum方法：第一次加入时，元素加到大根堆，之后加入的元素如果小于大根堆堆顶，加入到大根堆，但是加入之后如果
    // 两个堆元素之差大于1，需要将大根堆堆顶弹出进入小根堆，如果元素大于大根堆将元素加入小根堆，同理如果两者元素之差
    // 大于1，将小根堆的堆顶弹出加入到大根堆中
    PriorityQueue<Integer> large = new PriorityQueue<>((a,b)->Integer.compare(b,a));
    PriorityQueue<Integer> small = new PriorityQueue<>();

    public FindMedianFromDataStream() {

    }

    public void addNum(int num) {
        if(large.isEmpty()||num<large.peek()){
            large.offer(num);
        }else {
            small.offer(num);
        }
        if(large.size()-small.size()>1){
            small.offer(large.poll());
        }else if(small.size()-large.size()>1){
            large.offer(small.poll());
        }
    }

    public double findMedian() {
        if(large.size()==small.size()){
            return (double) (large.peek()+small.peek())/2;
        }else if(large.size()>small.size()){
            return large.peek();
        }else {
            return small.peek();
        }

    }



}
