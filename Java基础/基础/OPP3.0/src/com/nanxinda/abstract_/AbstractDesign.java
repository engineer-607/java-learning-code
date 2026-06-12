package com.nanxinda.abstract_;

public class AbstractDesign {
    //利用抽象完成模板设计模式
    //需求：有多个类，执行不同的任务，各自得到完成任务的时间
    public static void main(String[] args) {
        B b = new B();
        b.calTime();

    }
}
abstract class Template{
    public void calTime(){
        long start = System.currentTimeMillis();
        job();//动态绑定机制会执行子类B的job方法
        long end = System.currentTimeMillis();
        System.out.println("任务执行的时间 "+(end-start));
    }
    public abstract void job();
}
class B extends Template{
    @Override
    public void job() {
        int num=0;
        for (int i = 1; i <= 100000; i++) {
            num+=i;
        }
    }
}
