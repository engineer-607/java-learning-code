package com.nanxinda.IO;

import org.junit.Test;

import java.io.File;

public class Directory_ {
    public static void main(String[] args) {
        /*
        目录的操作和文件删除
        mkdir创建一级目录，mkdirs创建多级目录、delete删除空目录（如果该
        目录下有子目录或者文件需要先清除才能删除该空目录）或文件
         */
    }
    //判断d:\\news1.txt 是否存在，如果存在就删除
    @Test
    public void m1(){
        String filePath = "d:\\news1.txt";
        File file = new File(filePath);
        if(file.exists()){
            if(file.delete()){
                System.out.println(filePath+"删除成功");
            }else {
                System.out.println(filePath+"删除失败");
            }
        }else {
            System.out.println("该文件不存在...");
        }
    }
    //判断d:\\cxdownload是否存在，存在就删除，否则提示不存在
    //在java编程中，目录被当作文件
    @Test
    public void m2(){
        String directoryPath = "d:\\cxdownload";
        File file = new File(directoryPath);
        if(file.exists()){
            if(file.delete()){
                System.out.println(directoryPath+"删除成功");
            }else {
                System.out.println(directoryPath+"删除失败");
            }
        }else {
            System.out.println("该目录不存在...");
        }
    }
    //判断d:\\demo\\a\\b\\c目录是否已经存在，如果已经存在，则提示存在，否则就创建
    @Test
    public void m3(){
        String directoryPath = "d:\\demo\\a\\b\\c";
        File file = new File(directoryPath);
        if(file.exists()){
            System.out.println(directoryPath+"已经存在");
        }else {
            if(file.mkdirs()){//创建多级目录用mkdirs
                System.out.println("创建该目录");
            }else {
                System.out.println("目录创建失败");
            }
        }
    }
}
