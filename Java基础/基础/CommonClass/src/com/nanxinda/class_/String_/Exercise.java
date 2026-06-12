package com.nanxinda.class_.String_;

public class Exercise {
    public static void main(String[] args) {
        String a = "hello"+"abc";//->"helloabc"
        //只创建了一个对象，解析：
        //编译器优化判断常量池对象，是否有引用指向
        String b = "hello";
        String c = "abc";
        String d = b+c;
        /*
 * 第1步：StringBuilder sb = new StringBuilder();
 *   在堆中创建StringBuilder对象
 *   StringBuilder内部有char[]数组（初始长度16）
 *
 * 第2步：sb.append("hello")
 *   将"hello"的字符复制到StringBuilder的char[]数组中
 *   复制来源：常量池中"hello"的char[]数组
 *   复制目标：StringBuilder内部的char[]数组
 *
 * 第3步：sb.append("abc")
 *   将"abc"的字符追加到StringBuilder的char[]数组中
 *
 * 第4步：String c = sb.toString()
 *   这是关键！toString()的实现：
 *   return new String(value, 0, count);
 *
         * 流程：
         * 1. StringBuilder内部维护char[]数组
         * 2. 调用 sb.toString()
         * 3. 调用 String(char[] value, int offset, int count)
         * 4. 复制char[]数组创建新String对象（新String对象没有与常量池共享char数组）
         * 结果：❌ 常量池没有对应字面量
         * // 常量池：有"hello"、"abc"，但没有"helloabc"
         *
         * 对比new String("helloabc");
         * 调用 String(String original) 构造器
         * 新String对象与常量池共享char[]数组
         *
         * 结果：✅ 常量池有"helloabc"
         *
         * 对比：
         * final String b = "hello";
         * final String c = "abc";
         * String d = b + c;
         * 编译器处理过程：
         *
         * 源码：
         *   final String a = "hello";
         *   final String b = "world";
         *   String s5 = a + b;
         *
         * 编译期优化为：
         *   String s5 = "helloworld";
         *
         * 常量池状态：
         *   "hello", "world", "helloworld" 都在常量池
         *
         * 内存图示：
         *   a → 常量池"hello"
         *   b → 常量池"world"
         *   s5 → 常量池"helloworld"
         */

    }
}
