package com.nanxinda.ployDetail;

public class PloyDetail {
    //多态细节
    /*
    多态前提:两个对象(类)存在继承关系
    向上转型:父类的引用指向子类的对象
    语法:父类类型 引用名 = new 子类类型();

     */
    public static void main(String[] args) {
        Animal animal = new Cat();
        // 这里演示了多态的方法调用规则
        //编译时检查变量的编译类型是否又该方法,运行时执行运行类型的方法,若没有该方法
        // 则按继承中方法查找的关系依次到父类中查找
        // 1. animal.eat() 编译通过，因为Animal类有eat方法
        // 2. 运行时会调用Cat类重写的eat方法（动态绑定）
        animal.eat(); // 输出：小猫吃鱼
        // 3. animal.cathMouse() 编译不通过，因为Animal类没有cathMouse方法
        // animal.cathMouse(); // 这里会编译报错
        animal.run();//从运行类型Cat类中查找,发现没有去父类中查找
        animal.sleep();

        //多态的向下转型
        //1)语法:子类类型 引用名 = (子类类型)父类引用;
        //2)只能强转父类引用,不能强转父类的对象
        //3)要求父类的引用必须指向的是当前目标类型的对象
        //4)下乡转型后,可以调用子类类型中所有成员
        Cat cat = (Cat) animal;
        cat.cathMouse();//第一种写法
        ((Cat)animal).cathMouse();//第二种写法
        //二者等价本质是创建一个Cat类型的变量指向创建Cat对象
        //告诉编译器将animal当作Cat类型来检查
        //编译器检查发现Cat类有catchMouse()方法编译通过

    }
}