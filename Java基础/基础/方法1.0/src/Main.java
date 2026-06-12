import java.util.Arrays;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        //递归案例一
        A(4);//4传入A方法，在栈中开辟一块新区域（1）
        //递归案例二：
        //阶乘
        int result = factorial(5);
        System.out.println("阶乘的结果" + result);
        /*
        方法知识点：
        执行一个方法时，会创建一个新的受保护独立空间（栈空间）
        方法的局部变量是独立的，不会相互影响
        多个方法都是使用同一引用类型变量（数组/变量），就会共享引用类型数据

         */
        //作业1
        int sum = factorial1(9);
        System.out.println("第9个菲波那契为：" + sum);
        //作业2
        int origin = factorial2(9, 1);
        if (origin != -1) {
            System.out.println("原本的桃子个数为" + origin);
        }
        //作业3--自己所做
        int[][] arr = new int[8][8];
        //设置障碍物
        Arrays.fill(arr[0], 1);
        Arrays.fill(arr[7], 1);
        for (int i = 1; i < 7; i++) {
            arr[i][0] = 1;
            arr[i][7] = 1;
        }
        arr[3][1] = 1;
        arr[3][2] = 1;
        /*
        我的代码核心不足在于缺乏缺乏回溯机制（重头来过的机会）以及没有对路径的实时反馈（即当前这一级结合下一级和当前的状态return给上一级状态）
        如同没有侦察兵的报告便直接采取行动
        ，即便走上死胡同也不知
         eg.
         1 1 1 1 1 1 1 1
         1 2 0 0 0 0 0 1
         1 2 1 1 1 1 0 1
         1 2 1 0 0 0 0 1
         1 2 1 0 0 0 0 1
         1 2 1 0 0 0 0 1
         1 2 1 0 0 0 0 1
         1 1 1 1 1 1 1 1
         在这种情况下我的程序将走入死胡同，由于缺少实时反馈
         （父递归无法得知子递归的情况，即所有递归都不清楚上一级递归和下一级递归的结果），无法得知该路为死胡同，
         即便加上return的实时反馈，又会因为缺少回溯机制而导致无法重新来过
         */
        if (factorial3(arr, 1, 1)) {
            System.out.println("找到路径");
        } else {
            System.out.println("未找到路径");
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        //对改进策略进行进行更难的检测
        int[][] arr_2 = new int[8][8];
        //外层障碍物设置
        Arrays.fill(arr_2[0], 1);
        Arrays.fill(arr_2[7], 1);
        for (int i = 1; i < 7; i++) {
            arr_2[i][0] = 1;
            arr_2[i][7] = 1;
        }
        //内层障碍物设置
        for (int i = 2; i < 6; i++) {
            arr_2[2][i] = 1;
            arr_2[i][2] = 1;
        }
        arr_2[6][2] = 1;
        if (factorial3_2(arr_2, 1, 1)) {
            System.out.println("找到路径");
        } else {
            System.out.println("未找到路径");
        }

        for (int i = 0; i < arr_2.length; i++) {
            for (int j = 0; j < arr_2[i].length; j++) {
                System.out.print(arr_2[i][j] + " ");
            }
            System.out.println();
        }//汉诺塔
        TowerMove(2, 'A', 'B', 'C');
        // 八皇后
        //我的代码核心问题：每次递归都只是随机生成坐标，回溯也只是再随机换个位置，用循环来掩盖低效率
        int[][] arr2 = new int[8][8];
        boolean flag = false;
        while (!flag) {
            flag = factorial4(arr2, 0);
        }
        System.out.println("八皇后找到答案");
        for (int i = 0; i < arr2.length; i++) {
            for (int j = 0; j < arr2[i].length; j++) {
                System.out.print(arr2[i][j] + " ");
            }
            System.out.println();
        }
        //八皇后优化1.0
        int[] res = factorial4(0, 0, arr2, 0, 0);
        System.out.println("1.0版本八皇后的个数：" + res[2]);
        /*优化失败，反思：
        （1）对八皇后游戏规则有误解，皇后可以攻击同一行或同一列或整个斜对角，
        所以要确保每一行每一列，每一斜对角不存在两个八皇后
        （2）放置策略存在问题：固定某一列，然后尝试所有行（没有理解游戏规则）
        ，应该逐行放置
        （3）状态n，计数num更新混乱，进而导致回溯失败
        （4）没有逐行推进机制进而导致递归的逻辑错误

         */
        //八皇后优化2.0
        int[][] arr3 = new int[8][8];
        int[] res1 = factorial4(0, arr3, 0);
        System.out.println("2.0版本八皇后个数:" + res1[0]);


        //方法的重载
        /*
        在同一个类中，允许多个同名方法的存在，但要求形参列表不一致，System.out.println();
        out是PrintStream类型，其定义许多同名函数println()
        好处：减轻起名和记名的麻烦
        注意事项：
        （1）方法名：必须相同
        （2）形参列表：必须不同（形参类型或个数或顺序必须有一个不一样，对参数名没有要求）
        （3）返沪类型：无要求
        （4)与方法的内容无关
        eg.public int calculate(int n1,int n2)和public void calculate(int n1,int n2)
        无法形成重载，为方法的重复定义
         */
        Methods methods = new Methods();
        methods.calculate(9);
        methods.calculate(1, 2);
        methods.calculate("a");
        int max1 = methods.max(1, 2);
        double max2 = methods.max(1.0, 2.0);
        double max3 = methods.max(2.0, 7.0, 9.0);
        System.out.println("方法运算得到最大值：" + max1 +
                " 方法重载后运行得到最大值：" + max2 + " 方法再次重载后所得最大值：" + max3);
        /*可变参数：
        （1）基本概念：将在同一个类中多个同名但参数个数不同的方法，封装成同一个方法
        eg.
        class calculate {
           public int sum(int n1,int n2){
               return n1+n2;
           }
           public int sum(int n1,int n2,int n3){
               return n1+n2+n3;
           }
           public int sum(int n1,int n2,int n3,int n4){
               return n1+n2+n3+n4;
           }
        }
        名称相同，功能相同，参数个数不同
        int... 表示接受的可变参数，类型为int，即可以接受多个int
        nums可以当作数组来使用
        public int sum(int... nums){
           int res = 0;
           for(int i = 0;i<nums.length;i++){
              res+=nums[i];
           }
           return res;
        }
        可变参数使用细节：
        （1）可变参数的实参可以为0个或者多个
        （2）可变参数可以为数组
        （3）可变参数本质就是数组
        (4)可变参数可以和普通类型的参数放在形参列表中，但必须保证可变参数放在最后
        eg.public void f2(String str,double... nums){
        }
        (5)一个形参列表中只能出现一个可变参数
         */
        //作业
        HspMethod hspMethod = new HspMethod();
        System.out.println(hspMethod.record("jack", 96, 93));
        System.out.println(hspMethod.record("lihua", 96, 93, 79));
        System.out.println(hspMethod.record("mike", 96, 93, 79, 86, 78));
        /*
        作用域：
        1.局部变量一般指在成员方法中定义的变量
        eg.class Cat{
        //2.全局变量，也称属性，作用域为整个Cat类，可以在cry方法中使用
        int age=10;
        public void cry(){
            int n = 10;
            String name = "jack";
            //n和name就是局部变量，作用域在cry方法中
            }
        }
        全局变量可以不赋值，直接使用，因为有默认值（属性不赋值有默认值）
        局部变量必须赋值，因为没有默认值
        作用域细节：
        （1）属性和局部变量可以重名，访问遵循就近原则
        eg.class Person{
           public void say(){
              String name = "king";
              System.out.println(name);
              会打印出king
           }
        }
        (2)属性生命周期较长，伴随对象的创建而创建，伴随对象销毁而销毁
        局部变量，生命周期较短，伴随代码块的执行而创建，伴随代码块的结束而销毁
        （在一次方法调用中）
        解析内存：
        （1）执行main方法时
┌─────────────────栈(stack)─────────────────┐   ┌─────────────堆(heap)─────────────┐
│                                           │   │                                 │
│ main方法栈帧:                               │   │                                 │
│   ┌─────────────────────────────┐         │   │  Person对象: 0x1001              │
│   │ args: String[]              │         │   │ ┌────────────────────────────┐  │
│   │ p1: Person (引用)─────┐      |         │   │ │ name: "jack"               │   │
│   │                      │      │         │   │ │ (全局变量/属性)              │
│   │                      ▼      │         │   │ └────────────────────────────┘  │
│   └─────────────────────────────┘         │   │                                 │
│                                           │   │                                 │
└───────────────────────────────────────────┘   └─────────────────────────────────┘
(2)执行p1.say()方法时
┌─────────────────栈(stack)──────────----────┐   ┌─────────────堆(heap)─────────────┐
│                                           │   │                                 │
│ say方法栈帧:                               │   │  Person对象: 0x1001              │
│   ┌─────────────────────────────┐        │   │ ┌────────────────────────────┐   │
│   │ 局部变量 name: "king"         │        │   │ │ name: "jack"               │  │
│   │ (暂时屏蔽了对象的name属性)       │        │   │ │ (全局变量/属性)              │  │
│   └─────────────────────────────┘         │   │ └────────────────────────────┘ │
│                                           │   │                                 │
│ main方法栈帧:                               │   │                                 │
│   ┌─────────────────────────────┐         │   │                                 │
│   │ args: String[]              │         │   │                                 │
│   │ p1: 0x1001 ────────────────┼─────────┼───▶                                 │
│   └─────────────────────────────┘         │   │                                 │
│                                           │   │                                 │
└───────────────────────────────────────────┘   └─────────────────────────────────┘
（3）say（）方法执行之后
┌─────────────────栈(stack)─────────────────┐   ┌─────────────堆(heap)─────────────┐
│                                           │   │                                 │
│ main方法栈帧:                               │   │  Person对象: 0x1001             │
│   ┌─────────────────────────────┐         │   │ ┌────────────────────────────┐ │
│   │ args: String[]              │         │   │ │ name: "jack"               │ │
│   │ p1: 0x1001 ─────────────── ┼─────────┼───▶ │ (全局变量/属性仍然存在)         │ │
│   │                             │         │   │ └────────────────────────────┘ │
│   └─────────────────────────────┘         │   │                                │
│                                           │   │                                │
│ (say方法的栈帧已经销毁，                      │   │                                │
│  局部变量name="king"不复存在)                │   │                               │
│                                           │   │                                │
└───────────────────────────────────────────┘   └────────────────────────────────┘
        public class Main{
            public static main(String[] args){
               Person p1 = new Person();
               p1.sat();
            }
        }
        class Person{
            String name = "Jack";
            public void say(){
               String name = "king";
            }
        }
        作用域注意事项：
        （1）全局变量/属性：可以被本类使用，也可以被其他类使用
        （2）属性可以加修饰符，局部变量不能加修饰符
        //
         */

    }
    public static class HspMethod{
        public HashMap record(String str,int...nums){
            int count = 0 ;
            for (int i = 0; i < nums.length; i++) {
                count+=nums[i];
            }
            HashMap hashMap = new HashMap<>();
            hashMap.put("name",str);
            hashMap.put("count",count);
            return hashMap;

        }
    }
    public static boolean factorial3(int[][] arr,int i,int j){
        //标记走过的路
        arr[i][j]=2;
        //先下后右
        if(arr[i+1][j]!=1){//不足之处（1）缺少边界检查，如果i=7会越界
            //不足之处（2）只考虑单方向，如果是死路无法尝试先右后左或者时回溯
            //应该有多方向的搜索
            factorial3(arr,i+1,j);
            //不足之处（3）：缺少返回值即便找到路径也可能被忽略
        } else if (arr[i][j+1]!=1) {
            factorial3(arr,i,j+1);
        }
        if(arr[6][6]==2){
            return true;
        }//递归结束得条件
        return false;
        //不足之处（4）缺少回溯机制（失败时应清除标记）
        //如果这条失败会直接返回失败，而由于arr[i][j]=2的标记会保留，无法撤销
        //回溯：以迷宫为例，试探性地前进，碰壁之后返回，能够回到上一个岔路口（回溯），
        // 并且撤销2的标记，同时以其他符号标记这条思路，继续尝试其他可能
    }
    //策略改进
    public static boolean factorial3_2(int[][] arr,int i,int j){
        if(i<0||i>arr.length||j<0||j>arr.length){
            return false;
        }
        //判断已经过或有障碍物
        if(arr[i][j]==1||arr[i][j]==2){
            return false;
        }
        arr[i][j]=2;
        if(i==6&&j==6){
            return true;
        }
        //下
        if(factorial3_2(arr,i+1,j)){
            return true;
        }
        //右
        if(factorial3_2(arr,i,j+1)){
            return true;
        }//确保走错路可以返回，形成回溯机制
        //上
        if(factorial3_2(arr,i-1,j)){
            return true;
        }
        //左
        if(factorial3_2(arr,i,j-1)){
            return true;
        }
        //回溯机制
        arr[i][j]=0;
        return false;

    }public static void TowerMove(int num,char a,char b,char c){
        //方法的第一个参数为原柱，第二个参数为辅助柱（如何辅助不用关心），第三个参数为目标柱，因而打印操作的时候会一直a->c,即原柱搬到目标柱，
        //并且递归使用时需要在第一个参数放入输入端，第三个参数放入输出端
        //无论有多少个塔，将其分为两部分，上面一罗和最下面一个
         if(num==1){//递归结束条件
             System.out.println(a+"->"+c);
         }else {
             // 将num看成两份，一份1个，一份num-1，现在解决num-1的操作
             //将a上num-1个盘子移到到b上，借助于c
             TowerMove(num-1,a,c,b);
             System.out.println(a+"->"+c);
             TowerMove(num-1,b,a,c);
             // 再将b上num-1个盘子移动到c，借助于a
         }
    }
    //先随机在0-7中随意生成两个数，这两个数作为一个皇后的坐标
    //对该坐标进行判断，首先判断该坐标是否位于边界，根据不同边界判断三个相邻坐标，如果不存在一个皇后返回true
    //如果不在边界，则检验周围八个位置是否有皇后，若无返回true
    //如果发现有，则返回false，并且将痕迹抹除
    public static boolean factorial4(int[][] arr,int n){
        if(n==9){
            return true;
        }
        int i = (int)(Math.random()*8);
        int j = (int)(Math.random()*8);
        arr[i][j]=2;
        //判断角落
        if(i==0&&j==0){
            if(arr[i+1][j+1]!=2&&arr[i][j+1]!=2&&arr[i+1][j]!=2){
                if(factorial4(arr,n+1)){
                return true;
                }
            }
        } else if(i==0&&j==7){
            if(arr[i+1][j-1]!=2&&arr[i][j-1]!=2&&arr[i+1][j]!=2){
                if(factorial4(arr,n+1)){
                    return true;
                }
            }
        }
        else if(i==7&&j==0){
            if(arr[i-1][j+1]!=2&&arr[i][j+1]!=2&&arr[i-1][j]!=2){
                if(factorial4(arr,n+1)){
                    return true;
                }
            }
        }else if(i==7&&j==7){
            if(arr[i-1][j-1]!=2&&arr[i][j-1]!=2&&arr[i-1][j]!=2){
                if(factorial4(arr,n+1)){
                    return true;
                }
            }//检查边界
        } else if (i == 0) {
            if(arr[i][j-1]!=2&&arr[i+1][j-1]!=2&&arr[i+1][j]!=2&&arr[i+1][j+1]!=2&&arr[i][j+1]!=2){
                if(factorial4(arr,n+1)){
                    return true;
                }
            }
        }else if(j==0){
            if(arr[i-1][j]!=2&&arr[i-1][j+1]!=2&&arr[i][j+1]!=2&&arr[i+1][j+1]!=2&&arr[i+1][j]!=2){
                if(factorial4(arr,n+1)){
                    return true;
                }
            }
        } else if (i==7) {
            if(arr[i][j-1]!=2&&arr[i-1][j-1]!=2&&arr[i-1][j]!=2&&arr[i-1][j+1]!=2&&arr[i][j+1]!=2){
                if(factorial4(arr,n+1)){
                    return true;
                }
            }
        } else if (j==7) {
            if(arr[i-1][j]!=2&&arr[i-1][j-1]!=2&&arr[i][j-1]!=2&&arr[i+1][j]!=2&&arr[i+1][j]!=2){
                if(factorial4(arr,n+1)){
                    return true;
                }
            }
        }else {
            if(arr[i-1][j-1]!=2&&arr[i-1][j]!=2&&arr[i-1][j+1]!=2&&arr[i][j+1]!=2&&arr[i+1][j+1]!=2&&arr[i+1][j]!=2&&arr[i+1][j-1]!=2&&arr[i][j-1]!=2){
                if(factorial4(arr,n+1)){
                    return true;
                }
            }
        }

        arr[i][j]=0;
        return false;

    }
    //用一维数组来表示在每一列中皇后所在的位置
    //先将第一个皇后放在（1，1），随后将第二个皇后放在（1，2），（1，3）...... 判断是否可行，
    // 然后依次执行，如果到某一个皇后发现没有位置时
    //返回到上一个皇后，让其重新再选位置，如果，再不行再返回到上一个皇后，直到第一个皇后
    //判断该位置是否可行：仍沿用先角后边界再中间的判断
    //结束条件：是否到达8个皇后
    public static int[] factorial4(int i,int j,int[][] arr,int n,int num){
        int[] res = new int[3];
        res[1]=n;
        res[2]=num;
        if (res[1] == 9) {
            res[2]++;
            return res;
        }
        int origin = i;
        arr[i][j]=2;
        //通过数组记录结果，第一个记录是否能通过，即true或false（1或0），
        // 第三个数字记录多少种类型，第二个数字记录当前是否达到8个皇后
        for (; i < arr.length; i++) {
            if(i==0&&j==0){
                if(arr[i+1][j+1]!=2&&arr[i][j+1]!=2&&arr[i+1][j]!=2){
                     res[0]=1;
                     if(factorial4(i,j+1,arr,n+1,res[2])[0]==1){
                         return res;
                     }

                }
            } else if(i==0&&j==7){
                if(arr[i+1][j-1]!=2&&arr[i][j-1]!=2&&arr[i+1][j]!=2){
                    res[0]=1;
                    if(factorial4(i,j+1,arr,n+1,res[2])[0]==1){
                        return res;
                    }


                }
            }
            else if(i==7&&j==0){
                if(arr[i-1][j+1]!=2&&arr[i][j+1]!=2&&arr[i-1][j]!=2){
                    res[0]=1;
                    if(factorial4(i,j+1,arr,n+1,res[2])[0]==1){
                        return res;
                    }

                }
            }else if(i==7&&j==7){
                if(arr[i-1][j-1]!=2&&arr[i][j-1]!=2&&arr[i-1][j]!=2){
                    res[0]=1;
                    if(factorial4(i,j+1,arr,n+1,res[2])[0]==1){
                        return res;
                    }

                }//检查边界
            } else if (i == 0) {
                if(arr[i][j-1]!=2&&arr[i+1][j-1]!=2&&arr[i+1][j]!=2&&arr[i+1][j+1]!=2&&arr[i][j+1]!=2){
                    res[0]=1;
                    if(factorial4(i,j+1,arr,n+1,res[2])[0]==1){
                        return res;
                    }

                }
            }else if(j==0){
                if(arr[i-1][j]!=2&&arr[i-1][j+1]!=2&&arr[i][j+1]!=2&&arr[i+1][j+1]!=2&&arr[i+1][j]!=2){
                    res[0]=1;
                    if(factorial4(i,j+1,arr,n+1,res[2])[0]==1){
                        return res;
                    }

                }
            } else if (i==7) {
                if(arr[i][j-1]!=2&&arr[i-1][j-1]!=2&&arr[i-1][j]!=2&&arr[i-1][j+1]!=2&&arr[i][j+1]!=2){
                    res[0]=1;
                    if(factorial4(i,j+1,arr,n+1,res[2])[0]==1){
                        return res;
                    }

                }
            } else if (j==7) {
                if(arr[i-1][j]!=2&&arr[i-1][j-1]!=2&&arr[i][j-1]!=2&&arr[i+1][j]!=2&&arr[i+1][j]!=2){
                    res[0]=1;
                    if(factorial4(i,j+1,arr,n+1,res[2])[0]==1){
                        return res;
                    }
                }
            }else {
                if (arr[i - 1][j - 1] != 2 && arr[i - 1][j] != 2 && arr[i - 1][j + 1] != 2 && arr[i][j + 1] != 2 && arr[i + 1][j + 1] != 2 && arr[i + 1][j] != 2 && arr[i + 1][j - 1] != 2 && arr[i][j - 1] != 2) {
                    res[0]=1;
                    if(factorial4(i,j+1,arr,n+1,res[2])[0]==1){
                        return res;
                    }
                }
            }

        }
        arr[origin][j]=0;
        res[0]=0;
        res[1]--;
        return res;
    }//优化2.0
    public static int[] factorial4(int row,int[][] arr,int num){
        int[] res = new int[2];
        //第一个参数记录总数，第二个参数记录是否存在
        res[0]=num;
        if(row>=8){
            res[0]=num+1;
            res[1]=1;
            printArray(arr);
            System.out.println();
            return res;
        }//递归结束条件
        for (int j = 0; j < arr[row].length; j++) {
            //尝试一行每一列
            arr[row][j]=2;
            //先假设这一列可行
            if(Judge(row,j,arr)){
                //判断这一列是否可行
                res[1]=1;//可行按照定义记录为1
                int[] nextResult = factorial4(row+1,arr,res[0]);
                //尝试下一行
                res[0] = nextResult[0];
                //获取下一级递归得到的总数
            }
            arr[row][j]=0;
            //如果这一列不成立，撤销选择

        }
        return res;

    }
    public static boolean Judge(int row,int col,int[][] arr){

        //判断某一列是否有2
        for (int i = 0; i < row; i++) {
            if(arr[i][col]==2){
                return  false;
            }
        }

        //判断对角线上是否有2
        // 2. 检查左上对角线
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (arr[i][j] == 2) {
                return false;
            }
        }

        // 3. 检查右上对角线
        for (int i = row - 1, j = col + 1; i >= 0 && j < 8; i--, j++) {
            if (arr[i][j] == 2) {
                return false;
            }
        }

        return true;
    }





    public static void printArray(int[][] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }



    public static void A(int n){
        if(n>2){
            A(n-1);
            /*
            3传入，开辟一块新区域（2）3>2，所以2会再传入，开辟一块新区域（3）但2=2，不会执行if语句，而是打印n=2
            区域（3）执行完返回到区域（2），区域执行未执行的打印语句，输出n=3，区域（2）执行完毕返回到区域（1）如法炮制输出n=4
            区域（1）执行完返回到main函数区域
             */
        }
        System.out.println("n="+n);
    }
    public static int factorial(int n){
        if(n>1){
            n=n*factorial(n-1);
        }else{
            n=1;
        }
        return n;
    }
    public static int factorial1(int n){
        if(n==1||n==2) {
            return 1;
        }
        return factorial1(n-1)+factorial1(n-2);
    }
    public static int factorial2(int day,int surplus){
        if(day>=1){
            return factorial2(day-1,(surplus+1)*2);
        } else if (day<1) {
            return -1;
        }
        return (surplus+1)*2;
    }
    public static class Methods{
        public void calculate(int a,int b){
            System.out.println("和的结果为："+(a+b));
        }
        public void calculate(int n){
            System.out.println("平方的结果为："+n*n);
        }
        public void calculate(String str){
            System.out.println("字符串为："+str);
        }
        public int max(int a,int b){
            if(a>b){
                return a;
            }
            return b;
        }
        public double max(double a,double b){
            if(a>b){
                return a;
            }
            return b;
        }
        public double max(double a,double b,double c){
            double max = max(a,b);
            max=max(max,c);
            return max;
        }
    }
}