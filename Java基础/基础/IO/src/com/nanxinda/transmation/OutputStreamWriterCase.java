package com.nanxinda.transmation;

import java.io.*;

@SuppressWarnings({"all"})
public class OutputStreamWriterCase {
    public static void main(String[] args) throws IOException {
        String filePath = "d:\\news3.txt";
        Writer writer = new OutputStreamWriter(new FileOutputStream(filePath,true),"utf-8");
        writer.write("hello,韩顺平");
        writer.close();
    }
}
