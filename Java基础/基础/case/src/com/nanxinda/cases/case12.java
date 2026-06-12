package com.nanxinda.cases;

public class case12 {
    public static void main(String[] args) {
        C c = new C();
    }
    /*
    我是A类
    haha我是B类的有参构造
    我是c类的有参参构造
    我是c类的无参构造
     */
}
        class A{
              public A(){
                   System.out.println("我是A类");
             }
        }
        class B extends A {
            public B() {
                System.out.println("我是B类的无参构造");
            }
            public B(String name) {
                //super();
                System.out.println(name + "我是B类的有参构造");
            }
        }
        class C extends B{
            public C(){
                 this("hello");//this和super只能有一句放在第一句，不能同时出现
                 System.out.println("我是c类的无参构造");
            }
            public C(String name) {
                 super("hahah");
                 System.out.println("我是c类的有参参构造");
            }
        }