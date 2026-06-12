package com.nanxinda.thread;

public class ThreadHomeWork02 {
    public static void main(String[] args) {
        T4 t4 = new T4();
        T4 t5 = new T4();
        t5.start();
        t4.start();

    }
}
class T4 extends Thread{
    static Object  object=new Object();
    static int num = 10000;

    @Override
    public void run() {
        while (true){
            synchronized (object) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                if (num >= 1000) {
                    num = num - 1000;
                    System.out.println(Thread.currentThread().getName() + "取出1000￥，剩余" + (num));
                } else {
                    System.out.println("余额不足，为" + num);
                    break;
                }
            }
        }
    }
}