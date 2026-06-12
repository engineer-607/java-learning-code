package com.nanxinda.thread;
/// 在java中使用线程的两种方法
/// 1.继承Thread类，重写run方法
/// 2.实现Runnable接口，重写run方法（java是单继承的，在某些情况下一个类可能已经继承某个父类
/// ，这时无法使用继承Thread类方法来创建线程，这时候就需要用到Runnable接口）
public class ThreadUse {
    public static void main(String[] args) throws InterruptedException {
        //创建Cat对象，可以当作线程使用
        Cat cat = new Cat();
        cat.start();//启动子线程（最终会执行cat的run方法）
        /*
        (1)
        public synchronized void start() {
              start0();（核心方法）
        }
        (2)
        //start()是本地方法，是JVM调用，底层是c/c++实现
        //真正实现多线程的效果，是start0()，而不是run
        private native void start0();
         */
        //cat.run();run方法就是一个普通的方法，没有真正的启动一个线程，而是执行完run方法，才向下执行
        //当main线程启动一个子线程Thread-0，主线程不会阻塞，会继续执行
        System.out.println("主线程继续执行"+Thread.currentThread().getName());
        for (int i = 0; i < 10; i++) {
            System.out.println("主线程i="+i);
            //让主线程休眠
            Thread.sleep(1000);
        }

        /// =============================================================


    }
}

/**
 /*
 * --------------------------------------------------------------
 *                     进程与线程执行状态图
 * --------------------------------------------------------------
 *
 *      [JVM进程]
 *          │
 *          ├─ [主线程: main] ───────────────────────────────┐
 *          │      │                                         │
 *          │      │ cat.start()                            │
 *          │      ▼                                         │
 *          │  [创建子线程 Thread-0]                         │
 *          │      │                                         │
 *          │      │                                         │
 *          │      ▼                                         │
 *          │  [并行执行]                                    │
 *          │      │                                         │
 *          │      ├─────────────────┐                     │
 *          │      │                 │                     │
 *          │      ▼                 ▼                     │
 *          │ [主线程main]      [子线程Thread-0]            │
 *          │      │                 │                     │
 *          │      │                 │ run()               │
 *          │      │                 ▼                     │
 *          │      │          "喵喵喵" times=1             │
 *          │      │                 │                     │
 *          │      │           sleep(1000)                 │
 *          │      │                 │                     │
 *          │      ▼                 ▼                     │
 *          │ "主线程继续执行main"   "喵喵喵" times=2       │
 *          │      │                 │                     │
 *          │  for i=0 to 9          │                     │
 *          │      │                 ▼                     │
 *          │      ▼          sleep(1000)                  │
 *          │ "主线程i=0"            │                     │
 *          │      │                 ▼                     │
 *          │ sleep(1000)     "喵喵喵" times=3             │
 *          │      │                 │      ...           │
 *          │      ▼                 ▼                     │
 *          │ "主线程i=1"      "喵喵喵" times=8            │
 *          │      │                 │                     │
 *          │      ...              break                 │
 *          │                        │                     │
 *          │                        ▼                     │
 *          │                  [子线程结束]                │
 *          │                        │                     │
 *          │                        │                     │
 *          ▼                        ▼                     ▼
 *     [主线程执行完毕]        [线程资源回收]        [JVM进程退出]
 *
 * --------------------------------------------------------------
 * 只有当进程中所有的线程都完成时，进程才会结束（不是main线程结束，进程就结束）
 */
//Thread类中的run方法实现Runnable接口的run方法
//    @Override
//    public void run() {
//        if (target != null) {
//            target.run();
//        }
//    }
    ///模拟简易的代理模式
    ///分析：
    /// 1.代理类TigerPoxy和被代理类（传入的Thread对象）实现共同的接口Runnable
    class TigerPoxy implements Runnable{
        Runnable target = null;
        /// 2.代理类持有真实类的对象引用
    public TigerPoxy(Thread thread){
        this.target = thread;
    }

    @Override
    public void run() {
        if(target!=null){
            target.run();
            /// 3.代理类控制真实类的访问
        }
    }
    public void start(){
        start0();
    }
    public void start0(){
        run();
    }

}
class Tiger implements Runnable{
        int count;
    @Override
    public void run() {
        System.out.println("虎啸"+(++count)+"次，"+Thread.currentThread().getName());
    }
}
class Cat extends Thread{//继承Thread类，该类可以当作线程使用
    int times;
    @Override
    public void run() {//重写run方法
        while (true) {
            System.out.println("喵喵喵"+(++times)+"线程名="+Thread.currentThread().getName());
            try {
                //线程休眠1s
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (times == 8){
                break;
            }
        }
        //每隔1秒会输出喵喵喵

    }
}