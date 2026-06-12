package com.nanxinda.innerclass_;
//成员内部类
public class MemberInner {
    /*
    成员内部类是定义在外部类的成员位置，并且没有static修饰
    1.可以直接访问外部类的所有成员，包含私有的
    2.可以添加访问修饰符（public，protected，默认，private），因为它本身是一个成员
     */
    public static void main(String[] args) {
        Outer06 outer06 = new Outer06();
        outer06.function();
        //4.外部其他类访问成员内部类
        //第一种方式
        //outer06.new Inner01();相当于把new Inner01()当做是outer06成员
        Outer06.Inner01 inner01 = outer06.new Inner01();
        inner01.say();
        //第二种方式
        //在外部类中编写一个方法，可以返回Inner01对象
        Outer06.Inner01 inner2 = outer06.getInner01();
        inner2.say();


    }
}
class Outer06{
    private int age = 10;
    public String name = "张三";
    class Inner01{
        private int age = 11;
        public  void say(){
            System.out.println("name="+name +" age="+age);
            //如果成员内部类的成员和外部类的成员重名，会遵守就近原则
            //可以通过外部类名.this.属性 来访问外部类的成员
            System.out.println(Outer06.this.age);//10
            System.out.println(age);//11
        }
    }
    //3.外部类访问成员内部类，先要创建对象再进行访问
    public void function(){
        Inner01 inner01 = new Inner01();
        inner01.say();
    }
    public Inner01 getInner01(){
        return new Inner01();
    }
}