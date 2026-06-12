package com.nanxinda.class_.System_;

import java.util.Arrays;

public class SystemDetail {
    public static void main(String[] args) {
        //1）exit退出当前程序
//        System.out.println("ok1");
//        System.exit(0);
        //1.exit(0) 表示程序退出
        //2. 0表示一个状态，表示正常状态
//        System.out.println("ok2");
        int[] src = {1,2,3};
        int[] dest = new int[3];
        /*
        1.五个参数
     * @param      src      the source array.
     srcPos：从源数组的哪个索引位置开始拷贝
     * @param      srcPos   starting position in the source array.
     //dest：目标数组，即把源数组的数据拷贝到哪个数组
     * @param      dest     the destination array.
     //destPos：把源数组的数据拷贝到目标数组的哪个索引
     * @param      destPos  starting position in the destination data.
     //length：从源数组拷贝多少个数据到目标数组
     * @param      length   the number of array elements to be copied.
         */
        System.arraycopy(src,0,dest,0,src.length);
        System.out.println(Arrays.toString(src));
        //currentTimeMillis()返回当前时间距离1970-1-1的毫秒数

    }

}
