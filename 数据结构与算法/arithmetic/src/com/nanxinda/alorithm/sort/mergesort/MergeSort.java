package com.nanxinda.alorithm.sort.mergesort;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] array = {8,4,5,7,1,3,6,2,0,234};
        Merge merge = new Merge(array);
        merge.excrete(0,array.length-1);
        System.out.println(Arrays.toString(merge.getArray()));
    }
}
class Merge{
    private int[] array;
    private int[] tempArray;
    public Merge(int[] array){
        this.array = array;
        tempArray = new int[array.length];
    }
    public void excrete(int left,int right){
        int mid = (left+right)/2;
        if(left<right){
            ///使用递归不断的将序列进行拆分
            ///通过递归不断缩小分出组的元素个数，直至left=right即组中只有一个元素
            excrete(left,mid);
            excrete(mid+1,right);
            /// 再将当下分出来的两个组（虽然元素只有一个但可以视为有序）进行合并
            merge(left,mid,right);
        }
        /// 该轮次结束，会得到一个在一定范围内的有序数列，该序列会和其他轮次得到的有序数列进行合并
    }
    private void merge(int left,int mid,int right){
        int i = left;
        int j = mid+1;
        int temp = left;
        /// 将分出来的进行合并，temp可以视为一个中转站，通过temp将无序的序列进行排序
        while (i<=mid&&j<=right){
            if(array[i]<=array[j]){/// 如果要保持归并排序的稳定性，就要确保遇到相同元素时是左边的元素先加入到tempArray中
            /// 右边的元素后加入到tempArray中，这就需要在这里取等
                tempArray[temp++] = array[i++];
            }else {
                tempArray[temp++] = array[j++];
            }
        }
        while (i<=mid){
            tempArray[temp++] = array[i++];
        }
        while (j<=right){
            tempArray[temp++] = array[j++];
        }
        temp = left;
        int k =left;
        /// 再利用temp数组将有序的序列返回到原序列
        while (k<=right){
            array[k++] = tempArray[temp++];
        }
    }

    public int[] getArray() {
        return array;
    }
}