package com.nanxinda.vector_;

import java.util.Vector;
@SuppressWarnings({"all"})
public class VectorDetail {
    public static void main(String[] args) {
        /*
        1）Vector类的定义说明
         public class Vector<E>
         extends AbstractList<E>
         implements List<E>, RandomAccess, Cloneable, java.io.Serializable
        2）Vector底层也是一个对象数组，protected Object[] elementData;
        3）Vector是线程同步的，即线程安全，Vector类的操作方法带有synchronized
            public synchronized void trimToSize() {
                   modCount++;
                   int oldCapacity = elementData.length;
                   if (elementCount < oldCapacity) {
                       elementData = Arrays.copyOf(elementData, elementCount);
                   }
            }
        4）在开发中，需要线程同步安全时，考虑使用Vector
         */
        Vector vector = new Vector(8);
        //扩容机制：
        //1）如果是无参，默认10，满后就按2倍扩
        /*
            public Vector(int initialCapacity) {
                  this(initialCapacity, 0);
            }
            public Vector() {
                  this(10);
            }
         */
        //2）如果指定大小，则每次直接按2倍扩
        for (int i = 0; i < vector.size(); i++) {
            vector.add(i);
            /*
            2.1将数据添加到vector集合中
                public synchronized boolean add(E e) {
                       modCount++;
                       ensureCapacityHelper(elementCount + 1);
                       elementData[elementCount++] = e;
                       return true;
                }
             2.2确定是否需要继续扩容
                private void ensureCapacityHelper(int minCapacity) {
                   // overflow-conscious code
                       if (minCapacity - elementData.length > 0)
                           grow(minCapacity);
                       }
                 }
             2.3实际扩容的算法
                     private void grow(int minCapacity) {
                         // overflow-conscious code
                         int oldCapacity = elementData.length;
                         int newCapacity = oldCapacity + ((capacityIncrement > 0) ?
                                            capacityIncrement : oldCapacity);
                        //capacityIncrement是构造器public Vector(int initialCapacity, int capacityIncrement) {}
                        //中设定好的，在Vector vector = new Vector(8);和Vector vector = new Vector();中都是默认为0
                         if (newCapacity - minCapacity < 0)
                                 newCapacity = minCapacity;
                         if (newCapacity - MAX_ARRAY_SIZE > 0)
                                 newCapacity = hugeCapacity(minCapacity);
                         elementData = Arrays.copyOf(elementData, newCapacity);
                     }
             */
        }

    }
}
