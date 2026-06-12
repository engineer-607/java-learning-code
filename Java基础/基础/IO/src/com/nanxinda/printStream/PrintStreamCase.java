package com.nanxinda.printStream;

import java.io.IOException;
import java.io.PrintStream;

public class PrintStreamCase {
    public static void main(String[] args) throws IOException {
        /// 字节打印流
        PrintStream out = System.out;
        //在默认情况下，PrintStream输出数据的位置是标准输出，即显示器
        //    public void print(String s) {
        //        if (s == null) {
        //            s = "null";
        //        }
        //        write(s);
        //    }
        out.print("hello，树先生");
        //print的底层使用的write方法，所以也可以直接调用write方法进行打印
        out.write("hello".getBytes());
        //可以修改打印流输出的位置/设备
        System.setOut(new PrintStream("d:\\news3.txt"));
        System.out.println("hello,world");
        out.close();
    }
}
