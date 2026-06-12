package com.nanxinda.Object.equalsExercise;

public class Person {
    private String name;
    private int age;
    private char gender;

    public Person(char gender, int age, String name) {
        this.gender = gender;
        this.age = age;
        this.name = name;
    }
    public Person(char gender, String name, int age) {
        this.gender = gender;
        this.age = age;
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object obj) {
        if(super.equals(obj)){
            return true;
        }
        if(obj instanceof Person){
            Person person = (Person) obj;
            if(person.getName().equals(name)&&person.getAge()==age&&person.getGender()==getGender()){
                return true;
            }
            return false;
        }
        return false;
    }
    public boolean oldEquals(Object object){
        return super.equals(object);
    }
}
