package com.nanxinda.OPPexercise;

public class Exercise06 {
    public static void main(String[] args) {
//       Person person = new Person("唐僧");
//        person.setVehicle("大河");
//        System.out.println(person.getVehicle().work());
        Person person = new Person("唐僧",new Horse());
        person.common();
        person.PassRiver();

    }
}
interface Vehicles{
    public String work();
}
class Horse implements Vehicles{
    @Override
    public String work() {
        return "交通工具🐎在工作";
    }
}
class Boat implements Vehicles{

    @Override
    public String work() {
        return "交通工具船在工作";
    }
}
class Airplane implements Vehicles{
    @Override
    public String work() {
        return "交通工具飞机在工作";
    }
}
class Factory{//采用饿汉式，调用之前就先实例出一个对象
    static private Horse saveHorse = new Horse();
    private Factory(){}//构造器私有化
    static public Vehicles getHorse(){
        return saveHorse;//只能通过固定的方法获得已经实例好的唯一对象
    }
    static public Vehicles getBoat(){
        return new Boat();
    }
    static public Vehicles getAirPlane(){
        return new Airplane();
    }

}
class Person{
    private String name;
    private Vehicles vehicle;

    public Person(String name,Vehicles vehicle) {
        this.name = name;
        this.vehicle = vehicle;
    }

//    public void setVehicle(String situation) {
//        if(situation.equals("大河")){
//            vehicle = Factory.getBoat();
//        }else {
//            vehicle = Factory.getHorse();
//        }
//    }
    //将一般情况下用马，渡河情况下用船封装成方法
    public  void common(){
        if(!(vehicle instanceof Horse)){
            vehicle = Factory.getHorse();

        }
        System.out.println(vehicle.work());
    }
    public void PassRiver(){
        if(!(vehicle instanceof Boat)){
            vehicle = Factory.getBoat();
        }
        System.out.println(vehicle.work());
    }
    public  void PassMountain(){
        if(!(vehicle instanceof Airplane)){
            vehicle = Factory.getAirPlane();
        }
        System.out.println(vehicle.work());
    }

    public Vehicles getVehicle() {
        return vehicle;
    }
}