package com.nanxinda.encap02;

public class Account {
    public String name;
    private double balance;
    private String password;

    public Account() {
    }

    public Account(String name, double balance, String password) {
        this.setName(name);
        this.setBalance(balance);
        this.setPassword(password);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.length() >= 2 && name.length() <= 4) {
            this.name = name;
        } else {
            System.out.println("姓名要求（长度为2或3或4），默认值为无名");
            this.name = "无名";
        }
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        if (balance > 20) {
            this.balance = balance;
        } else {
            System.out.println("余额(必须>20)默认为0");
            this.balance = 0;
        }

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password.length() == 6) {
            this.password = password;
        }else{
            System.out.println("密码必须是6位，默认是000000");
            this.password="000000";
        }
    }
    public void info(String name,String password) {
        if(name==this.name&&password==this.password){
        System.out.println("信息：姓名="+name+" 余额="+balance);
        }else{
            System.out.println("信息匹配错误");
        }
    }
}