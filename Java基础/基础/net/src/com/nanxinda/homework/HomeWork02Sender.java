package com.nanxinda.homework;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

@SuppressWarnings({"all"})
public class HomeWork02Sender {
    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket(9999);
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        byte[] bytes = s.getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(bytes,bytes.length, InetAddress.getLocalHost(),8888);
        datagramSocket.send(datagramPacket);

        byte[] bytes1 = new byte[1024];
        DatagramPacket datagramPacket1 = new DatagramPacket(bytes1,bytes1.length);
        datagramSocket.receive(datagramPacket1);
        byte[] buf = datagramPacket1.getData();
        int length = datagramPacket1.getLength();
        String string = new String(buf,0,length);
        System.out.println(string);

        datagramSocket.close();

    }
}
