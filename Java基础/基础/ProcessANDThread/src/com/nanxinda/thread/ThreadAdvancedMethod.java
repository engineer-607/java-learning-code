package com.nanxinda.thread;

public class ThreadAdvancedMethod {
    public static void main(String[] args) throws InterruptedException {
        /*
        常用方法第二组：
        1）yield：线程的礼让，让出cpu，让其他线程执行，但礼让的时间不确定，所以不一定礼让成功
        2）join：线程的插队，插队的线程一旦插队成功，则肯定先执行完插入的所有任务
eg.
// main thread                    t1 thread
//     |                              |
//     |------------------------------> (t1 starts)
//     |                              |
//     | t1.join() -----------------> (1)让t1线程执行完毕
//     | (main blocked)               | (t1 running...)
//     |                              |
//     |                              |
//     |                              |
//     |                              |
//     | (resumed) <----------------- (t1 finishes)
//     | (2)main线程才继续执行
//     v
         */
        T2 t2 = new T2();
        t2.start();
        for (int i = 0; i < 20; i++) {
            Thread.sleep(1000);
            System.out.println("主线程（小弟）吃"+i+"个包子");
            if(i == 5){
                System.out.println("主线程（小弟）让子线程（老大）先吃");
                //join：线程插队，一定成功
//                t2.join();
                Thread.yield();//礼让，不一定成功
            }
        }
    }
}
class T2 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("子线程（老大）吃"+i+"个包子");
        }
    }
}