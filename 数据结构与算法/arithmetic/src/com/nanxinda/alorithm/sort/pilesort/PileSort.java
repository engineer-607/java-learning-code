package com.nanxinda.alorithm.sort.pilesort;

import java.util.Arrays;

/**
 * 1）堆排序是利用堆这种数据结构而设计的一种排序算法，堆排序是一种选择排序，他的最坏、最好、平均时间复杂度
 * 为O(nlogn)，它也是不稳定排序
 * 2）堆是具有以下性质的完全二叉树：每个节点的值都大于或等于其左右孩子节点的值，称为大顶堆
 * （注意：没有要求节点的左孩子和右孩子值的大小关系）
 * 3）每个节点的值都小于或等于其左右孩子节点的值，称为小顶堆
 */
public class PileSort {
    public static void main(String[] args) {
        int[] arr = {4,6,8,5,9};
        Pile pile = new Pile(arr);
        pile.sort();

    }
}
/// 大顶堆
class Pile{
    private int[] array;
    public Pile(int[] array){
        this.array = array;
    }
    public void sort(){
        /// 建堆
        for (int j = array.length/2-1; j >=0 ;j--) {
            adjustHeap(j,array.length);
        }
        int temp;
        for (int i = array.length-1; i >=0 ; i--) {
            /// 将堆顶的元素和二叉树最后一个元素进行交换，获得最大元素
            temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            /// 交换完之后需要再进行调整
            adjustHeap(0,i);
        }
        System.out.println(Arrays.toString(array));

    }
    public void adjustHeap(int i,int length){
        int temp = array[i];
        for (int j = 2*i+1; j <length ; j=j*2+1) {
            /// 先比较出两个子孩子哪个最大
            if(j+1<length&&array[j]<array[j+1]){
                j++;
            }
            /// 再将比较得出的子孩子和父节点进行比较
        /// 如果子孩子值大于父节点就进行交换
            if(array[j]>temp){
                array[i] = array[j];
                i = j;
                array[i] = temp;
            }else {/// 不管是建堆还是后来的调整，因为都是从下到上都是符合大顶堆
            /// 所以如果没有交换就意味着下面以下面两个子孩子为父节点的子树本省就是大顶堆
            /// 无需继续检查，可以直接跳出循环
                break;
            }

       }
    }


}
