package com.nanxinda.smallchanges;

import java.util.Date;
import java.util.Scanner;

public class SmallChangeSys {
    public static void main(String[] args) {
        SmallChanges smallChanges = new SmallChanges();
        Scanner scanner = new Scanner(System.in);
        boolean judge=true;
        String key;
        do {
            System.out.println("--------零钱通--------");
            System.out.println("\t1.零钱通明细");
            System.out.println("\t2.收益入账");
            System.out.println("\t3.消费");
            System.out.println("\t4.退\t出");
            System.out.println("请输入数字(1-4)");
            key=scanner.next();
            switch (key){
                case "1":
                    System.out.println("-----零钱通明细-----");
                    smallChanges.getImf();
                    break;
                case "2":
                    System.out.println("收益入账");
                    smallChanges.earnings(scanner.nextDouble(),new Date(),scanner);
                    break;
                case "3":
                    System.out.println("消费金额：");
                    smallChanges.consume(scanner.nextDouble(),new Date(),scanner);
                    break;
                case "4":
                    System.out.println("您是否确定要退出系统：(y/n)");
                    String s = scanner.next();
                    while(!(s.equals("y")||s.equals("n"))){
                        System.out.println("输入错误，请重新输入(y/n)");
                        s=scanner.next();
                    }
                    /*
                    利用while和break实现来处理只接收y/n
                    while(true){
                    System.out.println("您是否确定要退出系统：(y/n)");
                    if(s.equals("y")||s.equals("n")){
                        break;
                    }
                    注意点：一段代码实现一个功能，可以降低代码之间的耦合性和提高代码的扩展性
                     */
                    if(s.equals("y")){
                    System.out.println("-----退出系统-----");
                    scanner.close();
                    judge=false;
                    break;
                    }
                default:
                    System.out.println("输入有误");
            }
        }while(judge);

    }

}
