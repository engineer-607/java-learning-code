package com.nanxinda.cases;

public class case01 {
    public static void main(String[] args) {
        Person[] people = {new Person("jack",18,"engineer")
        ,new Person("mike",20,"doctor")
        ,new Person("lucy",22,"scientist")};
        new Person().sort(people);
        for (int i = 0; i < people.length; i++) {
            System.out.println(people[i].toString());
        }
    }

}
class Person{
    private String name;
    private int age;
    private String job;

    public Person() {
    }

    public Person(String name, int age, String job) {
        this.name = name;
        this.age = age;
        this.job = job;
    }

    public void sort(Person[] people){
        int i=0,j=0;
        for (i = 0; i < people.length; i++) {
            for ( j = 0; j < people.length-1-i; j++) {
                if(people[j].age>people[j+1].age){
                    Person person = people[j];
                    people[j]=people[j+1];
                    people[j+1]=person;
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", job='" + job + '\'' +
                '}';
    }
}
