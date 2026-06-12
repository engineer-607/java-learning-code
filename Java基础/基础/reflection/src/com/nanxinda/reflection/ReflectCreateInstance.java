package com.nanxinda.reflection;

import java.lang.reflect.Constructor;

@SuppressWarnings({"all"})
public class ReflectCreateInstance {
    public static void main(String[] args) throws Exception{
        //1.先获取user类的Class对象
        Class<?> userClass = Class.forName("com.nanxinda.reflection.User");
        //2.通过public的午餐构造器创建实例
        Object o = userClass.newInstance();
        System.out.println(o);
        //3.通过public的有参构造器创建实例
        //3.1得到对应构造器
        Constructor<?> constructor = userClass.getConstructor(String.class);
        //3.2创建实例，并传入实参
        Object jack = constructor.newInstance("jack");
        System.out.println(jack);
        /*
            constructor对象就是
                public User(String name){
                      this.name = name;
                }

         */
        //4.通过非public的有参构造器创建实例
        //4.1得到private的构造对象
        Constructor<?> declaredConstructor = userClass.getDeclaredConstructor(String.class, int.class);
        //4.2爆破【暴力破解】，使用反射可以访问private构造器
        declaredConstructor.setAccessible(true);
        //4.3创建实例
        Object tom = declaredConstructor.newInstance("tom", 18);
        System.out.println(tom);

    }
}
class User{
    private int age;
    private String name;
    public User(){}
    public User(String name){
        this.name = name;
    }
    private User(String name,int age){
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}