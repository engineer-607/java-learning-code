package com.nanxinda.alorithm.sort.quicksort;

import java.lang.reflect.Array;

public class QuickSort {
    /**
     *
     */
    public static void main(String[] args) {
        int[] array = {2,10,8,21,34,5,12,28,21,11};
        Quick quick = new Quick(array);
        quick.sort(0,array.length-1);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+" ");
        }

    }
}
class Quick{
    private int[] array;

    public Quick(int[] array) {
        this.array = array;
    }
    public void sort(int left,int right){
        if(left==right){
            return;
        }
        int l = left;///从左边开始的指针
        int r = right;///从右边开始的指针
        int pivot = array[(left+right)/2];
        while (l<r){
            /// 不断寻找左边大于等于中轴
            while (/*l<array.length&&不会越界，无需加入进行多余的判断
            */array[l]<pivot){
                l++;
            }
            /// 不断寻找右边大于等于中轴
            while (/*r>=0 不会越界，无需加入进行多余的判断
            &&*/array[r]>pivot){
                r--;
            }
            /// 进行互换
            if(/*l<array.length&&r>=0&&*/l<r){
                int temp = array[l];
                array[l] = array[r];
                array[r] = temp;
            }
//            if(l>=r){
//                break;
//            }
            /// 如果序列中有相同的元素，一般会在最后左右指针各指向其中一个
            ///左右指针都不满移动条件，就会进行交换，但交换之后依旧不满足移动条件
            ///而如果始终无法移动就会造成l永远小于r，就会陷入死循环中
            if(array[l]==pivot&&array[r]==pivot){
                /// 可以让l++，使得l=r
                l++;
            }
        }
        if(l==r){
            ///上述循环结束的结果通常左右指针指向中轴
            /// 这时候左部分缺少下一次递归所需要的最右起始点，所以要r--
            /// 而右部分缺少下一次递归所需要的最左起始点，所以l++
           /// 将这两个变量给下一次递归传参就能确定指针的起始位置
            l++;
            r--;
        }
        if(left<r) {/// 需要提前判断需要提前判断--之后的r是否会小于left
        /// 因为当不断分组之后，每组元素减少到1-2个时，很容易出现循环结束r和left指向的元素一样
        /// 这时候r--就会小于left造成紊乱
            sort(left, r);
        }
        if(right>l) {/// 需要提前判断++之后的l是否会大于right
        /// 因为当不断分组之后，每组元素减少到1-2个时，很容易出现循环结束l和right指向的元素一样
        /// 这时候l++就会超出right造成紊乱
            sort(l, right);
        }
    }
}