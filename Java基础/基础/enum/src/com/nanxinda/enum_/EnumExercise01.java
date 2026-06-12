package com.nanxinda.enum_;



public class EnumExercise01 {
    public static void main(String[] args) {
        Gender2 boy = Gender2.BOY;
        Gender2 boy2 = Gender2.BOY;
        System.out.println(boy);//BOY
        //本质调用Gender2的父类Enum的toString()
        //public String toString(){
                 //return name;
        //}
        System.out.println(boy==boy2);//true
        //Gender2.BOY为静态属性，所以得到的boy和boy2一样
    }

}
enum Gender2{
    BOY,GIRL;
}
