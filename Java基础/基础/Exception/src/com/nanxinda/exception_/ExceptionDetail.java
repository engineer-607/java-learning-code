package com.nanxinda.exception_;

public class ExceptionDetail {
    public static void main(String[] args) {
        //可以有多个catch语句，但异常发生时只能匹配到一个catch
        //子类异常在前，父类异常在后
        try {
            Person person = new Person();
            person=null;
            System.out.println(person.getName());
            //捕获到异常之后try下面的代码将不会执行
            int n1 = 10;
            int n2 = 0;
            int res = n1/n2;
            System.out.println(res);
        } catch (NullPointerException e) {
            System.out.println("空指针异常="+e.getMessage());
        }catch (ArithmeticException e){
            System.out.println("算术异常="+e.getMessage());
        }catch (Exception e){
            System.out.println("获得的异常"+e.getMessage());
        }finally {
            System.out.println("finally被执行");
        }
        /*
        可以进行try-finally配合使用，这种写法相当于没有捕获异常，因此程序会执行完finally代码块
        直接退出
        应用场景：不管前面有没有异常，有一段代码都必须执行某个业务逻辑
         */


    }
}
class Person{
    private String name = "jack";

    public String getName() {
        return name;
    }
}