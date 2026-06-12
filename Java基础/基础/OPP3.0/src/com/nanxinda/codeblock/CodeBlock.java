package com.nanxinda.codeblock;

public class CodeBlock {
    /*
    代码块
    代码块又称初始化块，属于类中的成员[类的一部分]，
    类似于方法，但没有方法名，没有返回，没有参数，只有方法体
    不用通过对象或类显式调用，而是加载类时，或创建对象时隐式调用
    基本语法：
    [修饰符]{
    代码
    };
    说明注意：
    1）修饰符可选，要写的话，只能写static
    2）代码块分为两部分：静态代码块和普通代码块
    3）逻辑语句可以是任何逻辑语句
    4）;号可以写上，也可以省略
     */
    //使用场景：当构造器中有重复代码可以写到代码块中，提高代码复用性
    public static void main(String[] args) {
        Movie movie = new Movie("哪吒2");
        System.out.println("=========");
        Movie movie1 = new Movie(50, "唐探1900");
        System.out.println("=========");
        Movie movie2 = new Movie("影", 40, "张艺谋");
        System.out.println("=========");
    }
}
class Movie{
    private String name;
    private double price;
    private String director;
    //三个构造器构成重载
    //当我们不管调用哪个构造器，创建对象，都会先调用代码块的内容
    {
        System.out.println("电影屏幕打开...");
        System.out.println("广告开始...");
        System.out.println("电影正式开始...");
    }

    public Movie(String name) {
        this.name = name;
    }

    public Movie(double price, String name) {
        this.price = price;
        this.name = name;
    }

    public Movie(String name, double price, String director) {
        this.name = name;
        this.price = price;
        this.director = director;
    }
}
