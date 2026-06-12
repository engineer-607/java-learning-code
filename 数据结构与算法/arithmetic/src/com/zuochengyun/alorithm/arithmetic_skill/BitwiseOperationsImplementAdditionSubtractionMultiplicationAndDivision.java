package com.zuochengyun.alorithm.arithmetic_skill;

import org.junit.Test;
/// 二进制实现增删改查
public class BitwiseOperationsImplementAdditionSubtractionMultiplicationAndDivision {
    public int add(int a,int b){
          //案例
          //10111001
          //01011010
  //第一步：异或运算（无进位相加）
          //11100011
  //第二步：先与再移位:
         //&00011000
        //  00110000
  //然后再重复以上步骤
  //        11100011
  //        00110000
  //       ^11010011
  //       &00100000
  //        01000000
  //再次重复操作
  //        11010011
  //        01000000
  //       ^10010011
  //       &01000000
  //        10000000
  //再次进行操作
  //        10010011
  //        10000000
  //       ^00010011
        int c;
        while((a&b)!=0){
            c = a^b;
            b = (a&b)<<1;
            a = c;
        }
        return a^b;
    }
    public int minus(int a,int b){
        return add(a,add(~b,1));
    }
    public int neg(int a){
        return add(~a,1);
    }
    //案例
    //a:01011
    //b:10110
    //第一步：b向右移动0位，a向左移动0位，b的最低位与a做&运算
//结果   00000
    //第二步：b向右移动1位，a向左移动1位，二者&运算
    //a:10110
    //b:01011
//结果   10110
    //第三步：b向右移动2位，a向左移动2位，二者&运算
    //a:01100
    //b:00101
//结果   01100
    //.......
    public int multiply(int a,int b){
        int ans = 0;
        while (b!=0){
            if((b&1)!=0) {
                ans = add(ans, a);
            }
            a<<=1;
            b>>>=1;
        }
        return ans;
    }
    //计算方式
    //比如a为280，b为25
    //依次判断280是不是比25*2^32,25*2^31,...,25*2^0大，如果大就在相应的二进制位上填1
    public int divideProcess(int a,int b){
        int ans = 0;
        //当a或者b为负数时，先按照正数来处理
        int x = a>0?a:neg(a);
        int y = b>0?b:neg(b);
        //移动的范围应该是30位而不是31位，因为31位得到的也是符号位，由于x，y都是正数（
        //先排除最小值的情况），所以移动31位得到只是0
        int moveBit = 30;
        while (moveBit>=0){
            //
            if((x>>>moveBit)>=y){
                ans|=0x00000001<<moveBit;
                x=minus(x,y<<moveBit);
            }
            moveBit=minus(moveBit,1);
        }
        //如果a和b一个非负一个负数，才会将结果转化位负数，如果是同号，直接返回结果
        return a>0^b>0?neg(ans):ans;
    }
    public int divide(int dividend, int divisor){
        int min = Integer.MIN_VALUE;
        if(dividend==min&&divisor==min){
            return 1;
        }
        if(dividend!=min&&divisor!=min){
            return divideProcess(dividend,divisor);
        }
        //前提是被除数不是最小值
        if(divisor==min){
            return 0;
        }
        //前提是被除数是最小值
        if(divisor==neg(1)){
            return Integer.MAX_VALUE;
        }
        //剩下的情况为被除数为最小值且除数不为-1也不为最小值
        return divisor>0?
                add(divideProcess(dividend+divisor,divisor),neg(1)):
                add(divideProcess(dividend-divisor,divisor),1);

    }

    @Test
    public void testAdd(){
        System.out.println(add(0,7));
        System.out.println(minus(19,11));
    }
    @Test
    public void testMultiply(){
        System.out.println(multiply(11,10));
    }
    @Test
    public void testDivision(){
//        System.out.println(divideProcess(1000,20));
        System.out.println(divide(-2147483648,-3));
    }
}
