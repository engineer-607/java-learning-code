package com.nanxinda.homework;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

@SuppressWarnings({"all"})
public class HomeWork03Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("客户端在9999端口监听");
        Socket socket = serverSocket.accept();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(socket.getInputStream());
        int readLen = 0;
        String s = null;
        byte[] bytes = new byte[1024];
        while ((readLen = bufferedInputStream.read(bytes))!=-1){
            s=new String(bytes,0,readLen);
        }
        System.out.println(s);
        String filePath;
        if(s.equals("old头像")){
            filePath = "src\\头像.jpg";
        }else {
            filePath = "src\\头像new.jpg";
        }
        System.out.println(s);
        InputStream inputStream = new FileInputStream(filePath);
        OutputStream outputStream = socket.getOutputStream();
        byte[] buf = new byte[1024];
        int readLength = 0;
        while ((readLength = inputStream.read(buf))!=-1){
            outputStream.write(buf,0, readLength);
        }
        outputStream.close();
        inputStream.close();
        bufferedInputStream.close();
        socket.close();
        serverSocket.close();
    }
}
