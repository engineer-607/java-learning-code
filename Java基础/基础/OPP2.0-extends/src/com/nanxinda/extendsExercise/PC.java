package com.nanxinda.extendsExercise;

public class PC extends Computer{
    public String brand;
    public PC(String CPU,String internal,String HardDisk,String brand){
        super(CPU, internal, HardDisk);
        this.brand=brand;
    }
    public void showBInfo(){
        System.out.println(showInfo()+" brand="+brand);
    }
}
