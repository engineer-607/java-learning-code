package com.nanxinda.TCPsocket;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

@SuppressWarnings("all")
public class Socket03Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(InetAddress.getLocalHost(),9999);
        Writer writer = new OutputStreamWriter(socket.getOutputStream());
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        bufferedWriter.write("hello,server");
        bufferedWriter.newLine();///插入一个换行符，表示写入内容的结束（要求对方使用readLine()）
        bufferedWriter.flush();///如果使用的是字符流，需要手动刷新，否则不会写入数据
        Reader reader = new InputStreamReader(socket.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(reader);
        String s = bufferedReader.readLine();
            System.out.println(s);
        bufferedWriter.close();
        bufferedReader.close();
        socket.close();

    }
}
