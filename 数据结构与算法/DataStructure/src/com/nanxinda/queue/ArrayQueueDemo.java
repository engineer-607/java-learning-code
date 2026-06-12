package com.nanxinda.queue;

import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args) {
        /*
        队列：
        1.队列是一个有序列表，可以使用数组或者链表来实现
        2.遵循先入先出的原则（先存入队列的数据要先取出，后存入的要后取出）
        3.存入的时候rear往后移动，取出的时候front往后移动
         */
        /// 目前代码的问题：
        /// 当取完数据，再添加数据，会无法添加
        ArrayQueue arrayQueue = new ArrayQueue(3);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出队列");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列中获取数据");
            System.out.println("h(head):查看队列头的数据");
            System.out.println("请输入一个字符");
            key = scanner.next().charAt(0);
            switch (key){
                case 's':
                    try {
                        arrayQueue.showQueue();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    loop = false;
                    scanner.close();
                    break;
                case 'a':
                    System.out.println("请输入一个整数：");
                    arrayQueue.addQueue(scanner.nextInt());
                    break;
                case 'g':
                    try {
                        System.out.println(arrayQueue.getQueue());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        System.out.println(arrayQueue.headQueue());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    System.out.println("字符输入错误，请重新输入：");
            }
        }
        System.out.println("程序退出");
    }
}
//使用数组模拟队列
class ArrayQueue{
    private int maxSize;//表示数组的最大容量
    private int rear;//队列尾
    private int front;//队列头
    private int[] array;//该数组用于存放数组，模拟队列
    public ArrayQueue(int maxSize){
        this.maxSize = maxSize;
        rear = -1;//指向队列尾部【包含队列最后一个元素】
        front = -1;//指向队列头部【实际上是队列头的第一个位置】
        array = new int[maxSize];
    }
    public boolean isEmpty(){
        return rear==front;
    }
    public boolean isFull(){
        return rear==maxSize-1;
    }
    public void addQueue(int n){
        if (isFull()){
            System.out.println("队列已满，不能添加数据");
             return;
        }
        array[++rear] = n;
    }
    public int getQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空,不能取出数据");
        }
        return array[++front];
    }
    public void showQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空，没有数据");
        }
        for (int i = 0; i < array.length; i++) {
            System.out.printf("array[%d] = %d\n",i,array[i]);
        }
    }
    public int headQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空，没有数据");
        }
        return array[front+1];
    }
}