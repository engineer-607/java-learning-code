package com.nanxinda.ployDeatil02;

public class PloyDetail02 {

    public static void main(String[] args) {
        //属性没有重写之说!属性的值看编译类型
        //字段是静态绑定的：编译时根据变量的声明类型确定
        //方法是动态绑定的：运行时根据对象的实际类型确定
        Base base = new Sub();//向上转型
        System.out.println(base.count);//创建的对象中有Base类的count属性,有Sub类的count属性
        //但属性的调用看编译类型
        //instance of 用于比较操作符,判断对象的运行类型是否为XX类型或XX类型的子类型
        System.out.println(base instanceof Base);
        System.out.println(base instanceof Sub);
        //错题
        Sub sub= new Sub();
        Base b = sub;
        System.out.println(b==sub);
        //==对于基本类型只看值,对于引用类型只看地址,都不看编译类型
    }
}
class Base{
    int count=10;

}
class Sub extends Base{
    int count=20;

}
