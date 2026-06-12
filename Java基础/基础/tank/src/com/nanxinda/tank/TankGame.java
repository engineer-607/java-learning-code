package com.nanxinda.tank;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;




/// 坦克大战遇到八个问题：
/// 1）坦克不动，子弹便不动：
/// 1.原因：repaint方法只在按键事件中才会触发，独立线程Bullet虽然坐标在不断变化
/// 但是由于没有触发重绘，所以画面始终不会更新，也就造成坦克不动，画面不动的结果
/// 2.解决方案：采用Swing Timer
/// *在Panel构造器中创建Timer
/// *设置定时任务不断调用repaint方法
///
/// 2）产生新的子弹时旧子弹会消失
/// 1.原因：虽然每个子弹都有自己的线程，但是重绘时只重绘新子弹，旧子弹被覆盖
/// 2.解决方案：
/// *采用Vector集合存储每个子弹，每次重绘的对象改为集合中所有子弹
/// （利于后期删除子弹，也有利于处理多线程问题）
///
/// 3）在增强for循环中直接调用remove方法Vector集合元素进行删除抛出ConcurrentModificationException异常
/// 1.原因：增强for循环底层用的时迭代器，而迭代器检测到集合被修改，
/// 从而抛出ConcurrentModificationException
///解决方法：
/// *使用普通for循环从后至前遍历，不满足条件使用bullets.remove()
/// *使用迭代器的删除方法：iterator.remove();
///
/// 4）再次抛出ConcurrentModificationException
/// 1.原因：执行子弹线程时judgeShot()方法中虽然采取iterator.remove()对集合进行修改，
/// 迭代器不会抛出ConcurrentModificationException，但main线程执行paint方法时可能同时
/// 执行到用增强for循环对enemies进行遍历，而paint方法中的迭代器恰巧发现集合被修改，就会抛出
/// ConcurrentModificationException，这是没有考虑到多线程对同一个集合进行访问
/// 2.解决方案：*使用synchronized(enemies)限制多个线程中只能有一个对集合进行访问
///
/// 5）敌方坦克消失，但是依旧在发射子弹
/// 1.原因：bullet线程中修改enemy的hit属性，但是JVM为了性能默许enemy线程读取其CPU缓存（hit=false）
/// 而看不到Bullet线程对主内存的修改；此外，即便hit属性能够实时更新，坦克被击中的时机可能恰好在子弹发射的前一刻
/// 这就导致坦克被击中消失，但是子弹依旧发射
/// 2.解决方案：
/// *采用volatile关键字可以强制获取主内存中的新值，但该新值正常情况下是在本次循环结束下次循环开始
/// 才会发生作用，并不会因为获取的新值而中断循环
/// *在循环体中频繁加入hit值校验，达到坦克被击中后一颗子弹也不会发射的效果
///
/// 6）碰撞检测无思路
/// 解决方案：
/// *碰撞检测分为两大部分：1.检测坦克当前方向是否能继续行驶2.检测坦克是否能转动方向
/// *再细分为：1.初始化时的坦克位置是否重叠2.运行时敌军和敌军是否重叠3.敌军和我军是否重叠
/// *实操方法：利用Rectangle类的intersects方法
///
/// 7）读取内存敌军坦克信息时抛出EOFException异常
/// 1.原因：在循环中包装流ObjectInputStream读取到文件末尾时不会返回null，而是抛出EOFException
/// 2.解决方案：将计就计，通过抛出得异常EOFException中断循环
///
/// 8）当选择“继续游戏”时，上一局的坦克对象没有原本的位置，而是全部都在左上角
/// 1.原因：只在子类Enemy类添加序列化（继承Serializable），而父类Tank并没有添加序列化（继承Serializable）
/// 这就会导致，父类的坐标元素不被保存到文件中也就造成位置数据丢失
/// （x、y、direction 这些属性虽然在 Enemy 对象中可以被访问，但它们的所有权属于父类 Tank，子类不过
/// 是通过继承机制获得了这些属性）
/// 2.解决方案：让父类继承Serializable

public class TankGame {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
            new Frame();
    }
}
@SuppressWarnings({"all"})
class Frame extends JFrame{
    Panel panel = null;
    public Frame() throws IOException, ClassNotFoundException {
        this.panel = new Panel();
        this.add(panel);//把面板加入进去
        this.setSize(1000,750);
        this.setVisible(true);
        this.setDefaultCloseOperation(Frame.DISPOSE_ON_CLOSE);
        this.addKeyListener(panel);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    Bullet.recorder.record();
                    Bullet.recorder.recordEnemyTank();
                    System.out.println("数据保存成功");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                System.exit(0);
            }
        });
    }
}
@SuppressWarnings({"all"})
class Panel extends JPanel implements KeyListener {
    Hero hero;
    //定义敌人的坦克集合
    Vector<Enemy> enemies  = null;
    //定义发射子弹的集合
    Vector<Bullet> bullets = new Vector<>();
    //定义爆炸的集合
    Vector<Bomb> bombs = new Vector<>();
    //记录是否重新开始
    boolean start = false;
    int enemyTankSize = 10;
    Timer timer ;//添加timer成员变量
    Image image1;
    Image image2;
    Image image3;
    Rectangle rectangleHero;
    public Panel() throws IOException, ClassNotFoundException {
        System.out.println("请选择是继续游戏（输入1），还是开启新的游戏（输入2）：");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.close();
        boolean judge = false;
        if(choice == 1){
            start = false;
            enemies = Bullet.recorder.readEnemyTank();
            if(enemies !=null){
                judge = true;
            }else {
                enemies = new Vector<>();
            }
        }else  if(choice==2){
            start = true;
            enemies = new Vector<>();
        }
        if(start){
            Bullet.recorder.setDeadTank(0);
            Bullet.recorder.record();
        }
        hero = new Hero(100,100);
        if(!judge) {
            rectangleHero = new Rectangle(hero.getX(), hero.getY(), 40, 60);
            Vector<Rectangle> rectangles = new Vector<>();
            int randomX;
            int randomY;
            for (int i = 1; i <= enemyTankSize; ) {
                randomX = (int) (Math.random() * 940);
                randomY = (int) (Math.random() * 690);
                Enemy enemy = new Enemy(randomX, randomY);
                Rectangle rectangle = null;
                if (enemy.getDirection() == 0 || enemy.getDirection() == 1) {
                    rectangle = new Rectangle(randomX, randomY, 40, 60);
                }
                if (enemy.getDirection() == 2 || enemy.getDirection() == 3) {
                    rectangle = new Rectangle(randomX, randomY, 60, 40);
                }
                boolean judgeAdd = true;
                if (i == 1) {
                    if (!rectangleHero.intersects(rectangle)) {
                        rectangles.add(rectangle);
                        enemies.add(enemy);
                        i++;
                    }
                } else {
                    for (Rectangle oldRectangle : rectangles) {
                        if (oldRectangle.intersects(rectangle)) {
                            judgeAdd = false;
                        }
                    }
                    if (judgeAdd == true) {
                        if (!rectangleHero.intersects(rectangle)) {
                            rectangles.add(rectangle);
                            enemies.add(enemy);
                            i++;
                        }
                    }
                }
            }
        }
        Enemy.bullets = bullets;
        Bullet.hero = hero;
        Bullet.enemies = enemies;
        Bullet.bombs = bombs;
        for (Enemy enemy :enemies) {
            new Thread(enemy).start();
        }

        timer = new Timer(25, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        timer.start();
        /// 可以简单理解为一个每隔25ms就会进行重绘的线程
        try {
            image1 = ImageIO.read(Panel.class.getResource("/image1.png"));
            image2 = ImageIO.read(Panel.class.getResource("/image2.png"));
            image3 = ImageIO.read(Panel.class.getResource("/image3.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //画面版
        g.fillRect(0, 0, 1000, 750);
        //画坦克
        if (hero.isHit() == false) {
            drawTank(hero.getX(), hero.getY(), g, 0, hero.getDirection());
        }
        //画敌军的坦克
        /// 敌军的坦克数量多，可以放入集合中
        /// 选择Vector，因为要考虑多线程
        synchronized (enemies) {
            for (Enemy enemy : enemies) {
                if (enemy.isHit() == false) {
                    drawTank(enemy.getX(), enemy.getY(), g, 1, enemy.getDirection());
                }
            }
        }
        if (bullets == null) {
            return;
        }
        synchronized (bullets) {
            Iterator<Bullet> iterator = bullets.iterator();
            while (iterator.hasNext()) {
                Bullet bullet = iterator.next();
                if (!bullet.isLoop()) {
                    iterator.remove();
                    continue;
                }
                if (bullet.getX() >= 0 && bullet.getX() <= 1000 && bullet.getY() <= 750 && bullet.getY() >= 0) {
                    if (bullet.getSize() == 0) {
                        g.setColor(Color.cyan);
                    } else if (bullet.getSize() == 1) {
                        g.setColor(Color.YELLOW);
                    }
                    g.drawOval(bullet.getX(), bullet.getY(), 2, 2);
                } else {
                    bullet.setLoop(false);
                    iterator.remove();
                }
            }
        }
        Iterator<Bomb> iterator = bombs.iterator();
        while (iterator.hasNext()) {
            com.nanxinda.tank.Bomb bomb = iterator.next();

            if (bomb.getLife() > 7) {
                g.drawImage(image1, bomb.getX(), bomb.getY(), 60, 60, this);
            } else if (bomb.getLife() > 3) {
                g.drawImage(image2, bomb.getX(), bomb.getY(), 60, 60, this);
            } else {
                g.drawImage(image3, bomb.getX(), bomb.getY(), 60, 60, this);
            }
            bomb.lifeDown();
            if (bomb.getLife() == 0) {
                iterator.remove();
            }
        }
        g.setColor(Color.BLACK);
        g.setFont(new Font("楷书",Font.BOLD,30));
        g.drawString("您累计击毁敌军坦克：",1010,50);
        int x = 1010;
        int y = 70;
        g.setColor(Color.YELLOW);
        g.fill3DRect(x,y,10,60,false);
        g.fill3DRect(x+10, y+10,20,40,false);
        g.fill3DRect(x+30,y,10,60,false);
        g.fillOval(x+10,y+20,20,20);
        g.drawLine(x+20,y,x+20,y+30);
        g.setColor(Color.BLACK);
        try {
            String num = Bullet.recorder.read();
            if(num == "-1"||start) {
                g.drawString("0", 1100, 115);
                start = false;
            }else {
                Bullet.recorder.setDeadTank(Integer.parseInt(num));
                g.drawString(num,1100,115);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //画坦克

    /**
     *
     * @param x               坦克左上角x坐标
     * @param y               坦克左上角y坐标
     * @param graphics        画笔
     * @param type            坦克类型
     * @param direct          坦克方向(上下左右）
     */
    public void drawTank(int x,int y,Graphics graphics,int type,int direct){
        switch (type){
            case 0:
                graphics.setColor(Color.cyan);
                break;
            case 1:
                graphics.setColor(Color.YELLOW);
                break;
        }
        switch (direct){
            case 0:
                //上
                graphics.fill3DRect(x,y,10,60,false);
                graphics.fill3DRect(x+10, y+10,20,40,false);
                graphics.fill3DRect(x+30,y,10,60,false);
                graphics.fillOval(x+10,y+20,20,20);
                graphics.drawLine(x+20,y,x+20,y+30);
                break;
            case 1:
                //下
                graphics.fill3DRect(x,y,10,60,false);
                graphics.fill3DRect(x+10, y+10,20,40,false);
                graphics.fill3DRect(x+30,y,10,60,false);
                graphics.fillOval(x+10,y+20,20,20);
                graphics.drawLine(x+20,y+60,x+20,y+30);
                break;
            case 2:
                //左
                graphics.fill3DRect(x,y,60,10,false);
                graphics.fill3DRect(x+10,y+10,40,20,false);
                graphics.fill3DRect(x,y+30,60,10,false);
                graphics.fillOval(x+20,y+10,20,20);
                graphics.drawLine(x,y+20,x+30,y+20);
                break;
            case 3:
                //右
                graphics.fill3DRect(x,y,60,10,false);
                graphics.fill3DRect(x+10,y+10,40,20,false);
                graphics.fill3DRect(x,y+30,60,10,false);
                graphics.fillOval(x+20,y+10,20,20);
                graphics.drawLine(x+60,y+20,x+30,y+20);
                break;
            default:
                System.out.println("暂时不做处理");
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {

        if(!hero.isHit()) {
            int preX = hero.getX();
            int preY = hero.getY();
            int preDirection = hero.getDirection();
            if (e.getKeyCode() == KeyEvent.VK_W) {
                hero.setDirection(0);
                    hero.moveUp();
            } else if (e.getKeyCode() == KeyEvent.VK_S) {
                hero.setDirection(1);
                    hero.moveDown();
            } else if (e.getKeyCode() == KeyEvent.VK_A) {
                hero.setDirection(2);
                    hero.moveLeft();
            } else if (e.getKeyCode() == KeyEvent.VK_D) {
                hero.setDirection(3);
                    hero.moveRight();
            }
            if(!canMove() || hero.getX() < 0 || hero.getX() > 940 || hero.getY() < 0 || hero.getY() > 690){
                hero.setX(preX);
                hero.setY(preY);
                hero.setDirection(preDirection);
            }
            if (e.getKeyCode() == KeyEvent.VK_G) {
                Bullet bullet = new Bullet(hero);
                bullet.setSize(0);
                Thread thread = new Thread(bullet);
                thread.start();
                synchronized (bullets) {
                    bullets.add(bullet);
                }
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {}
    public boolean canMove(){
     Rectangle rectangle = null;
     rectangleHero = newRectangle(hero);

        for (Enemy enemy :enemies) {
            rectangle = newRectangle(enemy);

         if(rectangle.intersects(rectangleHero)){
             return false;
         }
        }
        return true;
    }
    //Java是值传递，而不是引用传递，rectangle传进来只是局部变量，不会影响外面的变量
    public static Rectangle newRectangle(Tank tank ){
        if(tank.getDirection()==0||tank.getDirection()==1){
           return new Rectangle(tank.getX(), tank.getY(),40,60);
        }
        if(tank.getDirection()==2|| tank.getDirection()==3){
            return new Rectangle(tank.getX(),tank.getY(),60,40);
        }
        return null;
    }
}