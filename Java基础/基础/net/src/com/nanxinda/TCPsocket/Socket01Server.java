package com.nanxinda.TCPsocket;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

@SuppressWarnings({"all"})
public class Socket01Server {
    public static void main(String[] args) throws IOException {
        //1.在本机的9999端口监听，等待连接
        //细节：要求在本机没有其他服务在监听9999
        //细节：ServeSocket可以通过accept()返回多个Socket[多个客户端连接服务器的并发]
        ServerSocket socket = new ServerSocket(9999);
        System.out.println("服务器在9999端口监听，等待连接");
        //2.当没有客户端连接9999端口时，程序会阻塞，等待连接
        //如果有客户端连接，则会返回Socket对象，程序继续
        Socket socket1 = socket.accept();
        System.out.println("socket="+socket1.getClass());
        //3.通过socket.getInputStream()获取客户端
        InputStream inputStream = socket1.getInputStream();
        //4.通过IO流
        byte[] bytes = new byte[1024];
        int readLen = 0;
        while ((readLen = inputStream.read(bytes))!=-1){
            System.out.println(new String(bytes,0,readLen));
        }
        //关闭流和socket
        inputStream.close();
        socket1.close();
        socket.close();



    }
}
