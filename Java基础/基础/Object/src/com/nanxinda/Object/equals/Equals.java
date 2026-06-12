package com.nanxinda.Object.equals;

public class Equals {
    //==是一个比较运算符
    //==：既可以判断基本类型，又可以判断引用类型
    //==：如果判断基本类型，判断的是值是否相等
    //==：如果判断引用类型，判断的是地址是否相等，即判定是不是同一个对象
    public static void main(String[] args) {
        System.out.println("hello".equals("abc"));
        //String equals源码
//        public boolean equals(Object anObject) {
//            if (this == anObject) {//先判断是否为同一个对象，即判断地址是否相等
//                return true;
//            }
//            if (anObject instanceof String) {//判断类型
//                String anotherString = (String)anObject;//向下转型
//                int n = value.length;
//                if (n == anotherString.value.length) {//判断长度是否相等
//                    char v1[] = value;
//                    char v2[] = anotherString.value;
//                    int i = 0;
//                    while (n-- != 0) {//一个一个字符进行比较
//                        if (v1[i] != v2[i])
//                            return false;
//                        i++;
//                    }
//                    return true;
//                }
//            }
//            return false;//如果不是字符串直接返回false
//        }
        B b = new B();
        A a = b;
        A c = b;
        System.out.println(a.equals(c));
        /*
        Object的equals方法
            public boolean equals(Object obj) {
                 return (this == obj);
             }//比较地址
         */
        Integer i = new Integer(5);
        Object m = i;
        System.out.println(i.equals(m));
        /*
                public boolean equals(Object obj) {
                   if (obj instanceof Integer) {
                     return value == ((Integer)obj).intValue();
                   }
                     return false;
                }
                public int intValue() {
                    return value;
                }//由于值是私有属性，通过公共方法获取Integer类的值
                private final int value;
         */

    }
}
class A{}
class B extends A{}
