package com.nanxinda.enum_;

public class EnumMethod {
    /*
    enum常用方法（隐式继承父类Enum）
     */
    public static void main(String[] args) {
        Season3 autumn = Season3.AUTUMN;
        //1.name()-输出枚举对象的名字
        System.out.println(autumn.name());
        //2.ordinal()输出的是该枚举对象的次序/编号，从0开始编号
        //AUTUMN枚举对象时第三个，因此输出2
        System.out.println(autumn.ordinal());
        //3.values()从反编译可以看出values方法，返回Season3[]
        //含有定义的所有枚举对象
        Season3[] values = Season3.values();
        for (int i = 0; i < values.length; i++) {
            System.out.println(values[i]);
        }
        //4.valueOf:将字符串转换成枚举对象，要求字符串必须为已有的常量名，否则报异常
        Season3 autumn1 = Season3.valueOf("AUTUMN");
        System.out.println("autumn="+autumn1);
        //5.compareTo,比较两个枚举常量，比较时编号
        System.out.println(Season3.AUTUMN.compareTo(Season3.SPRING));
        /*
            public final int compareTo(E o) {
                 return self.ordinal - other.ordinal;
            }
            前一个常量的编号减去后一个常量的编号
         */




    }
}
enum Season3{
    SPRING("春天","温暖"),
    SUMMER("夏天","炎热"),
    AUTUMN("秋天","凉爽"),
    WINTER("冬天","寒冷");
    private String name;
    private String desc;
    private Season3(String name, String desc) {
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