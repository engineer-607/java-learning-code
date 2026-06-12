package com.nanxinda;


import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"all"})
public class GenericExtends {
    public static void main(String[] args) {
        Object o = new String("xx");
        //泛型不具备继承性
//        List<Object> list = new ArrayList<String>();(X)

        //举例说明下面三个方法的使用
        List<Object> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        List<AAA> list3 = new ArrayList<>();
        List<BBB> list4 = new ArrayList<>();
        List<CCC> list5 = new ArrayList<>();
        //如果是List<?> objects，可以接受任意的泛型类型
        printCollection1(list1);
        printCollection1(list2);
        printCollection1(list3);
        printCollection1(list4);
        printCollection1(list5);

//        printCollection2(list1);//x
//        printCollection2(list2);//x
        printCollection2(list3);//√
        printCollection2(list4);//√
        printCollection2(list5);//√

        printCollection3(list1);//√
//        printCollection3(list2);//x
        printCollection3(list3);//√
//        printCollection3(list4);//x
//        printCollection3(list5);//x


    }
    //List<?> objects表示任意泛型类型都可以接受
    public static void printCollection1(List<?> objects){
        for (Object object :objects) {//通配符，取出时，就是Object
            System.out.println(object);
        }
    }
    //? extends AAA表示上限，可以接受AAA或者AAA的子类，规定泛型的上限
    public static void printCollection2(List<? extends AAA> objects){
        for (Object object :objects) {
            System.out.println(object);
        }
    }
    //List<? super AAA> objects:支持AAA类以及AAA类的父类，不限于直接父类，规定泛型的下限
    public static void printCollection3(List<? super AAA> objects){
        for (Object object :objects) {
            System.out.println(object);
        }
    }
}
class AAA{}
class BBB extends AAA{}
class CCC extends BBB{}