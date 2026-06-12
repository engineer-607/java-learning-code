package com.nanxinda.stack;


import java.util.Scanner;

public class CalculatorDemo {
    public static void main(String[] args) {
        Calcular calcular = new Calcular();
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("请输入表达式：");
//        System.out.println(calcular.count(scanner.next()));
        System.out.println(calcular.count("1-1*1+1"));
    }
}
@SuppressWarnings({"all"})
class Calcular{
    private ArrayCalculatorStack numStack;
    private ArrayCalculatorStack operStack;
    //1.获取表达式，拆分表达式
    private void expressionSpilt(String s){
        char[] chars = s.toCharArray();

        numStack = new ArrayCalculatorStack(chars.length);
        operStack = new ArrayCalculatorStack(chars.length);
        for (int i = 0; i < chars.length;) {
            if(isOper(chars[i])){
                operPush(chars[i]);
                i++;
            }else {
                int start = i;
                int count = 0;
                while (i<chars.length&&chars[i]>='0'&&chars[i]<='9'){
                    i++;
                    count++;
                }
                numStack.push(new Integer(new String(chars,start,count)));
            }
        }
    }
    //获取优先级
    private int getPriority(char c){
        if(c=='+'||c=='-'){
            return 0;
        }else if(c=='*'||c=='/'){
            return 1;
        }else {
            return -1;
        }
    }
    //分情况将运算符入栈
    private void operPush(char c){
        if(operStack.isEmpty()){
            operStack.push(c);
            return;
        }
        if(getPriority(c)>=getPriority((char) operStack.peek())){
            operStack.push(c);
        }else {
            char c1 = (char) operStack.pop().intValue();
            int num1 = numStack.pop();
            int num2 = numStack.pop();
            calcular(num1,num2,c1);
            operStack.push(c);
        }
    }
    //进行加减乘除运算
    private void calcular(int num1,int num2,char c1){
        switch (c1){
            case '+':
                numStack.push(num1+num2);
                break;
            case '-':
                numStack.push(num2-num1);
                break;
            case '*':
                numStack.push(num1*num2);
                break;
            case '/':
                numStack.push(num2/num1);
                break;
        }

    }
    //判断是否为运算符
    private boolean isOper(char c){
        return c=='+'||c=='-'||c=='*'||c=='/';
    }
    //api，用以外部调用
    public int count(String s){
        expressionSpilt(s);
        while (true){
            if( numStack.getTop()==0){
                return numStack.pop();
            }
            calcular(numStack.pop(),numStack.pop(),(char)(operStack.pop().intValue()));
        }
    }
}
//自定义栈
class ArrayCalculatorStack {
    int[] stack;
    int maxSize;
    int top = -1;//表示栈顶
    public ArrayCalculatorStack(int maxSize){
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }
    public int peek(){
        return stack[top];
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
        Integer i = stack[top];
        top--;
        return i;
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

    public int getTop() {
        return top;
    }
}
