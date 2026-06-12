package com.nanxinda.set_;
@SuppressWarnings({"all"})
public class HashSetIncrement {
    public static void main(String[] args) {
        /*
        1.HashSet底层是HashMap，第一次添加时，table
        数组扩容到16，临界值(threshold)是16*0.75=12
        2.如果table数组使用到了临界值12，就会扩容到16*2=32
        新的临界值就是32*0.75=24，依次类推
        3.在Java8中，如果一条链表的元素个数到达TREEIFY_THRESHOLD（默认是8）
        并且table大小>=MIN_TREEIFY（默认是64），就会进行树化（红黑树）
         */
    }
}
