package com.nanxinda.thread;

public class ThreadEnd {
    public static void main(String[] args) throws InterruptedException {
        /*
        线程终止（基本说明）：
        1.当线程完成任务后，会自动退出
        2.可以使用变量来控制run方法退出的方式停止线程，即通知方式
         */
        T t = new T();
        t.start();
        Thread.sleep(10*1000);
        t.setLoop(false);
    }
}
class T extends Thread{
    boolean loop = true;
    @Override
    public void run() {
        while (loop){
            System.out.println(Thread.currentThread().getName()+"在运行...");
        }
    }

    public boolean isLoop() {
        return loop;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }
}