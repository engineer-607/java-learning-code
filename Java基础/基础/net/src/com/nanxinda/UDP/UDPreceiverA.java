package com.nanxinda.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

@SuppressWarnings({"all"})
public class UDPreceiverA {
    public static void main(String[] args) throws IOException {
        //1.创建一个DatagramSocket对象，准备在9999接收数据
        DatagramSocket socket = new DatagramSocket(9999);
        //2.创建DatagramPacket对象作为接收模板，准备接收数据（数据报最大64K）
        // 此时packet的状态：
        // - data = bytes (引用)
        // - length = 1024 (最大可接收长度)
        // -实际接收长度 = 0 (还没接收数据)
        // - address = null
        // - port = 0
        byte[] bytes = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(bytes,bytes.length);
        //3.调用接收方法，将通过网络传输的DatagramPacket对象填充到datagramPacket对象
        socket.receive(datagramPacket);//阻塞等待，直到接收到数据
        // receive()之后，packet被"填充"了：
        // - 实际接收长度 = packet.getLength()
        // - 发送方IP = packet.getAddress()
        // - 发送方端口 = packet.getPort()
        //4.可以把packet进行拆包，取出数据并显示
        int length = datagramPacket.getLength();//实际接收到的数据字节长度
        byte[] buf = datagramPacket.getData();//接收到数据
        System.out.println(new String(buf,0,length));
        //5.关闭资源
        socket.close();
    }
}
