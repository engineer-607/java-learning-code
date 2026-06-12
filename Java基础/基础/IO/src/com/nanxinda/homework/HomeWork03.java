package com.nanxinda.homework;

import java.io.*;
import java.util.Properties;
@SuppressWarnings({"all"})
public class HomeWork03 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Properties properties = new Properties();
        properties.load(new FileReader("src\\mysql3.properties"));
        Dog dog = new Dog();
        dog.setAge(new Integer(properties.getProperty("age")));
        dog.setName(properties.getProperty("name"));
        dog.setColor(properties.getProperty("color"));
        System.out.println(dog);
        String filePath = "d:\\news2.dog";
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath));
        outputStream.writeObject(dog);
        outputStream.close();
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filePath));
        System.out.println(objectInputStream.readObject());


    }
}
