package com.nanxinda.cases;

public class case07 {
    public static void main(String[] args) {
        SavingsAccount savingsAccount = new SavingsAccount(1000);
        savingsAccount.deposit(100);
        savingsAccount.deposit(100);
        savingsAccount.deposit(100);
        System.out.println(savingsAccount.getBalance());
        savingsAccount.deposit(100);
        System.out.println(savingsAccount.getBalance());
        savingsAccount.earnMonthlyInterest(0.01);
        System.out.println(savingsAccount.getBalance());//1412.99
        savingsAccount.withdraw(100);//免手续费
        savingsAccount.withdraw(100);//免手续费
        savingsAccount.withdraw(100);//免手续费
        System.out.println(savingsAccount.getBalance());//1412.99-300=1112.99
        savingsAccount.deposit(100);
        System.out.println(savingsAccount.getBalance());//1112.99+100-1=1211.99
    }

}
class BankAccount{
    private double balance;
    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        balance += amount;
    }
    public void withdraw(double amount) {
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
class CheckingCount extends BankAccount{
    private double check=1;
    public CheckingCount(double initialBalance) {
        super(initialBalance);
    }


    @Override
    public void deposit(double amount) {
        super.deposit(amount-check);
    }

    @Override
    public void withdraw(double amount) {
        super.withdraw(amount+check);
    }

    public double getCheck() {
        return check;
    }
}
class SavingsAccount extends CheckingCount{
    public SavingsAccount(double initialBalance) {
        super(initialBalance);
    }
    private int num=0 ;

    @Override
    public void deposit(double amount) {
        if(num<3){
            super.deposit(amount+getCheck());
            num++;
        }else {
            super.deposit(amount);
        }
    }

    @Override
    public void withdraw(double amount) {
        if(num<3) {
            super.withdraw(amount-getCheck());
            num++;
        }else{
            super.withdraw(amount);
        }
    }
    public void earnMonthlyInterest(double rate){
        super.setBalance(getBalance()*(1+rate));
        //可以直接调用父类的deposit
        //super.deposit(getBalance()*rate);
        num=0;
    }
}
