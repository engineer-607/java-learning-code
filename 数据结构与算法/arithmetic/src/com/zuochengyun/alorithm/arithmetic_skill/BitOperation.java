package com.zuochengyun.alorithm.arithmetic_skill;

public class BitOperation {
    public static void main(String[] args) {
        //1.判断一个数是否为2的幂
        //如果是2的幂，那么这个数的二进制只有一位上是1
        //所以只需要得到该数的二进制最右侧的2判断其是否和原数相等就可以判断该数是否为2的幂
        System.out.println(judgeTwoPower(4));
        //2.判断一个数是否为3的幂
        //1162261467是int型范围内中最大的3的幂，为3的19次幂
        //1162261467只含有3这个质数因子，如果n也是只含有3这个质数因子
        //那么1162261467%n==0
        System.out.println(judgeThreePower(9));
        //3.获取n>=最小的2某次方
        System.out.println(nearTwoPower(9));
        //如果传入int范围内的最大值即为011111....
        //按照代码的逻辑最终会变成100000....
        //按照有符号中补码的计算结果是-2的31次幂
        //4.求在区间[left,right]上的所有数字&的结果
        System.out.println(andBitRangeFromLeftToRight(10,17));
        //5.将一个二进制进行翻转
        System.out.println(reserveBits(43261596));
        //6.计算一个二进制数中有多少个1
        System.out.println(countBits(0b11111001));





    }
    /// 考虑需要全面，2和3的幂肯定是要大于0的
    public static boolean judgeTwoPower(int n){
        return n>0&&(n&(~n+1))==n;
    }
    public static boolean judgeThreePower(int n){
        return n>0&&1162261467%n==0;
    }
    public static int nearTwoPower(int n){
        if(n<=0){
            return 1;
        }
        n--;
        n|=n>>>1;
        n|=n>>>2;
        n|=n>>>4;
        n|=n>>>8;
        n|=n>>>16;
        return n+1;
    }
    public static int andBitRangeFromLeftToRight(int left,int right){
        while (left<right){
            //不断去除最右边的数直到其小于或者等于left，此时的right就是最终的结果
            right-=right&(-right);
        }
        return right;
    }
    public static int reserveBits(int bit){
        //abcdefgh先进行1v1翻转
        //即为(ba)(dc)(fe)(hg)
        //方式：1.分别&10101010(0xaa)和&01010101(0x55)
        // & abcdefgh   abcdefgh
        //   10101010   01010101
        //   a0c0e0g0   0b0d0f0h
        //2.结果一>>>1=0a0c0e0g 结果二<<1=b0d0f0h0(>>>是逻辑右移)
        //3.再进行|得bacdefgh
        bit=(bit&0xaaaaaaaa)>>>1|(bit&0x55555555)<<1;//1v1
        bit=(bit&0xcccccccc)>>>2|(bit&0x33333333)<<2;//2v2
        bit=(bit&0xf0f0f0f0)>>>4|(bit&0x0f0f0f0f)<<4;//4v4
        bit=(bit&0xff00ff00)>>>8|(bit&0x00ff00ff)<<8;//8v8
        bit=(bit>>>16)|(bit<<16);//16v16，因为被分为两部分，所以直接移动就可以
        return bit;
    }
    public static int countBits(int bit){
        //先看长度为1
        bit = Bitset.getBit(bit);
        return ((bit>>>16)&0x0000ffff)+(bit&0x0000ffff);
    }
}
