package com.nanxinda.exception_;

public class Exception02 {
    /*
    异常处理方式
    1）try-catch-finally
    在代码中捕获发生的异常，自行处理
    try{
    代码（可能异常）
    }catch(Exception e){
    //捕获到异常
    1.当异常发生时
    2.系统将异常封装成Exception对象e，传递给catch()
    3.如果没有异常发生catch代码不执行
    }finally{
    //不管try代码是否有异常发生，始终要执行finally
    }
    2）throws
    将发生的异常抛出，交给调用者（方法）来处理，最顶级的处理者是JVM
 * 异常处理流程图（基于文件描述的层次结构）
 *
 * JVM (顶级异常处理器)
 * │
 * ├── 处理未捕获异常的默认行为：
 * │   1. 输出异常信息（包含堆栈跟踪）
 * │   2. 退出当前程序
 * │
 * └── main方法 (程序入口)
 *      │
 *      ├── 方式A：try-catch-finally处理异常
 *      │   │
 *      │   ├── 在main方法内部直接处理异常
 *      │   │
 *      │   └── 异常处理流程：
 *      │       1. try块执行代码
 *      │       2. 捕获异常 → catch块处理
 *      │       3. 无论是否异常，finally块都会执行
 *      │
 *      └── 方式B：throws声明抛出异常
 *          │
 *          └── 将异常抛给上层（JVM）处理
 *               │
 *               └── f1方法 (被main调用的方法)
 *                    │
 *                    ├── 方式A：try-catch-finally处理异常
 *                    │   │
 *                    │   └── 在f1方法内部直接处理异常
 *                    │
 *                    └── 方式B：throws声明抛出异常
 *                         │
 *                         └── 继续向上抛出异常
 *                              │
 *                              └── f2方法 (被f1调用的方法)
 *                                   │
 *                                   └── 抛出异常 (throw new Exception())
 *                                        │
 *                                        └── 异常沿调用链向上传播
 *                                             │
 *                                             └── 最终到达JVM（如果所有方法都throws）
 *
 要点：1）try-catch-finally和throws二选一
      2）如果没有显示处理异常，默认throws
     */
}
