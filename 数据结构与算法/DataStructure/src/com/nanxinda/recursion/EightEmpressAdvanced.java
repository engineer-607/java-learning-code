package com.nanxinda.recursion;

public class EightEmpressAdvanced {
    public static void main(String[] args) {
        Test test = new Test();
        test.check(0);
        System.out.printf("一共有%d解法",Test.count);

    }
}
class Test {
    int[] array = new int[8];
    public static int count = 0;
    int max = 8;
    public void print(){
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.printf(array[i]+" ");
        }
        System.out.println();
    }
    public void check(int n){
        if(n==max){
            print();
            return;
        }
        for (int i = 0; i < max; i++) {
            array[n] = i;
            if(judge(n)){
                check(n+1);
            }else {
                array[n] = 0;
            }
        }
    }

    public boolean judge(int n){
        for (int i = 0; i < n; i++) {
           if(array[i]==array[n]||Math.abs(array[n]-array[i])==Math.abs(n-i)){
               return false;
           }
        }
        return true;
    }
}
