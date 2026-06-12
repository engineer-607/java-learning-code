package com.nanxinda.superDetail;

public class B extends A{
    int n1=888;
    public void showInfo(){
        System.out.println("n1="+super.n1+" n2="+super.n2+" n3="+super.n3);
        super.test01();
        super.test02();
        super.test03();
    }
    /*
    super代表父类的引用，用于访问父类的属性，方法，构造器
    1）访问父类的属性，但不能访问父类的private属性（super.属性名）
    2）访问父亲的方法，不能访问父类的private方法 super.方法名
    3）访问父类的构造器：super(参数列表)只能放在构造器第一句且只能出现一句
     */

    /*
    super细节
    1）调用父类构造器的好处（分工明确，父类属性由父类初始化，子类属性由子类初始化）
    2）当子类中有和父类的成员（属性和方法）重名时，为了访问父类的成员，必须通过super。
    如果没有重名，使用super，this，直接访问是一样的效果
     */
    public void sum(){
        System.out.println("B类的sum()");
        //调用父类A的cal方法
        //这时，因为子类B没有cal方法，可以使用下面三种方法
        cal();//找cal方法时，顺序是
        //(1)先找本类如果有，则调用，
        //(2)如果没有，则找父类（如果有，并可以调用，则调用）
        //(3)如果父类没有，则继续找父类的父类，这个规则就是一样的
        //提示：如果查找方法的过程中，找到了，但是不能访问，则报错
        //     如果查找方法的过程中，没有找到，则提示方法不存在
        this.cal();//等价于cal()
        super.cal();//直接查找父类
        System.out.println(n1);
        System.out.println(this.n1);
        System.out.println(super.n1);
        /*
        super的访问不限于直接父类，如果爷爷类和本类中有同名的成员，也可以使用super访问爷爷类的成员
        如果多个基类（上级类）中都有同名的成员，使用super，遵循就近原则，A>B>C，也需要遵守访问
         */
    }
}
