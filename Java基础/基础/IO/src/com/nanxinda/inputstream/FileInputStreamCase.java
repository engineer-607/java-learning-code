package com.nanxinda.inputstream;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;

public class FileInputStreamCase {
    public static void main(String[] args) {

    }
    @Test
    public void readFile01(){
        String filePath = "d:\\news3.txt";
        int readData = 0;
        FileInputStream fileInputStream = null;
        try {
            //创建一个FileInputStream类的对象，用于读取文件
             fileInputStream = new FileInputStream(filePath);
             //从该输入流读取一个字节的数据，如果没有输入可用，该方法将被阻止
            //如果达到文件的末尾，则返回-1
            while ((readData = fileInputStream.read())!=-1){
                System.out.print((char) readData);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Test
    public void readFile02(){
        String filePath = "d:\\news3.txt";
        FileInputStream fileInputStream = null;
        byte[] bytes = new byte[8];//一次读取八个字节
        int byteLen = 0;
        try {
            //创建一个FileInputStream类的对象，用于读取文件
            fileInputStream = new FileInputStream(filePath);
            //从该输入流读取最多bytes.length个字节的数据
            //如果返回-1，表示读取完毕
            //如果读取正常，返回实际读取的字节数
            while ((byteLen = fileInputStream.read(bytes))!=-1){
                System.out.print(new String(bytes,0,byteLen));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
