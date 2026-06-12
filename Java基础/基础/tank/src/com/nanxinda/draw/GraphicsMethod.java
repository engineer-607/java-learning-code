package com.nanxinda.draw;

import javax.swing.*;
import java.awt.*;

public class GraphicsMethod {
    public static void main(String[] args) {
        new Frame();

    }
}
class Frame extends JFrame{
    Panel panel = null;

    public Frame() {
        this.panel = new Panel();
        this.add(panel);
        this.setSize(3000,2000);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
class Panel extends JPanel{
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //1. 画直线 drawLine(int x1, int y1, int x2, int y2)
        g.drawLine(10,10,100,100);
        //2. 画矩形边框 drawRect(int x, int y, int width, int height)
        g.drawRect(120,120,100,100);
        //3. 画椭圆边框 drawOval(int x, int y, int width, int height)
        //4. 填充矩形 fillRect(int x, int y, int width, int height)
        g.setColor(Color.blue);
        g.fillRect(120,120,100,100);
        //5. 填充椭圆 fillOval(int x, int y, int width, int height)
        g.setColor(Color.red);
        g.fillOval(230,230,100,50);
        //6. 画图片 drawImage(Image img, int x, int y, ...)
        //1）获取图片资源,/R-C.jpg
        Image image = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/R-C.jpg"));
        g.drawImage(image,500,600,960,600,this);
        //7. 画字符串 drawString(String str, int x, int y)
        g.setColor(Color.red);
        g.setFont(new Font("隶书",Font.BOLD,50));
        g.drawString("刺客信条",980,540);
        //8. 设置画笔的字体 setFont(Font font)
        //9. 设置画笔的颜色 setColor(Color c)
    }
}