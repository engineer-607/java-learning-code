package model;

public class Person {
    public String name;
    public int age;
    public String sex;
    public void speak(){
        System.out.println(" 我是好人");
    }
    public void call01(){
        int sum=0;
        for (int i = 1; i <= 1000; i++) {
            sum+=i;
        }
        System.out.println(sum);
    }
    public int call02(int n){
        int sum=0;
        for (int i = 1; i <=n; i++) {
            sum+=i;
        }
        return sum;
    }
    public int getSum(int a,int b){
        int sum=a+b;
        return sum;
    }

}
