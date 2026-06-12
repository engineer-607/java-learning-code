package com.nanxinda.reflection;

import java.lang.reflect.Field;

@SuppressWarnings({"all"})
public class ReflectAccessProperty {
    public static void main(String[] args) throws Exception {
        //1.得到Student类对应的Class对象
        Class<?> stuClass = Class.forName("com.nanxinda.reflection.Student");
        //2.创建对象
        Object o = stuClass.newInstance();
        //3.通过反射得到age属性
        Field age = stuClass.getField("age");
        age.set(o,18);
        System.out.println(o);
        System.out.println(age.get(o));
        //4.使用反射操作name属性
        Field name = stuClass.getDeclaredField("name");
        //对name进行爆破，可以操作private属性
        name.setAccessible(true);
        name.set(o,"jack");
        System.out.println(name.get(o));
        ///如果属性是静态的，则set和get中的参数o可以设置为null
        name.set(null,"tom");
        System.out.println(name.get(null));
    }
}
class Student{
    public int age;
    private static String name;
    public Student(){
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                '}';
    }
}
