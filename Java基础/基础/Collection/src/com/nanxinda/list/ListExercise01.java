package com.nanxinda.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListExercise01 {
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        List list = new ArrayList();
        for (int i = 0; i < 11; i++) {
            list.add("hello"+i);
        }
        list.add(1,"韩顺平教育");
        System.out.println("第五个元素="+list.get(4));
        list.remove(5);
        list.set(6,"jack");
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            Object next =  iterator.next();
            System.out.print("next="+next+" ");

        }


    }
}
