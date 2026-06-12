package com.nanxinda.filereaderANDfilewriter;

import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;

public class FileReaderCase {
    public static void main(String[] args) {
        /*
        FileReader相关方法：
        1）new FileReader(File/String)
        2）read:每次读取单个字符，返回该字符，如果到文件末尾返回-1
        3）read(char[])：批量读取多个字符到数组，返回读取到的字符数，如果到文件末尾返回-1
        相关api:
        1）new String(char[]):将char[]转换为String
        2）new String(char[],off,len):将char[]的指定部分转换为String
         */
        String filePath = "d:\\news3.txt";
        int data = 0;
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(filePath);
            while ((data = fileReader.read())!=-1){
                System.out.print((char) data);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void readFile01(){
        String filePath = "d:\\news3.txt";
        int dataLen = 0;
        char[] chars = new char[8];
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(filePath);
            while ((dataLen = fileReader.read(chars))!=-1){
                System.out.print(new String(chars,0,dataLen));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
