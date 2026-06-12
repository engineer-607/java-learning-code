package com.nanxinda.ploy02;

public class Cat extends Animal{
    public Cat(String name) {
        super(name);
        this.name=name;
    }
    public String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
