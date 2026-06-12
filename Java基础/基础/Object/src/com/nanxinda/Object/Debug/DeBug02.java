package com.nanxinda.Object.Debug;

import java.util.Arrays;

public class DeBug02 {
    //利用debug追源码
    public static void main(String[] args) {
        int[] arr={1,-1,10,-20,100};
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+"\t");
            //快捷键；f8执行下一行代码
            //快捷键：f7跳入方法
        }//快捷键：shift+f8跳出方法
        //快捷键：f9直接跳到下一个断点，可以动态加断点
    }
}
