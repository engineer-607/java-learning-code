package com.nanxinda.netstat;

public class Netstat {
    /*
    netstat指令
    1.netstat -an 可以查看当前主机网络情况，包括端口监听情况和网络连接情况
    2.netstat -an | more 可以分页显示
    3.要求dos控制台下执行
     */
    //说明：
    //1.Listening表示某个端口在监听
    //2.如果有一个外部程序（客户端）连接到该端口，就会显示一条连接信息
    //3.可以输入ctrl+c退出当前指令

    /// 当客户端连接到服务器后，实际上客户端也是通过一个端口和服务器进行通讯的，
    /// 这个端口是TCP/IP来分配的（不确定，随机的）
}
