package com.nanxinda.final_;

public class Final01 {
    /*
    final可以修饰类、属性、方法和局部变量
    final使用情况：
    1）当不希望类被继承时，可以用final修饰
    2）当不希望父类的某个方法被子类覆盖/重写（override）时，可以用final关键字
    修饰
    3）当不希望类的某个属性被修改，可以用final修饰
    4）当不希望某个局部变量被修改，可以使用final修饰
     */
    /*
    final使用细节：
    1）final修饰的属性又叫常量，一般使用XX_XXX_XXX来命名
    2）final修饰的属性必须附初值，赋值可以在如下位置：
    1.定义时
    2.在构造器中
    3.在代码块中
    3）如果final修饰的属性时静态的，则初始化的位置只能时
    1.定义时2.在静态代码块（不能在构造器中）
    4）final类不能继承，但是可以实例化对象
    5）如果不是final类，但是含有final方法，则该方法虽然不能重写，但是可以被继承
     */
    public static void main(String[] args) {
        //4）案例
        A02 a02 = new A02();
        //5）案例
        A04 a04 = new A04();
        a04.function();
    }
}
final class A{}
//class B extends A {}会报错
class C{
    //如果要求hi不能被子类重写
    //可以使用final修饰hi方法
    public final void hi(){}
}
class D extends C{
    //会报错
//    @Override
//    public void hi() {
//        super.hi();
//    }
}
//如果不希望某个属性被修改可以加上final
class E{
    public final double TAX_RATE = 0.08;
}
class F{
    public void function(){
        final double NUM = 0.01;
        //局部变量用final修饰就不可以再进行修改
    }
}
class A01{
    public final double TAX_RATE = 0.08;
    public final double TAX_RATE1;
    public final double TAX_RATE2;
    public A01(){
        TAX_RATE1 = 1.1;
    }
    {
        TAX_RATE2 = 8.8;
    }
}
final class A02{}
class A03{
    public void function(){

    }
}
class A04 extends A03{

}