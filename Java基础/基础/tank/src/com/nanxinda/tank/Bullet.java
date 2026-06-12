package com.nanxinda.tank;

import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;
@SuppressWarnings({"all"})
public class Bullet implements Runnable{
    private int speed = 3;
    private  int x;
    private  int y;
    private int direction;
    private boolean loop = true;//决定子弹什么时候消失
    private int size;//决定我军还是敌军的子弹，0表示我军，1表示敌军
    static Hero hero;
    static Vector<Enemy> enemies;
    static Vector<Bomb> bombs;
    static Recorder recorder = new Recorder();
    public Bullet(Tank tank){
        direction = tank.getDirection();
            if(direction == 0){
                x = tank.getX()+20;
                y = tank.getY();
            }else  if(direction == 1){
                x = tank.getX()+20;
                y = tank.getY()+60;
            }else if(direction == 2){
                x = tank.getX();
                y = tank.getY()+20;
            }else if(direction == 3){
                x = tank.getX() + 60;
                y = tank.getY()+ 20;
            }
    }
    @Override
    public void run() {
        while (loop){
//            if(direction == 0){
//                y=y-speed;
//            }else if(direction == 1){
//                y=y+speed;
//            }else if(direction == 2){
//                x=x-speed;
//            }else if(direction == 3){
//                x=x+speed;
//            }
            //面对进行单一变量数值判断可以使用switch
            switch (direction){
                case 0:
                    y=y-speed;
                    break;
                case 1:
                    y=y+speed;
                    break;
                case 2:
                    x=x-speed;
                    break;
                case 3:
                    x=x+speed;
                    break;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            judgeShot();
        }

    }
    public void judgeShot() {
        if(this.size == 0){
            synchronized (enemies) {
                Iterator<Enemy> iterator = enemies.iterator();
                while (iterator.hasNext()) {
                    Enemy enemy = iterator.next();
                    switch (enemy.getDirection()) {
                        case 0:
                        case 1:
                            if (this.getX() > enemy.getX() && this.getX() < (enemy.getX() + 40) && this.getY() > enemy.getY() && this.getY() < (enemy.getY() + 60)) {
                                loop = false;
                                enemy.setHit(true);
                                iterator.remove();
                                recorder.setDeadTank(recorder.getDeadTank()+1);
                                try {
                                    recorder.record();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                synchronized (bombs) {
                                    Bomb bomb = new Bomb(enemy.getX(), enemy.getY());
                                    bombs.add(bomb);
                                }
                            }
                            break;
                        case 2:
                        case 3:
                            if (this.getX() > enemy.getX() && this.getX() < (enemy.getX() + 60) && this.getY() > enemy.getY() && this.getY() < (enemy.getY() + 40)) {
                                loop = false;
                                enemy.setHit(true);
                                iterator.remove();
                                recorder.setDeadTank(recorder.getDeadTank()+1);
                                try {
                                    recorder.record();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                synchronized (bombs) {
                                    Bomb bomb = new Bomb(enemy.getX(), enemy.getY());
                                    bombs.add(bomb);
                                }
                            }
                            break;
                    }
                }
            }
        }else if(this.size == 1){
            synchronized (enemies) {
                for (Enemy enemy : enemies) {
                    switch (enemy.getDirection()) {
                        case 0:
                        case 1:
                            if (this.getX() > enemy.getX() && this.getX() < (enemy.getX() + 40) && this.getY() > enemy.getY() && this.getY() < (enemy.getY() + 60)) {
                                loop = false;
                            }
                            break;
                        case 2:
                        case 3:
                            if (this.getX() > enemy.getX() && this.getX() < (enemy.getX() + 60) && this.getY() > enemy.getY() && this.getY() < (enemy.getY() + 40)) {
                                loop = false;
                            }
                            break;
                    }
                }
            }
            switch (hero.getDirection()){
                case 0:
                case 1:
                    if(!hero.isHit()) {
                        if (this.getX() > hero.getX() && this.getX() < (hero.getX() + 40) && this.getY() > hero.getY() && this.getY() < (hero.getY() + 60)) {
                            loop = false;
                            hero.setHit(true);
                            synchronized (bombs) {
                                Bomb bomb = new Bomb(hero.getX(), hero.getY());
                                bombs.add(bomb);
                            }
                        }
                    }
                      break;
                case 2:
                case 3:
                    if(!hero.isHit()) {
                        if (this.getX() > hero.getX() && this.getX() < (hero.getX() + 60) && this.getY() > hero.getY() && this.getY() < (hero.getY() + 40)) {
                            loop = false;
                            hero.setHit(true);
                            synchronized (bombs) {
                                Bomb bomb = new Bomb(hero.getX(), hero.getY());
                                bombs.add(bomb);
                            }
                        }
                    }
                    break;
            }

        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isLoop() {
        return loop;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
