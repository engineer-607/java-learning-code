package com.nanxinda.final_;

public class FinalExercise {
    public static void main(String[] args) {
        System.out.println(new Circle(4).calArea());
    }
}
class Circle{
    private final double PI = 3.14 ;
    private int radius ;
    public Circle(int radius) {
        this.radius = radius;
        //PI=3.14;
    }
    {
        //PI=3.14;
    }
    public double calArea(){
        return PI*radius*radius;
    }
}
