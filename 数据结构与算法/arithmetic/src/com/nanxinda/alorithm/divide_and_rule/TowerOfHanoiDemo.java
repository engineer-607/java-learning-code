package com.nanxinda.alorithm.divide_and_rule;

import java.util.HashMap;
import java.util.Stack;

public class TowerOfHanoiDemo {
    /**
     * 分治算法：
     * 1.把一个复杂的问题分成两个或者更多相似的子问题，再把子问题分成更小的子问题，直到最后子问题可以
     * 简单的直接求解，最终原问题的解即为子问题解的合并
     * 2.基本步骤：
     * 1）分解：讲原问题分解为若干个规模较小、相互独立、与原问题形式相同的子问题
     * 2）解决：若子问题规模较小而容易被解决则直接解决，否则递归地解决各个子问题
     * 3）合并：讲各个子问题的解合并为原问题的解
     */
    public static void main(String[] args) {
        Tower tower = new Tower(3);
        tower.move();

    }
}
class Tower{
    Stack<Integer> stackA;
    Stack<Integer> stackB;
    Stack<Integer> stackC;
    HashMap<String,Stack<Integer>> hashMap;
    int size;
    public Tower(int num){
        stackA = new Stack<>();
        stackB = new Stack<>();
        stackC = new Stack<>();
        for (int i = num-1; i >=0 ; i--) {
            stackA.add(i);
        }
        hashMap = new HashMap<>();
        hashMap.put("A",stackA);
        hashMap.put("B",stackB);
        hashMap.put("C",stackC);
        size = stackA.size();
    }
    public void move(){
        move("A","B","C",stackA.size());
    }
    private void move(String start,String mid,String last,int moveNum){
        if(stackC.size()==size){
            return;
        }
        Stack<Integer> tempStart = hashMap.get(start);
        Stack<Integer> tempLast = hashMap.get(last);
        Stack<Integer> tempMid = hashMap.get(mid);
        if(moveNum>1){
            move(start,last,mid,moveNum-1);//将moveNum个盘搬到辅助柱子上
            tempLast.push(tempStart.pop());
            System.out.println(start+"->"+last);
            move(mid,start,last,moveNum-1);//需要将辅助柱子上moveNum个盘搬到最终目标柱上
        }else if(moveNum==1) {
            tempLast.push(tempStart.pop());
            System.out.println(start + "->" + last);
        }
    }
}