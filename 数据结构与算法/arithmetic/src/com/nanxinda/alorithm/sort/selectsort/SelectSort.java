package com.nanxinda.alorithm.sort.selectsort;

public class SelectSort {
    public static void main(String[] args) {
        int[] array = {101,34,115,-1};
        Select select = new Select(array);
        select.sort();
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+" ");
        }
    }
}
class Select{
    private int[] array;
    public Select(int[] array){
        this.array = array;
    }
    public void sort(){
        for (int i = 0; i < array.length-1; i++) {
            int minIndex = i;
            int min = array[i];
            for (int j = i+1; j < array.length; j++) {
                /// 遍历后array.length-i-1位个数找到最小值
                if(min>array[j]){
                    minIndex = j;
                    min = array[minIndex];
                }
            }
            if(minIndex!=i){/// 优化，当最小值不为自己时，才交换
                array[minIndex] = array[i];
                array[i] = min;
             }
        }
    }
}