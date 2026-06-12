package com.nanxinda.class_.pcakage_;

public class Exercise01 {
    public static void main(String[] args) {
        Object obj1 = true ? new Integer(1) : new Double(2.0);
        /*
        1.编译器分析：Integer(1) 和 Double(2.0) → 公共类型为 double
        确定三元表达式的返回类型为 double
        2.根据true选择第一个分支Integer(1)
        3.将Integer(1)转化为目标类型double
        1）拆箱：Integer(1)被自动拆箱为int，然后提升为double(1.0)
        2）类型转化：int 1-》double 1.0
        4.装箱：double 1.0被装箱为Double对象
        5.赋值：将Double对象赋值给obj1
         */
        System.out.println(obj1);  // 输出：1.0
    }
}
