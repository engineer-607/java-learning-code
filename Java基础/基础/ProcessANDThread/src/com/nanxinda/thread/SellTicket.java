package com.nanxinda.thread;

public class SellTicket {
    public static void main(String[] args) {
        //实现接口Runnable或继承Thread都出现超票现象
        Ticket ticket = new Ticket();
        Thread thread = new Thread(ticket);
        Thread thread1 = new Thread(ticket);
        Thread thread2 = new Thread(ticket);
        thread.start();
        thread1.start();
        thread2.start();
    }
}
class Ticket implements Runnable{
    private static int num = 100;//静态属性让所有线程都共享num
    @Override
    public void run() {
        while (true){
            if(num<=0){
                //多个线程同时通过检查，但票数少于通过的线程数就会出现超票现象
                System.out.println("已售完票");
                break;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("窗口"+Thread.currentThread().getName()+"售出一张票" +
                    ",剩余票数为"+(--num)+"张");
        }
    }
}