package com.nanxinda.ObjectInputStreamANDObjectOutputStream;

import java.io.*;

@SuppressWarnings({"all"})
public class ObjectIO {
    public static void main(String[] args) throws Exception {
///1.序列化：在保存数据时，保存数据的值和数据类型
///2.反序列化：在恢复数据时，恢复数据的值和数据类型
///3.需要让某个对象支持序列化机制，则必须让其类是可序列化的，则该类就必须实现如下两个
///接口之一：1）Serializable（这是一个标记接口，没有方法）2）Externalizable（这个接口有方法需要实现）
///一般实现Serializable接口

        //序列化数据到d:\news3.txt
        String filePath = "d:\\news3.txt";
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath));
        outputStream.writeInt(100);//int ->Integer(实现Serializable)
        outputStream.writeBoolean(true);//boolean->Boolean(实现Serializable)
        outputStream.writeChar('a');//char ->Character(实现Serializable)
        outputStream.writeUTF("hello，树先生");//String(实现Serializable)
        outputStream.writeObject(new Dog(3,"大黄"));
        outputStream.writeObject(new Dog("小白",3,"白色","中国"));
        outputStream.close();

        //读取的顺序（反序列化）应该与保存数据（序列化）的顺序一致
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filePath));
        System.out.println(objectInputStream.readInt());
        System.out.println(objectInputStream.readBoolean());
        System.out.println(objectInputStream.readChar());
        System.out.println(objectInputStream.readUTF());
        System.out.println(objectInputStream.readObject());
//        ((Dog)(objectInputStream.readObject())).print();
        System.out.println(objectInputStream.readObject());
        /// 序列化时不会传入静态变量nation
        /// 但是在反序列化时JVM会创建一个对象Dog类
        /// 而静态变量时所有对象共享的，保存在类的方法区i，所以会输出中国，但是nation的值
        /// 并不是从文件中获取的
        objectInputStream.close();

        //序列化和反序列化的细节：
        //1.读写顺序一致
        //2.要求序列化和反序列化对象，需要实现Serializable
        //3.序列化的类中建议添加serialVersionUID序列化的版本号，可以提高兼容性
        //4.序列化对象时，默认将里面所有属性都进行序列化，但除了static或transient修饰的成员
        //5.序列化对象时，要求里面属性的类型也需要实现序列化接口
        //6.序列化具备可继承性，也就是如果某类已经实现序列化，则他的所有子类也默认实现序列化
    }
}
class Dog implements Serializable{
    private  String name;
    private int age;
    //serialVersionUID序列化的版本号，可以提高兼容性（例如，如果Dog后续添加新的属性
    //在进行序列化和反序列化时不会认为是新的Dog类，而是版本更新）
    private static final long serialVersionUID = 1L;
    private static String nation;
    private transient String color;

    public Dog(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public Dog(String name, int age, String color,String nation) {
        this.name = name;
        this.age = age;
        this.color = color;
        this.nation = nation;
    }

    public static String getNation() {
        return nation;
    }

    public static void setNation(String nation) {
        Dog.nation = nation;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}'+nation;
    }
}