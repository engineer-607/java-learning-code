package com.nanxinda.thread;

public class SynchronizedResolveSellTicket {
    public static void main(String[] args) {
        /*
        线程同步机制：
        1.在多线程编程中，一些敏感数据不允许被多个线程同时访问，此时就使用同步访问技术
        保证数据在任何同一时刻，最多有一个线程访问，以保证数据的完整性
        2.线程同步：当有一个线程在对内存进行操作时，其他线程都不可以对这个内存地址进行操作
        直到该线程完成操作，其他线程才能对该内存地址进行操作

        同步代码块具体操作方法————————Synchronized
        1.同步代码块
        synchronized(对象){//得到对象的锁，才能操作同步代码
                //需要被同步的代码块
        }
        2.synchronized还可以放在方法声明中，表示整个方法-为同步方法
        public synchronized void m(String name){
        //需要被同步的代码
        }
         */
        /*
        互斥锁
        1.Java语言中，引入对象互斥的概念，来保证共享数据操作的完整性
        2.每个对象都对应于一个可称为“互斥锁”的标记，这个标记用来保证在任意时刻，
        只能有一个线程访问该对象
        3.关键字synchronized来与对象的互斥锁联系，当某个对象用synchronized修饰时
        表明该对象在任意时刻只能由一个线程访问
        4.同步的局限性：导致程序的执行效率降低
        5.同步代码块（非静态）的锁可以是this，也可以是其他对象（要求是同一个对象）
        静态同步代码块的锁应是类
        public static void m2(){
             synchronized( SellTicketAdvanced.class){...}

        6.同步方法（静态的）的锁为当前类本身，或（非静态）为this对象
        eg.
        public synchronized static void m1(){} 锁是加在SellTicket.class
         */
        SellTicketAdvanced sellTicketAdvanced = new SellTicketAdvanced();
        Thread thread = new Thread(sellTicketAdvanced);
        Thread thread1 = new Thread(sellTicketAdvanced);
        Thread thread2 = new Thread(sellTicketAdvanced);
        thread2.start();
        thread1.start();
        thread.start();
        ///实例化三个对象，但本质是执行同一个对象的方法（run），相当于有三辆正在运行的赛车，但是
        /// 本质是一辆赛车，又因为共同拥有成员属性object，
        ///所以synchronized代码块用object作为锁确保只有一个线程对代码块进行操作
        ///        堆内存中的同一个对象
        ///┌─────────────────────────┐
        ///│ SellTicketAdvanced实例  │
        ///│  ┌──────────────────┐   │
        ///│  │ num = 100        │   │ ←─── 线程1、2、3都能访问
        ///│  │ object(锁对象)   │   │ ←─── 但一次只能有一个线程持有这把锁
        ///│  └──────────────────┘   │
        ///└─────────────────────────┘
        ///        ▲       ▲       ▲
        ///        │       │       │
        ///       线程1     线程2     线程3
       ///       (赛车1)   (赛车2)   (赛车3)

        /// 也可以同时实例化三个SellTicketAdvanced对象，但用static来修饰num和object
        /// 这样虽然是三辆不同的赛车在运行，但是依旧共享num和object
        ///方法区（静态变量存储区）
        ///┌────────────────────────────┐
        ///│ SellTicketAdvanced类       │
        ///│  ┌──────────────────────┐  │
        ///│  │ static int num = 100 │  │ ←───┐
        ///│  │ static Object lock   │  │ ←───┼─── 所有实例共享
        ///│  └──────────────────────┘  │     │
        ///└────────────────────────────┘     │
        ///                                   │
        ///堆内存                             │
        ///┌────────────────────────────┐     │
        ///│ s1实例 (线程1)             │     │
        ///│  (没有num,指向静态的num)   │─────┘
        ///├────────────────────────────┤
        ///│ s2实例 (线程2)             │─────┐
        ///│  (没有num,指向静态的num)   │     │
        ///├────────────────────────────┤     │
        ///│ s3实例 (线程3)             │─────┘
        ///│  (没有num,指向静态的num)   │
        ///└────────────────────────────┘
        /// 使用静态方法优点：
        /// 1）允许多个Runnable实例，更灵活
        /// 2）适合复杂场景（比如不同的线程池使用不同的Runnable对象）====》

    }
}
class SellTicketAdvanced implements Runnable{
    private  int num = 100;//所有线程都共享num
    //1.public synchronized void sell(){}
    //2.这时锁在this对象上
    //3.也可以在代码块上写synchronized，同步代码块
    Object object = new Object();
    private /*synchronized*/ boolean  sell(){
        //同步方法，在同一时刻，只能有一个线程来执行sell方法
        synchronized (/*this*/object) {
            if (num <= 0) {
                //多个线程同时通过检查，但票数少于通过的线程数就会出现超票现象
                System.out.println("已售完票");
                return false;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("窗口" + Thread.currentThread().getName() + "售出一张票" +
                    ",剩余票数为" + (--num) + "张");
            return true;
        }
    }
    @Override
    public void run() {
        while (true){
            if(!sell()){
                break;
            }

        }
    }
}