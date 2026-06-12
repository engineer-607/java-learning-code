package com.nanxinda.queue;

import java.util.Scanner;

public class CircularQueue {
    public static void main(String[] args) {
        /// 1.核心概念
        /// 1.1 环形数组可以解决普通数组出队之前的空间被浪费的问题
        /// 1.2 环形数组：首尾相连，当指针指向数组末尾时，会自动绕到开头
        /// 2.关键指针
        /// 2.1 front：队列顺序中第一个元素的位置
        /// 2.2 rear：队列顺序中最后一个元素的下一个位置（即下一次插入的位置）
        /// 3.核心公式
        /// 3.1 判断是否队列是否为空：front==rear
        /// 3.2 判断队列是否已满：(rear+1)%数组长度==front
        /// 3.3 计算队列的长度：(rear-front+数组长度)%数组长度
        /// 3.4 rear = (rear+1)%数组长度、front = (front+1)%数组长度
        /// 案例
        /**
         * 第1步：初始状态
         * [ ][ ][ ][ ][ ]
         * front=0, rear=0，队列空
         * 第2步：插入A、B、C
         * [A][B][C][ ][ ]
         * front=0, rear=3，有3个元素
         * 第3步：取出A
         * [ ][B][C][ ][ ]
         * front=1, rear=3，有2个元素
         * 第4步：插入D、E
         * 先插入D：[ ][B][C][D][ ]，front=1, rear=4
         * 再插入E：[E][B][C][D][ ]，front=1, rear=0
         * 此时 (rear+1)%5 = 1 == front，队列已满
         * 第5步：取出B
         * text
         * [E][ ][C][D][ ]
         * front=2, rear=0，有3个元素
         * 第6步：插入F
         * [E][F][C][D][ ]
         * front=2, rear=1，有4个元素，再次满
         */

        /// 个人理解：
        /// 环形数组的精髓是取模和留出一个空位，在添加（入队）或者取出数据（出队）时，
        ///如果使用普通数组，则rear和front到达最后就需要数组扩容，同时造成之前开辟的空间被浪费
        ///而取模能够使rear和front从索引0重新开始，但这时候就出现一个新的问题，无法区分队列为空和队列已满的情况，
        ///这时候就需要留出一个空位，它能够让这两个情况区分开来（也可以添加一个变量count作为计数器，记录队列元素个数）

        CircleArray circleArray = new CircleArray(5);
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
                        circleArray.showQueue();
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
                    circleArray.addQueue(scanner.nextInt());
                    break;
                case 'g':
                    try {
                        System.out.println(circleArray.getQueue());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        System.out.println(circleArray.headQueue());
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
class CircleArray{
    private int maxSize;//表示数组的最大容量
    private int rear;//队列尾
    private int front;//队列头
    private int[] array;//该数组用于存放数组，模拟队列

    public CircleArray(int maxSize) {
        this.maxSize = maxSize;
        array = new int[maxSize];
    }
    public boolean isEmpty(){
        return rear==front;
    }
    public boolean isFull(){
        return (rear+1)%maxSize==front;
    }
    public void addQueue(int n){
        if (isFull()){
            System.out.println("队列已满，不能添加数据");
            return;
        }
        array[rear] = n;
        rear = (rear+1)%maxSize;
    }
    public int getQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空,不能取出数据");
        }
        int value = array[front];
        front = (front+1)%maxSize;
        return value;
    }
    public void showQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空，没有数据");
        }
        for (int i = front; i < (rear-front+maxSize)%maxSize; i++) {
            System.out.printf("array[%d] = %d\n",i%maxSize,array[i%maxSize]);
        }
    }
    public int headQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空，没有数据");
        }
        return array[front];
    }
}