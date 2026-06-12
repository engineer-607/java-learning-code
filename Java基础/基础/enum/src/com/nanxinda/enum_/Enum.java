package com.nanxinda.enum_;

public class Enum{
    //枚举是一组常量的集合
    //枚举是一种特殊的类，里面只包含一组有限的特定的对象
    //枚举类的两种实现方式
    //1）自定义枚举2）使用关键字enum实现枚举案例
    //枚举类不能继承其他类，因为已经隐式继承Enum
    //枚举类和普通类一样，可以实现接口
    //enum 类名 implements 接口1,接口2{}
    public static void main(String[] args) {
        System.out.println(Season.AUTUMN);
        System.out.println(Season2.SUMMER);
        /*
        当我们使用·enum关键字开发一个枚举类时，默认会继承Enum类，而且是一个final类
        （可以用javap反编译工具来演示）
         */
    }

}
class Season{
    private String name;
    private String desc;
    //1.将构造器私有化，目的是防止直接new
    //2.去掉setXXX方法，防止属性被修改
    //3.在Season内部，直接创建固定的对象
    //4.优化，可以加入final修饰符
    public static Season SPRING = new Season("春天","温暖");
    public static Season WINTER = new Season("冬天","寒冷");
    public static Season AUTUMN = new Season("秋天","凉爽");
    public static Season SUMMER = new Season("夏天","炎热");


    private Season(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Season{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
//使用关键字enum实现枚举案例
enum Season2{
//    public static Season SPRING = new Season("春天","温暖");
//    public static Season WINTER = new Season("冬天","寒冷");
//    public static Season AUTUMN = new Season("秋天","凉爽");
//    public static Season SUMMER = new Season("夏天","炎热");
    /*
    使用enum实现枚举类
    1.使用关键字enum替代class
    2.public static Season SPRING = new Season("春天","温暖");
    直接使用SPRING("春天","温暖") 解读 常量名(实参列表)
    3.如果有多个常量（对象），使用,间隔即可
    4.如果使用enum来实现枚举，要求将定义常量对象，写在最前面
    5.如果我们使用的是无参构造器，创建常量对象，则可以省略()

     */
    SPRING("春天","温暖"),
    WINTER("冬天","寒冷"),
    AUTUMN("秋天","凉爽"),
    SUMMER("夏天","炎热");
    private String name;
    private String desc;
    private Season2(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Season{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}