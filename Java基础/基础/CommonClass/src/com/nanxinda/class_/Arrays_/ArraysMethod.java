package com.nanxinda.class_.Arrays_;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ArraysMethod {
    public static void main(String[] args) {
        Integer[] integers = {1,20,30};
        //1.使用Arrays.toString方法显示数组
        System.out.println(Arrays.toString(integers));
        //2.使用sort方法进行排序（数组是引用类型，排序后会直接影响到实参arr
        Integer arr[] = {1,-1,7,0,89};
        //默认排序
        System.out.println("默认排序");
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        //定制排序
        Arrays.sort(arr, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                Integer i1 = (Integer) o1;
                Integer i2  = (Integer) o2;
                return i2 - i1;//从大到小排序
            }
        });
        System.out.println(Arrays.toString(arr));
        //
        /*
        源码分析：
        1）调用定制排序时，传入两个参数1.排序数组arr2.实现Comparator接口的匿名内部类
        要求实现compare方法
        2）传入参数后最终会到Timsort的private static <T> void binarySort(T[] a, int lo, int hi, int start,
                                       Comparator<? super T> c)
        3）执行到binarySort方法的代码，会根据动态绑定机制c.compare()执行我们传入的
        匿名内部类的compare()方法
            while (left < right) {
                int mid = (left + right) >>> 1;
                if (c.compare(pivot, a[mid]) < 0)
                    right = mid;
                else
                    left = mid + 1;
            }
        4）public int compare(Integer o1, Integer o2)返回的值>0还是<0会影响
        整个排序结果
         */
        Integer[] arr1= {1,90,8,4,5};
        bubble(arr1, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Integer i1 = (Integer) o1;
                Integer i2  = (Integer) o2;
                return i2 - i1;
            }
        });
        System.out.println(Arrays.toString(arr1));
        //3.使用binarySearch 通过二分法搜索进行查找，要求必须有序
        //如果数组中不存在的话。则返回-(low+1)
        Integer[] arr2 = {1,2,90,111,789};
        int index = Arrays.binarySearch(arr2,111);
        System.out.println(index);
        //4.copyOf 数组元素的复制
        Integer[] newArr = Arrays.copyOf(arr2,arr2.length);
        System.out.println(Arrays.toString(newArr));
        //如果拷贝的长度大于arr.length就在新数组后面增加null
        //[1, 2, 90, 111, 789,null]
        //如果拷贝长度<0，就会抛出异常NegativeArraySizeException
        //5.fill数组元素的补充（使用99去填充num数组，可以理解成是成员替换原来的元素）
        Integer[] num = new Integer[]{9,3,2};
        Arrays.fill(num,99);
        System.out.println(Arrays.toString(num));
        //6.equals比较两个数组元素内容是否完全一致
        boolean equals = Arrays.equals(arr,arr2);
        System.out.println("equals="+equals);
        //7.asList将一组值，转化list
        List list = Arrays.asList(2,3,4,5,6,1);
        System.out.println("list"+list);
        System.out.println("list的运行类型为"+list.getClass());
        //1）asList方法，会将(2,3,4,5,6,1)数据转成一个List集合
        //2）返回的asList编译类型 List（接口）
        //3）asList运行类型java.util.Arrays#ArrayList,是Arrays类的
        //静态内部类private static class ArrayList<E> extends AbstractList<E>
        //        implements RandomAccess, java.io.Serializable

    }
    //模拟定制排序
    public static void bubble (Integer[] arr,Comparator comparator){
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 0; j < arr.length-i-1; j++) {
                if(comparator.compare(arr[j],arr[j+1])>0){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }
}
