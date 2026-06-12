package com.nanxinda.reflection;

import java.lang.reflect.Method;

public class ReflectAccessMethod {
    public static void main(String[] args) throws Exception{
        //1.得到Boss对应的Class对象
        Class<?> bossClass = Class.forName("com.nanxinda.reflection.Boss");
        //2.创建对象
        Object o = bossClass.newInstance();
        //3.调用public方法hi
        Method hi = bossClass.getMethod("hi", String.class);
        hi.invoke(o, "jack");
        //4.调用静态私有方法say
        Method say = bossClass.getDeclaredMethod("say", int.class, String.class, char.class);
        say.setAccessible(true);
        System.out.println(say.invoke(o,100,"tom",'M'));
        //因为say方法是静态的，还可以使用null代替o
        Object invoke = say.invoke(null, 200, "mary", 'W');
        System.out.println(invoke);
        System.out.println(invoke.getClass());
        /// 在反射中，如果方法有返回值，统一返回Object，但是运行类型和方法定义的返回类型一致


    }
}
class Boss{
    public int age;
    private static String name;
    public Boss(){}
    private static String say(int n,String s,char c){
        return n+" "+s+" "+c;
    }
    public void hi(String s){
        System.out.println("hi "+s);
    }
}