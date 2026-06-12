package com.nanxinda.IO;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class FileInformation {
    public static void main(String[] args) {

    }
    //获取文件的信息
    @Test
    public void info(){
        File file = new File("d:\\news3.txt");
        try {
            file.createNewFile();
            System.out.println("文件创建成功");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("文件名字="+file.getName());
        System.out.println("文件的绝对路径="+file.getAbsolutePath());
        System.out.println("文件父级目录="+file.getParent());
        System.out.println("文件大小（字节）="+file.length());
        System.out.println("文件是否存在="+file.exists());//T
        System.out.println("是不是一个文件="+file.isFile());//T
        System.out.println("是不是一个目录="+file.isDirectory());//F


    }
}
