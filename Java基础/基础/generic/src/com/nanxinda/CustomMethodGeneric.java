package com.nanxinda;

public class CustomMethodGeneric {
    public static void main(String[] args) {
        /*
        修饰符 <T,R...>返回类型 方法名(参数列表){}
         */
        Car car = new Car();
        car.fly("宝马",100);//当调用方法时，传入参数，编译器，就会确定类型
        //自动装箱为String,Integer
        car.fly(3000,100.1);
        //自动装箱Integer,Double
    }
}
//泛型方法，可以定义在普通类中，也可以定义在泛型类中
class Car{//普通类
    public void run(){//普通方法
    }
    public <T,R> void fly(T t,R r){//泛型方法
        System.out.println(t.getClass());
        System.out.println(r.getClass());
    }
}
class Fish<T,R>{//泛型类
    public void run(){//普通类
    }
    public <U,N> void eat(U u,N n){
        //泛型方法
    }
    //说明
    //1.下面hi方法不是泛型方法
    //2.是hi方法使用类声明的泛型
    public void hi(T t){
    }
    //泛型方法可以使用自己定义泛型，也可以使用类定义的泛型
    public <M> void swim(T t,M m){

    }
}