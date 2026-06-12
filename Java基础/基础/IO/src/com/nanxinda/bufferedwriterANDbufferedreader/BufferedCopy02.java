package com.nanxinda.bufferedwriterANDbufferedreader;

import java.io.*;

public class BufferedCopy02 {
    public static void main(String[] args) {
        String srcFilePath = "d:\\photo\\头像.jpg";
        String destFilePath = "d:\\photo\\头像2.jpg";
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        int readLen;
        byte[] bytes = new byte[1024];
        try {
            bufferedInputStream =new BufferedInputStream(new FileInputStream(srcFilePath));
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(destFilePath));
            while ((readLen=bufferedInputStream.read(bytes))!=-1){
                bufferedOutputStream.write(bytes,0,readLen);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                bufferedInputStream.close();
                bufferedOutputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
