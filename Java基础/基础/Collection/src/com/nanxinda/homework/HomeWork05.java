package com.nanxinda.homework;

import java.util.HashSet;
import java.util.Objects;

@SuppressWarnings({"all"})
public class HomeWork05 {
    public static void main(String[] args) {
        HashSet set = new HashSet();
        Person p1 = new Person(1001, "AA");
        //根据1001，AA得到p1.hashCode() = Objects.hash(1001,"AA") 假设为h1
        Person p2 = new Person(1002, "BB");
        //根据1002，BB得到p2.hashCode() = Objects.hash(1002,"BB") 假设为h2
        set.add(p1);
        set.add(p2);
        p1.name = "CC";
        //此时p1的hash值已经不再为h1，发生改变假设为h3
        set.remove(p1);//false，set根据p1的哈希值在h3桶进行删除，但是真正p1其实存储在h1桶，所以会无法删除
        System.out.println(set);
        //// [Person{name='CC', num=1001}, Person{name='BB', num=1002}]
        set.add(new Person(1001, "CC"));//true
        //添加的对象其实是修改过后的p1，由于这时h3桶没有存储Node对象，set就会将新的Person对象封装成
        //Node对象，添加到h3桶
        System.out.println(set);
        // [Person{name='CC', num=1001}, Person{name='BB', num=1002},
        //  Person{name='CC', num=1001}]
        set.add(new Person(1001, "AA"));//true
        //检查h1桶是否有Node对象，发现有，又发现不是红黑树，再检查是否与存储的Node对象相等
        //发现不等，将其添加到链表之后
        System.out.println(set);
        // h1桶：Person{name='CC', num=1001}（原p1） → Person{name='AA', num=1001}（新）
        // h2桶：Person{name='BB', num=1002}
        // h3桶：Person{name='CC', num=1001}

        //总结：
        //1.修改哈希相关字段的危险性：导致对象"失联"
        //
        //2.可能产生重复数据：虽然有两个 (1001, "CC") equals 为 true，但因为哈希值不同，被视为不同对象
        //
        //3.可能出现哈希冲突链：两个不同对象 (1001,"CC") 和 (1001,"AA") 在同一个桶中形成链表
    }
}
class Person{
    public String name;
    public int num;

    public Person( int num,String name) {
        this.name = name;
        this.num = num;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Person person = (Person) object;
        return num == person.num && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, num);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", num=" + num +
                '}';
    }
}
