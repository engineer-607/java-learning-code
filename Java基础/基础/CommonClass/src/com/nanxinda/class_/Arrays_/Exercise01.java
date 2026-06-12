package com.nanxinda.class_.Arrays_;

import java.util.Arrays;
import java.util.Comparator;

public class Exercise01 {
    public static void main(String[] args) {
        Book[] books = new Book[4];
        books[0] = new Book("红楼梦", 100);
        books[1] = new Book("金瓶梅", 90);
        books[2] = new Book("青年文摘", 5);
        books[3] = new Book("java入门到放弃", 300);
        System.out.println("===按价格从小到大===");
        Book.bubble(books, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Book book1 = (Book) o1;
                Book book2 = (Book) o2;
                if(book2.getPrice()<book1.getPrice()){
                    return -1;
                } else if (book2.getPrice()== book1.getPrice()) {
                    return 0;
                }
                return 1;
            }
        });
        Book.print(books);
        System.out.println("===按价格从大到小排序===");
        Book.bubble(books, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Book book1 = (Book) o1;
                Book book2 = (Book) o2;
                if(book2.getPrice()> book1.getPrice()){
                    return -1;
                } else if (book2.getPrice()== book1.getPrice()) {
                    return 0;
                }
                return 1;
            }
        });
        Book.print(books);
        System.out.println("===按名字排序===");
        Book.bubble(books, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Book book1 = (Book) o1;
                Book book2 = (Book) o2;
                return book1.getName().compareTo(book2.getName());
            }
        });
        Book.print(books);
    }
}
class Book{
    private String name;
    private double price;

    public Book(String name, double price) {
        this.name = name;
        this.price = price;
    }
    static public void bubble(Book[] books, Comparator comparator){
        for (int i = 0; i < books.length - 1; i++) {
            for (int j = 0; j < books.length - i -1; j++) {
                if(comparator.compare(books[j],books[j+1])<0){
                    Book temp = books[j];
                    books[j] = books[j+1];
                    books[j+1] = temp;
                }
            }
        }
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public static void print(Book[] books){
        for (int i = 0; i < books.length; i++) {
            System.out.println("书名="+books[i].name+" 价格="+books[i].price);
        }
    }
}