package com.nanxinda.hash;

import java.util.Scanner;

public class HashTableDemo {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable(8);
        Scanner scanner = new Scanner(System.in);
        String key = "";
        boolean judge = true;
        while (judge){
            System.out.println("add:添加雇员");
            System.out.println("list:显示雇员");
            System.out.println("find:查找雇员");
            System.out.println("exit:退出系统");
            key = scanner.next();
            switch (key){
                case "add":
                    System.out.println("请输入姓名：");
                    String name = scanner.next();
                    System.out.println("请输入id");
                    int id = scanner.nextInt();
                    Employer employer = new Employer(name,id);
                    hashTable.add(employer);
                    break;
                case "list":
                    hashTable.list();
                    break;
                case "exit":
                    judge = false;
                    break;
                case "find":
                    System.out.println("请输入查找雇员的id：");
                    hashTable.find(scanner.nextInt());
                    break;
                default:
                    System.out.println("输入错误，请重新输入");
            }
        }

    }
}
class HashTable{
    private static class SingleLinkedList{
        private Employer  head;
        public void add(Employer employer){
            if(head==null){
                head = employer;
                return;
            }
            Employer temp = head;
            while (true){
                if(temp.getNext()==null){
                    break;
                }
                temp = temp.getNext();
            }
            temp.setNext(employer);
        }
        public void list(){
            if(head==null){
                System.out.println("链表为空");
                return;
            }
            Employer temp = head;
            while (true){
                System.out.printf("id=%d name=%s\t\n",temp.getId(),temp.getName());
                if(temp.getNext()==null){
                    break;
                }
                temp = temp.getNext();
            }
        }
        public void find(int id){
            if(head==null){
                System.out.println("不存在");
                return;
            }
            Employer temp = head;
            while (true){
                if(temp.getId()==id){
                    System.out.println("该id对应的信息为"+temp);
                    break;
                }
                if(temp.getNext()==null){
                    System.out.println("不存在");
                    return;
                }
                temp = temp.getNext();

            }
        }
    }
    private SingleLinkedList[] hashTable;
    public HashTable(int size){
        hashTable = new SingleLinkedList[size];
        for (int i = 0; i < hashTable.length; i++) {
            hashTable[i] = new SingleLinkedList();
        }
    }
    public void add(Employer employer){
        hashTable[hashFun(employer)].add(employer);
    }
    private int hashFun(Employer employer){
        return employer.getId()%hashTable.length;
    }
    public void list(){
        for (int i = 0; i < hashTable.length; i++) {
            System.out.printf("%d条链表信息:\n",i+1);
            hashTable[i].list();
            System.out.println("==========");
        }
    }
    public void find(int id){
         hashTable[id%hashTable.length].find(id);
    }



}
class Employer{
    private String name;
    private int id;
    private Employer next;

    public Employer(String name,int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Employer{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    public Employer getNext() {
        return next;
    }

    public void setNext(Employer next) {
        this.next = next;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}