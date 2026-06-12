package com.nanxinda.alorithm.sort.radixsort;

import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {
        int[] array = {53,3,542,748,14,214};
        Radix radix = new Radix(array);
        radix.sort();
        System.out.println(Arrays.toString(array));
    }

}
class Radix{
    private int[][] bucket;
    private int[] bucketElementCounts;
    private int[] array;
    private int max;
    private int count = 0;
    public Radix(int[] array){
        this.array = array;
        bucket = new int[10][array.length];
        bucketElementCounts = new int[10];
        for (int i = 0; i < array.length; i++) {
            int count = 1;
            int element = array[i];
            while ((element = element/10)!=0){
                count++;
            }
            /// 可以用(array[i]+"").length()表示当前元素的位数
            if(count>max){
                max = count;
            }
        }
    }
    public void sort(){
        if(count==max){
            return;
        }
        int digitOfElement = 0;
        for (int i : array) {
            digitOfElement = i/(int)Math.pow(10,count)%10;
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = i;
            bucketElementCounts[digitOfElement]++;
        }
        int index=0;
        for (int i = 0; i < bucket.length; i++) {
            if(bucketElementCounts[i]!=0){
                for (int j = 0; j < bucketElementCounts[i]; j++) {
                    array[index++] = bucket[i][j];
                    bucket[i][j] = 0;
                }
                bucketElementCounts[i] = 0;
            }
        }
        count++;
        sort();
    }

}
