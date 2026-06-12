package com.zuochengyun.alorithm.linkedlist;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class LinkedListExerciseSortListTest {
    private final LinkedListExercise exercise = new LinkedListExercise();

    @Test(timeout = 1000)
    public void sortList_example1() {
        assertSortResult(new int[]{4, 2, 1, 3}, new int[]{1, 2, 3, 4});
    }

    @Test(timeout = 1000)
    public void sortList_example2() {
        assertSortResult(new int[]{-1, 5, 3, 4, 0}, new int[]{-1, 0, 3, 4, 5});
    }

    @Test(timeout = 1000)
    public void sortList_example3() {
        Assert.assertNull(exercise.sortList(null));
    }

    @Test(timeout = 1000)
    public void sortList_twoNodesReverse() {
        assertSortResult(new int[]{4,19,14,5,-3,1,8,5,11,15}, new int[]{-3,1,4,5,5,8,11,14,15,19});
    }

    private void assertSortResult(int[] input, int[] expected) {
        LinkedListExercise.ListNode head = buildList(input);
        LinkedListExercise.ListNode actualHead = exercise.sortList(head);
        int[] actual = toArray(actualHead, expected.length + 1);
        Assert.assertArrayEquals(
                "input=" + Arrays.toString(input) + ", actual=" + Arrays.toString(actual),
                expected,
                actual);
    }

    private LinkedListExercise.ListNode buildList(int[] values) {
        if (values == null || values.length == 0) {
            return null;
        }

        LinkedListExercise.ListNode head = exercise.new ListNode(values[0]);
        LinkedListExercise.ListNode current = head;
        for (int i = 1; i < values.length; i++) {
            current.next = exercise.new ListNode(values[i]);
            current = current.next;
        }
        return head;
    }

    private int[] toArray(LinkedListExercise.ListNode head, int maxLength) {
        int[] values = new int[maxLength];
        int size = 0;
        LinkedListExercise.ListNode current = head;
        while (current != null && size < maxLength) {
            values[size++] = current.val;
            current = current.next;
        }

        if (current != null) {
            Assert.fail("linked list may contain a cycle, visited more than " + maxLength + " nodes");
        }

        return Arrays.copyOf(values, size);
    }
}
