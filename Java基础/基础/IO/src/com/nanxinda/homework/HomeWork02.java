package com.nanxinda.homework;

import java.io.*;

@SuppressWarnings({"all"})
public class HomeWork02 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("d:\\news3.txt")));
        int count = 0;
        String readLine = null;
        while ((readLine = bufferedReader.readLine())!=null){
            count++;
            System.out.println(count+" "+readLine);
        }
        bufferedReader.close();

    }
}
