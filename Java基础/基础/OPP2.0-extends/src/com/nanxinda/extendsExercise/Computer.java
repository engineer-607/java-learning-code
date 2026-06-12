package com.nanxinda.extendsExercise;

public class Computer {
    private String CPU;
    private String internal;
    private String HardDisk;
    public Computer(String CPU,String internal,String HardDisk){
        this.CPU=CPU;
        this.internal=internal;
        this.HardDisk=HardDisk;
    }
    public String showInfo(){
        return "CPU="+CPU+" internal="+internal+" HardDisk="+HardDisk;
    }
}
