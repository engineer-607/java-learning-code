package com.nanxinda;

import java.util.*;

public class HomeWork01 {
    public static void main(String[] args) {

    }
}
class DAO<T>{
    Map<Integer,T> map;

    public DAO() {
        map = new HashMap<>();
    }

    public void save(int id, T entity){
        map.put(id,entity);
    }
    public T get(int id){
        return map.get(id);
    }
    public void update(int id,T entity){
        save(id,entity);
    }
    public List<T> list(){
        Set<Map.Entry<Integer,T>> sets = map.entrySet();
        List<T> arrayList = new ArrayList<>();
        for(Map.Entry<Integer,T> set :sets) {
            arrayList.add(set.getValue());
        }
        return arrayList;
    }
    public void delete(int id){
        map.remove(id);
    }
}
class User{
    private int age;
    private int id;
    private String name;

    public User(int age, int id, String name) {
        this.age = age;
        this.id = id;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}