package com.nanxinda.reflection;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;
@SuppressWarnings({"all"})
public class ReflectionCase {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        //1.使用Properties类，读写文件
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\re.properties"));
        String classfullPath = properties.get("classfullpath").toString();
        String methodName = properties.get("method").toString();
        System.out.println("classfullpath="+classfullPath);
        System.out.println("method="+ methodName);
        //2.传统的方法无法创建对象解决不了问题=》反射机制
        //3.使用反射机制解决
        ///(1)加载类(Class是一个普通的类与Cat、Dog这些一样不过其名称被起做Class)
        Class aClass = Class.forName(classfullPath);
        ///(2)可以通过aClass得到加载的类com.nanxinda.Cat的对象实例
        Object o = aClass.newInstance();
        ///(3)通过aClass得到加载类 com.nanxinda.Cat的methodName(hi)的方法对象
        //在反射中，把方法视为对象（万物皆对象）
        Method method = aClass.getMethod(methodName);
        ///(4)通过method调用方法，即通过方法对象来实现调用方法
        method.invoke(o);///在传统方法中：对象.方法(),而在反射机制中：方法.invoke(对象)
    }
    /// 反射
    /// 1.反射机制允许程序在执行期借助于ReflectionAPI取得任何类的内部信息（比如成员变量，构造器，成员方法等等）
    /// 并能操作对象的属性及方法（反射在设计模式和框架底层用到）
    /// 2.加载完类之后，在堆中就产生一个Class类型的对象（一个类只有一个Class对象），这个类包含类的完整结构
    /// 信息。通过这个对象得到类的结构（这个Class对象就像一面镜子，透过这个镜子看到类的结构）


    ///编译阶段是需要我自己来操纵，但是现在IDEA自动帮我完成这个步骤，
    ///而类加载实际上加载的对应于Cat类的Class类的对象，这个对象里面有很多数组，
    ///他们对应着Cat类的成员变量，构造器等等，
    ///等到运行时JVM会根据这个Class类的对象对Cat类的对象进行实例（在内存中开辟这个对象的地址）
    // ========== 编译过程 ==========
/**
 * 执行编译命令：javac Cat.java
 *
 * 编译结果生成：Cat.class 字节码文件
 *
 * Cat.class 字节码文件内容（结构信息）：
 * ┌─────────────────────────────────┐
 * │  类名: Cat                       │
 * │  成员变量:                       │
 * │    - private String name        │
 * │  构造器:                         │
 * │    - public Cat()               │
 * │  成员方法:                       │
 * │    - public void hi()           │
 * └─────────────────────────────────┘
 */
/**
 * 类加载器 ClassLoader
 * ---------------------------------------------------
 * 作用：将 Cat.class 字节码文件加载到内存中
 * 体现：反射机制的基础
 *
 * 加载过程：
 * Cat.class 字节码文件
 *     ↓ (ClassLoader.loadClass())
 * 内存中的Class对象
 */

/**
 * 第二阶段：Class类阶段/加载阶段 (Loading Phase)
 * ---------------------------------------------------
 * 当JVM启动并首次用到Cat类时，类加载器开始工作
 */
/**
 * 在堆内存的方法区中创建了一个 Class 对象
 * 这个对象是Cat类的"模板/说明书"
 *
 * Class类对象 (堆内存中)
 * ┌─────────────────────────────────────┐
 * │        java.lang.Class对象           │
 * │        代表 Cat 类                   │
 * ├─────────────────────────────────────┤
 * │ 成员变量 Field[] fields              │
 * │  ┌─────────────────────────────┐   │
 * │  │ [0] Field("name", String)   │   │
 * │  └─────────────────────────────┘   │
 * ├─────────────────────────────────────┤
 * │ 构造器 Constructor[] cons           │
 * │  ┌─────────────────────────────┐   │
 * │  │ [0] Constructor("Cat()")    │   │
 * │  └─────────────────────────────┘   │
 * ├─────────────────────────────────────┤
 * │ 成员方法 Method[] ms                │
 * │  ┌─────────────────────────────┐   │
 * │  │ [0] Method("hi()")          │   │
 * │  └─────────────────────────────┘   │
 * └─────────────────────────────────────┘
 */
/**
 * 第三阶段：Runtime运行阶段 (Runtime Phase)
 * ---------------------------------------------------
 * 程序真正执行时，创建对象并调用方法
 */
    /**
     * Cat对象 (堆内存中)
     * ┌─────────────────────────┐
     * │       Cat对象            │
     * ├─────────────────────────┤
     * │ 对象头                   │
     * │  └─ Class指针 ───────┐  │
     * ├─────────────────────│──┤  │
     * │ name = null         │  │  │
     * └─────────────────────│──┘  │
     *                       │     │
     *                       ▼     │
     *                 ┌───────────────────┐
     *                 │   Class对象       │
     *                 │   (Cat的模板)     │
     *                 └───────────────────┘
     * 该对象知道自己是属于哪个Class对象
     * 通过对象头的Class指针可以找到Class对象
     */
}
