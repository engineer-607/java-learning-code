package com.nanxinda.houserenting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
//实现功能的三部曲(明确完成功能->思路分析->代码实现)
public class HouseRentOPP {
    private HashMap<Integer,House> houseImf = new HashMap<Integer,House>();
    private int number = 1;
    public void HouseFunction(){
        boolean judge = true;
        Scanner scanner = new Scanner(System.in);
        do {//菜单可以写在HouseView中
            System.out.println("--------房屋出租系统--------");
            System.out.println("1.新增房源");
            System.out.println("2.查找房源");
            System.out.println("3.删除房源");
            System.out.println("4.修改房屋信息");
            System.out.println("5.房屋列表");
            System.out.println("6.退出");
            System.out.println("请选择(1-6)");
            int key=scanner.nextInt();
            switch (key){
                case 1:
                    addHouse(scanner);
                    break;
                case 2:
                    seekHouse(scanner);
                    break;
                case 3:
                    deleteHouse(scanner);
                    break;
                case 4:
                    alterHouse(scanner);
                    break;
                case 5:
                    listHouse();
                    break;
                case 6:
                    exit(scanner);
                    judge=false;
                    break;
                default:
                    System.out.println("输入错误");
            }
        }while(judge);
    }
    //每个方法可以分为两个部分：操作页面和内在业务逻辑
    //每个方法都重复出现输入操作的检查，可以合并成工具类，在方法中调用，提高代码复用性
    public void addHouse(Scanner scanner){
        System.out.println("--------添加房源--------");
        House house = new House();
        System.out.println("姓名：");
        house.setName(scanner.next());
        System.out.println("电话：");
        house.setTelephone(scanner.next());
        System.out.println("地址：");
        house.setPlace(scanner.next());
        System.out.println("月租：");
        house.setMonthlyRent(scanner.next());
        System.out.println("状态(未出租/已出租)：");
        house.setState(scanner.next());
        houseImf.put(number++,house);
        System.out.println("--------添加完成--------");
    }
    public void seekHouse(Scanner scanner){
        System.out.println("--------查找房源--------");
        System.out.println("请输入您要查找的id：");
        int key = scanner.nextInt();
        if(houseImf.containsKey(key)){
            System.out.println(key+houseImf.get(key).toString());
            //toString方法在打印对象时可以自动调用，无需在调用
            System.out.println("--------找到房源--------");
        }else{
            System.out.println("--------没有该房源--------");
        }
    }
    //可以添加返回boolean，用于检查是否删除成功机制
    public void deleteHouse(Scanner scanner){
        System.out.println("--------删除房源--------");
        System.out.println("请选择待删除房屋编号(-1退出):");
        int key = scanner.nextInt();
        if(key==-1){
            System.out.println("--------退出删除--------");
            return;
        }
        String s = null;
        while(true){
            if(houseImf.containsKey(key)){
                break;
            }
            System.out.println("输入编号不存在，请问是否重新输入(y/n):");
            s= scanner.next();
            if(s.equals("n")){
                break;
            }else{
                System.out.println("请重新输入房屋编号：");
                key=scanner.nextInt();
            }
        }
        System.out.println("确认是否删除(y/n):(小心选择)");
        s=scanner.next();
        while(true){
            if (s.equals("y")||s.equals("n")){
                break;
            }
            System.out.println("输入错误,请在重新输入：(y/n)");
            s=scanner.next();
        }
        if(s.equals("n")){
            System.out.println("--------退出删除--------");

        }else{
           if(houseImf.containsKey(key)){
            houseImf.remove(key);
               System.out.println("--------删除完成--------");
           }
        }
    }
    public void alterHouse(Scanner scanner){
        System.out.println("--------修改客户--------");
        System.out.println("请选择待修改房屋编号：(-1退出)");
        int key = scanner.nextInt();
        if(key==-1){
            return;
        }
        if(houseImf.containsKey(key)){
            House house = houseImf.get(key);
            System.out.println("输入-1表示不修改");
            System.out.println("姓名（"+house.getName()+"）：");
            house.setName(scanner.next());
            System.out.println("电话（"+house.getTelephone()+"）：");
            house.setTelephone(scanner.next());
            System.out.println("地址（"+house.getPlace()+"）：");
            house.setPlace(scanner.next());
            System.out.println("租金（"+house.getMonthlyRent()+"）");
            house.setMonthlyRent(scanner.next());
            System.out.println("状态（"+house.getState()+"）");
            house.setState(scanner.next());
            System.out.println("--------修改完成--------");
        }else{
            System.out.println("--------该客户不存在---------");
        }
    }
    public void listHouse(){
        System.out.println("--------房屋列表--------");
        System.out.println("编号\t房主\t电话\t地址\t月租\t状态(未出租/已出租)");
        for(Integer key:houseImf.keySet()){
            House house = houseImf.get(key);
            System.out.println(key+"\t"+house.getName()+
                            "\t"+house.getTelephone()+
                            "\t"+house.getPlace()+
                            "\t"+house.getMonthlyRent()+
                            "\t"+house.getState()
                    );
        }
        System.out.println("--------房屋列表完成--------");
    }
    public void exit(Scanner scanner){
        System.out.println("请输入你的选择(Y/N)");
        String s = scanner.next();
        while(true){
            if(s.equals("Y")||s.equals("N")){
                break;
            }
            System.out.println("选择错误，请重新输入：");
            s=scanner.next();
        }
        System.out.println("--------退出程序--------");

    }

}
