package com.nanxinda.thread;

public class ThreadMethod {
    public static void main(String[] args) throws InterruptedException {
        /*
        线程常用方法
        1.setName//设置线程名称，使之与参数name相同
        2.getName//返回该线程的名称
        3.start//使该线程开始执行;Java虚拟机底层调用该线程的start0()方法
        4.run//调用线程对象run方法
        5.setPriority//更改线程优先级
        6.getPriority//获取线程的优先级
        7.sleep//在指定的毫秒数内让当前正在执行的线程休眠（暂停执行）
        8.interrupt//中断线程

        注意事项和细节：
        1）start底层会创建新的线程，调用run，run就是一个简单的方法调用，不会启动新线程
        2）线程优先级的范围
        3）interrupt，中断线程，但并没有真正的结束线程，所以一般用于中断正在休眠线程
        4）sleep：线程的静态方法，使当前线程休眠
         */
        T1 t1 = new T1();
        t1.setName("老韩");
        t1.setPriority(Thread.MIN_PRIORITY);//1
        t1.start();//启动子线程

        for (int i = 0; i < 5; i++) {
            t1.sleep(1000);
            System.out.println("hi"+i);
        }
        t1.interrupt();//中断t线程的休眠
    }
}
class T1 extends Thread{
    @Override
    public void run() {
        while (true) {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + " 吃包子~~~" + i);
                //Thread.currentThread().getName() 获取当前线程的名称
            }
            try {
                System.out.println(Thread.currentThread().getName() + " 休眠中~~~");
                Thread.sleep(20000);//20秒
            } catch (InterruptedException e) {
                // 当该线程执行到一个interrupt方法时，就会catch一个异常，可以加入自己的业务代码
                //InterruptedException 使捕获到一个中断异常
                System.out.println(Thread.currentThread().getName() + " 被interrupted");
            }
        }
    }
}