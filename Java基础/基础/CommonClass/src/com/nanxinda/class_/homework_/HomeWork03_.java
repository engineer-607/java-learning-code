package com.nanxinda.class_.homework_;

public class HomeWork03_ {
    public static void main(String[] args) {
        printName("Han shun Ping");

    }
    public static void printName(String string){
        if(string==null){
            System.out.println("输入不能为空");
            return;
        }
        String[] name = string.split(" ");//对字符串进行分割
        if(name.length!=3){
            System.out.println("输入的格式不正确");
        }
        System.out.println(String.format("%s,%s.%c",name[2],name[0],name[1].toLowerCase().charAt(0)));
        //使用自定义的格式

    }
}
