package com.nanxinda.TCPsocket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

@SuppressWarnings({"all"})
public class Socket01Client {
    public static void main(String[] args) throws IOException {
        //思路
        //1.连接服务端（ip，端口）
        //连接本机的9999端口，如果连接成功，返回Socket对象
        Socket socket = new Socket(InetAddress.getLocalHost(),9999);
        System.out.println("客户端 socket返回="+socket.getClass());
        //2.连接上后，生成Socket，通过Socket.getOutputStream()
        //  得到和socket对象关联的输出流对象
        OutputStream outputStream = socket.getOutputStream();
        //3.通过输出流，将数据写到数据通道
        outputStream.write("hello,server".getBytes());
        //4.关闭对象和流
        outputStream.close();
        socket.close();
    }
}
