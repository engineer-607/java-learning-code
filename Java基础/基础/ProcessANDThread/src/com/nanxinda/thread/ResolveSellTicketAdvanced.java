package com.nanxinda.thread;

public class ResolveSellTicketAdvanced {
    public static void main(String[] args) {
        // 创建不同类型的窗口，但它们共享同一个票池
        Thread t1 = new Thread(new TrainWindow("窗口1"));
        Thread t2 = new Thread(new BusWindow("窗口2"));
        Thread t3 = new Thread(new StudentWindow("窗口3"));
        Thread t4 = new Thread(new TrainWindow("窗口4"));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
/// 不同的售票类可以有不同的功能，但是将售票这个功能封装成一个TicketPool（实际上是单例模式），
/// 想要实现售票功能，直接调用其静态方法一样可以实现多线程同步操作
/// 优点：
/// 1）解耦：
/// *将售票功能和每个窗口的不同逻辑进行分离，
/// *如果需要修改售票规则只需要TicketPool，不影响各自的窗口，
/// *同样的，如果修改窗口逻辑不会影响售票规则
/// 2）提高代码的复用性
/// 3）可以灵活扩展，如果票池有新功能需要添加（比如退票）只需要在TicketPool中添加相关
/// 的静态方法即可
class TicketPool{
    private static int totalTickets = 1000;
    private static final Object poolLock = new Object();  // final修饰更好

    // 静态方法，提供售票功能
    public static boolean sellTicket(String windowName) {
        synchronized (poolLock) {
            if (totalTickets > 0) {
                totalTickets--;
                System.out.println(windowName + "售出1张，剩余" + totalTickets);

                // 模拟一些业务逻辑
                generateTicket(windowName);
                return true;
            } else {
                System.out.println(windowName + "购票失败：票已售完");
                return false;
            }
        }
    }
    // 还可以提供其他票务相关方法
    public static int getRemainingTickets() {
        synchronized(poolLock) {
            return totalTickets;
        }
    }

    private static void generateTicket(String windowName) {
        // 生成票号、打印票据等操作
        System.out.println(windowName + "已生成电子票");
    }
}
// 不同类型的售票窗口 - 可以实现不同的业务逻辑
//火车票窗口
class TrainWindow implements Runnable {
    private String windowName;

    public TrainWindow(String name) {
        this.windowName = name + "(火车票)";
    }

    @Override
    public void run() {
        while (true) {
            // 火车票窗口可能有一些特殊逻辑
            if (!TicketPool.sellTicket(windowName)) {
                break;
            }

            // 火车票窗口卖票速度较慢
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
//汽车票窗口
class BusWindow implements Runnable {
    private String windowName;

    public BusWindow(String name) {
        this.windowName = name + "(汽车票)";
    }

    @Override
    public void run() {
        while (true) {
            // 汽车票窗口可以批量售票
            for (int i = 0; i < 3; i++) {  // 一次卖3张
                if (!TicketPool.sellTicket(windowName)) {
                    return;
                }
            }

            // 汽车票窗口卖票速度快
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
//学生票窗口
class StudentWindow implements Runnable {
    private String windowName;

    public StudentWindow(String name) {
        this.windowName = name + "(学生票)";
    }

    @Override
    public void run() {
        while (true) {
            // 学生票窗口检查学生证等特殊逻辑
            boolean hasStudentId = Math.random() > 0.2;  // 80%概率有学生证

            if (hasStudentId) {
                if (!TicketPool.sellTicket(windowName + "[优惠]")) {
                    break;
                }
            } else {
                System.out.println(windowName + "请出示学生证");
            }

            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
