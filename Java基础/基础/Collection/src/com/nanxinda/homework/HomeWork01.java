package com.nanxinda.homework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@SuppressWarnings({"all"})
public class HomeWork01 {
    public static void main(String[] args) {
        News news = new News("新冠确诊病例超千万，数百万印度信徒赴恒河“圣浴”印民众担忧");
        News news1 = new News("男子突然想起2个月前钓的鱼还在网兜里，捞起一看赶紧放生");
        List list = new ArrayList();
        list.add(news);
        list.add(news1);
        for (int i = list.size()-1; i >=0 ; i--) {
            String s =((News)list.get(i)).getTitle();
            if(s.length()>15){
                String s1 = s.substring(0,15);
                System.out.println(s1+"...");
            }else {
                System.out.println(s);
            }
        }

    }
}
class News{
    private String title;
    private String content;

    public News(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                '}';
    }
}