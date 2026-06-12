package com.nanxinda.homework;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@SuppressWarnings({"all"})
public class HomeWork01 {
    public static void main(String[] args) throws Exception {
        Class<?> aClass = Class.forName("com.nanxinda.homework.PrivateTest");
        Object o = aClass.newInstance();
        Field name = aClass.getDeclaredField("name");
        name.setAccessible(true);
        name.set(o,"hellojack");
        Method getName = aClass.getDeclaredMethod("getName");
        Object invoke = getName.invoke(o);
        System.out.println(invoke);


    }
}
class PrivateTest{
    private String name = "hellokitty";
    public String getName(){
        return name;
    }
}