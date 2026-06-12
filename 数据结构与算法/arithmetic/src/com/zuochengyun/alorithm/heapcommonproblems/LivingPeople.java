package com.zuochengyun.alorithm.heapcommonproblems;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 给定 N 个人的出生年份和死亡年份，第 i 个人的出生年份为 birth[i]，死亡年份为 death[i]，实现一个方法以计算生存人数最多的年份。
 * 你可以假设所有人都出生于 1900 年至 2000 年（含 1900 和 2000 ）之间。如果一个人在某一年的任意时期处于生存状态，
 * 那么他应该被纳入那一年的统计中。例如，生于 1908 年、死于 1909 年的人应当被列入 1908 年和 1909 年的计数。
 * 如果有多个年份生存人数相同且均为最大值，输出其中最小的年份。
 */
public class LivingPeople {
    public static void main(String[] args) {
       int[] birth = {1900,1901,1950};
       int[] death = {1948,1951,2000};
       Solution solution = new Solution();
        System.out.println(solution.maxAliveYear(birth,death));
    }
}
//思路：
//根据出生年份查找对应的死亡年份，将死亡年份依次入堆，每次入堆前对比当前堆中是否有有小于等于出生日期
//如果有，让其出堆，并记录出堆前堆中元素的个数，其代表一种可能的最大值，如果没有就不再入堆
//最后统计所有最大值的解，对比得出其中的最大值
class Solution {
    public int maxAliveYear(int[] birth, int[] death) {
        int[][] years = new int[birth.length][2];
        for (int i = 0; i < years.length; i++) {
            years[i][0] = birth[i];
            years[i][1] = death[i];
        }
        Arrays.sort(years,0,birth.length,(a,b)->(a[0]-b[0]));
        int max = 0,maxAliveYear = 0;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < years.length; i++) {
                    while (!priorityQueue.isEmpty() && priorityQueue.peek() < years[i][0]) {
                        priorityQueue.poll();
                    }
                    priorityQueue.add(years[i][1]);
                    if(priorityQueue.size()>max){
                        maxAliveYear = years[i][0];
                        max = priorityQueue.size();
                    }
        }
        return maxAliveYear;
    }
}