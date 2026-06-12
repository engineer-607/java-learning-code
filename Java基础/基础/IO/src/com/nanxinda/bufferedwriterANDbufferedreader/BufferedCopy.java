package com.nanxinda.bufferedwriterANDbufferedreader;

import java.io.*;

public class BufferedCopy {
    /// 使用Writer的子类，在传入文件路径时需要注意:
    /// 文件不存在，但父目录存在→ 自动创建
    ///父目录不存在→ 报错（FileNotFoundException）
    public static void main(String[] args) {
        String srcFilePath = "d:\\news3.txt";
        String destFilePath = "d:\\news2.txt";
        String line = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        //BufferedReader和BufferedWriter是按照字符操作
        //不要去操作二进制文件（声音、视频、doc、pdf），可能会造成文件损坏
        try {
            bufferedReader =  new BufferedReader(new FileReader(srcFilePath));
            bufferedWriter = new BufferedWriter(new FileWriter(destFilePath));
            while ((line = bufferedReader.readLine())!=null){
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                bufferedReader.close();
                bufferedWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
