package com.nanxinda.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

@SuppressWarnings({"all"})
public class UDPsenderB {
    public static void main(String[] args) throws IOException {
        //1.创建DatagramSocket对象，准备在9998端口接收数据
        DatagramSocket socket = new DatagramSocket(9998);
        //2.将需要发送的数据，封装到DatagramPacket对象
        byte[] bytes = "hello，明天去吃火锅".getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(
                /*数据内容*/bytes,/*数据长度*/bytes.length,/*IP地址*/InetAddress.getByName("127.0.0.1"),/*端口*/9999);
        socket.send(datagramPacket);
        socket.close();
    }
}
