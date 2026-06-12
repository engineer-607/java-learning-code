package com.nanxinda.class_.homework_;

public class HomeWork01 {
    public static void main(String[] args) {
        String s = "abcdef";
        System.out.println("==交换前==");
        System.out.println(s);
        System.out.println("==交换后==");
        try {
            String s1 = reserve(s,1,4);
            System.out.println(s1);
        } catch (Exception e) {
            System.out.println("错误信息："+e.getMessage());
        }
    }
    public static String reserve(String string,int start,int end){
        if(!(start>=0&&start<end&&end<string.length()&&string!=null)){//先取正确的情况，再进行取反
            throw new RuntimeException("参数不正确");
        }
        char[] chars = string.toCharArray();
        for (int i = 0; i < (end+start)/2; i++) {
            char temp = chars[i+start];
            chars[i+start] = chars[end-i];
            chars[end-i] = temp;
        }
        /*
        for循环也可以写作
        for(int i=start,j=end;i<j;i++,j--){
            char temp = chars[i];
            chars[j] = chars[i];
            chars[i] = temp;
        }
         */
        return new String(chars);//重新构建一个String即可
    }
}
