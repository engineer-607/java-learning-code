package com.nanxinda.class_.StringBuffer_;

public class StringBufferMethod {
    public static void main(String[] args) {
        StringBuffer s = new StringBuffer("hello");
        //1.增 append
        s.append(',');
        s.append("张三丰");
        s.append("赵敏").append(100).append(true).append(10.5);
        System.out.println(s);
        //2.删 delete(start,end)
        s.delete(11,14);//[11,14)
        System.out.println(s);//hello,张三丰赵敏true10.5
        //3.改 replace(start,end)
        s.replace(9,11,"周芷若");//[9,11),hello,张三丰周芷若true10.5
        //4.indexOf()查找字符串第一次出现的索引位置，找不到返回-1
        System.out.println(s.indexOf("张三丰"));//6
        //5.插，insert(),在某个索引的地方插入字符串

    }
}
