package com.zuochengyun.alorithm.merge;

public class OverturnPair {
    public static void main(String[] args) {
        int[] nums = {100,100,-100,-100,-100,100};
        Solution02 solution02 = new Solution02();
        System.out.println(solution02.reversePairs(nums));
    }

}
@SuppressWarnings("all")
/**
 * Given an integer array nums, return the number of reverse pairs in the array.
 *
 * A reverse pair is a pair (i, j) where:
 *
 * 0 <= i < j < nums.length and
 * nums[i] > 2 * nums[j].
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,2,3,1]
 * Output: 2
 * Explanation: The reverse pairs are:
 * (1, 4) --> nums[1] = 3, nums[4] = 1, 3 > 2 * 1
 * (3, 4) --> nums[3] = 3, nums[4] = 1, 3 > 2 * 1
 * Example 2:
 *
 * Input: nums = [2,4,3,5,1]
 * Output: 3
 * Explanation: The reverse pairs are:
 * (1, 4) --> nums[1] = 4, nums[4] = 1, 4 > 2 * 1
 * (2, 4) --> nums[2] = 3, nums[4] = 1, 3 > 2 * 1
 * (3, 4) --> nums[3] = 5, nums[4] = 1, 5 > 2 * 1
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 5 * 104
 * -231 <= nums[i] <= 231 - 1
 */
class Solution02 {
    public int reversePairs(int[] nums) {
        return reservePairs(nums,0,nums.length-1);

    }
    public int reservePairs(int[] nums,int left,int right){
        if(nums==null||nums.length==0||nums.length==1){
            return 0;
        }
        if(left==right){
            return 0;
        }
        int[] temp = new int[right-left+1];
        int mid = (left+right)/2;
        int leftCount = reservePairs(nums,left,mid);
        int rightCount = reservePairs(nums,mid+1,right);
        int leftTemp = left,rightTemp = mid+1;
        int i=0,count = 0;
        int p1=leftTemp,p2=rightTemp;
        while (p1<=mid&&p2<=right){
            if(nums[p1]>(long)2*nums[p2]){
                count+=right-p2+1;
                p1++;
            }else {
                p2++;
            }
        }
        while (leftTemp<=mid&&rightTemp<=right){
            if(nums[leftTemp]>nums[rightTemp]){
                temp[i++] = nums[leftTemp++];
            }else {
                temp[i++] = nums[rightTemp++];
            }
        }
        while (leftTemp<=mid){
            temp[i++] = nums[leftTemp++];
        }
        while (rightTemp<=right){
            temp[i++] = nums[rightTemp++];
        }
        if(left==0&&right==nums.length-1){
            return leftCount+rightCount+count;
        }
        int k = 0,m = left;
        while (m<=right){
            nums[m++] = temp[k++];
        }
        return leftCount+rightCount+count;
    }
}