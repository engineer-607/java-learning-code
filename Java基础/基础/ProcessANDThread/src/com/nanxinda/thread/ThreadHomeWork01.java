package com.nanxinda.thread;

import java.util.Scanner;

public class ThreadHomeWork01 {
    public static void main(String[] args) {
        AA aa = new AA();
        BB bb = new BB();
        aa.start();
        bb.start();

    }
}
class AA extends Thread{
    @Override
    public void run() {
        while (BB.loop){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println((int)(Math.random()*100));
        }
        System.out.println("AA进程结束");
    }
}
class BB extends Thread{
    public static boolean loop = true;
    @Override
    public void run() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入指令（Q）控制进程：");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            String c = scanner.next();
            if (c.equals("Q")) {
                loop = false;
                System.out.println("BB进程结束");
                break;
            }
        }
    }
}