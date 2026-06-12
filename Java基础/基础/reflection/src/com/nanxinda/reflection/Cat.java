package com.nanxinda.reflection;
public class Cat {
    private String name = "招财猫";
    public int age = 2;

    public Cat() {
    }

    public Cat(String name){
        this.name = name;
    }
    public void hi(){
        System.out.println("你好"+name);
    }

}
