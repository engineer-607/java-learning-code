package com.nanxinda.alorithm.sort.bubblesort;

public class BubbleSorting {
    public static void main(String[] args) {
        int[] array = {3,0,-1,7,2};
        BubbleBase bubbleBase = new BubbleBase(array);
        bubbleBase.sort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+" ");
        }
        int[] array1 = {3,0,-1,7,2};
        BubbleAdvanced bubbleAdvanced = new BubbleAdvanced(array1);
        bubbleAdvanced.sort(array1);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+" ");
        }
    }
}
class BubbleBase{
    private int[] array;
    public BubbleBase(int[] array){
        this.array = array;
    }
    public void sort(int[] array){
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if(array[j]>array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }
}
class BubbleAdvanced extends BubbleBase{

    public BubbleAdvanced(int[] array) {
        super(array);
    }

    @Override
    public void sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            /// 减一是一共要进行array.length-1趟比较
        boolean judge = false;///优化：判断是否进行交换，如果没有交换说明后面已经排好序，无需交换
            for (int j = 0; j < array.length - 1 -i; j++) {
                /// array.length - 1 -i减i是因为最后i位都已经排好序，减一是因为避免交换的时候超出数组大小
                if(array[j]>array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                    judge = true;
                }
                if (judge){
                    break;
                }


            }
        }
    }
}
