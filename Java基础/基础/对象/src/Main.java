import model.Cat;
import model.Person;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /* 面向对象（OOP）：
        （1）有利于对数据的管理
        （2）有利于变量名称和内容的对应
         */
        Cat cat1 = new Cat();
        cat1.name="小白";
        cat1.age=3;
        cat1.color="白色";
        Cat cat2 = cat1;//cat1的地址赋值给cat2
        /*
        创建流程：
        （1）先加载Cat类的信息（属性和方法信息只会加载一次）
        （2）在堆中分配空间，进行初始化（即为对象）
        （3）把堆中的地址赋值给cat1，cat1此时就指向堆中的对象
        （4）进行指定初始化，如cat1.age=3
         */
        /*对象在内存中存在形式
        cat1( 对象名/对象引用）存储在栈中，指向地址0x0011（假设）（真正的对象），
        该地址在堆中存储变量cat1相关信息
        名称，年龄，颜色，由于名称和颜色是字符串，是引用类型，所以在堆中不会直接存储内容，
        而是存放着在方法区常量池中的地址，比如0x0022，0x0033，这些地址在方法区常量池中
        存放着cat1的名称，颜色真正内容：小白/白色，但是年龄与之不同，其为基本数据类型，
        在堆中直接存储信息3
        此外，执行第8行程序时，方法区还会加载Cat信息：
        类和对象的内存分配机制：
        （1）栈：一般存放基本数据类型
        （2）堆：存放对象（Cat cat,数组）
        （3）方法区：常量池（常量，eg. 字符串），类加载信息
        1.属性信息
        2.行为（方法信息）
        属性：1.基本数据类型
             2.引用类型（数组/字符串）
        属性不赋值会有默认值：int 0/short 0/byte 0/long 0/float 0.0/
        double 0.0/char \u0000/boolean false
         */
        /*
        访问修饰符（初级）：
        控制属性的访问范围
        形式：public/protected/private/默认
         */
        Person person = new Person();//（1）
        int res = person.getSum(10,20);//(2)
        person.speak();
        person.call01();
        System.out.println(person.call02(5));
        System.out.println(person.getSum(1,2));
        /*方法调用机制
        执行（1）时在堆中开辟空间，对对象进行默认初始化
        执行到（2）的getSum（）方法时会在栈开辟一个空间
        在空间中a被赋值为10，b被赋值为20，res得到30
        方法遇到return或者方法执行完毕就会返回
        返回到调用方法的地方
         */
        int[][] arr = {{1,2,3},{2,3,4}};
        MyTools myTools  = new MyTools();
        myTools.printArr(arr);
        //方法调用传入的实参必须方法定义的形参相同或者兼容的类型
        //兼容：低精度到高精度，eg.byte->int/int->double
        byte b=1;
        myTools.A(b);
        B();
        /*同一个类中的方法可以直接调用，但是Main类的方法需要加static
        因为Main类没有创建对象，加上static后加载Main类的时候就可以调用该方法，
        不需要依赖创建对象才能调用该方法
         */
        System.out.println();
        System.out.println("判断是否为奇数");
        System.out.println(myTools.judge(7));
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入字符：");
        System.out.println("请输入行数:");
        System.out.println("请输入列数:");
        myTools.print(scanner.next(),  scanner.nextInt(),scanner.nextInt());
        /*
        方法传参（parameter）机制：
        （1）传入基本数据类型时，方法和main函数在栈中有独立的空间，不会影响各自的变量
        （2）传入为引用数据，方法和mian函数的引用数据都指向堆中同一地址，
        所以无论在mian函数还是在方法中改变引用数据
        在两个栈中的数组都会发生改变，总而言之，方法和mian函数都公用一个数据空间
         */
        //传入为基本数据类型
        int a=10,c=20;
        myTools.Exchange(a,c);
        System.out.println("mian函数中的a："+a+" b："+c);
        //传入为引用数据类型（数组/对象）
        int[] arr1={1,2,3};
        myTools.Change(arr1);
        System.out.println();
        System.out.println("main函数中的数组:");
        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i]+" ");
        }
        Person person1 = new Person();
        person1.name="Jack";
        person1.age=18;
        person1.sex="男";
        System.out.println();
        System.out.println("名称"+ person1.name+"年龄"+person1.age+"性别"+person1.sex);
        myTools.copy(person1);


    }
    public static void B(){
    }
}
//成员方法
class MyTools{
    public void printArr(int[][] map){
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j]+" ");
            }
        }
    }
    //方法要求的返回类型和return实际返回的类型需要兼容或者一致
    //兼容：float->double/byte->int/int->double
    public double A(int b){
        float a = 1.0F;
        return a;
    }
    //同一个类中方法可以直接调用
    //作业
    public boolean judge(int number){
//        if(number%2==0){
//            return true;
//        }
//        return false;
         return number % 2 != 0;
    }
    public void print(String str,int column,int line){
        for (int i = 0; i <line; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(str);
            }
            System.out.println();

        }
    }
    public void Exchange(int a,int b){
        System.out.println(" 交换前的a："+a+ "，交换后的b："+b);
        int temp = a;
         a = b;
         b = temp;
        System.out.println("交换后的a："+a+"，交换后的b："+b);
    }
    public void Change(int[] arr){
        arr[0]=200;
        System.out.println("方法中的数组");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
    }
    // 作业：克隆对象
    public void copy(Person person){
        Person person1 = new Person();
        person1.age=person.age;
        person1.name=person.name;
        person1.sex=person.sex;
        System.out.println("克隆对象的名称"+person1.name+"克隆对象的年龄"+ person1.age+"克隆对象的性别"+person1.sex);
    }

}