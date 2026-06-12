package com.nanxinda.recursion;

import java.util.ArrayList;
import java.util.List;

public class EightEmpressesCase {
    public static void main(String[] args) {
        for (int i = 0; i < 8; i++) {


            EightEmpresses.map = new int[8][8];
            EightEmpresses.explore(0, i, 0);
        }


        for (int[][] ints : EightEmpresses.list) {
            for (int i = 0; i < ints.length; i++) {
                for (int j = 0; j < ints[i].length; j++) {
                    System.out.printf("%d\t",ints[i][j]);
                }
                System.out.println();
            }
            System.out.println();
        }
        System.out.println("八皇后一共有"+EightEmpresses.list.size()+"个");
    }
}
class EightEmpresses{
    public static int[][] map;
    public static List<int[][]> list = new ArrayList<>();
    public static void explore(int i, int j, int count){
        if(i<0||j<0||i>7|j>7){
            return ;
        }
        if (isPlace(i,j)){
            map[i][j] = 1;
            count++;
            if(count==8){
                int[][] mapCopy = new int[8][8];
                for (int k = 0; k < map.length; k++) {
                    for (int l = 0; l < map.length; l++) {
                        mapCopy[k][l] = map[k][l];
                    }
                }
                list.add(mapCopy);
                map[i][j] = 0;
                count--;
                return;
            }
            int startY = i;
            //如果发现这个点可以放置，并且总数仍未达到8，就继续探索
            for (int k = 0; k < map.length; k++) {
                explore(startY+1,k,count);
            }//如果该点持续探索没有找到最终结果，说明该点最终并不满足条件
            map[i][j] = 0;
            count--;

        }
    }

    /**
     *
     * @param i:表示行
     * @param j：表示列
     * &#064;return：返回放置的位置是否满足八皇后的要求
     */
    private static boolean isPlace(int i,int j){
        //行不必判断，每次都会换行
        //再判断列是否已满
        for (int k = 0; k < map.length; k++) {
            if(map[k][j]==1&&k!=i){
                return false;
            }
        }
        //左上->右下
        if(i>=j){
            int gap = i-j;
            for (int k = 0; (k+gap) < map.length; k++) {
                if(map[k+gap][k]==1&&k!=j){
                    return false;
                }
            }
        }else{
            int gap = j-i;
            for (int k = 0; k+gap < map.length; k++) {
                if(map[k][k+gap]==1&&k!=i){
                    return false;
                }
            }
        }
        //右上->左下
        if((i+j)<=7) {
            for (int k = 0; k <= (i + j); k++) {
                if (map[k][i + j - k] == 1 && k != i) {
                    return false;
                }
            }
        }else {
            for (int k = (i+j-7); k <=7 ; k++) {
                if(map[i+j-k][k] == 1&&k != j){
                    return false;
                }
            }
        }
        return true;
    }
}

