package com.nanxinda.bufferedwriterANDbufferedreader;

import java.io.*;

public class BufferedCase {
    public static void main(String[] args) throws IOException {
        /*
        节点流：节点可以从一个特定的数据源（数据源就是存放数据的地方，例如文件、数组、管道、字符串）
        读写数据，如FileReader、FileWriter
        处理流（包装流）是“连接”在已存在的流（节点流或处理流）之上，为程序提供
        更强大的读写功能，如BufferedReader、BufferedWriter
        BufferedReader类中，有属性Reader，即可以封装一个节点流（只要是Reader的子类）

        节点流和处理流的区别和联系
        1.节点流是底层流/低级流，直接跟数据源相接
        2.处理流（包装流）包装节点流，既可以消除不同
        3.处理流对节点流进行包装，使用修饰器设计模式，不会直接与数据源相连

        BufferedReader和BufferedWriter属于字符流，是按照字符来读取数据的
        关闭处理流，只需要关闭外层流即可
    public void close() throws IOException {
        synchronized (lock) {
            if (in == null)
                return;
            try {
                in.close();
            } finally {
                in = null;
                cb = null;
            }
        }
    }
         */
        BufferedReader_ bufferedReader = new BufferedReader_(new FileReader_());
        bufferedReader.readFiles(10);


        String filePath = "d:\\news3.txt";
        String line = null;
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        while ((line = reader.readLine())!=null){
            System.out.println(line);
        }
        reader.close();


        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath,true));
        bufferedWriter.write("hello,树先生");
        bufferedWriter.newLine();
        bufferedWriter.write("士兵突击");
        bufferedWriter.close();
    }
}
