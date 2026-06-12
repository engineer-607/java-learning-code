package com.nanxinda.set_;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"all"})
public class HashSetDetail {
    public static void main(String[] args) {
        //1.HashSet实现Set接口
        Set set = new HashSet();
        /*2.HashSet实际上是HashMap
    public HashSet() {
        map = new HashMap<>();
    }
         */
        //3.执行add方法时，会返回boolean值
        //如果添加成功，返回true，否则返回false
        System.out.println(set.add("jack"));//T
        System.out.println(set.add("lucy"));//T
        System.out.println(set.add("john"));//T
        System.out.println(set.add("jack"));//F
        System.out.println(set.add("Rose"));//T
        //4.可以通过remove方法指定删除哪个对象
        set.remove("jack");
        System.out.println("set="+set);

        set = new HashSet();
        set.add("lucy");//成功添加
        set.add("lucy");//添加不了
        set.add(new Dog("tom"));//能够成功添加
        set.add(new Dog("tom"));//无法成功添加
        System.out.println("set="+set);
        set.add(new String("hsp"));//成功添加
        set.add(new String("hsp"));//添加失败
        System.out.println("set="+set);

    }
}
class Dog{
    private String name;

    public Dog(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                '}';
    }
}
