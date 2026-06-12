package com.nanxinda.dynamic;

public class DynamicBinging {
    public static void main(String[] args) {
        /*
        动态绑定机制
        1)当调用对象方法的时候,该方法会和该对象的内存/运行类型绑定
        2)当调用对象属性时,没有动态绑定机制,哪里声明哪里使用
         */
        //案例一
        A a = new B();
        System.out.println(a.sum());//运行类型为B,但B中无sum方法,查找父类\
        //父类有sum方法,但其中调用getI方法,由于动态绑定机制,查找运行类型B中有无getI方法
        //找到传回属性i,而属性i没有动态访问机制,在B中声明在B中使用
        //最终返回30


    }

}
class A{
    public int i=10;
    public int sum(){
        return getI()+10;//20+10
    }
    public int sum1(){
        return i+10;
    }
    public int getI(){
        return i;
    }
}
class B extends A{
    public int i=20;
//    public int sum(){
//        return i+20;
//    }
    public int getI(){
        return i;//20
    }public int sum1(){
        return i+10;
    }
}
