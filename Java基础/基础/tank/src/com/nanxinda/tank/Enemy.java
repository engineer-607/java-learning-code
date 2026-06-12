package com.nanxinda.tank;

import java.awt.*;
import java.io.Serializable;
import java.util.Vector;
@SuppressWarnings({"all"})
public class Enemy extends Tank implements Runnable, Serializable {
    static Vector<Bullet> bullets;

    public Enemy(int x, int y) {
        super(x, y);
        int direction = (int) (Math.random() * 4);
        super.setDirection(direction);
//        Thread thread = new Thread(this);
//        thread.start();
    }


    @Override
    public void run() {
        while (!isHit()) {
            if (isHit()) {
                break;
            }
            if (!isHit() && Math.random() < 0.05) {
                Bullet bullet = new Bullet(this);
                bullet.setSize(1);
                Thread thread = new Thread(bullet);
                bullets.add(bullet);
                thread.start();
            }
            if (this.judgeMove()) {
                switch (this.getDirection()) {
                    case 0:
                        this.moveUp();
                        break;
                    case 1:
                        this.moveDown();
                        break;
                    case 2:
                        this.moveLeft();
                        break;
                    case 3:
                        this.moveRight();
                        break;
                }
            }else{
                boolean findDirection = false;
                for (int i = 0; i < 4; i++) {
                    this.setDirection(i);
                    if(judgeMove()){
                        findDirection = true;
                        break;
                    }
                }
                if(!findDirection){
                    this.setDirection((int) (Math.random() * 4));
                }
            }
                if (isHit()) {
                    break;
                }
                if (Math.random() < 0.02) {
                    int direction = (int) (Math.random() * 4);
                    if(judgeTurn(direction)) {
                        this.setDirection(direction);
                    }
                }
                if (this.getX() < 0) {
                    this.setX(0);
                }
                if (this.getX() > 940) {
                    this.setX(940);
                }
                if (this.getY() < 0) {
                    this.setY(0);
                }
                if (this.getY() > 690) {
                    this.setY(690);
                }

                try {
                    Thread.sleep(50);
                    //休眠时间应该短一些，这样才能做到平滑移动
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }



    }
    public boolean judgeMove () {
        Vector<Enemy> enemies = Bullet.enemies;
        Hero hero = Bullet.hero;
        Rectangle rectangleHero = null;
        if(!hero.isHit()) {
            rectangleHero = Panel.newRectangle(hero);
        }
        int newX = this.getX();
        int newY = this.getY();
        switch (this.getDirection()){
            case 0:newY--;break;
            case 1:newY++;break;
            case 2:newX--;break;
            case 3:newX++;break;
        }
        Rectangle rectangle = null;
        if(this.getDirection()==0||this.getDirection()==1){
            rectangle = new Rectangle(newX,newY,40,60);
        }else {
            rectangle = new Rectangle(newX,newY,60,40);
        }
        Rectangle rectangle1 = null;
synchronized (Bullet.enemies) {
    for (Enemy enemy : enemies) {
        rectangle1 = Panel.newRectangle(enemy);
        if(rectangleHero!=null&&rectangleHero.intersects(rectangle)){
            return false;
        }
        if (enemy == this) {
           continue;
        }
                if (rectangle1.intersects(rectangle)) {
                    return false;
                }
    }
}
        return true;


    }
    public boolean judgeTurn(int direction){
        Vector<Enemy> enemies = Bullet.enemies;
        Hero hero = Bullet.hero;
        Rectangle rectangle = null;
        Rectangle rectangleHero = null;
        rectangle = Panel.newRectangle(this);

        synchronized (enemies) {
            for (Enemy enemy : enemies) {
                Rectangle rectangle1 = null;
                if (enemy != this) {
                    rectangle1 = Panel.newRectangle(enemy);

                    if (rectangle1.intersects(rectangle)) {
                        return false;
                    }
                }
            }
        }
        if(!hero.isHit()) {
            rectangleHero = Panel.newRectangle(hero);

        }
        if(rectangleHero!=null&&rectangle.intersects(rectangleHero)){
            return false;
        }
        return true;


    }

}

