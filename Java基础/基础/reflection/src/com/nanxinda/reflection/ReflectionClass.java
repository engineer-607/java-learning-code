package com.nanxinda.reflection;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

public class ReflectionClass {
    /**
    反射机制可以完成：
     1.运行时判断任意一个对象所属的类
     2.在运行时构造任意一个类的对象
     3.在运行时得到任意一个类所具有的成员变量和方法
     4.在运行时调用任意一个对象的成员变量和方法
     5.生成动态代理
     */
    @SuppressWarnings({"all"})
    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        //1.java.lang.Class：代表一个类，Class对象表示某个类加载后在堆中的对象
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\re.properties"));
        String classfullPath = properties.get("classfullpath").toString();
        String methodName = properties.get("method").toString();
        System.out.println("classfullpath="+classfullPath);
        System.out.println("method="+ methodName);
        Class aClass = Class.forName(classfullPath);
        Object o = aClass.newInstance();
        //2.java.lang.reflect.Method：代表类的方法
        Method method = aClass.getMethod(methodName);
        method.invoke(o);
        //3.java.lang.reflect.Field：代表类的成员变量
        //getField()不能得到私有的属性
        Field nameField = aClass.getField("age");
        System.out.println(nameField.get(o));
        //4.java.lang.reflect.Constructor：代表类的构造方法，Constructor对象表示构造器
        Constructor constructor = aClass.getConstructor();
        System.out.println(constructor);
        Constructor constructor1 = aClass.getConstructor(String.class);
        System.out.println(constructor1);
    }
}
