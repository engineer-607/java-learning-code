package com.nanxinda.draw;

import javax.swing.*;
import java.awt.*;
@SuppressWarnings({"all"})
public class DrawCircle extends JFrame{//JFrame对应窗口（类似于画框）
    /*
    Java坐标系：
    坐标原点位于左上角，以像素为单位，第一个是x坐标，表示当前位置为水平方向，距离坐标原点
    x个像素，距离坐标原点x个像素；第二个是y坐标，表示当前位置为垂直方向，距离坐标远点y个
    像素
    像素：密度单位，计算机在屏幕上显示的内容都是由屏幕上的每一个像素组成的
    例如，如果计算机显示器分辨率是800*600，表示计算机屏幕的每一行由800个
    组成，共600行，整个计算机屏幕480000个像素（无法与长度单位进行比较）
     */
    private MyPanel myPanel = null;
    public static void main(String[] args) {
        new DrawCircle();

    }
    public DrawCircle(){
        myPanel = new MyPanel();
        //把面板放入到窗口（面板）
        this.add(myPanel);//设置窗口的大小
        this.setSize(400,300);
        //点击窗口的红差，程序退出
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);//可以显示

    }
}
//先定义一个MayPanel，继承JPanel类，画图形，就在画板上画
class MyPanel extends JPanel{

    //1.MyPanel对象可以理解为一个画板
    //2.Graphics g 理解为一支画笔
    //3.Graphics 提供很多绘图的方法
    @Override
    public void paint(Graphics g) {//绘图方法
        super.paint(g);//调用父类的方法完成初始化
        //画出一个圆形
        g.drawOval(10,10,100,100);
        System.out.println("paint方法被调用");

    }
    //paint方法自动被调用的情况：
    //1）当组件第一次在屏幕显示的时候，程序会自动的调用paint()方法来绘制组件
    //2）窗口最小化，再最大化
    //3）repaint函数被调用
    //4）窗口大小被调用
}