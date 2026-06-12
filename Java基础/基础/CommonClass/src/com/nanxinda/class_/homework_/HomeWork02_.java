package com.nanxinda.class_.homework_;

public class HomeWork02_ {
    public static void main(String[] args) {
        String name="jack";
        String pwd = "123456";
        String email = "jack@qq.com";
        try {
            Judge(name,pwd,email);
        } catch (Exception e) {
            System.out.println("错误信息："+e.getMessage());
        }

    }
    public static void Judge(String name,String pwd,String email){
        if(!(name.length()>=2&&name.length()<=4)){
            throw new RuntimeException("用户名的长度应为2或3或4");
        }
        if(pwd.length()!=6||isDigital(pwd)){
            throw new RuntimeException("密码长度应为6且应全为数字");
        }
        int i = email.indexOf('@');//如果查找不到会返回-1
        int j = email.indexOf('.');
        if(!(i>0&&i<j)){
            throw new RuntimeException("邮箱中应包含@和.并且@在.的前面");
        }
    }
    public static boolean isDigital(String pwd){
        char[] chars = pwd.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if(chars[i]<'0'||chars[i]>'9'){
                return false;
            }
        }
        return true;
    }
}
