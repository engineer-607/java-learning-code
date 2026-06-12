package com.nanxinda.ployarr;

public class PloyArr {
    public static void main(String[] args) {
        Person person[]={new Person("jack",18),
                new Student("lucy",19,100),
                new Student("mike",20,90),
                new Teacher("dong",34,30000),
                new Teacher("zhang",32,40000)};
        for (int i = 0; i < person.length; i++) {
            if(person[i] instanceof Student){
                System.out.println(((Student)person[i]).study());
            }else if(person[i] instanceof Teacher){
                System.out.println(((Teacher)person[i]).teach());
            }
                System.out.println(person[i].say());

        }
    }

}
