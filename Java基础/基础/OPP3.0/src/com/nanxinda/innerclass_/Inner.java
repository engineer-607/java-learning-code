package com.nanxinda.innerclass_;

public class Inner {
    public static void main(String[] args) {
        /*
        内部类：
        一个类的内部又完整的嵌套了另一个类的结构，被嵌套的类被称为
        内部类（inner class），嵌套其他类的类称为外部类。是我们类
        的第五大成员（属性，方法，构造器，代码块，内部类）
        分类：
        1）定义在外部类的局部位置上
        1.局部内部类（有类名）
        2.匿名内部类（没有类名）
        2）定义在外部类的成员位置上
        1.成员内部类（没用static修饰）
        2.静态内部类（使用static修饰）
         */

        //局部内部类
        Outer01 outer01 = new Outer01();
        outer01.m1();
        //匿名内部类（接口）
        AnonymousInner anonymousInner = new AnonymousInner();
        anonymousInner.tiger.cry();
        System.out.println(anonymousInner.tiger.getClass());
        //匿名内部类（普通类）
        anonymousInner.adultTighter.cry();
        System.out.println(anonymousInner.adultTighter.getClass());

    }
}
//局部内部类
/*
要点：
1）局部内部类定义在方法中/代码块中
2）作用域在方法体或者代码块中
3）本质仍然是一个类
 */
class Outer01 {
    private int n1 = 100;
    public void m1() {
        //1.局部内部类是定义在外部类的局部变量，通常在方法
        //3.局部内部类不能加访问修饰符，因为它的地位为一个局部变量。局部变量是
        //不能使用修饰符的，但是可以使用final修饰（避免该类被继承）
        //4.作用域：仅仅定义在它的方法或者代码块中
        class Inner02 {//局部内部类（本质仍然是一个类）
            //2.可以直接访问外部类的所有成员，包含私有的
            //8.如果外部类和局部内部类的成员重名时，默认就近原则，如果想访问外部类的
            //成员，则可以使用（外部成员.this.成员）去访问
            private int n1 = 200;
            public void f1() {
                System.out.println("n1=" + n1);//就近原则，200
                System.out.println("n2=" + Outer01.this.n1);//100(Outer01.this本质就是
                //外部类的对象）
                m2();
            }
        }
        //6.外部类在方法中，可以创建Inner02对象，然后调用方法即可
        //7.外部其他类不能访问局部内部类（因为局部内部类地位是一个局部变量）
        Inner02 inner02 = new Inner02();
        inner02.f1();
    }
    public void m2(){
        System.out.println("方法m2()被调用");
    }
    {
        class Inner03{}
        //作用域仅仅在代码块中
    }

}

class AnonymousInner{
    /*
    匿名内部类
    1）本质是类2）内部类3）该类没有名字4）同时还是一个对象
     */
    //采用匿名内部类可以避免为了只是一次性使用某个成员而去额外创建一个类
    //演示接口的匿名内部类
        Animal tiger = new Animal() {
        @Override
        public void cry() {
            System.out.println("老虎在叫唤");
        }
    };
    //匿名内部类只是一次性使用，后面不再使用，可以简化开发
    //tighter的编译类型是Animal，运行类型是匿名内部类(AnonymousInner$1-系统自动命名)
    /*
    底层：
    class AnonymousInner$1 implements Animal{
          @Override
          public void cry() {
            System.out.println("老虎在叫唤");
          }
    }
     */
    //演示类的匿名内部类
    Tighter adultTighter = new Tighter(){
        @Override
        public void cry() {
            System.out.println("成年老虎在叫唤...");
        }
    };
    /*
    底层：
    class AnonymousInner$2 extends Tighter {
         @Override
         public void cry() {
            System.out.println("成年老虎在叫唤...");
        }
    }
    同时也返回匿名内部类AnonymousInner$2的对象
     */
    //演示抽象类的匿名内部类
    Vegetable carrot  = new Vegetable() {
        @Override
        void grow() {
            System.out.println("胡萝卜在生长...");
        }
    };
}
interface Animal{
    public void cry();
}
//如果实现接口只是一次性使用某个方法，额外创建一类去实现接口太麻烦
class Tighter implements Animal{

    @Override
    public void cry() {
        System.out.println("老虎叫唤...");
    }
}
abstract class Vegetable{
    abstract void grow();
}