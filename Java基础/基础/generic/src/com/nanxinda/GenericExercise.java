package com.nanxinda;

import java.util.*;

@SuppressWarnings({"all"})
public class GenericExercise {
    public static void main(String[] args) {
        Student s1 = new Student("jack",19);
        Student s2 = new Student("lucy",20);
        Student s3 = new Student("mike",21);
        Set<Student> sets = new HashSet<Student>();
        sets.add(s1);
        sets.add(s2);
        sets.add(s3);
        for (Student student : sets) {
            System.out.println(student);
        }
        Map<String,Student> map = new HashMap<String,Student>();
        //K->String/V->Student
        map.put(s1.getName(),s1);
        map.put(s2.getName(),s2);
        map.put(s3.getName(),s3);
        Set<Map.Entry<String,Student>> set = map.entrySet();
        //public class HashMap<K,V> extends AbstractMap<K,V>
        //    implements Map<K,V>, Cloneable, Serializable
        //public Set<Map.Entry<K,V>> entrySet()
        Iterator<Map.Entry<String,Student>> iterator = set.iterator();
        //public final Iterator<Map.Entry<K,V>> iterator()
        while (iterator.hasNext()) {
            Map.Entry entry =  iterator.next();
            System.out.println(entry.getKey()+"-"+entry.getValue());
        }

    }
}
class Student{
    private String name;
    private int ages;

    public Student(String name, int ages) {
        this.name = name;
        this.ages = ages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAges() {
        return ages;
    }

    public void setAges(int ages) {
        this.ages = ages;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", ages=" + ages +
                '}';
    }
}