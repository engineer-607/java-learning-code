package com.nanxinda.extendsDetail;

public class extendsDetail {
    /*
    子类继承所有的属性和方法，但是私有属性和方法不能在子类直接访问，要通过公共的方法去访问（子类有父类的私有属性和方法
    但是这个属性和方法不能访问）
     */
    public static void main(String[] args){
        System.out.println("===第三个对象===");
        Sub sub = new Sub();
        sub.getBase();
        System.out.println("====第二个对象===");
        Sub sub01 = new Sub("jack");
        System.out.println("===第三个对象===");
        Sub01 sub2 = new Sub01("jack",18);

    }




}
