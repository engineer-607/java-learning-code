package com.nanxinda;

import com.nanxinda.extend01.Graduate;

public class Test {
    public static void main(String[] args){
        Graduate graduate = new Graduate();
        graduate.name="jack";
        graduate.age=18;
        graduate.setScore(80);
        graduate.testing();
        graduate.showInfo();
    }
}
