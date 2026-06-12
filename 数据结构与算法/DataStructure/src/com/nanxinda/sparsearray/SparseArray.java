package com.nanxinda.sparsearray;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"all"})
/// 显示棋盘
public class SparseArray {
    public static void main(String[] args) throws IOException {
        //1.创建原始数组
        int[][] oldArray = new int[11][11];
        oldArray[1][2] = 1;//1表示黑子
        oldArray[2][3] = 2;//2表示白字
        System.out.println("=========原始的数组=========");
        for (int[] ints : oldArray) {
            for (int anInt : ints) {
                System.out.printf("%d\t",anInt);
            }
            System.out.println();
        }
        //将二维数组转化稀疏数组：
        //1）遍历原始二维数组，获取有效数据个数
        int sum = 0;
        for (int[] ints : oldArray) {
            for (int anInt : ints) {
                if(anInt!=0){
                    sum++;
                }
            }
        }
        //2）创建稀疏数组
        int[][] sparseArray =new int[sum+1][3];
        sparseArray[0][0] = oldArray.length;
        sparseArray[0][1] = oldArray[0].length;
        sparseArray[0][2] = sum;
        int m = 0;
        for (int i = 0; i < oldArray.length; i++) {
            for (int j = 0; j < oldArray[i].length; j++) {
               if(oldArray[i][j]!=0){
                   sparseArray[++m][0] = i;
                   sparseArray[m][1] = j;
                   sparseArray[m][2] = oldArray[i][j];
               }
            }
        }
        System.out.println("========稀疏数组========");
        for (int[] ints : sparseArray) {
            for (int anInt : ints) {
                System.out.printf("%d\t",anInt);
            }
            System.out.println();
        }
        //将稀疏数组写入到文件之中
        String filePath = "d:\\数据结构与算法\\DataStructure\\src\\spareArray.txt";
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));
        for (int[] ints : sparseArray) {
            bufferedWriter.write(ints[0]+","+ints[1]+","+ints[2]);
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
        System.out.println("========稀疏数组写入成功========");
        List<int[]> list = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        String s = null;
        while ((s = bufferedReader.readLine())!=null){
            String[] strings = s.split(",");
            int[] row = new int[3];
            row[0] = Integer.parseInt(strings[0]);
            row[1] = Integer.parseInt(strings[1]);
            row[2] = Integer.parseInt(strings[2]);
            list.add(row);
        }
        System.out.println("========稀疏数组读入成功=========");
        int row = list.get(0)[0];
        int column = list.get(0)[1];
        int count = list.get(0)[2];
        int[][] newArray = new int[row][column];
        for (int i = 1; i <=count ; i++) {
            newArray[list.get(i)[0]][list.get(i)[1]] = list.get(i)[2];
        }
        System.out.println("========原数组========");
        for (int[] ints : newArray) {
            for (int anInt : ints) {
                System.out.printf("%d\t",anInt);
            }
            System.out.println();
        }
    }
}
