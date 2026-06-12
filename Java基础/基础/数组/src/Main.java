import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /*数组：是多个相同类型数据的组合
        （1）先声明在创建
        声明数组
        int a[]||int[] a,内存空间此时为null
        创建数组
        a=new int[10]，分配内存空间
        （2）声明创建同时
        int a[]=new int[10];
        初始化数组
        int a[]={1,2,3,4,5,6,7,8,9,10};

         */
        // 案例一
        char[] chars = new char[26];
        chars[0]='A';
        for (int i = 0; i < chars.length; i++) {
            chars[i]= (char) (chars[0]+i);
            System.out.print(chars[i]+" ");
        }
        //案例二
        System.out.println();
        int array[]= {4,-1,9,10,23}, max=array[0],index=0;
        for (int i = 0; i < array.length; i++) {
            if(array[i]>max){
                max=array[i];
                index=i;
            }
        }
         System.out.print("寻找最大值：");
        System.out.println("最大值为"+max+"所对应的下标"+index);
            /*
    基本数据类型赋值为值拷贝
     int n1=10;
     int n2=n1;
     n2=80;
     n1的值仍为10
    数组赋值机制
    数组赋值方式为引用传递，地址传递
    int array1[]={1,2,3};
    int array2[]=arr1;
    array2[0]=10;//  array1发生变化为{10，2，3}，array2,array1都指向堆中的同一地址，即二者数据空间一致，修改数组0索引array1，array2
    都会发生改变，
    jvm中有栈，堆，方法区

     */
        //数组拷贝，需要开辟新的空间
        int[] array1={1,2,3};
        int[] array2=new int[3];
        for(int i=0;i<array1.length;i++){
            array2[i]=array1[i];
        }
        System.out.print("数组拷贝：");
        for (int i = 0; i < array2.length; i++) {
            System.out.print(array2[i]+" ");
        }
        System.out.println();
        //数组翻转（1）
         int[] array3={1,2,3,4,5,6,7,8,9};
        int[] array4=new int[array3.length];
        for (int i = 0, j=array4.length-1; i < array3.length&&j>=0; i++,j--) {
            array4[i]=array3[j];
        }
        System.out.print("数组翻转方案一：");
        for (int i = 0; i < array4.length; i++) {
            System.out.print(array4[i]+" ");
        }
        //验证地址传递
        System.out.print("对地址传递进行验证：");
        array3=array4;//array3原本的数据空间没有变量使用，数据空间被销毁
        for (int i = 0; i < array3.length; i++) {
            System.out.print(array3[i]+" ");
        }

        System.out.println();
        //数组翻转（2）（已经遗忘）
        for(int i=0;i< array3.length/2;i++){
            int temp=array3[i];
            array3[i] = array3[array3.length-i-1];
            array3[array3.length-i-1] = temp;
        }
        System.out.print("数组翻转方案二（复习重点关注）：");
        for (int i = 0; i < array3.length; i++) {
            System.out.print(array3[i]+" ");

        }
        //数组添加,扩容需要开辟新空间
         System.out.println();
        System.out.print("是否需要添加元素");
        int[] arr={1,2,3};
        int index_=3;
        Scanner scanner = new Scanner(System.in);
        String result= scanner.next();
        while (result.equals("yes")){
            index_++;
            int[] arr1 = new int[index_];
            System.out.print("请输入需要添加的整型元素：");
            int number = scanner.nextInt();
            for (int i = 0; i < arr.length; i++) {
                arr1[i]=arr[i];
            }
            arr1[arr1.length-1]=number;
            arr=arr1;
            System.out.print("是否继续添加元素");
            result=scanner.next();
        }

        System.out.print("数组现在的元素：");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
        //数组缩减
        System.out.println();
        System.out.print("是否缩减元素");
        Scanner scanner1 = new Scanner(System.in);
        String result1 = scanner1.next();
        while(result1.equals("yes")&&index_>=0){
            index_--;
            System.out.print("需要删除第几个元素");
             int index2=scanner1.nextInt()-1;
             int[] arr1 = new int[index_];
            for (int i = 0; i < index2; i++) {
                arr1[i]=arr[i];
            }
            for (int i = index2+1; i < index_+1; i++) {
                arr1[i-1] = arr[i];
            }
            arr= arr1;
            System.out.print("是否继续删除元素");
            result1 = scanner1.next();
        }
        System.out.print("数组现在的元素：");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
        //冒泡排序法

        System.out.println();
        System.out.print("冒泡排序结果：");
        int[] arr5 = {24,69,80,57,13};
        boolean judge;//冒泡排序法的优化：记录每轮排序之中是否进行元素交换，若没有，则排序已完成，直接结束排序
        for (int i = 0; i < arr5.length-1; i++) {
            judge=true;
            for (int j = 0; j < arr5.length-i-1; j++) {
                if(arr5[j]>arr5[j+1]){
                    judge=false;
                    int temp = arr5[j];
                    arr5[j]=arr5[j+1];
                    arr5[j+1] = temp;
                }
            }
            if(judge){
                break;
            }
        }
        for (int i = 0; i < arr5.length; i++) {
            System.out.print(arr5[i]+ " ");
        }
       //二维数组
        /*
        二维数组在内存存储形式： int arr[][] = new int[2][3];
        在栈中arr指向一个地址，这个地址在堆中存放着二维数组，二维数组的arr[0],arr[1]分别为各自一维数组的地址
        二维数组进行初始化时允许列数不确定
         int[][] arr = new int[3][];
         创建二维数组，但是只是确定一维数组的个数，但是每个一维数组还未开空间
         后续使用一维数组的时候必须提前开空间，因为存在二维数组中的arr[0],arr[1],arr[2]这些都是null，没有一维数组的地址
         */
        System.out.println();
        System.out.println("静态初始化：");
        int[][] arr3 = {{0,0,0,0,0,0},{0,0,1,0,0,0},{0,2,0,3,0,0},{0,0,0,0,0,0}};
        for (int i = 0; i < arr3.length; i++) {
            for (int j = 0; j < arr3[i].length; j++) {
                System.out.print(arr3[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("动态初始化：");
        int[][] arr4 = new int[3][];
        for (int i = 0; i < arr4.length; i++) {
            arr4[i] =new int [i+1];// 开辟一维数组的空间
            for (int j = 0; j < arr4[i].length; j++) {
                arr4[i][j] = i+1;
            }
        }
        for (int i = 0; i < arr4.length; i++) {
            for (int j = 0; j < arr4[i].length; j++) {
                System.out.print(arr4[i][j]+" ");
            }
            System.out.println();
        }
        //二维数组案例，杨辉三角

        System.out.println("杨辉三角：");

        int[][] arrYH = new int[10][];
        for (int i = 0; i < arrYH.length; i++) {
             arrYH[i] = new int[i+1];
                 arrYH[i][0]=1;
                 arrYH[i][i]=1;
             if(i>=1){
                 for (int j = 1; j <i ; j++) {
                     arrYH[i][j]=arrYH[i-1][j]+arrYH[i-1][j-1];
                 }
             }
        }
        for (int i = 0; i < arrYH.length; i++) {
            for (int j = 0; j < arrYH[i].length; j++) {
                System.out.print(arrYH[i][j]+" ");
            }
            System.out.println();
        }
        /*二维数组细节：
         int[] y[]也可以表示二维数组
         */
        //作业1
        //String strs[] = new String[]{"a","b","c"}; 这是正确的
        //作业2
        // 布尔类型的数组初始值为false
        //作业4（自己所写），缺点：冒泡排序不适合已经有序的数组
        int index2 =4;
         int[] arr6 = {10,12,45,90};
         Scanner scanner2 = new Scanner(System.in);
         System.out.print("是否需要添加元素");
         String result2  = scanner2.next();
         while(result2.equals("yes")){
             index2++;
             int[] arr7 = new int[index2];
             System.out.println("请输入需要添加的元素:");
             for (int i = 0; i <index2-1; i++) {
                 arr7[i]=arr6[i];
             }
             int number1 = scanner2.nextInt();
             arr7[index2-1]=number1;
             for (int i = 0; i < arr7.length-1; i++) {
                 judge=true;
                 for (int j = 0; j < arr7.length-i-1; j++) {
                     if(arr7[j]>arr7[j+1]){
                         judge=false;
                         int temp = arr7[j];
                         arr7[j]=arr7[j+1];
                         arr7[j+1] = temp;
                     }
                 }
                 if(judge){
                     break;
                 }
             }
             arr6=arr7;
             System.out.println(" 是否要继续添加元素");
             result2=scanner2.next();
         }
        System.out.println("现在的元素：");
        for (int i = 0; i < arr6.length; i++) {
            System.out.print(arr6[i]+" ");
        }
        //作业4（课堂所讲）：顺序查找
         int index3=-1;
         int number4 = 23;
        for (int i = 0; i < arr6.length; i++) {
            if(number4<=arr6[i]){
                index3=i;
                break;
            }
        }
        if(index3==-1){
            index3=arr6.length;
        }
        int[] arr7 =new int[arr6.length+1];
        for (int i = 0,j=0; i < arr7.length; i++) {
            if(i==index3){
                arr7[i]=number4;
            }else {
               arr7[i] =arr6[j];
               j++;
            }
        }
        //作业五
        System.out.println();
        int[] numbers = new int[10];
        int count=0;
        for (int i = 0; i < numbers.length; i++) {
            numbers[i]= (int) (Math.random()*100)+1;
            count+=numbers[i];
        }
        double average = 1.0*count/10;
        System.out.println("和为"+count+" 平均数为"+average);
        for (int i = 0; i < numbers.length-1; i++) {
            for (int j = 0; j < numbers.length-i-1; j++) {
                if(numbers[j]>numbers[j+1]){
                    int temp=numbers[j];
                    numbers[j]=numbers[j+1];
                    numbers[j+1]=temp;
                }
            }
        }
        boolean judge1 =false;
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i]+" ");
            if(numbers[i]==8){
                judge1=true;
            }
        }
        System.out.println("最大值为"+numbers[0]);
        if(judge1){
            System.out.println("数组中存在8");
        }





    }
}