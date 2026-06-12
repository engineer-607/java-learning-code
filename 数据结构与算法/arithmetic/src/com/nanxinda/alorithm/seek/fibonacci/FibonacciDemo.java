package com.nanxinda.alorithm.seek.fibonacci;

import java.util.Arrays;

public class FibonacciDemo {
    public static void main(String[] args) {
        int[] array = {1,8,10,89,1000,1234};
        Fibonacci fibonacci = new Fibonacci(array);
        System.out.println(fibonacci.seek(1000,0,array.length-1));

    }
}
class Fibonacci{
    private int maxSize;
    private int[] fibo ;
    private int[] seekArray;
    private int[] copyArray;
    private int k = 0;
    public Fibonacci(int[] seekArray){
        this.seekArray = seekArray;
        this.maxSize = 20;
        setFibo();
        while (seekArray.length>fibo[k]-1){
            k++;
        }
        copyArray = Arrays.copyOf(seekArray,fibo[k]-1);
        for (int i = seekArray.length-1; i < copyArray.length; i++) {
            copyArray[i] = seekArray[seekArray.length -1];
        }
    }
    private void setFibo(){
        fibo = new int[maxSize];
        fibo[0] = 0;
        fibo[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            fibo[i] = fibo[i-1]+fibo[i-2];
        }
    }
    public int seek(int n,int left,int right){
        if(left>right){
            return -1;
        }
        int mid =left+ fibo[k-1] -1;
        if(n>copyArray[mid]){
            k-=2;
            return seek(n,mid+1,right);
        }else if(n<copyArray[mid]){
            k--;
            return seek(n,left,mid-1);
        }else {
            if(mid>seekArray.length -1){
                mid = seekArray.length -1;
            }
            return mid;
        }
    }

}
