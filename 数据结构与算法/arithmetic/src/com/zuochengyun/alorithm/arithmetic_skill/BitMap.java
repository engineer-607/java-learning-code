package com.zuochengyun.alorithm.arithmetic_skill;


import java.util.HashSet;

/**
 * 位图原理：
 * 用bit组成的数组来存放值，用bit状态1、0代表存在、不存在，限制是这个表示范围必须为连续且不能过大
 */
/// 性能分析：1.时间复杂度极低，仅为O(1)
///         2.但是空间复杂度线性增长，因为位图的本质是通过一段连续的空间去映射一段范围
///         这就意味着即便用到的数字极少（在这段空间内分布的很稀疏），仍然需要申请这么连续的空间
///         这就会导致内存的利用率极低。此外，位图在处理几亿以内的高密度数据，可以说是性能天花板，因为其仅消耗十几MB
/// 1,0000,0000/8/2**32/2**32 = 11.92MB
///         但是当这个数据量达到百亿级别，会遇到硬件方面（比如缓存）等的限制，就需要再考虑别的数据结构
public class BitMap {
    public int[] storeBits;
    public int maxInt;

    public static void main(String[] args) {
        int n = 1000;
        int testTimes = 10000;
        /// 对数器
        /// 暴力解决用HashSet
        System.out.println("测试开始");
        BitMap bitMap = new BitMap(n);
        HashSet<Integer> hashSet = new HashSet<>(n);
        System.out.println("调用阶段开始");
        for (int i = 0; i <=testTimes; i++) {
            double decide = Math.random();
            int num = (int)(Math.random()*n);
            if(decide<0.33){
                bitMap.add(num);
                hashSet.add(num);
            }else if(decide<0.66){
                bitMap.remove(num);
                hashSet.remove(num);
            }else {
                bitMap.reserve(num);
                if(hashSet.contains(num)){
                    hashSet.remove(num);
                }else {
                    hashSet.add(num);
                }
            }
        }
        System.out.println("测试阶段开始");
        for (int i = 0; i <=n; i++) {
           if(bitMap.contains(i)!=hashSet.contains(i)){
               System.out.println("出错了");
           }
        }

    }
    public BitMap(int n){
        storeBits = new int[n/32+1];
        maxInt = n;
    }
    public void add(int num){
        if(num<0||num>maxInt||contains(num)){
            System.out.println("无法添加");
            return;
        }
        storeBits[num/32] ^=(0x00000001<<(num%32));
        /// 添加依赖contains这个方法的外部判断，如果一个数并没有添加，经过判断和添加要经历两次
        /// 位运算，较为累赘，可以改为一次变化
        /// storeBits[num/32] |=(0x00000001<<(num%32));这样无论这位是1还是0最后都还是1
    }
    public void remove(int num){
        if(num<0||num>maxInt||!contains(num)){
            System.out.println("无法移除");
            return;
        }
        storeBits[num/32] ^=(0x00000001<<(num%32));
        /// 与上面添加逻辑相似，删除的操作同样累赘，可以采用
        /// storeBits[num/32] &=~(0x00000001<<(num%32));这样不管这位是0还是1最后都是0
    }
    public boolean contains(int num){
        return ((storeBits[num/32]>>>(num%32))&0x00000001)==1;
    }
    public void reserve(int num){
        if(num<0||num>maxInt){
            System.out.println("无法进行反转");
        }
        storeBits[num/32] ^=(0x00000001<<(num%32));
    }
}
/// leetcode实操
/// 分析不足之处：
/// 我把sortBits当作最终结果来操作，这就导致我在执行flip方法时真的会把每一位进行翻转，这是思维的惰性，我可以把他当工具，我完全没有必要
/// 把他当作结果来操作，因为我最终给结果我是通过toString，count等方法给出当前位图的状态，而不是把数组直接给外界，在把数组转化成
/// 正确的结果这个过程，我就有很多可以操作的空间。
/// 如果把该数组当作工具的话，我就没有必要真的将数组进行翻转，我可以设置一个boolean属性reserve，来记录这个数组是否进行了翻转
/// 我只要把数组的当前状态结合reserve属性就可以得到真实的数组，这就解决了toString方法；同时设置zeros和ones来贯穿始终来记录
/// 0和1的数量，如果发生翻转，将这个两个数进行互换就可以；fix和unfix根据是否进行翻转的状态和当前数组得知是否需要进行操作
class Bitset {
    private int maxSize;
    private int[] sortBits;
    //Bitset(int size) Initializes the Bitset with size bits, all of which are 0.
    public Bitset(int size) {
        //size表示这个这个位图能连续表示的范围的长度
        /// 如果a/b想要向上取整应该写做(a+b-1)/b
        sortBits = new int[(size+31)/32];
        maxSize = size;
    }
//    void fix(int idx) Updates the value of the bit at the index idx to 1. If the value was already 1, no change occurs.
    public void fix(int idx) {
       sortBits[idx/32] |=0x000000001<<(idx%32);
    }
//    void unfix(int idx) Updates the value of the bit at the index idx to 0. If the value was already 0, no change occurs.
    public void unfix(int idx) {
       sortBits[idx/32] &=~(0x00000001<<(idx%32));
    }
//    void flip() Flips the values of each bit in the Bitset. In other words, all bits with value 0 will now have value 1 and vice versa.
    public void flip() {
        for (int i = 0; i < sortBits.length; i++) {
            if(i==sortBits.length-1&&maxSize%32!=0){
                sortBits[i] |= (0xffffffff<<(maxSize%32));
            }
            sortBits[i]=~sortBits[i];
        }
    }
//    boolean all() Checks if the value of each bit in the Bitset is 1. Returns true if it satisfies the condition, false otherwise.
    public boolean all() {
        int i;
        for (i = 0; i < sortBits.length-1; i++) {
            if((sortBits[i])!=0xffffffff){
                return false;
            }
        }
        int remainder = maxSize % 32;
        /// 考虑最后一个存储二进制元素满32位的情况
        if (remainder == 0) {
            return sortBits[i] == 0xffffffff;
        } else {
            return sortBits[i] == ~(0xffffffff << remainder);
        }
    }

    public boolean one() {
        for (int i = 0; i < sortBits.length; i++) {
            if(sortBits[i]!=0){
                return true;
            }
        }
        return false;
    }
//    int count() Returns the total number of bits in the Bitset which have value 1.
    public int count() {
        int count=0;
        for (int i = 0; i < sortBits.length; i++) {
            int bit = sortBits[i];
            bit = getBit(bit);
            count+=((bit>>>16)&0x0000ffff)+(bit&0x0000ffff);
        }
        return count;

    }

    static int getBit(int bit) {
        bit=((bit>>>1)&0x55555555)+(bit&0x55555555);
        bit=((bit>>>2)&0x33333333)+(bit&0x33333333);
        bit=((bit>>>4)&0x0f0f0f0f)+(bit&0x0f0f0f0f);
        bit=((bit>>>8)&0x00ff00ff)+(bit&0x00ff00ff);
        return bit;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        // 直接从第 0 位遍历到最后一位
        for (int i = 0; i < maxSize; i++) {
            // 利用你之前写 contains 的逻辑：定位到具体的 int，再右移提取
            s.append((sortBits[i / 32] >> (i % 32)) & 1);
        }
        return s.toString();
    }

    public static void main(String[] args) {
        Bitset bitset = new Bitset(5);
        bitset.fix(1);
        bitset.fix(3);
        System.out.println(bitset);
    }
}

/**
 * Your Bitset object will be instantiated and called as such:
 * Bitset obj = new Bitset(size);
 * obj.fix(idx);
 * obj.unfix(idx);
 * obj.flip();
 * boolean param_4 = obj.all();
 * boolean param_5 = obj.one();
 * int param_6 = obj.count();
 * String param_7 = obj.toString();
 */