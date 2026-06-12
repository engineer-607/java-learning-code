package com.nanxinda.thread;

public class ThreadDaemon {
    public static void main(String[] args) throws InterruptedException {
        /**
         * 用户线程：也称工作线程，以线程任务执行完或者通知方式结束
         * 守护线程：一般为工作线程服务。当所有的用户线程结束，守护线程会自动结束
         * 常见守护线程：垃圾回收机制
         */
        Daemon daemon = new Daemon();
        //如果希望主线程结束，子线程自动结束
        //只需要将子线程设为守护线程即可
        daemon.setDaemon(true);
        daemon.start();
        for (int i = 1; i < 10; i++) {
            Thread.sleep(1000);
            System.out.println("宝强在辛苦工作...");
        }
        System.out.println("宝强下班回家了");
    }
}
class Daemon extends Thread{
    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("马蓉和宋喆在聊天...");
        }
    }
}