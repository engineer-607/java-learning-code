package com.nanxinda.class_;

import java.lang.reflect.Field;

@SuppressWarnings("all")
public class ClassMethod {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        //1.获取Car对象的Class对象
        Class cls = Class.forName("com.nanxinda.class_.Car");
        //2.输出cls
        System.out.println(cls);//显示cls对象，是哪个类的Class对象 com.nanxinda.class_.Car
        System.out.println(cls.getClass());//输出cls的运行类型 java.lang.Class
        //3.得到包名
        System.out.println(cls.getPackage().getName());//包名
        //4.得到类全名
        System.out.println(cls.getName());
        //5.通过cls创建对象实例
        Car car = (Car) cls.newInstance();//使用无参构造器
        System.out.println(car);
        //6.通过反射获取属性brand
        Field brand = cls.getField("brand");
        System.out.println(brand.get(car));//宝马
        //7.通过反射给属性赋值
        brand.set(car,"奔驰");
        System.out.println(brand.get(car));//奔驰

    }
}
