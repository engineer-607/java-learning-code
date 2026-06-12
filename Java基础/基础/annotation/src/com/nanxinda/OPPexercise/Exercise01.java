package com.nanxinda.OPPexercise;

public class Exercise01 {
    public static void main(String[] args) {
        Car car = new Car();
        Car car1 = new Car(100);
        System.out.println(car);
        System.out.println(car1);
    }
}
class Car{
    double price = 10;
    static String color ="white";
//静态变量只在类加载时才会初始化，且只执行一次
    //静态变量是所有创建对象共享的，在一个对象中修改，其他对象中值也会发生改变
    @Override
    public String toString() {
        return price+"\t"+color;
    }
    public Car(){
        this.price = 9;
        Car.color = "red";
    }
    public Car(double price){
        this.price = price;
    }
}