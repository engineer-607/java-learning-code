package com.nanxinda.class_.StringBuffer_;

import java.util.Scanner;

public class Exercise02 {
    public static void main(String[] args) {
        System.out.println("输入商品名称：");
        Scanner scanner = new Scanner(System.in);
        String s1 = scanner.next();
        System.out.println("输入商品价格");
        String s2 = scanner.next();
        StringBuffer stringBuffer = new StringBuffer(s2);
        int index = stringBuffer.indexOf(".");
        int count = 1;
        for (int i = index-1; i >= 0 ; i--) {
            if(count==3&&i!=0){
                stringBuffer.insert(i,",");
                count=1;

            }
            if(stringBuffer.charAt(i)!=','){
            count++;
            }

        }
        System.out.println("商品名称\t商品价格");
        System.out.println(s1+"\t"+stringBuffer.toString());
    }
}
