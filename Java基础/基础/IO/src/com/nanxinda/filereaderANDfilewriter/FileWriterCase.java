package com.nanxinda.filereaderANDfilewriter;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterCase {
    public static void main(String[] args) {
        /*
        FileWriter常用方法：
        1.new FileWriter(File/String):覆盖模式，相当于流的指针在首端
        2.new FileWriter(File/String):追加模式，相当于流的指针在尾端
        3.write(int):写入单个字符
        4.write(char[]):写入数组
        5.write(char[],off,len):写入指定数组的指定部分
        6.write(String):写入整个字符串
        7.write(String,off,len):写入字符串的指定部分
        相关api：String类：toCharArray：将String转换为char[]
        注意：
        FileWriter使用后，必须要关闭(close)或刷新(flush),否则写入不到指定的文件
         */
        String filePath = "d:\\news3.txt";
        FileWriter fileWriter= null;
        char[] chars = {'a','b','c','d','e','f'};
        try {
            fileWriter = new FileWriter(filePath);//默认时覆盖写入
            //1)write(char)写入单个字符
            fileWriter.write('H');
            //2)write(char[])写入指定数组
            fileWriter.write(chars);
            //3)write(char[],off,len):写入指定数组的指定部分
            fileWriter.write("南京信息工程大学".toCharArray(),0,8);
            //4)write(String):写入整个字符串
            fileWriter.write("你好北京");
            //5)write(String,off,len):写入字符串的指定部分
            fileWriter.write("上海外国语学校",0,2);



        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                //FileWriter一定要关闭流，或者flush才能真正的把数据写入文件
                //close和flush中才有真正将数据写入文件的代码
                fileWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
