package com.zuochengyun.alorithm.merge;

/**
 * 在股票交易中，如果前一天的股价高于后一天的股价，则可以认为存在一个「交易逆序对」。
 * 请设计一个程序，输入一段时间内的股票交易记录 record，返回其中存在的「交易逆序对」总数。
 * 思路：能通过左右数组和跨左右来解决
 */
public class TransactionReserveSequence {
    public static void main(String[] args) {
        int[] array ={3,2,1,3,1};
        Solution01 solution = new Solution01();
        System.out.println(solution.reversePairs(array));
    }
}
class Solution01 {
    public int reversePairs(int[] record) {
        return reversePairs(record,0,record.length-1);
    }
    public int reversePairs(int[] record,int left,int right){
        if(record==null||record.length==1||record.length==0){
            return 0;
        }
        if(left==right){
            return 0;
        }
        int count = 0;
        int leftTemp = left;
        int mid = (left+right)/2;
        int rightTemp = mid+1;
        int i = 0;
        int[] tempArray = new int[right-left+1];
        int leftCount = reversePairs(record,left,mid);
        int rightCount = reversePairs(record,mid+1,right);
        while(leftTemp<=mid&&rightTemp<=right){
            if(record[leftTemp]>record[rightTemp]){
                count+=right-rightTemp+1;
                tempArray[i++] = record[leftTemp++];
            }else{
                tempArray[i++] = record[rightTemp++];
            }
        }
        while(leftTemp<=mid){
            tempArray[i++] = record[leftTemp++];
        }
        while(rightTemp<=right){
            tempArray[i++] = record[rightTemp++];
        }
        if(right==record.length-1&&left==0){
            return count+leftCount+rightCount;
        }
        int k = left,m = 0;
        while(k<=right){
            record[k++] = tempArray[m++];
        }
        return count+leftCount+rightCount;
    }
}