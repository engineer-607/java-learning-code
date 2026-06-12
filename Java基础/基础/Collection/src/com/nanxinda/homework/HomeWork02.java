package com.nanxinda.homework;

import java.util.ArrayList;
import java.util.Iterator;


@SuppressWarnings({"all"})
public class HomeWork02 {
    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();
        Car car = new Car("宝马",400000);
        Car car1 = new Car("宾利",5000000);
        arrayList.add(car1);
        arrayList.add(car);
        System.out.println("arrayList="+arrayList);
        System.out.println("宝马是否存在:"+arrayList.contains(car));
        System.out.println("元素个数："+arrayList.size());
        arrayList.clear();
        System.out.println("检查元素是否为空："+arrayList.isEmpty());
        ArrayList arrayList1 = new ArrayList();
        arrayList1.add(car1);
        arrayList1.add(car);
        arrayList.addAll(arrayList1);
        System.out.println("arrayList="+arrayList);
        System.out.println("===增强for循环===");
        for (Object object :arrayList) {
            System.out.println(object);
        }
        System.out.println("===迭代器进行遍历===");
        java.util.Iterator iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            Object next =  iterator.next();
            System.out.println(next);
        }

        arrayList.removeAll(arrayList1);
        System.out.println("宝马，宾利是否存在："+arrayList.containsAll(arrayList1));


    }
}
class Car{
    private String name;
    private double price;

    public Car(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}