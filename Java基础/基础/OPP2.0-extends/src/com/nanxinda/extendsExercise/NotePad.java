package com.nanxinda.extendsExercise;

public class NotePad extends Computer{
    private String color;
    public NotePad(String CPU,String internal,String HardDisk,String color){
        super(CPU, internal, HardDisk);
        this.color=color;
    }
    public void showCINfo(){
        System.out.print(showInfo()+" color="+color);
    }
}
