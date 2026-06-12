package com.nanxinda.ploy02;

public class Master {
    public String name;

    public Master(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String feed(Animal animal,Food food){
        return "主人"+getName()+"在喂"+animal.getName()+"吃"+food.getName();
    }
}
