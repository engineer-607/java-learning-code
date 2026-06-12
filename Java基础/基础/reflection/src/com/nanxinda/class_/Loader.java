package com.nanxinda.class_;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Scanner;
@SuppressWarnings("all")
public class Loader {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        switch (s){
            case "1":
                //Dog dog = new Dog();
                /// 静态加载：编译时加载相关的类，如果没有会直接报错（依赖性强）
                break;
            case "2":
                /// 动态加载：运行时才加载相关的类，如果运行时不用该类，则不会报错（降低依赖性）
                Class<?> aClass = Class.forName("Person");
                Object o = aClass.newInstance();
                Method method = aClass.getMethod("hi");
                method.invoke(o);
                break;
            default:
                System.out.println("do nothing");
                break;
        }
        /// 类加载的时机：
        /// 1.当创建对象时（new）（静态加载）
        /// 2.当子类被加载时（静态加载）
        /// 3.调用类中的静态成员时（静态加载）
        /// 4.通过反射（动态加载）
    }
}
