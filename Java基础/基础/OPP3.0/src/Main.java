
// main方法：
// 1.main方法由java虚拟机调用
// 2.java虚拟机需要调用类的main()方法，所以该方法的访问权限必须是public
// 3.java虚拟机在执行main()方法时不必要创建对象，所以必须是static
// 4.该方法接收String类型的数组参数，该数组中保存执行java命令时传递给所
// 执行类的参数
// eg. java 执行的程序 第一个参数 第二个参数 第三个参数
//提醒：
//1）main()方法中，可以直接调用main方法所在类的静态方法或静态属性
//2）不能访问类中的非静态成员
public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }


    }
}