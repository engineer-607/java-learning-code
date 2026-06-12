package com.nanxinda.exception_;

import java.util.Scanner;

public class Exercise02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入一个整数：");
        int num = Tool.inputInt(scanner);
        System.out.println("num="+num);

    }
}
class Tool{
    public static int inputInt(Scanner scanner){
        int num;
        while (true){
            String str = scanner.next();
            try {
                 num = Integer.parseInt(str);
                break;
            } catch (Exception e) {
                System.out.println("类型不匹配，请重新输入");

            }
        }
        return num;
    }
}