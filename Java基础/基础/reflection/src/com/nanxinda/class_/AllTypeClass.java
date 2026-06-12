package com.nanxinda.class_;

import java.io.Serializable;

public class AllTypeClass {
    public static void main(String[] args) {
        Class<String> stringClass = String.class;//外部类
        Class<Serializable> serializableClass = Serializable.class;//接口
        Class<Integer> integerClass = Integer.class;//数组
        Class<float[][]> aClass = float[][].class;//二维数组
        Class<Deprecated> deprecatedClass = Deprecated.class;//注解
        Class<Thread.State> stateClass = Thread.State.class;//枚举
        Class<Long> longClass = long.class;
        Class<Void> voidClass = void.class;
        Class<Class> classClass = Class.class;
        /// 万物皆对象，因此Class类也有自己的Class类对象
        /// Car.class可以视为汽车的设计图
        /// 而设计图一样有设计图（蓝图），特殊地，蓝图(Class.class)的设计图就是其本身
        /// 这被称为自举或者自描述

    }
}
