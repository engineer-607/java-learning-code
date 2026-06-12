package com.nanxinda.class_.StringBuilder_;

public class StringBuilderDetail {
    public static void main(String[] args) {
        /*
        StringBuilder
        1）一个可变的字符序列。此类提供一个与StringBuffer兼容的API，但不保证同步（StringBuilder不是一个
        线程安全）。该类被设计用作StringBuffer的一个简易替换，用在字符串缓冲区被单个线程使用的时候
        因为在大多数实现中，它比StringBuilder要快，所以建议优先采用该类。
        2）在StringBuilder上的主要操作时append和insert方法，已重载这些方法，可以接受任意类型的数据
         */
        //1.StringBuilder 继承 AbstractStringBuilder类
        //2.实现了 Serializable ，说明StringBuilder 对象是可以进行串行化（对象可以网络传输，可以保存到文件）
        //3.StringBuilder是final类，不能被继承
        //4.StringBuilder对象字符序列虽然是存放在父类AbstractStringBuilder的char[] value;
        //因此，字符序列是在堆中的
        //5.StringBuilder的方法，没有做互斥处理，即没有synchronized关键字，因此在单线程的情况下使用
        //StringBuilder
        /*
        String,StringBuilder,StringBuffer比较
        1）StringBuilder和StringBuffer非常类似，均代表可变的字符序列，而且方法也一样
        2）String：不可变字符序列，效率低，但是复用率高
        3）StringBuffer：可变字符序列，效率较高（增删），线程安全
        4）StringBuilder：可变字符序列，效率最高，线程不安全
         */
        String s = "a";      // 第1步：在常量池创建/引用"a"
        s += "b";            // 第2步：相当于 s = new StringBuilder().append("a").append("b").toString()
        //创建新的String对象"ab"
//        执行 s += "b"; 后：
//        常量池："a", "b"
//        堆内存：新String对象"ab"（不在常量池中）
//        栈内存：s → 指向堆中的新对象"ab"
        //结果：不断修改String会导致大量的字面量对象存储在常量池中，却不发挥复用的作用，进而降低
        //效率，影响程序的性能，因此如果对String做大量修改，不要使用String
        /*
        如果字符串存在大量的修改操作，一般使用StringBuffer和StringBuilder
        如果字符串存在大量的修改操作，并在单线程的情况，使用StringBuilder
        如果字符串存在大量的修改操作，并在多线程的情况，使用StringBuffer
        如果字符很少被修改，被多个对象引用，使用String，比如配置信息
         */
    }
}
