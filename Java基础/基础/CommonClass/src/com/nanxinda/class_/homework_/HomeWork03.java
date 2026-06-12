package com.nanxinda.class_.homework_;

public class HomeWork03 {
    public static void main(String[] args) {
        String s = "Han shun Ping";
        System.out.println(change(s));

    }
    public static String change(String s){
        char[] chars = s.toCharArray();
            int a1 = s.indexOf(" ");//可以使用字符串分割
            int a2 = s.lastIndexOf(" ");
            String s1 = new String(chars,a2, chars.length-a2);
            char c =  (char) (chars[a1+1]+'A'-'a');
            String s2 = new String(chars,0,a1);
            return s2+","+s1+"."+c;//可以使用自定义的格式
    }
}
