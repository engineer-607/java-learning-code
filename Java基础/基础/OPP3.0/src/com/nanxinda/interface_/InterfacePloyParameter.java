package com.nanxinda.interface_;

public class InterfacePloyParameter {
    /*
    接口多态特性
    1）多态参数（接口引用可以指向实现接口的类对象）
    2）多态数组
    3）接口多态传递现象
     */
    public static void main(String[] args) {
        //接口类型的变量cat可以指向实现Animal接口类的对象实例
        Animal cat = new Cat();
        cat = new Dog();
        Usb[] usbs = new Usb[2];
        usbs[0]=new Camera();
        usbs[1]=new Computer();
        for (int i = 0; i < usbs.length; i++) {
            usbs[i].work();//动态绑定机制
            if(usbs[i] instanceof Computer){
                ((Computer) usbs[i]).calculate();//向下转型
            }
        }
        //IB继承IA接口，而Ia类实现IB接口，
        //那么实际上相当于Ia类也实现了IA接口
        //这就是接口多态传递
        IA ia = new Ia();
        IB ib = new Ia();


    }


}
interface Animal{}
class Cat implements Animal{}
class Dog implements Animal{}
interface Usb{
    void work();
}
class Camera implements Usb{
    @Override
    public void work() {
        System.out.println("相机工作中");
    }
}
class Computer implements Usb{
    @Override
    public void work() {
        System.out.println("电脑工作中");
    }
    public void calculate(){
        System.out.println("电脑计算中");
    }
}
interface IA{}
interface IB extends IA{}

class Ia implements IB{}