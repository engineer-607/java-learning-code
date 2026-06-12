package com.zuochengyun.alorithm.heapcommonproblems;

import java.util.*;

/**
 * 给定一个链表数组，每个链表都已经按升序排列。
 * 请将所有链表合并到一个升序链表中，返回合并后的链表
 */
public class Merge_K_SortedList {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1, new ListNode(4, new ListNode(5)));
        ListNode l2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode l3 = new ListNode(2, new ListNode(6));
        //[1,4,5],[1,3,4],[2,6]
        ListNode[] lists = new ListNode[]{l1, l2, l3};

        Solution04 sol = new Solution04();
        ListNode result = sol.mergeKLists(lists);
        sol.traverse(result);

    }
}
//Definition for singly-linked list
class ListNode {
    int val;
    ListNode next;
    ListNode() {
    }
    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
class Solution04 {
    public ListNode mergeKLists(ListNode[] lists) {
        //构建一个小根堆，里面存放k条链表的头节点
        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        ListNode start = null;
        ListNode pre = null;
        for (int i = 0; i < lists.length; i++) {
            queue.add(lists[i]);
        }
        while (!queue.isEmpty()){
            if(start==null){
                start = queue.poll();
                pre = start;
            }else {
                pre.next = queue.poll();
                pre = pre.next;
            }
            if(pre.next!=null) {
                queue.add(pre.next);
            }
        }

        return start;
    }
    public void traverse(ListNode start){
        ListNode pre = start;
        while (pre!=null){
            System.out.print(pre.val+"->");
            pre = pre.next;
        }
    }
}