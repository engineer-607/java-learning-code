package com.nanxinda.UDP;

public class UDP {
    /*
    UDP网络通信编程
    1.类DatagramSocket和DatagramPacket实现了基于UDP协议网络程序
    2.UDP数据报通过数据报套接字DatagramSocket发送和接收，系统不保证UDP
    数据报一定能够安全送到目的地，也不能确定什么时候可以抵达
    3.DatagramPacket对象封装UDP数据报，在数据报中包含发送端和IP地址和端口号
    以及接收端的IP地址和端口号
    4.UDP协议中每个数据报都给出完整地址信息，因此无需建立发送给方和接收方的连接
     */
    /**
     * UDP 通信示意图（发送端和接收端）
     *
     * 发送端：                           接收端：
     * ┌─────────────────┐               ┌─────────────────┐
     * │  DatagramSocket │               │  DatagramSocket │
     * │  （发送端口）    │               │  （接收端口）    │
     * └────────┬────────┘               └────────┬────────┘
     *          │                                  │
     *          │  send()                          │  receive()
     *          ↓                                  ↓
     * ┌─────────────────┐               ┌─────────────────┐
     * │ DatagramPacket  │───────────────│ DatagramPacket  │
     * │ [数据]          │   网络传输     │ [数据]          │
     * │ [InetAddress]   │    ═══════►   │ [InetAddress]   │
     * │ [port]          │               │ [port]          │
     * └─────────────────┘               └─────────────────┘
     *          ↑                                  ↑
     *          │ 装包（打包）                      │ 拆包（解析）
     *          │                                  │
     *    应用程序数据                         应用程序数据
     *    "hello"                              "hello"
     */
    /**
     * UDP说明：
     * 1.没有明确的服务端和客户端，演变成数据的发送端和接收端
     * 2.接收数据和发送数据是通过DatagramSocket对象完成
     * 3.将数据封装到DatagramPacket对象，需要进行拆包，取出数据
     * 4.当接收到DatagramPacket对象，需要进行拆包，取出数据
     * 5.DatagramSocket可以指定哪个端口接收数据
     */
}
