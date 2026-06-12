package com.zuochengyun.alorithm.linkedlist;


import org.junit.Assert;
import org.junit.Test;

public class LinkedListExercise {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //1.只定义计数变量，两个链表对这个变量进行操作获取A和B链表的长度之差，没有必要使用两个变量
        //2.定义两个指针变量，这两个变量并不是一定属于某一个链表，可以根据计数变量的大小调整指向
        //3.在计算两个链表的差值时就可以判断这两个链表有无交点，无直接返回，可以避免通过后续找交点无结果再返回
        //导致代码冗余
        if(headA==null|headB==null){
            return null;
        }
        ListNode pointerA = headA;
        ListNode pointerB = headB;
        int differ = 0;
        while (pointerA.next!=null){
            differ++;
            pointerA = pointerA.next;
        }
        while (pointerB.next!=null){
            differ--;
            pointerB = pointerB.next;
        }
        //如果A和B指针没有指向同一个说明没有交点
        if(pointerA!=pointerB){
            return null;
        }
        if(differ>=0){
            pointerA = headA;
            pointerB = headB;
        }else {
            differ = Math.abs(differ);
            pointerA = headB;
            pointerB = headA;
        }
        while (differ>0){
            differ--;
            pointerA = pointerA.next;
        }
        while (pointerB!=pointerA){
            pointerA = pointerA.next;
            pointerB = pointerB.next;
        }
        return pointerA;
    }
    //Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list.
    //k is a positive integer and is less than or equal to the length of the linked list.
    //If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
    //You may not alter the values in the list's nodes, only nodes themselves may be changed.
    public ListNode reverseKGroup(ListNode head,int k){
        //第一步：先将找到那k个节点，用变量count进行计数，如果没有满k个节点直接返回头节点
        //第二步：如果当前有k个节点，需要有五个变量一个左指针left，一个右指针right，另外三个pre,current,next用于将链表进行翻转
        //在第一次对k个节点进行翻转时，pre为null，current指向左值针，pre和current之间进行指针的翻转，next记录下一个需要翻转的节点，
        //避免current指向pre导致链表断裂，当current运动到右指针的时候，
        //先需要将左值针指向右指针的下一个节点，再进行内部的翻转，这时候还需要一个全新变量lastTrail来记录当前列表的左值针，
        //为了下一次k个节点翻转指向新的头节点，这时候第一次k个节点的翻转就结束，左值针移动到lastTail
        //的下一个节点，pre来到
        int count = 1;
        ListNode left = head;
        ListNode right = left;
        ListNode pre = null,current = left,next = null,lastTail = null;
        while (left!=null) {
            while (count <k) {
                count++;
                right = right.next;
                if (right == null) {
                    return head;
                }
            }
            if(left==head){
                head = right;
            }
            do{
                next = current.next;
                current.next = pre;
                pre = current;
                current = next;
            }while (pre!=right);
            if(lastTail!=null){
                lastTail.next = right;
            }
            lastTail = left;
            lastTail.next = next;
            left = lastTail.next;
            right = lastTail.next;
            pre = lastTail;
            current = left;
            count=1;
        }
        return head;
    }
    //Construct a deep copy of the list.
    // The deep copy should consist of exactly n brand new nodes,
    // where each new node has its value set to the value of its corresponding original node.
    // Both the next and random pointer of the new nodes should point to new nodes in the copied list
    // such that the pointers in the original list and copied list represent the same list state.
    // None of the pointers in the new list should point to nodes in the original list.
    public ListNode copyListWithRandomPointer(ListNode head){
        //分析：
        //用哈希表来存储每个旧节点，新节点（形成键值对，旧节点是键，新节点是值），然后新节点根据旧节点的连接方式一一去做复制
        //这种用容器的方式本质是在用哈希表中映射关系，让旧节点和新节点产生可查询的联系
        //做法：（假设3个节点，值分别为1、2、3）
        //1.遍历该链表，在1、2之间插入新节点1'，依次类推，这时候新旧节点已经形成映射关系，新节点就是旧节点的下一个
        //2.进行第二轮遍历，一次指针移动跨越两个节点，将其随即指针指向旧节点随机指针所指向节点的下一个
        //3.进行第三轮遍历，这次将改变next指针，不过这次需要两个指针变量，来分别存储新旧指针，确保新链表形成，旧链表恢复原样
        /// 要对head为空的情况进行额外判断
        if(head==null){
            return null;
        }
        ListNode pointer = head;
        while (pointer!=null){
            ListNode listNode = new ListNode(pointer.val);
            listNode.next = pointer.next;
            pointer.next = listNode;
            pointer = listNode.next;
        }
        pointer = head;
        ListNode newHead = head!=null?head.next:null;
        while (pointer!=null){
            if(pointer.random!=null) {
                pointer.next.random = pointer.random.next;
            }
            pointer = pointer.next.next;
        }
        pointer = head;
        ListNode otherPointer = newHead;
        while (otherPointer.next!=null){
            pointer.next = otherPointer.next;
            pointer = pointer.next;
            otherPointer.next = pointer.next;
            otherPointer = otherPointer.next;
        }
        pointer.next = null;
        return newHead;
    }
    //Implement a function to check if a linked list is a palindrome.
    public boolean palindromeLinkedList(ListNode head){
        //可以用栈这种容器，利用栈先进后出的性质，判断出栈的节点和沿链表顺下来的节点是否一致
        //1.不用容器，可以使用快慢指针，在头节点记录两个指针，一个快指针一个慢指针，快指针一次移动两步
        //慢指针一次移动一步，当快指针下一个节点为空时停止移动
        //2.类似于之前需要三个指针pre，current，next将快慢指针之间的节点逆序
        //3.逆序之后，左右两个指针分别从头节点和快指针出发判断左右节点的值是否相等
        /// 犯的错误
        /// 1.没有考虑如果head为空的情况
        /// 2.第三步的判断结束条件有误，是当移动节点到了慢指针的地方才要停止，这种以某个点为循环结束条件得用break来中断
        /// 3.因为需要用到满指针所以不能为了省几个空间就把慢指针当成变量作为逆序或者判断时用
        if(head==null){
            return true;
        }
        ListNode quickPointer = head;
        ListNode slowPointer = head;
        while (quickPointer.next!=null&&quickPointer.next.next!=null){
            slowPointer = slowPointer.next;
            quickPointer = quickPointer.next.next;
        }
        quickPointer = quickPointer.next==null?quickPointer:quickPointer.next;
        ListNode pre = null;
        ListNode next = slowPointer;
        ListNode current = slowPointer;
        while (next!=null){
            next = current.next;
            current.next = pre;
            pre = current;
           current = next;
        }
        current = head;
        while (true){
            if(current.val!= quickPointer.val){
                return false;
            }
            if(current==slowPointer){
                break;
            }
            current = current.next;
            quickPointer = quickPointer.next;
        }
        return true;
    }
    //Given the head of a linked list, return the node where the cycle begins.
    //If there is no cycle, return null.
    //There is a cycle in a linked list if there is some node in the list
    //that can be reached again by continuously following the next pointer.
    //Internally, pos is used to denote the index of the node that tail's next pointer is connected to (0-indexed).
    //It is -1 if there is no cycle. Note that pos is not passed as a parameter.
    //Do not modify the linked list.
    public ListNode detectCycle(ListNode head){
        //1.定义快慢指针，与之前一样，快指针走两步，慢指针走一步，直到二者相遇或者快指针下一个节点为空便停下
        //如果快指针下一个节点为空，直接返回空，如果两个指针相遇
        //2.将快指针重置到头节点，不过快指针这次每次走一步，满指针从现在的节点一次走一步，直到二者再次相遇
        //这次相遇即为循环的开始节点
        ListNode quickNode = head;
        ListNode slowNode = head;
        do {
            if(quickNode==null||quickNode.next==null){
                return null;
            }
            quickNode = quickNode.next.next;
            slowNode = slowNode.next;
        }while (quickNode!=slowNode);
        quickNode = head;
        while (quickNode!=slowNode){
            quickNode = quickNode.next;
            slowNode = slowNode.next;
        }
        return slowNode;
    }
    /// 当链表题目指针变量越写越复杂，情况的讨论越来越多时，就要思考自己是不是方式错了，而不是死犟接着写
    /// 这道题目我表面上用的是归并排序，其实更加类似于插入排序，所以时间复杂度会很高，超出时间限制
    /// 真正的归并排序应该是
    //Given the head of a linked list, return the list after sorting it in ascending order.
//    public ListNode sortList(ListNode head){
//        //按照归并排序来，不过为了空间O（1），不能用递归，得手动用步长代替
//        //1.第一次先按步长为1来，其实就是将所有数都当成一组，然后两两排序
//        //但是这个排序过程很复杂，需要进行场景模拟，假设现在步长为n，即AB两条链进行合并
//        //先定义两套左右指针即A左、A右，B左、B右，但是在将B往A合并的过程，需要有额外变量lastEnd来记录B左的前面一个节点
//        //这是为了B左往A合并的时候避免B链断裂，在合并这个过程中同样需要一个额外变量Node来代替B左将B左并到A链中，
//        //这个合并的具体过程：当B左发现大于A左并且小于A左下一个节点(前提该节点在该组中)，Node代替B左，B左指向下一个节点，lastEnd指向B左
//        //接着再让Node进入A链，A左移动到Node，本次合并结束
//        //合并循环的结束条件是B左移动到B右的右边，即B右的next指向B左
//        //当合并循环结束，外层步长乘2再进行一次内部合并循环
//        //2.再合并循环和步长循环之间需要再来一层循环来确定A左，A右等变量的位置，
//        //停止条件是无法再形成两组，即计数器和长度的差值小于等于步长
//        //3.还有一种情况没有考虑，B左移动到A左前面的情况
//        //如果B左移动到A左之前，就需要分两种情况，一种是A左为头节点，一种是A左为非头节点,如果是头节点可以直接添加，但如果是非头节点
//        //（只可能是插入到最开始A左的前面，这时候需要一个新的变量记录上一次合并的最后一个节点
//        //4.现在遇到一个漏洞，当B左和B右重合的时候即B链只有一个元素的时候，如果这一个元素需要被移动进A链
//        //这时候B左会移动到下一个元素，但是B右由于还指向这个移动元素就会进入A链中，这时候循环结束条件不满足
//        //接下来会发生顺序错乱甚至空指针报错，解决方案就是先判断B左是否等于B右，如果是，定义一个布尔变量，由true
//        //变为false，让本次循环结束整个循环终止
//        //5.上面这个方案行不通，因为我的A左还要根据B右来定，所以必须有所变动，可以在最后让A左和B左相等
//        //因为当只剩一个元素，而B左又向右移动一位，这时候的B左其实就是A左的位置
//        ListNode rightA,leftA,rightB,leftB,lastEnd,node,lastTermEnd = null;
//        boolean isContinue;
//        int counter;
//        int length = getLength(head);
//        if(length==0||length==1){
//            return head;
//        }
//        for (int step = 1; step < length; step<<=1) {
//            counter=0;
//            leftA = head;
//            while (length-counter>step){
//                //先分（即确定AB左右节点的位置
//                rightA = spilt(leftA,step);
//                leftB = rightA.next;
//                rightB = spilt(leftB,step);
//                lastEnd = rightA;
//                counter+=step<<1;
//                //再合
//                isContinue = true;
//                while (isContinue&&rightB.next!=leftB){
//                    if(leftB==rightB){
//                        isContinue = false;
//                    }
//                    if(leftB.val>=leftA.val&&(step==1||leftB.val<leftA.next.val)){
//                        node = leftB;
//                        leftB = node.next;
//                        lastEnd.next = leftB;
//                        node.next = leftA.next;
//                        leftA.next = node;
//                        leftA = node;
//                    }else if(leftB.val<leftA.val){
//                        node = leftB;
//                        leftB = node.next;
//                        lastEnd.next = leftB;
//                        if(leftA==head){
//                            head = node;
//                            head.next = leftA;
//                        }else {
//                            node.next = lastTermEnd.next;
//                            lastTermEnd.next = node;
//                        }
//                    }
//                }
//                if(!isContinue){
//                    leftA = leftB;
//                    lastTermEnd = rightA;
//                }else {
//                    leftA = rightB.next;
//                    lastTermEnd = rightB;
//                }
//            }
//        }
//        return head;
//
//
//    }
//    public int getLength(ListNode head){
//        ListNode node = head;
//        int count = 0;
//        while (node!=null){
//            node = node.next;
//            count++;
//        }
//        return count;
//    }
//
//    public ListNode spilt(ListNode start,int step){
//        int counter = 1;
//        ListNode end = start;
//        while (counter<step&&end.next!=null){
//            counter++;
//            end = end.next;
//        }
//        return end;
//    }
        /// 第二版链表排序
        public ListNode sortList(ListNode head){
            //思路分析：
            //1.获取当前列表的长度，为后续根据不同步长分组合并做准备
            //2.进行分组合并，第一组比较特殊，因为涉及头节点，需要单独处理
            //分组根据步长选定组别，然后将两组尾部制空，方便合并结束的判断
            //再定义两个全局变量start、end，先比较左右链表的头节点，决定哪个为start
            //之后在循环体中进行比较，该合并类似于两个有序数列进行合并，如果一方已经全部进入
            //列表而另外一方仍有元素，剩下的一列直接接到尾部，第一组处理完之后，将head值
            //设置为start，下一组的开始为end的下一个节点，然后进行上述类似操作
            int count = getLength(head);
            if(count==0||count==1){
                return head;
            }
            ListNode rightA,leftA,rightB,leftB,next,lastTermEnd;
            int counter;
            for (int step = 1; step < count; step<<=1) {
                counter = 0;
                leftA = head;
                rightA = divide(leftA,step);
                leftB = rightA.next;
                rightB = divide(leftB,step);
                next = rightB.next;
                rightA.next = null;
                rightB.next = null;
                merge(leftA,rightA,leftB,rightB);
                head = start;
                end.next = next;
                lastTermEnd = end;
                counter+=step<<1;
                while (count-counter>step){
                    leftA = lastTermEnd.next;
                    rightA = divide(leftA,step);
                    leftB = rightA.next;
                    rightB = divide(leftB,step);
                    next = rightB.next;
                    rightA.next = null;
                    rightB.next = null;
                    merge(leftA,rightA,leftB,rightB);
                    lastTermEnd.next = start;
                    end.next = next;
                    lastTermEnd = end;
                    counter+=step<<1;
                }
            }
            return head;
        }
        private static ListNode start;
        private static ListNode end;
        public ListNode divide(ListNode left,int step){
            ListNode node = left;
            while (node.next!=null&&--step!=0){
                node = node.next;
            }
            return node;
        }
        public void merge(ListNode leftA,ListNode rightA,ListNode leftB,ListNode rightB){
            if(leftA.val<=leftB.val){
                start = leftA;
                leftA = leftA.next;
            }else {
                start = leftB;
                leftB = leftB.next;
            }
            ListNode node = start,store;
            while (leftA!=null&&leftB!=null){
                if(leftA.val<=leftB.val){
                    store = leftA;
                    leftA = leftA.next;
                }else {
                    store = leftB;
                    leftB = leftB.next;
                }
                store.next = node.next;
                node.next = store;
                node = node.next;
            }
            if(leftA!=null){
                node.next = leftA;
                end = rightA;
            }
            if(leftB!=null){
                node.next = leftB;
                end = rightB;

            }
        }
        public int getLength(ListNode head){
            int count = 0;
            ListNode node = head;
            while (node!=null){
                count++;
                node = node.next;
            }
            return count;
        }



    public class ListNode {
        int val;
        ListNode next;
        ListNode random;
        ListNode(int x) {
            val = x;
            next = null;
        }
        public void print(ListNode head){
            ListNode pointer = head;
            while (pointer!=null){
                System.out.print(pointer.val+"->");
                pointer = pointer.next;
            }
        }
    }

    @Test
    public void testReverse(){
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        // 对应截图中的 k = 2
        int k = 2;

        ListNode result =reverseKGroup(head, k);

        // 纯靠 main 函数内部循环直接打印验证结果
        System.out.print("最终执行的输出: [");
        ListNode temp = result;
        while (temp != null) {
            System.out.print(temp.val);
            if (temp.next != null) {
                System.out.print(",");
            }
            temp = temp.next;
        }
        System.out.println("]");
    }
    @Test
        public void testCopyListStrictValidation() {
            // 1. 构建原始节点
            ListNode node0 = new ListNode(7);
            ListNode node1 = new ListNode(13);
            ListNode node2 = new ListNode(11);
            ListNode node3 = new ListNode(10);
            ListNode node4 = new ListNode(1);

            // 串联 next
            node0.next = node1;
            node1.next = node2;
            node2.next = node3;
            node3.next = node4;
            // node4.next 默认为 null

            // 串联 random
            node0.random = null;
            node1.random = node0;
            node2.random = node4;
            node3.random = node2;
            node4.random = node0;

            // --- 重点 1：用数组把原始节点的内存地址“死死记住” ---
            ListNode[] originalNodes = {node0, node1, node2, node3, node4};

            // 2. 调用你的方法
            ListNode resultHead = copyListWithRandomPointer(node0);

            // 3. --- 重点 2：反向审计！检查原链表是否被篡改 ---
            System.out.println("开始严格审计原链表完整性...");

            for (int i = 0; i < originalNodes.length; i++) {
                ListNode currentOriginal = originalNodes[i];

                // 计算当前节点本应该指向的下一个原始节点
                ListNode expectedNext = (i == originalNodes.length - 1) ? null : originalNodes[i + 1];

                // 对比当前节点的实际 next 是否还是期望的那个对象（比对内存地址）
                if (currentOriginal.next != expectedNext) {
                    System.err.println("=================================================");
                    System.err.println("🚨 发现错误爆点！原链表被破坏！");
                    System.err.println("问题出现在原链表的节点 val = " + currentOriginal.val + " (索引 " + i + ")");

                    if (expectedNext == null) {
                        System.err.println("期望的 next: null");
                    } else {
                        System.err.println("期望的 next: val=" + expectedNext.val + " (原始对象)");
                    }

                    if (currentOriginal.next == null) {
                        System.err.println("实际的 next: null");
                    } else {
                        System.err.println("实际的 next: val=" + currentOriginal.next.val + " (它被错误地指到了别的地方！)");
                    }
                    System.err.println("=================================================");
                    return; // 找到错误直接停止
                }
            }

            System.out.println("✅ 恭喜！原链表的 next 指针完好无损，没有被篡改。");

            // --- 重点 3：检查是否是真正的深拷贝 ---
            ListNode currResult = resultHead;
            int index = 0;
            while (currResult != null && index < originalNodes.length) {
                if (currResult == originalNodes[index]) {
                    System.err.println("🚨 深拷贝失败！新链表的节点 (val=" + currResult.val + ") 和原链表是同一个内存对象！");
                    return;
                }
                currResult = currResult.next;
                index++;
            }
            System.out.println("✅ 深拷贝验证通过，内存地址隔离成功。");
        }

    @Test
    public void testPalindromeLinkedList_basicCases() {
        Assert.assertTrue(palindromeLinkedList(buildList(1)));
        Assert.assertTrue(palindromeLinkedList(buildList(1, 2, 1)));
        Assert.assertTrue(palindromeLinkedList(buildList(1, 2, 2, 1)));

        Assert.assertFalse(palindromeLinkedList(buildList(1, 2)));
        Assert.assertFalse(palindromeLinkedList(buildList(1, 2, 3)));
        Assert.assertFalse(palindromeLinkedList(buildList(1, 2, 3, 4)));
    }

    @Test
    public void testPalindromeLinkedList_emptyList() {
        Assert.assertTrue(palindromeLinkedList(null));
    }


    private ListNode buildList(int... values) {
        if (values == null || values.length == 0) {
            return null;
        }

        ListNode head = new ListNode(values[0]);
        ListNode current = head;

        for (int i = 1; i < values.length; i++) {
            current.next = new ListNode(values[i]);
            current = current.next;
        }

        return head;
    }
    }

