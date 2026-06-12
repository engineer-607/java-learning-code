package com.nanxinda.OPPexercise;

import java.util.concurrent.Callable;

public class Exercise04 {
    public static void main(String[] args) {
        Cellphone cellphone = new Cellphone();
        cellphone.testWork(new Computer() {
                               @Override
                               public void work() {
                                   System.out.println("手机的计算器工作");
                               }
                           }
        );
    }
}
interface Computer{
    public void work();
}
class Cellphone {
    public void testWork(Computer computer){
        computer.work();

    }

}