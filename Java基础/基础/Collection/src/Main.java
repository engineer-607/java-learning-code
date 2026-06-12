public class Main {
    public static void main(String[] args) {
        /*
        选择集合实现类——根据业务操作特点
        1）先判断存储的类型一组对象[单列]或者一组键值对[双列]
        2）一组对象[单列]：Collection接口
        允许重复：List
               增删多：LinkedList（底层维护了一个双向链表）
               改查多：ArrayList（底层维护Object而类型的可变数组）
        不允许重复：Set
               无序：HashSet[底层是HashMap，维护了一个哈希表 即（数组+链表+红黑树）
               有序（可排序）：TreeSet
               插入和取出顺序一致：LinkedHashSet，维护数组+双向链表
        3）一组键值对[双列]：Map
               键无序：HashMap[底层哈希表 jdk8：数组+链表+红黑树]
               键排序：TreeMap
               键插入和取出顺序一致：LinkedHashMap
               读取文件：Properties


         */

    }
}