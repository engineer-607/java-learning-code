package com.nanxinda.IO;

public class InputAndOutput {
    public static void main(String[] args) {
        //System.in  public final static InputStream in = null;
        //System.in 编译类型 InputStream
        //System.in 运行类型 BufferedInputStream
        //表示的时标准输入 键盘
        System.out.println(System.in.getClass());
        //1.System.out  public final static PrintStream out = null;
        //2.编译类型 PrintStream
        //3.运行类型 PrintStream
        //表示标准输出 显示器
        System.out.println(System.out.getClass());
    }
}
