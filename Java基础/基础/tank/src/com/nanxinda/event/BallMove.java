package com.nanxinda.event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BallMove extends JFrame {//窗口
    MyPanel myPanel = null;
    public static void main(String[] args) {
        new BallMove();

    }
    public  BallMove (){
        myPanel = new MyPanel();
        this.add(myPanel);
        //窗口JFrame对象可以监听键盘事件，即可以监听到面板发生的键盘事件
        this.addKeyListener(myPanel);
        this.setSize(400,300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
//画出小球
class MyPanel extends JPanel implements KeyListener {
    int x = 10;
    int y = 10;
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawOval(x,y,20,20);
    }
    /**
    java事件处理是采取“委派事件模型”。当事件发生时，产生事件的对象
     会把此“信息”传递给“事件的监听者”处理，
     这里所说的“信息”实际上就是java.awt.event
     事件类库里某个类所创建的对象，把它称为“事件的对象”
     */
    /**1.事件源：事件源是一个产生事件的对象
     * 2.事件：事件就是承载事件原状态改变时的对象，比如当键盘事件、鼠标
     * 事件、窗口事件等等，会生成一个事件对象，该对象保存着当前事件
     * 很多信息，比如KeyEvent对象含有被按下键的Code值。java.awt.event
     * 包和javax.swing.event包中定义了各种事件类型
     *3.事件监听器接口：
     * 1）当事件源产生一个事件，可以传给事件监听者处理
     * 2）事件监听者实际上是一个类，该类实现了某个事件监听器接口
     * 例如，案例中的MyPanel就是一个类，它实现了KeyListener接口
     * ，它可以作为一个事件监听者，对接受到的事件进行处理
     * 3）事件监听器接口有很多种，不同的事件监听器接口可以监听不同的
     * 事件，一个类可以实现多个监听接口
     * 4）这些接口在java.awt.event包和javax.swing.event包中定义
     * 列出常用的事件监听器接口
     */
    //有字符输出，该方法就会触发
    @Override
    public void keyTyped(KeyEvent e) {
        //这里的KeyEvent类对象e就是事件的对象

    }
    //当某个键按下，该方法会触发
    @Override
    public void keyPressed(KeyEvent e) {
//        System.out.println((char)e.getKeyCode()+"被按下...");
        //根据用户按下的不同键，来处理小球的移动（上下左右键）
        //在java中，会给每一个按键，分配一个值（int）
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            y=y+10;
        }else if(e.getKeyCode() == KeyEvent.VK_UP){
            y=y-10;
        }else if(e.getKeyCode() == KeyEvent.VK_LEFT){
            x=x-10;
        }else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            x=x+10;
        }
        //让页面进行重绘
        this.repaint();
    }
    //当某个键释放(松开)，该方法会触发
    @Override
    public void keyReleased(KeyEvent e) {

    }
}