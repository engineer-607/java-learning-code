package com.nanxinda.reflection;
@SuppressWarnings({"all"})
public class ReflectionUtils {
    public static void main(String[] args) throws Exception{
        //得到Class对象
        Class<?> aClass = Class.forName("com.nanxinda.reflection.Person");
//        getName：获取全类名
        System.out.println(aClass.getName());
//        getSimpleName：获取简单类名
//        getFields：获取所有public修饰的属性，包含本类以及父类的
//        getDeclaredFields：获取本类中所有属性
//        getMethods：获取所有public修饰的方法，包含本类以及父类的
//        getDeclaredMethods：获取本类中所有方法
//        getConstructors：获取所有public修饰的构造器，包含本类以及父类的
//        getDeclaredConstructors：获取本类中所有构造器
//        getPackage：以Package形式返回包信息
//        getSuperClass：以Class形式返回父类信息
//        getInterfaces：以Class[]形式返回接口信息
//        getAnnotations：以Annotation[]形式返回注解信息
    }
}
class Person{
    //属性
    public String name;
    protected int age;
    String job;
    private double sal;
    //方法
    public void m1(){}
    protected void m2(){}
    void  m3(){}
    private void m4(){}
}
