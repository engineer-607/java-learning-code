package com.nanxinda.class_.homework_;

public class HomeWork04 {
    public static void main(String[] args) {
        String s = "AHa7dl89Dsa";
        int n1 = calCharacterNum(s,'A','Z');
        int n2 = calCharacterNum(s,'a','z');
        int n3 = calCharacterNum(s,'0','9');
        System.out.println(s+"中大写字母有"+n1+"个，小写字母有"+n2+"个，数字有"+n3+"个");

    }
    public static int calCharacterNum(String s,char c1,char c2){
        char[] chars = s.toCharArray();
        int count=0;
        for (int i = 0; i < chars.length; i++) {
            if(chars[i]>=c1&&chars[i]<=c2){
                count++;
            }
        }
        return count;
    }
}

