package com.nanxinda.staticvariables;

/**
 * static变量：
 * 1）static变量是同一个类所有对象共享
 * 2）static类变量，在类加载的时候就生成
 * jdk8以后类变量存储在方法区
 * ┌────────────────────────────────────────────────────────────────────┐
 * │                           内存分配示意图                             │
 * ├────────────────────────────────────────────────────────────────────┤
 * │ 方法区（Method Area / Metaspace）                                  │
 * │  ┌────────────────────────────────────────────────────────────┐   │
 * │  │  Student类信息                                             │   │
 * │  │  ├─ school: "清华大学"  ←──┐                               │   │
 * │  │  ├─ count: 3          ←──┼─┼─┐ 所有对象共享同一份           │   │
 * │  │  └─ 方法表等              │ │ │                            │   │
 * │  └────────────────────────────┼─┼─┘                            │   │
 * │                              │ │                              │   │
 * ├──────────────────────────────┼─┼──────────────────────────────┤   │
 * │ 堆区（Heap）                  │ │                              │   │
 * │  ┌──────────────────────┐    │ │  ┌──────────────────────┐    │   │
 * │  │ Student对象1         │     │ │  │ Student对象2         │    │   │
 * │  │ ├─ name: "张三"      │     │ │  │ ├─ name: "李四"      │    │   │
 * │  │ ├─ age: 20           │     │ │  │ ├─ age: 21           │    │   │
 * │  │ └─ (指向方法区)       │───——┘ │  │ └─ (指向方法区)       │────┘   │
 * │  └──────────────────────┘     │  └──────────────────────┘        │
 * │                               │                                  │
 * │  ┌──────────────────────┐     │                                  │
 * │  │ Student对象3         │     │                                  │
 * │  │ ├─ name: "王五"      │     │                                  │
 * │  │ ├─ age: 22           │     │                                  │
 * │  │ └─ (指向方法区)       │─────┘                                  │
 * │  └──────────────────────┘                                        │
 * │                                                                  │
 * ├────────────────────────────────────────────────────────────────────┤
 * │ 栈区（Stack）                                                     │
 * │  ┌──────────────────────┐                                        │
 * │  │ main()方法栈帧        │                                        │
 * │  │ ├─ s1: 引用地址 → Student对象1                                 │
 * │  │ ├─ s2: 引用地址 → Student对象2                                 │
 * │  │ ├─ s3: 引用地址 → Student对象3                                 │
 * │  │ └─ 其他局部变量        │                                        │
 * │  └──────────────────────┘                                        │
 * └────────────────────────────────────────────────────────────────────┘
 * jdk8以前存储在堆中
 * 定义的语法：访问修饰符 static 数据类型 变量名
 * 访问变量：类名.变量（依然遵循相关的访问权限）
 * 类变量和实例变量（普通属性）区别：
 * 1）类变量是该类的所有对象共享的，实例变量是该对象独享的
 * 2）实例变量不能通过类名访问
 * 类变量是类加载的时候就初始化了，即便没有创建对象，也可以访问
 * 类变量生命周期是随着类加载而开始，随着类消亡而消亡
 */

/**
 * 静态方法
 * 定义方法：访问修饰符 static 数据返回类型 方法名(){}
 * 使用场景：当方法不涉及类的任何属性时，可以设置为静态方法
 * 可以不创建实例就可以使用
 * 类方法的细节：
 * 1）类方法和普通方法都是随着类的加载而加载，将结构信息存储在方法区，
 * 2）类方法中不允许使用和对象相关的关键字，例如this和super
 * 3）类方法中只能访问静态方法或静态变量,不能访问普通属性
 *
 */
public class Static {
    public static void main(String[] args) {
        Child jack = new Child("jack");
        //类变量的访问可以通过类名
        Child.count++;
        Child mike = new Child("mike");
        Child.count++;
        System.out.println("一共"+Child.count+"个孩子参加游戏");


        Student jack1 = new Student("jack");
        jack1.payFee(100);
        Student lucy = new Student("lucy");
        lucy.payFee(200);
        Student.showFee();



    }
}
class Child{
    //定义一个变量count，是一个静态变量（类变量）
    //该变量最大的特点就是会被Child类所有的实例共享
    public static int count=0;
    private String name;
    public Child(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void join(){
        System.out.println(name+"进入游戏...");
    }
}
class Student{
    private String name;
    private static double fee = 0;

    public Student(String name) {
        this.name = name;
    }

    public  void payFee(double fee){
        Student.fee+=fee;
    }
    public static void showFee(){
        System.out.println("总学费为"+Student.fee);
    }
}
