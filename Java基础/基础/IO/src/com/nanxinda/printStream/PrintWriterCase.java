package com.nanxinda.printStream;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PrintWriterCase {
    public static void main(String[] args) throws IOException {
        //改变打印输出的设备
        PrintWriter printWriter = new PrintWriter(new FileWriter("d:\\news3.txt"));
        printWriter.print("hi,北京你好~~~");
        printWriter.close();//flush + 关闭流，才会将数据写入到文件
    }
}
