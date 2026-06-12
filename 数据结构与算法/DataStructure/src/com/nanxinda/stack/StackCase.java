package com.nanxinda.stack;

import java.util.Scanner;

public class StackCase {
    /*
    栈（stack）
    1）栈是一个先入后出的有序列表
    2）栈是限制线性表中元素的插入和删除只能在线性表的同一端的一种特殊线性表，允许插入和删除的一端，为
    变化的一端，称为栈顶（Top），另一端为固定的一端，成为栈底（Bottom）
    3）根据栈的定义可知，最先放入栈中元素在栈底，最后放入的元素在栈顶，而删除元素刚好相反，最后放入的元素
    最先删除，最先放入的元素最后删除

     */
    public static void main(String[] args) {
        ArrayCalculatorStack arrayStack = new ArrayCalculatorStack(4);
        String key = "";
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            System.out.println("show:表示显示栈");
            System.out.println("exit:退出程序");
            System.out.println("push:表示添加数据到栈（入栈）");
            System.out.println("pop:表示从栈取出数据（出栈）");
            System.out.println("请输入你的选择：");
            key = scanner.next();
            switch (key){
                case "show":
                    arrayStack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    arrayStack.push(scanner.nextInt());
                    break;
                case "pop":
                    arrayStack.pop();
                    break;
                case "exit":
                    loop = false;
                    break;
                default:
                    System.out.println("输入有误");
            }
        }

    }
}
class ArrayStack{
    int[] stack;
    int maxSize;
    int top = -1;//表示栈顶
    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }
    public boolean isEmpty(){
        return top==-1;
    }
    public boolean isFull(){
        return top==maxSize-1;
    }
    public void push(int data){
        if(isFull()){
            System.out.println("栈已满，无法添加");
            return;
        }
        stack[++top] = data;
    }
    public Integer pop(){
        if (isEmpty()){
            System.out.println("栈为空，无法取出");
            return null;
        }
        return stack[--top];
    }
    public void list(){
        if(isEmpty()){
            System.out.println("栈为空，无数据");
            return;
        }
        for (int i = top; 0<=i ; i--) {
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }
}