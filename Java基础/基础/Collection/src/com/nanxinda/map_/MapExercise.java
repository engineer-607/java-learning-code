package com.nanxinda.map_;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"all"})
public class MapExercise {
    public static void main(String[] args){
        Map map = new HashMap();
        map.put("2025834",new Staff("jack",30000,"2025834"));
        map.put("2025835",new Staff("john",3000,"2025835"));
        map.put("20258356",new Staff("lucy",18200,"2025836"));
        Set sets = map.keySet();
        double salsry;
        for (Object key :sets) {
            if((salsry=((Staff)map.get(key)).getSalary())>18000)
            System.out.println(key+"-"+map.get(key));
        }
        Iterator iterator = sets.iterator();
        while (iterator.hasNext()) {
            Object key =  iterator.next();
            if((salsry=((Staff)map.get(key)).getSalary())>18000)
                System.out.println(key+"-"+map.get(key));
        }

    }
}
class Staff{
    private String name;
    private double salary;
    private String id;
    public Staff(String name, double salary, String id) {
        this.name = name;
        this.salary = salary;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", id='" + id + '\'' +
                '}';
    }
}