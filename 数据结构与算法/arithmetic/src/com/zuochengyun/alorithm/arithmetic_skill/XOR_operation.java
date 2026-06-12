package com.zuochengyun.alorithm.arithmetic_skill;

import java.util.Arrays;

public class XOR_operation {
    /*
    异或运算的性质
    1.异或运算理解为无进位相加
    2.异或运算满足交换律、结合律（可以用无进位相加来证明）
    3.n^0=n,n^n=0
    4.a^b=c => a=c^b,b=c^a
     */
    public static void main(String[] args) {
        //1.用异或运算交换两个数
        /// 使用条件，交换的两个数得有各自独立的内存空间
        int a = 10;
        int b = 19;
        a = a^b;
        b = a^b;//b^(b^a) = a
        a = a^b;//(a^b)^a = b
        System.out.println(a);
        System.out.println(b);

        //2.不用任何判断语句和比较操作，返回两个数的最大值
        System.out.println(getMax1(a,b));
        System.out.println(getMax1(a,b));

        //3.查找消失的数字
        int[] nums = {0,1,2,3,5};
        System.out.println(missingNumber(nums));

        //4.查找数组中出现奇数次的数字
        int[] numbers={8,8,9,9,0};
        System.out.println(singleNumber(numbers));

        /// 5.Brain Kernighan算法-提取出二进制状态中最右侧的1
        /// n&(~n+1) ===》 n&(-n)
        //eg. 10111000
        //取反 01000111
        //加一 01001000 然后再&10111000
        //    00001000
        //例题：查找数组中出现频率为奇数的两个数
        int[] nums1 = {1,2,1,3,2,5};
        System.out.println(Arrays.toString(singleNumberIII(nums1)));

        //6.一个数组一个数字出现了k次，而其他数字出现了m次，找出这个数字
        int[] nums2 = {2,2,3,2};
        System.out.println(singleNumberII(nums2,3));




    }
    //非负返回1，负数返回0
    public static int sign(int n){
        //n>>>31,int类型是32位，右移31位，得到符号位，然后异或1，如果符号位为1（负数），返回
        //0，如果符号位为0（正数）返回1
        return file(n>>>31);
    }
    public static int file(int n){
        return n^1;
    }
    //返回最大值
    public static int getMax1(int a,int b){
        /// 有c溢出的风险
        int c = a-b;
        /// 如果c非负，returnA为1
        /// 如果c为负，returnA为0
        int returnA = sign(c);
        /// 如果c非负，returnB为0
        /// 如果c为负，returnB为1
        int returnB = file(returnA);
        return returnA*a+returnB*b;
    }
    public static int getMax2(int a,int b){
        //溢出的情况只有一种，就是a、b异号
        int c = a-b;
        //判断a,b是否异号
        int sa = sign(a);
        int sb = sign(b);
        int sc = sign(c);
        //如果a，b异号，differAB为1，如果a，b同号，differAB为0
        int differAB = sa^sb;
        //如果a，b异号，same为0，如果a，b同号，same为1
        int sameAB = file(differAB);
        //如果a，b同号，最终结果应该等于sc，如果a，b异号，最终结果应该等于sa
        // （sa为0时，认为负数，sa为1时，认为为正数）
        int returnA = differAB*sa+sameAB*sc;
        int returnB = file(returnA);
        return returnA*sa+returnB*sb;
    }
    public static int missingNumber(int[] nums) {
        int origin = 0;
        for (int i = 0; i <=nums.length ; i++) {
            origin^=i;
        }
        int num = 0;
        for (int i = 0; i < nums.length; i++) {
            num^=nums[i];
        }
        return origin^num;
    }
    public static int singleNumber(int[] nums){
        int origin = 0;
        for (int i = 0; i < nums.length; i++) {
            origin^=nums[i];
        }
        return origin;
    }
    public static int[] singleNumberIII(int[] nums){
        int eor1 = 0;
        for (int i = 0; i < nums.length; i++) {
            eor1^=nums[i];
        }
        int judge = eor1&(-eor1), eor2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if((nums[i]&judge)==0){
                eor2^=nums[i];
            }
        }
        return new int[]{eor2,eor2^eor1};
    }
    public static int singleNumberII(int[] nums,int m){
        int[] places = new int[32];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < places.length; j++) {
                places[j]+=(nums[i]>>j)&1;
            }
        }
        int ans = 0;
        for (int i = 0; i < places.length; i++) {
            if(places[i]%m!=0){
                ans|=1<<i;
            }
        }
        return ans;
    }
}
