package com.nanxinda;
import com.nanxinda.print.C;
import com.nanxinda.test.A;
import com.nanxinda.test.B;

import java.util.concurrent.Callable;

public class Test {
    public static void main(String[] args) {
        /*
        包的三大作用：
        1.区分相同名字的类
        2.当类很多时，可以很好的管理类
        3.控制访问范围
        包的基本语法
        package（关键字） com.xxxx（包名）
        包的本质：创建不同的文件夹保存类文件
        包的命名：只能包含字母，数字，小圆点，下滑线，不能是数字开头，不能是关键字或保留字
        命名规范：com.公司.项目.模块
         */
        /*
        引入包；语法 import 包;（引入包的主要目的是使用该包下面的类）
         */
        //package的作用是声明当前类所在的包，需要放在类的最上面
        //import在package的后面，类定义的前面，可以有多句但没有顺序要求
        /*
        访问修饰符（控制方法和属性的访问权限）
        公开级别：public,对外开放
        受保护级别：protected,对子类和同一个包的类公开
        默认级别：没有修饰符号，向同一个包公开
        私有级别：用private修饰，只有类本身可以访问，不对外公开

        修饰符    同类  同包  不同包子类  不同包非子类      说明
       private   ✅   ❌     ❌          ❌     最严格的访问级别
 default (包私有) ✅   ✅     ❌          ❌      不使用任何修饰符
       protected ✅   ✅     ✅          ❌       允许子类访问
        public   ✅   ✅     ✅          ✅      最宽松的访问级别
         */
        A a = new A();
        a.A();
        B b = new B();
        b.say();
        C c = new C();
        c.setA();

        //只有public和默认可以修饰类



    }
}