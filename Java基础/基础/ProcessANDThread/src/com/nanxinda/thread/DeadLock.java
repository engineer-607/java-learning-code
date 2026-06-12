package com.nanxinda.thread;

public class DeadLock {
    public static void main(String[] args) {
        /*
        线程的死锁：
        多个线程都占有对方的锁资源，但不肯相让，导致死锁，在编程
        中一定要避免
         */
        /*
        释放锁
        1.当前线程的同步方法、同步代码块执行结束
        2.当前线程在同步代码块、同步方法中遇到break、return
        3.当前线程在同步代码块、同步方法中出现了未处理的Error或Exception
        导致异常结束
        4.当前线程在同步代码块、同步方法中执行线程对象的wait（）方法，当前
        线程暂停，并释放锁
        不会释放锁的情况：
        1.线程执行同步代码块或同步方法时，程序调用Thread.sleep()、Thread.yield()方法
        2.线程执行同步代码块时，其他线程调用该线程的suspend()方法将该线程挂起，该线程不会
        释放锁
         */
        DeadLockDemo deadLockDemo = new DeadLockDemo(true);
        DeadLockDemo deadLockDemo1 = new DeadLockDemo(false);
        deadLockDemo1.start();
        deadLockDemo.start();
    }
}
class DeadLockDemo extends Thread{
    static Object object = new Object();
    static Object object1 = new Object();
    boolean flag;

    public DeadLockDemo(boolean flag) {
        this.flag = flag;
    }
    //1.如果flag为T，线程A就会得到/持有 object对象锁，然后尝试去获取object1对象锁
    //2.如果线程A得不到object1对象锁，就会Blocked
    //3.如果flag为F，线程B就会得到/持有object1对象锁，然后尝试去获取object对象锁
    //4，如果线程B得不到object对象锁，就会Blocked

    @Override
    public void run() {
        if(flag){
            synchronized (object){
                System.out.println(Thread.currentThread().getName()+"进入1");
                synchronized (object1){
                    System.out.println(Thread.currentThread().getName()+"进入2");
                }
            }
        }else {
            synchronized (object1){
                System.out.println(Thread.currentThread().getName()+"进入3");
                synchronized (object){
                    System.out.println(Thread.currentThread().getName()+"进入4");
                }
            }
        }

    }
}