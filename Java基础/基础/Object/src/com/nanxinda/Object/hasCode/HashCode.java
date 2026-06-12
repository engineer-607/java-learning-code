package com.nanxinda.Object.hasCode;

public class HashCode {
    /*
    hashCode()方法
    1）提高具有哈希结构容器的效率
    2）两个引用，如果指向的是同一个对象，则哈希值肯定是一样的
    3）两个引用，如果指向的是不同对象，则哈希值是不一样的
    4）哈希值主要根据地址来的，不能完全将哈希值等价于地址
     */

    public static void main(String[] args) {
        AA aa = new AA();
        AA aa1 = new AA();
        AA aa2=aa;
        System.out.println(aa.hashCode());
        System.out.println(aa1.hashCode());
        System.out.println(aa2.hashCode());
    }

}
class AA{}