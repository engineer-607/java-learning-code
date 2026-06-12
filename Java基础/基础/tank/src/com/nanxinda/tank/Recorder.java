package com.nanxinda.tank;

import java.io.*;
import java.util.Vector;
@SuppressWarnings({"all"})
public class Recorder {
    private int deadTank = 0;
    public int getDeadTank() {
        return deadTank;
    }
    public void setDeadTank(int deadTank) {
        this.deadTank = deadTank;
    }
    public void record() throws IOException {
        String filePath = "D:\\Java基础\\基础\\tank\\tank.txt";
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath));
        outputStream.writeInt(deadTank);
        outputStream.close();
    }
    public String read() throws IOException {
        String filePath = "D:\\Java基础\\基础\\tank\\tank.txt";
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filePath));
        Integer integer =  objectInputStream.readInt();
        objectInputStream.close();
        return new String(String.valueOf(integer));
    }
    public void recordEnemyTank() throws IOException {
        String filePath = "D:\\Java基础\\基础\\tank\\EnemyTank.txt";
        Vector<Enemy> enemies = Bullet.enemies;
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath));
        for (Enemy enemy :enemies) {
            outputStream.writeObject(enemy);
        }
    }
    public Vector<Enemy> readEnemyTank() throws IOException, ClassNotFoundException {
        String filePath = "D:\\Java基础\\基础\\tank\\EnemyTank.txt";
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath));
        Vector<Enemy> enemies = new Vector<>();
        Enemy enemy = null;

        while (true){
            try {
                enemy = (Enemy) inputStream.readObject();
                enemies.add(enemy);
            }catch (EOFException e){
                break;
            }
        }
        return enemies;
    }
}
