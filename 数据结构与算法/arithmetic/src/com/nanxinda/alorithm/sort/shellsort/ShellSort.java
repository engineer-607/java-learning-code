package com.nanxinda.alorithm.sort.shellsort;

public class ShellSort {
    public static void main(String[] args) {
        int[] array = {3,8,0,9,4,5,7,1,2,6};
//        SellBase sellBase = new SellBase(array);
//        sellBase.sort();
        ShellAdvanced_Teacher shellAdvanced = new ShellAdvanced_Teacher(array);
        shellAdvanced.sort();
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+" ");
        }

    }
}
class SellBase {
    private int[] array;
    public SellBase(int[] array){
        this.array = array;
    }
    public void sort(){
        int gap = array.length/2;
        do{
            /// 每次循环获取对应的步长，用于分组
            for (int i = gap; i <array.length ; i++) {
                /// 内部使用交换排序
                for (int j = i-gap; j >=0; j-=gap) {
                    if(array[j]>array[j+gap]){
                        int temp = array[j+gap];
                        array[j+gap] = array[j];
                        array[j] = temp;
                    }
                }
            }

        }while ((gap = gap/2)!=0);
        /// 随着步长逐渐减少，每组包含的元素越来越多，而当步长不再存在，恰好为一组且有序
    }

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }
}
class ShellAdvanced_Stu extends SellBase{

    public ShellAdvanced_Stu(int[] array) {
        super(array);
    }

    @Override
    public void sort() {
        int gap = getArray().length/2;
        do {
            for (int i = 0; i < gap; i++) {
                /// 内部使用直接插入排序

                for (int j = i+gap; j <getArray().length ; j+=gap) {
                    int traverseIndex = j-gap;
                    int insertVal = getArray()[j];
                    while (traverseIndex>=0&&insertVal<getArray()[traverseIndex]){
                        getArray()[traverseIndex+gap] = getArray()[traverseIndex];
                        traverseIndex-=gap;
                    }

                        getArray()[traverseIndex + gap] = insertVal;

                }
            }

        }while ((gap = gap/2)!=0);//计算每次的步长
    }
}
class ShellAdvanced_Teacher extends SellBase{

    public ShellAdvanced_Teacher(int[] array) {
        super(array);
    }
    public void sort(){
        int[] array = getArray();
        int gap = array.length/2;
        do {
            for (int i = gap; i < array.length; i++) {
                int traverseIndex = i - gap;
                int insertVal = array[i];
                while (traverseIndex>=0&&insertVal<array[traverseIndex]){
                    array[traverseIndex+gap] = array[traverseIndex];
                    traverseIndex-=gap;
                }
                array[traverseIndex+gap] = insertVal;
            }
        }while ((gap = gap/2)!=0);
    }
}