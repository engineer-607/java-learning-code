package com.zuochengyun.alorithm.heapcommonproblems;

public class MinimumOperationsToHalveArraySum {
    public static void main(String[] args) {
        Solution05 solution = new Solution05();
        int[] nums = {32,98,23,14,67,40,26,9,96,96,91,76,4,40,42,2,31,13,16,37,62,2,27,25,100,94,14,3,48,56,64,59,33,10,74,47,73,72,89,69,15,79,22,18,53,62,20,9,76,64};
        System.out.println(solution.halveArray(nums));
    }
}

/**
 * You are given an array nums of positive integers.
 * In one operation, you can choose any number from nums and reduce it to exactly half the number.
 * (Note that you may choose this reduced number in future operations.)
 * Return the minimum number of operations to reduce the sum of nums by at least half.
 */
    ///The root cause is precision loss and integer overflow.
    /// Since the problem requires halving elements, using int leads to truncation errors during division.
    /// Additionally, the cumulative sum can exceed the maximum value of a 32-bit signed integer.
    /// The implementation of the Max Heap is logically sound.
    ///  Both percolate up (sift-up) and percolate down (sift-down) operations correctly maintain the heap invariant.
    class Solution05 {
        public int halveArray(int[] nums) {
            double originSum = 0; // 必须用 double 防溢出和保存小数
            Heap heap = new Heap(nums.length);

            for (int i = 0; i < nums.length; i++) {
                originSum += nums[i];
                heap.add(nums[i]);
            }

            // record 也必须是 double
            double record = originSum;
            double delNum = 0;
            int count = 0;

            // 注意：除以 2.0
            while (originSum > record / 2.0) {
                delNum = heap.poll() / 2.0;
                heap.add(delNum);
                originSum -= delNum;
                count++;
            }
            return count;
        }

        class Heap {
            double[] heap; // 堆底层数组改为 double
            int actualSize;

            public Heap(int size) {
                heap = new double[size];
                actualSize = 0;
            }

            public void add(double num) { // 参数改为 double
                heap[actualSize] = num;
                int i = actualSize;
                while (i > 0 && heap[i] > heap[(i - 1) / 2]) {
                    swap(i, (i - 1) / 2);
                    i = (i - 1) / 2;
                }
                actualSize++;
            }

            public double poll() { // 返回值改为 double
                double max = heap[0];
                swap(0, --actualSize);
                int i = 0, l = 1;
                while (l < actualSize) {
                    int best = l + 1 < actualSize && heap[l + 1] > heap[l] ? l + 1 : l;
                    best = heap[best] > heap[i] ? best : i;
                    if (best == i) {
                        break;
                    }
                    swap(i, best);
                    i = best;
                    l = i * 2 + 1;
                }
                return max;
            }

            public void swap(int a, int b) {
                double temp = heap[a]; // temp 改为 double
                heap[a] = heap[b];
                heap[b] = temp;
            }
        }
    }
