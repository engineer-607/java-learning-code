package com.nanxinda.thread;

public class ThreadExercise01 {
    public static void main(String[] args) throws InterruptedException {
        A a = new A();
        for (int i = 1; i <= 10 ; i++) {
            System.out.println("hi"+i);
            if(i == 5){
                Thread thread = new Thread(a);
                thread.start();
                thread.join();

            }
        }
        System.out.println("主线程结束...");
    }
}
class A implements Runnable{
    @Override
    public void run() {
        for (int i = 1; i <=10 ; i++) {
            System.out.println("hello"+i);
        }
        System.out.println("子线程结束...");
    }
}