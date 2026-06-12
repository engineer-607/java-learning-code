package com.nanxinda.IO;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
@SuppressWarnings({"all"})
public class FileDetail {
    public static void main(String[] args) {
        /*
        文件：保存数据的地方，文件在程序中以流的形式来操作
        流：数据在数据源（文件）和程序（内存）之间经历的路径
        输入流：数据从数据源（文件）到程序（内存）的路径
        输出流：数据从程序（内存）到数据源（文件）的路径
        * Java程序与文件（磁盘）之间的数据流动示意图：
 *
 *        (内存)                              (磁盘)
 *   +-------------+                      +-------------+
 *   |             |                      |             |
 *   |   Java程序  |  <--输入流--- 读取      |     文件    |
 *   |             |                      |             |
 *   |             |  ---输出流--> 写入      |             |
 *   +-------------+                      +-------------+
 *
         */
    }

    //创建文件对象相关构造器和方法
    //方式一：new File(String pathname)
    //根据路径构建一个File对象
    @Test
    public void create01() {
        String filePath = "d:\\news1.txt";
        File file = new File(filePath);
        //file对象在java程序中，只是一个对象
        //只有执行createNewFile方法，才会真正的创建该文件
        try {
            file.createNewFile();
            System.out.println("文件创建成功");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //方式二：new File(File parent,String child)
    //根据父目录文件+子路径构建
    @Test
    public void create02(){
        String parentFile = "d:\\";
        String childFile = "news2.txt";
        File file = new File(parentFile,childFile);
        try {
            file.createNewFile();
            System.out.println("文件创建成功");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void create03(){
        String parentPath = "d:\\";
        String filePath = "news3.txt";
        File file = new File(parentPath, filePath);
        try {
            file.createNewFile();
            System.out.println("文件创建成功");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}