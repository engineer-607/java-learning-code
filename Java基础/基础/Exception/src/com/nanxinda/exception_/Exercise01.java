package com.nanxinda.exception_;

public class Exercise01 {
    public static void main(String[] args) {
        System.out.println(ExceptionReturnDemo.method());
    }
}
class ExceptionReturnDemo {
     public static int method() {
         try {
             String[] names = new String[3]; // 创建长度为3的String数组，默认值都是null

             if (names[1].equals("tom")) {   // names[1] = null，抛出NullPointerException
                 System.out.println(names[1]);
             } else {
                 names[3] = "hspedu";
             }

             return 1;

         } catch (ArrayIndexOutOfBoundsException e) {
             return 2;

         } catch (NullPointerException e) {   // 捕获NullPointerException
             return 3;                        // 准备返回值3，但在返回前先执行finally

         } finally {                          // 必须执行的部分
             return 4;                        // 这会覆盖catch中的return 3
         }
     }
 }