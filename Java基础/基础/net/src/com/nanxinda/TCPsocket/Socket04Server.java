package com.nanxinda.TCPsocket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

@SuppressWarnings({"all"})
public class Socket04Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        Socket socket = serverSocket.accept();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(socket.getInputStream());
        String s = "D:\\Java基础\\基础\\net\\src\\头像.jpg";
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(s));
        byte[] bytes = new byte[1024];
        int readLen = 0;
        while ((readLen = bufferedInputStream.read(bytes))!=-1){
            bufferedOutputStream.write(bytes,0,readLen);
        }
        bufferedOutputStream.close();
//        bufferedInputStream.close();
        //关闭bufferedInputStream会关闭底层的socket.getInputStream()即关闭socket的输入流
        //但有些情况下，关闭输入流可能会导致socket处于失效状态，进而后续输出无法正常进行

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bufferedWriter.write("收到图片");
        bufferedWriter.newLine();
        bufferedWriter.flush();
        bufferedWriter.close();
        bufferedInputStream.close();
        socket.close();
        serverSocket.close();


    }
}
