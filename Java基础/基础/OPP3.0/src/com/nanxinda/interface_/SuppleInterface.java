package com.nanxinda.interface_;

public class SuppleInterface {
}
interface M{
    //接口可以有非抽象的方法
    //默认方法
    default void honk(){
        System.out.println("deepseek");
    }
    //静态方法
    static double pi(){
        return 3.1415926;
    }
}
/*
默认方法优点：
1）可以被子类继承
2）子类可以选择重写或者直接使用
3）主要用于接口的演化，避免破环现有实现
 */
//eg.
// 1.0版本：银行账户接口
 interface BankAccount {
    void deposit(double amount);
    void withdraw(double amount);
    double getBalance();
    //2.0版本需要添加转账功能
    //问题出现：如果直接添加抽象方法，所有类都需要立即实现
    //但如果使用默认方法
    default void transfer(){
        System.out.println("转账");
    }
    //现有的实现类无需修改，并且可以选择性地重写
}
 class SavingsAccount implements BankAccount {
    private double balance;

    @Override
    public void deposit(double amount) {
        balance += amount;
    }

    @Override
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        }
    }

    @Override
    public double getBalance() {
        return balance;
    }
}

//public class CheckingAccount implements BankAccount {
//     类似实现...
//}

// 还有其他100个类实现了BankAccount接口

/*
静态方法的特点：
1）属于接口本身，而不是实例
2）不能被子类继承或者重写
3）直接通过接口名调用
 */