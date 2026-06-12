package com.nanxinda.smallchanges;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class SmallChanges {
    private double balance;
    private ArrayList<String> arrayListEarnings = new ArrayList<String>();
    private ArrayList<String> arrayListConsumption = new ArrayList<String>();
    private ArrayList<Date> dateEarnings = new ArrayList<Date>();
    private ArrayList<Date> dateConsumption = new ArrayList<Date>();
    private String date;

    public ArrayList<String> getArrayListConsumption() {
        return arrayListConsumption;
    }

    public void setArrayListConsumption(ArrayList<String> arrayListConsumption) {
        this.arrayListConsumption = arrayListConsumption;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public ArrayList<String> getArrayListEarnings() {
        return arrayListEarnings;
    }

    public void setArrayListEarnings(ArrayList<String> arrayListEarnings) {
        this.arrayListEarnings = arrayListEarnings;
    }

    public String getDate() {
        return date;
    }

    public void setDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        this.date = simpleDateFormat.format(date);
    }
    public void earnings (Double earnings,Date date1,Scanner scanner) {
        if(earnings<=0){//过关斩将的校验方式
            System.out.println("您的输入错误，请问是否重新输入：(y/n)");
            String string = scanner.next();
            while(true){
                if(string.equals("y")||string.equals("n")){
                    break;
                }
                System.out.println("请遵循条件输入y或n决定您是否重新输入");
                string=scanner.next();
            }
            if (string.equals("y")){
                System.out.println("请输入您的收益:");
                earnings(scanner.nextDouble(),new Date(),scanner);
            }
            return;
        }

        setDate(date1);
        dateEarnings.add(date1);
        balance += earnings;
        String s = "收益入账：\t+" + earnings + "\t" + date + "\t余额:" + balance;
        arrayListEarnings.add(s);
    }
    public void consume(Double consumption,Date date1,Scanner scanner){
        if(consumption>balance){
            System.out.println("您的余额不足");
            System.out.println("您是否需要充值？y/n");
            String judge= scanner.next();
            if(judge.equals("y")){
                System.out.println("请输入您充值的金额：");
                earnings(scanner.nextDouble(),date1,scanner);
                consume(consumption,new Date(),scanner);
            }else if(judge.equals("n")){
                System.out.println("扣款失败");
            }else{
                System.out.println("输入错误");
            }
        }else{
            System.out.println("消费去处：");
            String place = scanner.next();
            setDate(date1);
            dateConsumption.add(date1);
            balance -=consumption;
            String s = place+"：\t-" +consumption + "\t" + date + "\t余额:" + balance;
            arrayListConsumption.add(s);
        }
    }
    public void getImf(){
        if(arrayListConsumption.isEmpty()&&arrayListEarnings.isEmpty()){
            System.out.println("无入账和消费记录");
            return;
        }
        int i=0,j=0;
        while(i<arrayListEarnings.size()&&j<arrayListConsumption.size()){
            if(dateConsumption.get(j).after(dateEarnings.get(i))){
                System.out.println(arrayListEarnings.get(i));
                i++;
            }else{
                System.out.println(arrayListConsumption.get(j));
                j++;
            }
        }
        while(i<arrayListEarnings.size()){
            System.out.println(arrayListEarnings.get(i++));
        }
        while(j<arrayListConsumption.size()){
            System.out.println(arrayListConsumption.get(j++));
        }

    }

}
