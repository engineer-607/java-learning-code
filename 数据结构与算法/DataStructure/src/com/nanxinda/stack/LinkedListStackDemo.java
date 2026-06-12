package com.nanxinda.stack;

import java.util.Scanner;

public class LinkedListStackDemo {
    public static void main(String[] args) {
        LinkedListStack linkedListStack = new LinkedListStack(4);
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
                    linkedListStack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    linkedListStack.push(scanner.nextInt());
                    break;
                case "pop":
                    linkedListStack.pop();
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
@SuppressWarnings({"all"})
class LinkedListStack{
    private int maxSize;
    private Node first = new Node(0,0);
    private Node top = first;
    private class Node{
        Node next;
        int no;
        int data;
        public Node(int data,int no){
            this.data = data;
            this.no = no;
        }

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", no=" + no +
                    '}';
        }
    }
    public LinkedListStack(int maxSize){
        this.maxSize = maxSize;
    }
    public boolean isEmpty(){
        return top == first;
    }
    public boolean isFull(){
        return top.getNo() == maxSize;
    }
    public void push(int data){
        if(isFull()){
            System.out.println("栈已满，无法继续添加");
            return;
        }
        Node newNode = new Node(data,top.no+1);
        top.next = newNode;
        top = newNode;
    }
    private Node getBefore(Node node){
        Node temp = first;
        while (true){
            if(temp.getNo() == node.getNo()-1){
                return temp;
            }
            temp = temp.next;
        }
    }
    public void pop(){
        if(isEmpty()){
            System.out.println("栈已空，无法继续取出");
        }
        top = this.getBefore(top);
    }
    public void list(){
        if (isEmpty()){
            System.out.println("栈已空，没有数据");
        }
        int num = top.getNo();
        Node temp = top;
        for (int i = num; i >0 ; i--) {
            System.out.println(temp);
            temp = this.getBefore(temp);
        }
    }
}
