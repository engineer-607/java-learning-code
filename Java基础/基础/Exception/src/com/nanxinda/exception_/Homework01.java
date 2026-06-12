package com.nanxinda.exception_;

public class Homework01 {
    public static void main(String[] args) {
        try {
            if(args.length!=2){
                throw  new ArrayIndexOutOfBoundsException("参数个数不正确");
            }
            int n1 ,n2;
            try {
                n1 = Integer.parseInt(args[0]);
                n2 = Integer.parseInt(args[1]);
            }catch(NumberFormatException e) {
                throw new NumberFormatException("数据格式不正确");
            }
            if(n2==0){
                throw new ArithmeticException("除数不能为0");
            }
            int res = new EcmDef().cal(n1,n2);
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("错误信息="+e.getMessage());
        }catch (NumberFormatException e){
            System.out.println("错误信息="+e.getMessage());
        }catch (ArithmeticException e){
            System.out.println("错误信息="+e.getMessage());
        }


    }
}
class EcmDef{
    public int cal(int n1,int n2){
        return n1/n2;
    }
}