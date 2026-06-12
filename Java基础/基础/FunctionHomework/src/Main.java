import com.sun.corba.se.impl.interceptors.PICurrent;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //作业01
        A01 a01 = new A01();
        double[] nums = {1.0,9.0,8.9};
        Double max=a01.max(nums);
        if(max!=null){
        System.out.println("数组中最大的元素为："+max);
        }else{
            System.out.println("arr输入错误,数组不能为null或者{}");
        }
        //作业02
        A02 a02 = new A02();
        char[] str= "abcdef".toCharArray();
        int index=a02.find(str,'a');
        System.out.println('a'+"在字符串中索引为"+index);
        //作业03
        Book book = new Book(125);
        System.out.println("书的价格为"+book.price);
        //作业04
        int[] oldArr = {2,67,89,67};
        A03 a03 = new A03(oldArr);
        for (int i = 0; i < a03.newArr.length ;i++) {
            System.out.print(a03.newArr[i]+" ");
        }
        System.out.println();
        //作业05
        Circle circle = new Circle(2.0);
        System.out.println("该圆的面积为"+circle.size+" 该圆的长度为"+circle.length);
        //作业06
        Cale cale = new Cale(4,9);
        cale.sum();
        cale.division();
        cale.multiplication();
        cale.substraction();
        //作业08
        //new Test().count1()
        //Test为类，count1为方法，使用的是匿名对象（在栈中没有变量指向堆中创建的Test类对象）
        //匿名对象只能被使用一次，用完即销毁
        //作业09
       Music music = new Music("Lost in Paradis",2478);
       music.play();
       music.show();
       //作业10
        System.out.println(method(method(10.0,20.0),100));
        //作业11
        Employee employee1 = new Employee("主管",20000);
        Employee employee2 = new Employee('M',"小王",18);
        Employee employee3 = new Employee("主管",20000,'M',"小王",18);
        System.out.println("第一位的职位："+employee1.position+" 工资"+employee1.salary);
        System.out.println("第二位的性别为"+employee2.sex+" 名字"+employee2.name+" 年龄"+employee2.age);
        System.out.println("第三位的职位："+employee1.position+" 工资"+employee1.salary+" 性别"+employee2.sex+" 名字"+employee2.name+" 年龄"+employee2.age);
        //作业12
        new PassObject().printAreas(5);
        //作业13

        Scanner scanner = new Scanner(System.in);
        Guest guest = new Guest();
        System.out.println("请输入选择（0表示石头，1表示剪刀，2表示布）");
        boolean judge;
        do {
             judge= guest.getSum(scanner.nextInt());
            System.out.println("请输入选择（0表示石头，1表示剪刀，2表示布）");
        }while(judge);
        System.out.println("你赢了"+guest.winSum+"局，输了"+guest.lostSum+"局，平了"+guest.drawSum+"局");

    }
    public static int method(double a,double b){
        return (int)(a+b);
    }
}
 class A01{
    public Double max(double[] nums){
        //返回类型为包装类，可以返回null
        if(nums!=null&&nums.length>0){
            //确保代码的健壮性
        double max=nums[0];
        for (int i = 1; i < nums.length; i++) {
            if(nums[i]>max){
                max=nums[i];
            }
        }
        return max;
       }
        return null;
    }
 }
 class A02{
    public int find(char[] str,char a){
        for (int i = 0; i < str.length; i++) {
            if(str[i]==a){
                return i;
            }
        }
        return -1;
    }
 }
 class Book{
    int price;
    public Book(int price){
        this.price = price;
        this.updatePrice();
    }
    public void updatePrice(){
        if(this.price<150&&this.price>100){
            this.price=100;
        }else if (this.price>150){
            this.price = 150;
        }
    }
 }
 class A03{
    int[] newArr ;
    public A03(int[] oldArr){
        this.newArr=new int[oldArr.length];
        this.copyArr(oldArr);
    }
    public void copyArr(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            this.newArr[i]=arr[i];
        }
    }
 }
 class Circle{
    double radius;
    double size;
    double length;
    public Circle(double radius){
        this.radius = radius;
        this.size = this.CalculateSize();
        this.length =this.CalculateLength();
    }
    public double CalculateSize(){
        return this.radius*this.radius*3.14;
    }
    public double CalculateLength(){
        return this.radius*2*3.14;
    }
 }
 class Cale{
    int number1;
    int number2;
    public Cale(int number1,int number2){
        this.number1=number1;
        this.number2=number2;
    }
    public void sum(){
        System.out.println("两个数的和为："+number1+number2);
    }
    public void substraction(){
        System.out.println("两个数的差为："+(number1-number2));
    }
    public void division(){
        if(number2!=0){
           System.out.println("两个数相除得："+((double)number1/number2));
        }else{
            System.out.println("被除数不能为零");
            //函数可以使用Double类，如果被除数为0，则返回null
        }

    }
    public void multiplication(){
        System.out.println("两个数相乘得："+number1*number2);
    }
 }
 class Music{
    String name;
    int times;
    public Music(String name,int times){
        this.name=name;
        this.times=times;
    }
    public void play(){
        System.out.println("音乐"+name+"正在播放中..."+"时长为"+times+"秒");
    }
    public void show(){
        System.out.println("音乐名称"+name+" 时长"+times+"秒");
    }
 }
 class Employee{
    String name;
    char sex;
    int age;
    double salary;
    String position;
    public Employee(String position,double salary){
        this.salary=salary;
        this.position=position;
    }
    public Employee(char sex,String name,int age){
        this.name=name;
        this.age=age;
        this.sex=sex;
    }
    public Employee(String position,double salary,char sex,String name,int age){
        this(position,salary);
        //调用构造器的时候，只能在构造器的首句
        this.name=name;
        this.age=age;
        this.sex=sex;
    }
 }
 class PassObject{
    public void printAreas(int times){
        System.out.println("radis                      area");
        for (int i = 1; i <=times ; i++) {
            System.out.println(i+"                          "+new Circle1(i).findAreas());
            //缺点：每次都要创建一个Circle1对象
            // 解决方案：可以函数传入Circle1对象，在Circle1中编写一个函数，可以修改圆的半径
            //printAreas(Circle c,...)
            //c.setRad(i);
            //Circle1类中的setRad:public void setRad(double radius){ this.radius = radius;}
        }
    }
 }
 class Circle1{
    double radius;
    public Circle1(double radius){
        this.radius=radius;
    }
    public double findAreas(){
        return Math.PI*radius*radius;
    }
 }
  class Guest{
    int num;
    int winSum;
    int lostSum;
    int drawSum;

    private void random(){
        this.num=(int)(Math.random()*3);
    }
    public Boolean Judge(int getNumber){
        this.random();
        if(getNumber==num){
            System.out.println("这一局平手");
            return null;
        }else if ((getNumber<num&&(num!=2&&num!=0))||(getNumber==2&&num==0)){
            System.out.println("这局你赢了");
            return true;
        }else{
            System.out.println("这局你输了");
            return false;
        }
    }
    public boolean getSum(int nums){
        if(nums<0||nums>2){
            System.out.println("输入错误");
            return false;
        }
        Boolean judge=this.Judge(nums);

            if(judge==null){
                drawSum++;
                return true;

            }
            else if(judge){
                winSum++;
            }else {
                lostSum++;
            }
            return true;

    }
 }