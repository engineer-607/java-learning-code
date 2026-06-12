package com.nanxinda.alorithm.seek.interpolation;

public class InterpolationDemo {
    public static void main(String[] args) {
        int[] array = {1,2,3,4,5,6,7,8,9,10,11};
        Interpolation interpolation = new Interpolation(array);
        System.out.println(interpolation.seek(1,0,array.length-1));

    }

}
class Interpolation {
    private int[] array;
    public Interpolation(int[] array){
        this.array = array;
    }
    public Integer seek(int n,int left,int right){
        if(left>right){
            return null;
        }
        /// 原理是将需要寻找值和最左边值之差，
        ///和最左边值与最右边值之间的差的比例，推算出寻找值的位置相较于最左边应该在什么地方(通过值的比例来估计索引的位置)
        /// 因为是通过值去推算索引，所以就会要求值最好和索引一样连续或者均匀变化
        int mid = left+(right-left)*(n - array[left])/(array[right]-array[left]);
        if(n>array[mid]){
            return seek(n,mid+1,right);
        }else if(n<array[mid]){
            seek(n,left,mid-1);
        }
        return mid;

    }
}
