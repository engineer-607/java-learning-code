package com.zuochengyun.alorithm.heapcommonproblems;

import java.util.Arrays;

public class PileSort {
    public static void main(String[] args) {
        Solution03 solution = new Solution03();
        int[] nums = {1,1,0,0,2,5};
        solution.sortArray(nums);
        System.out.println(Arrays.toString(nums));
    }
}
class Solution03 {
    public int[] sortArray(int[] nums) {
        //先从底到顶建堆
        // 从最后一个非叶子节点开始，自底向上执行下沉操作
        for (int i = (nums.length - 2) / 2; i >= 0; i--) {
            heapDown(nums, i, nums.length - 1);
        }
        for(int i = nums.length-1;i>=1;){
            swap(nums,i,0);
            heapDown(nums,0,--i);
        }
        return nums;
    }

    public void swap(int[] nums,int k,int m){
        int temp = nums[k];
        nums[k] = nums[m];
        nums[m] = temp;
    }
    public void heapDown(int[] nums,int k,int size){
        int l = 2*k+1;
        while(l<=size){
            int best = l+1<=size&&nums[l+1]>nums[l]? l+1:l;
            best = nums[k]>=nums[best]?k:best;
            if(best==k){
                break;
            }
            swap(nums,k,best);
            k = best;
            l = 2*k+1;
        }
    }
}