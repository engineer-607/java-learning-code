package com.nanxinda.class_.StringBuffer_;

public class StringBufferDetail {
    /*
    StringBuffer:
    1.StringBuffer的直接父类是AbstractStringBuilder
    2.StringBuffer实现Serialize，即StringBuffer的对象可以进行串行化
    3.在父类中 AbstractStringBuilder 有属性 char[] value，不是final
    4.StringBuffer是一个final类，不能被继承
    5.String 是不可变的（immutable），保存的是字符串常量。每次“修改”String 实际上会创建一个新的 String 对象，
    并更改引用地址，因此频繁修改时效率较低。
    6.StringBuffer 是可变的（mutable），保存的是字符串变量，可以在原对象上直接修改内容，
    不需要每次创建新对象和更改地址，因此频繁修改时效率较高。
     */
    public static void main(String[] args) {
        //StringBuffer构造器的使用
        //1.创建一个大小为16的char[]，用于存放字符内容
        StringBuffer stringBuffer = new StringBuffer();
        //2.通过构造器指定char[]大小
        StringBuffer stringBuffer1 = new StringBuffer(100);
        //3.通过一个String对象创建StringBuffer，char[]大小为str.length()+16
        StringBuffer stringBuffer2 = new StringBuffer("hello");

        //String和StringBuffer之间的相互转换
        //1）String -> StringBuffer
        //方式一 使用构造器
        //注意：返回的是StringBuffer对象，对str本身无影响
        String str = "hello world";
        StringBuffer stringBuffer3 = new StringBuffer(str);
        //方式二
        //使用append方法
        StringBuffer stringBuffer4 = new StringBuffer();
        stringBuffer4 = stringBuffer4.append(str);
        //2）StringBuffer->String
        //方式一 使用StringBuffer提供的 toString方法
        StringBuffer stringBuffer5 = new StringBuffer("jack");
        String s = stringBuffer5.toString();
        //方式二 使用构造器
        String string = new String(stringBuffer5);
    }

}
/*
1.String的属性value是数组引用，指向堆中的char[]数组，由于其为final，所以不可以扩容
2.String name = new String("hello");创建过程：
1）在类加载时会在常量池中检查没有没存储"hello"这个字面量对象，如果没有会在堆的普通区中创建一个字面量对象，
如果有则不会再进行创建，这个对象中的value属性指向堆中的char数组{'h','e','l','l','o'}，然后在常量池中存储该对象的地址
2）接着在堆的普通区中会创建一个String对象，这个对象的value属性与常量池中存储的String对象的value属性指向同一个char数组
变量name成为这个String对象的引用
3.这样的设计机制与String的设计理念有关，设计者希望String不可变，就使用final让value数组无法扩容并且使其
私有化并且外部无法改变其内容，希望其能复用就在常量池中存储字面量对象
4.StringBuffer的可变设计理念，使其value不为final，让其可以进行扩容，又由于可变所以就无需常量池来存储
字面量对象
 */