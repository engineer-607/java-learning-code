package com.nanxinda.class_.pcakage_;

public class Exercise02 {
    public static void main(String[] args) {
        Integer m = 1;
        Integer n = 1;
        System.out.println(m==n);//1在-128-127之间，直接返回数组中的Integer对象
        //T
        Integer x = 128;
        Integer y = 128;
        System.out.println(x==y);//大于127，都是重新new一个Integer对象
        //F
//        /**
//         * Returns an {@code Integer} instance representing the specified
//         * {@code int} value.  If a new {@code Integer} instance is not
//         * required, this method should generally be used in preference to
//         * the constructor {@link #Integer(int)}, as this method is likely
//         * to yield significantly better space and time performance by
//         * caching frequently requested values.
//         *
//         * This method will always cache values in the range -128 to 127,
//         * inclusive, and may cache other values outside of this range.
//         *
//         * @param  i an {@code int} value.
//         * @return an {@code Integer} instance representing {@code i}.
//         * @since  1.5
//         */
//        public static Integer valueOf(int i) {
//            if (i >= Integer.IntegerCache.low && i <= Integer.IntegerCache.high)
//                return Integer.IntegerCache.cache[i + (-Integer.IntegerCache.low)];
//            return new Integer(i);
//        }
        //在-128-127内就直接返回数组中的Integer对象（该数组已经创建完成）
        //大于127或者小于-128就返回new Integer(XXX)
        Integer integer = 127;
        int i = 127;
        System.out.println(i==integer);
        //只要有基本数据类型，判断值是否相等
    }
}
