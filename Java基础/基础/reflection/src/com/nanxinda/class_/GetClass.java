package com.nanxinda.class_;
@SuppressWarnings({"all"})
public class GetClass {
    public static void main(String[] args) throws ClassNotFoundException {
        /// 本质都是获取Car类在堆中对应的Class类对象
        //1.前提：已知一个类的全类名，且该类在类路径下，可通过Class类的静态方法forName()获取
        //可能会抛出ClassNotFoundException
        //应用场景：多用于配置文件，读取类全路径，加载类
        String classAllPath = "com.nanxinda.class_.Car";
        Class<?> aClass = Class.forName(classAllPath);
        System.out.println(aClass);

        //2.类名.class，应用场景：用于参数传递
        Class cls2 = Car.class;
        System.out.println(cls2);

        //3.对象.getClass()，应用场景：有对象的实例
        Car car = new Car();
        Class<? extends Car> aClass1 = car.getClass();//<? extends Car>是因为能调用编译类型
        //为Car的引用变量car那么其运行类型必然是Car或者其子类
        System.out.println(aClass1);

        //4.通过类加载器【4种】来获取类的Class对象
        //(1)先得到类加载器car
        ClassLoader classLoader = car.getClass().getClassLoader();
        //(2)通过类加载器得到Class对象
        Class cls4 = classLoader.loadClass(classAllPath);
        System.out.println(cls4);

        //5.基本数据类型(int,char,boolean,float,double,byte,long,short)按如下方式得到Class类对象
        Class<Integer> integerClass = int.class;
        Class<Character> characterClass = char.class;

        //6.基本数据类型对应的包装类，可以通过.TYPE得到Class对象
        Class<Integer> type = Integer.TYPE;
        Class<Character> type1 = Character.TYPE;

    }
}
