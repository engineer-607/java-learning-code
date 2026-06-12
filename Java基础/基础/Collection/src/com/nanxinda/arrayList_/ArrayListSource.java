package com.nanxinda.arrayList_;

import java.util.ArrayList;

public class ArrayListSource {
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        //1.ArrayList中维护一个Object类型的数组elementData
        //transient Object[] elementData;
        ArrayList arrayList = new ArrayList();
        /*
        private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
        public ArrayList() {
              this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
        }//创建一个空的elementData

         */
        for (int i = 1; i <= 10; i++) {
            arrayList.add(i);
            //2.当创建ArrayList对象时，如果使用的是无参构造器，则初始化elementData容量为0
            //第一次添加，则扩容elementData为10，如需再次扩容，则扩容elementData为1.5倍
            /*
                public boolean add(E e) {
                  ensureCapacityInternal(size + 1);  // Increments modCount!!
                  elementData[size++] = e;
                  return true;
                 }
                 执行arrayList.add
                 1）先确定是否要扩容
               private void ensureCapacityInternal(int minCapacity) {
                   ensureExplicitCapacity(calculateCapacity(elementData, minCapacity));
               }
               private static int calculateCapacity(Object[] elementData, int minCapacity) {
                    if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
                          return Math.max(DEFAULT_CAPACITY, minCapacity);
                    }
                    return minCapacity;//该方法确定minCapacity：1）第一次扩容为10
                }
                private void ensureExplicitCapacity(int minCapacity) {
                     modCount++;//记录集合被修改的次数，防止多个线程同时修改
                     // overflow-conscious code
                     if (minCapacity - elementData.length > 0)//用所需的最小容量减去现有的容量判断是否需要扩容
                        grow(minCapacity);//如果elementData的容量不够就调用grow方法去扩容
                }
                //真正进行扩容
                private void grow(int minCapacity) {
                     // overflow-conscious code
                     int oldCapacity = elementData.length;
                     int newCapacity = oldCapacity + (oldCapacity >> 1);//1.5倍扩容机制
                     //oldCapacity >> 1先将oldCapacity转化为二进制，然后向右移动一位
                     //再将二进制转为十进制，本质上是十进制除以2，但这样速度更快，并且可以避免浮点数问题
                     if (newCapacity - minCapacity < 0)//应对第一次elementData.length=0的情况
                     //0-10<0，所以newCapacity = 10
                         newCapacity = minCapacity;
                     if (newCapacity - MAX_ARRAY_SIZE > 0)
                         newCapacity = hugeCapacity(minCapacity);
                     // minCapacity is usually close to size, so this is a win:
                         elementData = Arrays.copyOf(elementData, newCapacity);
                         //1.创建一个长度为newCapacity的数组
                         //2.将旧数组elementData中的所有数据复制到新数组中
                         //3.elementData指向这个新数组
                         //4.多出的位置用null进行填充
                }
                 2）然后再执行赋值
             */
        }
        for (int i = 11; i <= 15; i++) {
            arrayList.add(i);
        }
        //3.如果使用的是指定大小的构造器，则初始化elementData容量为指定大小，如果需要扩容
        //则直接扩容elementData为1.5倍
        ArrayList arrayList1 = new ArrayList(8);
        for (int i = 0; i < arrayList1.size(); i++) {
            arrayList1.add(i);
        }
        /*
                     创建一个指定大小的elementData数组this.elementData = new Object[initialCapacity]
                     public ArrayList(int initialCapacity) {
                           if (initialCapacity > 0) {
                               this.elementData = new Object[initialCapacity];
                           } else if (initialCapacity == 0) {
                               this.elementData = EMPTY_ELEMENTDATA;
                           } else {
                               throw new IllegalArgumentException("Illegal Capacity: "+
                                               initialCapacity);
                           }
                      }
         */
    }
}
