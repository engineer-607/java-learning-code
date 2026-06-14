package com.nanxinda.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressCase {
    public static void main(String[] args) throws UnknownHostException {
        //InetAddress类常用方法：
        //1.获取本机InetAddress对象 getLocalHost
        InetAddress inetAddress = InetAddress.getLocalHost();//localhost/127.0.0.1
        System.out.println(inetAddress);
        //2.根据指定主机名，获取InetAddress对象
        InetAddress host = InetAddress.getByName("localhost");
        System.out.println("host="+host);
        //3.根据域名返回InetAddress对象，比如www.baidu.com对应
        InetAddress host2 = InetAddress.getByName("www.baidu.com");
        System.out.println("host2="+host2);
        //4.通过InetAddress对象，获取对应的地址
        String hostAddress = host2.getHostAddress();
        System.out.println("host2对应的ip="+hostAddress);
        //5.通过InetAddress对象，获取对应的主机名/域名
        String localName = host2.getHostName();
        System.out.println("host2对应的主机名/域名="+localName);
    }
}
