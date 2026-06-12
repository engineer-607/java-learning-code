package com.nanxinda.reflection;

public class ReflectionOptimize {
    public static void main(String[] args) {
        /// 反射优点：可以动态创建和使用对象（框架底层核心），使用灵活
        /// 缺点：使用反射基本都是解释执行，对执行速度有影响
        //反射调用优化
        //1.Method、Field、Constructor对象都有setAccessible()方法
        //2.setAccessible作用是启动和禁用访问安全检查的安全开关
        /// 3.参数值设置为true表示反射的对象在使用时取消访问检查，提高反射效率
    }
}
