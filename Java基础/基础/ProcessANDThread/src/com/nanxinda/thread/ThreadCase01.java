package com.nanxinda.thread;

public class ThreadCase01 {
    public static void main(String[] args) {
        Dog dog = new Dog();
        //dog.start();（X）这里不能调用start
        //创建了Thread对象，把dog对象（实现Runnable），放入Thread
        Thread thread = new Thread(dog);
        //底层使用设计模式：代理模式=》代码模拟
        thread.start();

    }
}
class Dog implements Runnable{
    int count;

    @Override
    public void run() {
        while (true){
            System.out.println("小狗汪汪叫..."+(++count)+Thread.currentThread().getName());
            try {
                //休眠1s
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (count == 10){
                break;
            }
        }
    }
}