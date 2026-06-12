package com.nanxinda.outputstream;

import org.junit.Test;

import javax.imageio.IIOException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class OutputStreamCase {
    public static void main(String[] args) {

    }
    @Test
    public void writeFile(){
        String filePath = "d:\\news3.txt";
        FileOutputStream stream = null;
        try {
            //new FileOutputStream(filePath)创建方式，当写入内容时，会覆盖原来的内容
            stream = new FileOutputStream(filePath);
            //new FileOutputStream(filePath,true)创建方式，当写入内容时，会追加到文件后面

            //写入一个字节
//            stream.write('H');
            //写入字符串
            String s = "hsp,world";
            //s.getBytes()可以把字符串->字节数组
//            stream.write(s.getBytes());
            //write(byte[],int off,int len),off是起始位置，len是写入文件的字节长度
            stream.write(s.getBytes(),0,3);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
