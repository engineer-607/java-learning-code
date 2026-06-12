package com.nanxinda.ploy02;

public class Fish extends Food{
    public Fish(String name) {
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
