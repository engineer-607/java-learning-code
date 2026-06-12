package com.nanxinda.Design;

public class Design01 {
    /*
    单例模式（单个实例）
    1）采取一定方法保证在整个软件系统中，对某个类只能存在一个对象实例，
    并且该类只提供一个取得其对象实例的方法
    2）两种设计模式：饿汉式和懒汉式
    饿汉式步骤：
    1.构造器私有化=》防止直接new
    2.类的内部私有化
    3.向外暴露一个静态的公共方法
    弊端：类加载时就创建了该对象，但是可能并没有用到造成资源浪费
    懒汉式步骤：
    1.仍然构造器私有化
    2.定义一个static静态属性对象
    3.提供一个public的static方法，可以返回一个Cat对象
    4.懒汉式，只有当用户使用getInstance时，才返回Cat对象
    弊端：多线程的时候会同时创建多个对象，单例模式会被破坏
     */
    public static void main(String[] args) {
        //类加载时对象就已经创建，即便后续没有使用到
        System.out.println(GirlFriend.getInstance());
        //类加载时对象并未创建，只有后续使用时才会创建
        System.out.println(Cat.getInstance());
    }
}
//饿汉式
class GirlFriend{
    private String name;
    //为了能保障我们只能创建一个GirlFriend对象
    private static GirlFriend girlFriend = new GirlFriend("雯雯");
    /*
    步骤：
    1）将构造器私有化
    2）在类的内部直接创建
    3）提供一个公共的static方法，返回gf对象
     */
    private GirlFriend(String name) {
        this.name = name;
    }

    public static GirlFriend getInstance(){
        return girlFriend;
    }

    @Override
    public String toString() {
        return "GirlFriend{" +
                "name='" + name + '\'' +
                '}';
    }
}
//懒汉式
class Cat{
    private String name;
    private static Cat cat;

    private Cat(String name) {
        this.name = name;
    }
    public static Cat getInstance(){
        if(cat == null){
            Cat.cat = new Cat("小白");
        }
        return cat;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                '}';
    }
}
