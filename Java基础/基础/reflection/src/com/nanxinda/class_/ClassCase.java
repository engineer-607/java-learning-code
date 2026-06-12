package com.nanxinda.class_;

public class ClassCase {
    //1.Class也是类，继承了Object类
    //2.Class类对象不是new出来的，而是系统创建出来的（类加载器ClassLoader的loadClass()）
    /*
        public Class<?> loadClass(String name) throws ClassNotFoundException {
            return loadClass(name, false);
        }
     */
    //3.对于某个类的Class类对象，在内存中只有一份，因此只加载一次
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> aClass1 = Class.forName("com.nanxinda.reflection.Cat");
        Class<?> aClass2 = Class.forName("com.nanxinda.reflection.Cat");
        System.out.println(aClass1.hashCode());
        System.out.println(aClass2.hashCode());
        //4.每个对象实例都会记住自己是哪个Class类对象
    }
    //5.通过Class对象可以完成地得到一个类的完整结构，通过一系列api
    //6.Class对象是存放在堆中的
    //7.类的字节码二进制数据（又称元数据：方法代码，变量名，方法名，访问权限），是放在方法区的

}
