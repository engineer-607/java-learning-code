package com.nanxinda.TCPsocket;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

@SuppressWarnings({"all"})
public class Socket04Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(InetAddress.getLocalHost(),9999);
        String s = "D:\\photo\\头像.jpg";
        byte[] bytes = new byte[1024];
        int readLen = 0;
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(s));
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
        while ((readLen = bufferedInputStream.read(bytes))!=-1){
            bufferedOutputStream.write(bytes,0,readLen);
        }
        socket.shutdownOutput();
//        bufferedOutputStream.close();bufferedOutputStream会在socket关闭输出流时自动关闭

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String string = bufferedReader.readLine();
        System.out.println(string);
        //关闭所有流
        bufferedReader.close();
        bufferedInputStream.close();
        socket.close();

    }
}
