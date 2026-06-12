package com.nanxinda.alorithm.dynamicplanning;

import java.util.Arrays;

public class Backpacker_01 {
    public static void main(String[] args) {
        int[] values = {1500,3000,2000};
        int[] weights = {1,4,3};
        Backpacker backpacker = new Backpacker(values,weights,4,3);
        System.out.println(backpacker.dynamicPlan());

    }
}
@SuppressWarnings({"all"})
class Backpacker{
    private int[] values;
    private int[] weights;
    private int weightLimit;
    private int valueNumLimit;
    private int result[][];

    public Backpacker(int[] values, int[] weights, int weightLimit, int valueNumLimit) {
        this.values = values;
        this.weights = weights;
        this.weightLimit = weightLimit;
        this.valueNumLimit = valueNumLimit;
        result = new int[values.length+1][weightLimit+1];
    }

    public int dynamicPlan(){
        //第i排意义为对前面i个物品进行选择，其本质由前面一行以及当前行对应的重量和价值所决定
        //所以应该将第一行进行初始化
        /// 需要考虑到重量限制为0时各个物品对应的情况，不然后续出现需要前一列重量为0时的价值会出现错配
        Arrays.fill(result[0], 0);
        for (int i = 0; i < result.length; i++) {
            result[i][0] = 0;
        }
        //之后将每一行对应的数值填满
        //i表示对应的每一个物品（从1开始）
        for (int i = 1; i < result.length; i++) {
            //j表示当前的重量
            for (int j = 1; j <= weightLimit; j++) {
                //1.选择当前行对应的物品（前提当前物品重量没有超过j）
                if(weights[i-1]>j){
                    result[i][j] = result[i-1][j];
                }else {
                    result[i][j] = Math.max(result[i-1][j],result[i-1][j-weights[i-1]]+values[i-1]);
                }
            }
        }
        return result[valueNumLimit][weightLimit];
    }
}