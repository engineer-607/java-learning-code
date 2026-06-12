package com.nanxinda.stack.fixexpression;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/// 中缀表达式转成后缀表达式
public class InfixToSuffix {
    //步骤分析
    //1）初始化两个栈：运算符栈s1和储存中间结果的栈s2；
    //2）从左至右扫描中缀表达式；
    //3）遇到操作数，将其压入s2；
    //4）遇到运算符时，比较其与s1栈顶运算符的优先级；
    //1.如果s1为空，或栈顶运算符为左括号“（”，则直接将此运算符入栈
    //2.否则，若优先级比栈顶运算符的高，也将运算符压入栈s1
    //3.否则，将s1栈顶的运算符弹出并压入到s2中
    //5）遇到括号时：
    //1.如果是左括号“（”，则直接压入s1
    //2.如果是右括号“）”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止
    //6）将s1剩余的运算夫以此弹出并压入s2
    //7）以此弹出s2中元素并输出，结果的逆序即为中缀表达是对应的后缀表达式
    //8）以此弹出s2中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式
    Stack<String> s1 = new Stack<>();
    Stack<String> s2 = new Stack<>();
    public String transform(String s){
        List<String> list = new ArrayList<>();
        list = spilt(s);
        for (String string : list) {
            if(isOper(string)){
                pushOper(string);
            }else {
                s2.add(string);
            }
        }
        while (!s1.empty()){
            s2.add(s1.pop());
        }
        return reserve();

    }
    private String reserve(){
        String key = "";
        for (int i = 0 ; i <s2.size(); i++) {
            key+=s2.get(i)+" ";
        }
        return key;
    }
    private List<String> spilt(String s){
        char[] chars = s.toCharArray();
        List<String> list = new ArrayList<>();
        String key = "";
        for (int i = 0; i < chars.length;) {
            if(!isOper(
                    (key = String.valueOf(chars[i]))
                    )
            ) {
                int start = i;
                int count = 0;

                while (i<chars.length&&!isOper(String.valueOf(chars[i]))){
                    count++;
                    i++;
                }
                list.add(new String(chars,start,count));
            }else {
                list.add(key);
                i++;
            }
        }
        return list;
    }
    private boolean isOper(String s){
        return s.equals("+")||s.equals("-")||s.equals("*")||s.equals("/")||s.equals("(")||s.equals(")");
    }
    //1.如果s1为空，或栈顶运算符为左括号“（”，则直接将此运算符入栈
    //2.否则，若优先级比栈顶运算符的高，也将运算符压入栈
    //3.否则，将s1栈顶的运算符弹出并压入到s2中,并按照4的规则判断运算符的去向
    private void pushOper(String s){
        if(s1.isEmpty()||s.equals("(")){
            s1.add(s);
            //1.如果是左括号“（”，则直接压入s1
            //2.如果是右括号“）”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止
        }else if(s.equals(")")){
            while (!s1.peek().equals("(")){
                s2.add(s1.pop());
            }
            s1.pop();
        }else if(getPriority(s)>getPriority(s1.peek())){
            s1.add(s);
        }else if(getPriority(s)<=getPriority(s1.peek())){
            s2.add(s1.pop());
            pushOper(s);
        }
    }
    private int getPriority(String s){
        if(s.equals("+")||s.equals("-")){
            return 0;
        }else if(s.equals("*")||s.equals("/")){
            return 1;
        }else {
            return -1;
        }
    }


}
