package com.nanxinda.OPPexercise;

public class Exercise07 {
    public static void main(String[] args) {
        Automobile.Air air = new Automobile(30).new Air();
        air.flow();

    }
}
class Automobile{
    private double temperature;

    public Automobile(double temperature) {
        this.temperature = temperature;
    }
    class Air{
        public void flow(){
            if(temperature>40){
                System.out.println("吹冷风");
            }else if(temperature<0){
                System.out.println("吹暖气");
            }else {
                System.out.println("关掉空调");
            }
        }
    }
}