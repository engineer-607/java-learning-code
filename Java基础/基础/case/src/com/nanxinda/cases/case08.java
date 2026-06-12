package com.nanxinda.cases;

public class case08 {

}
class Point{
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

}
class LabeledPoint extends Point{
    private String point;

    public LabeledPoint(double x, double y, String point) {
        super(x, y);
        this.point = point;
    }

}