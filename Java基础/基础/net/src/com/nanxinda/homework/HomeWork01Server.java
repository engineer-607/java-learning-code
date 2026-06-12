package com.nanxinda.homework;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

@SuppressWarnings({"all"})
public class HomeWork01Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        Socket socket = serverSocket.accept();
        sendMessage(socket);
        socket.close();
    }
    public static void sendMessage(Socket socket) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String s = bufferedReader.readLine();
        String send = null;
        if(s.equals("name")){
            send = "我是nova";
        } else if (s.equals("hobby")) {
            send = "编写java程序";
        }else {
            send = "你说啥呢";
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bufferedWriter.write(send);
        bufferedWriter.newLine();
        bufferedWriter.flush();
        bufferedWriter.close();
        bufferedReader.close();

    }
}
