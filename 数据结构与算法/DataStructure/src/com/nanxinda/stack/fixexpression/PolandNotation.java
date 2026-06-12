package com.nanxinda.stack.fixexpression;

import java.util.*;

public class PolandNotation {
//    后缀表达式
//        1）后缀表达式与前缀表达式相似，只是运算符位于操作符之后
//        2）eg.(3+4)*5-6对应的后缀表达式为3 4 + 5 * 6 -
//    运算逻辑：
//        1）从左到右扫描，将3和4压入堆栈
//        2）遇到+运算符，弹出4和3（4为栈顶，3为次顶元素），计算3+4的值，为7，再将7入栈
//        3）将5入栈
//        4）接下来是*运算符，弹出5和7，将35入栈
//        5）将6入栈
//        6）最后是-运算符，计算35-6的值，即29，得到最终结果
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        List<String> list = getList(new InfixToSuffix().transform(s));
        System.out.println(calculator(list));
    }
    public static List<String> getList(String s){
        String[] s1 = s.split(" ");
        List<String> list = new ArrayList<>();
        Collections.addAll(list, s1);
        return list;
    }
    public static int calculator(List<String> list){
        Stack<String> stack = new Stack<>();
        for (String s : list) {
            if(isOper(s)){
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                stack.add(""+calculate(num1,num2,s));
            }else {
                stack.add(s);
            }
        }
        return Integer.parseInt(stack.pop());
    }
    public static boolean isOper(String s){
        return s.equals("+")||s.equals("-")||s.equals("*")||s.equals("/");
    }
    public static Integer calculate(int num1,int num2,String oper){
        switch (oper){
            case "+":
                return num1+num2;
            case "-":
                return num2-num1;
            case "*":
                return num1*num2;
            case "/":
                return num2/num1;
        }
        return null;
    }
}
