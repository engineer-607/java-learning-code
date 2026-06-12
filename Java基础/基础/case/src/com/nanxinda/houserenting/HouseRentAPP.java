package com.nanxinda.houserenting;

public class HouseRentAPP {
    public static void main(String[] args) {
        HouseRentOPP houseRentOPP = new HouseRentOPP();
        houseRentOPP.HouseFunction();

    }
}
/*
     房屋租赁架构图（分层模式）
     1.系统有哪些类（文件）
     2.明确类与类之间的调用关系
     HouseRentAPP.java
     main(){//程序入口，创建HouseView对象，调用该对象，显示主菜单}
     HouseView.java类[界面]
     1.显示界面
     2.接收用户的输入
     3.调用HouseService完成对房屋信息的各种操作
     HouseService.java类[业务层]
     1.响应HouseView的调用
     2.完成对房屋信息的各种操作（增删改查c[create]r[read]u[update]d[delete]）
     House.java[domain/model]
     一个House对象表示一个房屋信息
     工具类
     完成获取用户各种输入
     当方法是static时，就是一个静态方法
     静态方法可以直接通过类名调用


 */