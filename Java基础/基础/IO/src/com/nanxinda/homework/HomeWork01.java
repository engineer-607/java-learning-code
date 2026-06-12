package com.nanxinda.homework;

import java.io.File;
import java.io.IOException;
@SuppressWarnings({"all"})
public class HomeWork01 {
    public static void main(String[] args) throws IOException {
        String directoryPath = "d:\\mytemp";
        String filePath = "hello.txt";
        File file = new File(directoryPath);
        File file1 = new File(directoryPath,filePath);
        if(file.exists()){
            System.out.println("目录已创建");
            if(file1.exists()){
                System.out.println("文件已创建");
            }else {
                file1.createNewFile();
            }
        }else {
            if(file.mkdir()){
                System.out.println("目录创建成功");
            }else {
                System.out.println("目录创建失败");
            }
            if(file1.createNewFile()){
                System.out.println("文件创建成功");
            }else {
                System.out.println("文件创建失败");
            }
        }
    }
}
