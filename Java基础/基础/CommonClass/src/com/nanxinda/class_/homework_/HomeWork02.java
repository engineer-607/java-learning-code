package com.nanxinda.class_.homework_;

import java.util.Scanner;

public class HomeWork02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("注册请输入如下信息");
        System.out.println("用户名：");
        String s = scanner.next();
        try {
            if(s.length()<2&&s.length()>4){
                throw new UsernameLengthException("用户名长度为2或3或4");
            }
        } catch (UsernameLengthException e) {
            System.out.println("错误信息："+e.getMessage());
        }
        System.out.println("请输入密码：");
        String s1 = scanner.next();
        try {
            if(s1.length()!=6){
                throw new PasswordException("密码长度应大于6");
            }
            try {
                Integer a = Integer.parseInt(s1);
            } catch (NumberFormatException e) {
                System.out.println("错误信息：请输入数字");
            }

        } catch (PasswordException e) {
            System.out.println("错误信息："+e.getMessage());
        }
        String s2 = scanner.next();
        char[] chars = s2.toCharArray();
        boolean judge = false;
        boolean judge1 = false;
        int n1=0,n2=-1;
        for (int i = 0; i < chars.length; i++) {
            if(chars[i]=='@'){
                judge=true;
                 n1 = i;
            }
            if(chars[i]=='.'){
                judge1=true;
                 n2 = i;
            }
        }
        try {
            if(judge1==false||judge==false){
                throw new EmailException("邮箱中应包含@和.");
            }
            try {
                if(n1>=n2){
                    throw new EmailException("@在.的前面");
                }
            } catch (EmailException e) {
                System.out.println("错误信息："+e.getMessage());
            }
        } catch (EmailException e) {
            System.out.println("错误信息："+e.getMessage());
        }


    }
}
class UsernameLengthException extends RuntimeException{
    public UsernameLengthException(String message) {
        super(message);
    }
}
class PasswordException extends RuntimeException{
    public PasswordException(String message) {
        super(message);
    }
}
class EmailException extends RuntimeException{
    public EmailException(String message) {
        super(message);
    }
}