package com.nanxinda.alorithm.sort.insertsort;

public class InsertSort {
    public static void main(String[] args) {
        int[] array = {34,78,-1,0,98,45};
        Insert insert = new Insert(array);
        insert.sort();
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+" ");
        }
    }
}
class Insert{
    private int[] array;
    public Insert(int[] array) {
        this.array = array;
    }
    public void sort(){
        /// 插入排序是将无序列表插入到有序列表
        /// 一共需要插入array.length-1轮
        for (int i = 1; i < array.length; i++) {
            /// 在有序列表中查找的开始索引是从无序列表的首尾-1、即有序列表的末尾
            int traverseIndex = i-1;
            /// 获取待插入元素的值，为后续的插入做准备
            int insertVal = array[i];
            /// 循环遍历有序列表（能够进行的条件是traverseIndex>=0并且insertVal<array[traverseIndex]
            while (traverseIndex>=0&&insertVal<array[traverseIndex]){
                array[traverseIndex+1] = array[traverseIndex];
                traverseIndex--;
            }
            /// 结束的两种情况：1）traverseIndex=-1，这时候直接插入到有序列表的头部
            /// 2）insertVal>=array[traverseIndex]同时也代表着insertVal<array[traverseIndex+1]
            /// 循环既找到插入的位置，也将该插入位置空出（在有序列表中该位置及后面元素进行后移）

        /// 将元素插入
        if(traverseIndex==(i-1)) {
            array[traverseIndex + 1] = insertVal;
        }
        }

    }
}