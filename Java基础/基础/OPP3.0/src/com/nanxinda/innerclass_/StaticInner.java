package com.nanxinda.innerclass_;

public class StaticInner {
    //静态内部类
    /*
    静态内部类是定义在外部类的成员位置，并且有static修饰
    1.可以直接访问外部类的所有静态成员，包含私有的，但不能直接访问非静态成员
     */
    public static void main(String[] args) {
        Outer08 outer08 = new Outer08();
        outer08.function();
        //4.外部其他类访问使用静态内部类
        //方式一(静态内部类可以通过类名直接访问）
        Outer08.Inner08 inner08 = new Outer08.Inner08();
        inner08.say();
        //方式二
        //编写一个方法，可以返回静态内部类的对象实例
        Outer08.Inner08 inner9 = Outer08.getInner08();
        inner9.say();
    }
}
class Outer08{
    private int num = 90;
    private static String name= "jack";
    //2.可以添加任意访问修饰符（public，protected，默认，private），因为它的地位是一个成员
    static class Inner08{
        private String name = "张三";
        public void say(){
            //1.可以直接访问外部类的所有静态成员，包含私有的，但不能直接访问非静态成员
            System.out.println(name);
            //如果外部类和静态内部类的成员重名时，静态内部类访问时，默认遵循就近原则，如果想访问
            //外部类的成员，则可以使用（外部类名.成员）去访问
        }
    }
    //3.外部类访问静态内部类的方法：先创建对象，再进行访问
    public void function(){
        Inner08 inner08 = new Inner08();
        inner08.say();
    }
    public static Inner08 getInner08(){
        return new Inner08();
    }
}
