package com.nanxinda.homework;

import java.io.IOException;
import java.net.*;

@SuppressWarnings({"all"})
public class HomeWork02Receiver {
    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket(8888);
        byte[] bytes = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(bytes,bytes.length);
        datagramSocket.receive(datagramPacket);
        byte[] buf = datagramPacket.getData();
        int length = datagramPacket.getLength();
        String question = new String(buf,0,length);
        String answer = null;
        System.out.println(question);
        if(question.equals("四大名著有哪些")){
            answer = "《红楼梦》《西游记》《水浒传》《三国演义》";
        }else {
            answer = "what?";
        }
        byte[] bytes1 = answer.getBytes();
        DatagramPacket datagramPacket1 = new DatagramPacket(bytes1,bytes1.length, InetAddress.getLocalHost(),9999);
        datagramSocket.send(datagramPacket1);
        datagramSocket.close();

    }
}
