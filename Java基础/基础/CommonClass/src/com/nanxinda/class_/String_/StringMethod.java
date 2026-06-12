package com.nanxinda.class_.String_;

public class StringMethod {
    /*
    equalsIgnoreCase//忽略大小写的判断内容是否相等
    indexOf//获取字符在字符串中第一次出现的索引，索引从0开始，如果找不到，则返回-1
    lastIndexOf//获取字符在字符串中最后一次出现的索引，索引从0开始，如果找不到，则返回-1
    substring//截取指定范围的子串
    trim//去前后空格
    charAt//获取某索引处的字符，注意不能使用Str[index]这种方式
     */
    public static void main(String[] args) {
        String s1 = "we@terwe@g";
        System.out.println(s1.indexOf('@'));//2
        System.out.println(s1.lastIndexOf('@'));//8
        System.out.println(s1.indexOf("we"));//0
        System.out.println(s1.lastIndexOf("we"));//6
        String name = "hello,张三";
        System.out.println(name.substring(6));
        //name.substring(6)代表从索引6开始截取后面所有的内容
        System.out.println(name.substring(0,5));
        //name.substring(0,5)表示从索引0开始截取，截取到索引5-1=4的位置
        //1.toUpperCase转换成大写
        String s = "hello";
        System.out.println(s.toUpperCase());//HELLO
        //2.toLowerCase
        System.out.println(s.toLowerCase());//hello
        //3.concat拼接字符串
        String s2 = "贾宝玉";
        s2=s2.concat(" and").concat(" 林黛玉");
        System.out.println(s2);
        //4.replace替换字符串中的字符
        s2 = s2.replace("贾宝玉","jack");
        System.out.println(s2);
        //5.spilt分割字符，对于某些分割字符，我们需要转义 比如\\等
        String poem  = "锄禾日当午，汗滴禾下土，谁知盘中餐，粒粒皆辛苦";
        String[] spilt  = poem.split("，");
        System.out.println("==这首诗分割之后的内容==");
        for (int i = 0; i < spilt.length; i++) {
            System.out.println(spilt[i]);
        }
        poem = "E:\\aaa\\bbb";
        spilt = poem.split("\\\\");
        System.out.println("==分割之后的内容==");
        for (int i = 0; i < spilt.length; i++) {
            System.out.println(spilt[i]);
        }
        //6.toCharArray 转换成字符数组
        s = "happy";
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            System.out.print(chars[i]+" ");
        }
        //7.compareTo 比较两个字符串的大小，如果前者大，则返回正数
        //如果后者大，则返回负数，如果相等，则返回0
        String a  = "jack";
        String b = "jac";
        System.out.println("\n"+a.compareTo(b));//返回a.length-b.length = 1
        a = "jaa";
        System.out.println(b.compareTo(a));//返回'c'-'a'=2
        //8.format格式字符串
        String s3 = "john";
        int age = 10;
        double score = 98.3;
        char gender = '男';
        String formatStr  = "我的姓名是%s 年龄是%d 成绩是%.2f 性别是%c，希望大家喜欢我";
        String info2  = String.format(formatStr, s3,age,score,gender);
        System.out.println(info2);






    }
}
