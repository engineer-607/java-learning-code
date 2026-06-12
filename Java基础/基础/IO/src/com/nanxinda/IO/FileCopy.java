package com.nanxinda.IO;

import java.io.*;

public class FileCopy {
    public static void main(String[] args) {
        byte[] bytes = new byte[1024];
        String srcFilePath = "d:\\photo\\头像.jpg";
        String destFilePath = "d:\\photo\\头像2.jpg";
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        int readLen = 0;
        try {
            inputStream = new FileInputStream(srcFilePath);
            outputStream = new FileOutputStream(destFilePath);
            while ((readLen = inputStream.read(bytes))!=-1){
                outputStream.write(bytes,0,readLen);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                outputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
