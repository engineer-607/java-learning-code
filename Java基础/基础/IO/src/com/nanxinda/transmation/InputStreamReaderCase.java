package com.nanxinda.transmation;

import com.nanxinda.bufferedwriterANDbufferedreader.Reader_;

import java.io.*;
@SuppressWarnings({"all"})
public class InputStreamReaderCase {
    public static void main(String[] args) throws IOException {
        //转换流可以将字节流转成字符流
        //构造器：public InputStreamReader(InputStream,Charset)（InputStream是Reader的子类）
        //可以传入字节流，指定编码方式，将其翻译成字符流
        String filePath = "d:\\news3.txt";
        //将字节流FileInputStream转化为字符流Reader
        //指定编码utf-8
        Reader reader = new InputStreamReader(new FileInputStream(filePath),"utf-8");
        //再用包装流BufferedReader对字符流FileInputStream进行包装
        BufferedReader bufferedReader = new BufferedReader(reader);
        System.out.println(bufferedReader.readLine());
        bufferedReader.close();
    }
}
