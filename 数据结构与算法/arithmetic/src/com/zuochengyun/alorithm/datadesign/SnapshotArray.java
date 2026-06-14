package com.zuochengyun.alorithm.datadesign;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//SnapshotArray(int length) initializes an array-like data structure with the given length.
//Initially, each element equals 0.
//void set(index, val) sets the element at the given index to be equal to val.
//int snap() takes a snapshot of the array and returns the snap_id:
//the total number of times we called snap() minus 1.
//int get(index, snap_id) returns the value at the given index,
//at the time we took the snapshot with the given snap_id
@SuppressWarnings("all")
public class SnapshotArray {
//    //思路分析：
//    //1.使用数组List<int[]>[] history的形式,解释：数组元素的类型是List，然后每个List里面
//    //记录每次快照所照的数据（只有数据被更改才会被记录进去）
//    //2.snap等于-1记录现在的状态,每次执行snap方法时都将最后一次快照的值和现在的值进行比较
//    //如果二者相等就把最后记录的快照的次数+1，如果二者不相等就这次的快照值和次数加入ints集合中
//    //这里有一种特殊情况就是ints只有一个元素的时候也就是第一次拍快照会遇到问题：无法将元素添加
//    //需要将这种情况单独讨论
//    List<int[]>[] history;
//    int snap = -1;
//
//    public SnapshotArray(int length) {
//        history = new ArrayList[length];
////NOTE
////  ints只是一个临时变量，不能通过这种方式给history每个元素赋值
//

    /// /        for (List<int[]> ints : history) {
    /// /            ints = new ArrayList<int[]>();
    /// /            ints.add(new int[]{snap,0});
    /// /        }
//        //等同于
//        //for (int i = 0; i < history.length; i++) {
//        //    List<int[]> ints = history[i];
//        //    ints = new ArrayList<int[]>();
//        //    ints.add(new int[]{snap, 0});
//        //}
//        for (int i = 0; i < history.length; i++) {
//            history[i] = new ArrayList<>();
//            history[i].add(new int[]{snap,0});
//        }
//    }
//
//
//    public void set(int index, int val) {
//        history[index].get(0)[1] = val;
//    }
//
//    public int snap() {
//        snap++;
//        int now;
//        for (List<int[]> ints : history) {
//            now = ints.get(0)[1];
//            if(ints.size()==1){
//                ints.add(new int[]{snap,now});
//            }else if(ints.get(ints.size()-1)[1]==now){
//                ints.get(ints.size()-1)[0] = snap;
//            }else {
//                ints.add(new int[]{snap,now});
//            }
//        }
//        return snap;
//    }
//
//    public int get(int index, int snap_id) {
//        int i = 0;
//        for (int[] ints : history[index]) {
//            if (snap_id<=ints[0]){
//                break;
//            }
//            i++;
//        }
//        return history[index].get(i)[1];
//
//    }
//
    public static void main(String[] args) {
        try {
            SnapshotArray snapshotArray = new SnapshotArray(3);

            snapshotArray.set(0, 5);
            int snapId0 = snapshotArray.snap();
            snapshotArray.set(0, 6);

            System.out.println("题目示例 snap()：期望 0，实际 " + snapId0);
            System.out.println("题目示例 get(0, 0)：期望 5，实际 "
                    + snapshotArray.get(0, snapId0));

            int snapId1 = snapshotArray.snap();
            snapshotArray.set(1, 8);
            int snapId2 = snapshotArray.snap();
            snapshotArray.set(1, 10);

            System.out.println("未设置元素 get(2, 0)：期望 0，实际 "
                    + snapshotArray.get(2, snapId0));
            System.out.println("修改前快照 get(1, 1)：期望 0，实际 "
                    + snapshotArray.get(1, snapId1));
            System.out.println("修改后快照 get(1, 2)：期望 8，实际 "
                    + snapshotArray.get(1, snapId2));
            System.out.println("旧快照保持不变 get(0, 0)：期望 5，实际 "
                    + snapshotArray.get(0, snapId0));
        } catch (Exception e) {
            System.out.println("测试过程中出现异常：");
            e.printStackTrace();
        }
    }

        //NOTE 超时分析：
        //  1.我的snap是想把当时快照时刻所有的数值都记录下来，但是其实没有必要，因为我设计的数据结构
        //  中就已经将snap_id和val捆绑在一起，我每一次set其实都是在预测下一次snap时候为多少，比如说
        //  我一开始就将snap_id设置为0，所有val默认值为0，之后经过几个set，然后现在的结果其实就是
        //  真正snap的结果
        //  2.我当时没有想到这种预测的方式，其实是因为我把现在的值和拍照记录的值分离开来，我专门用一个snap_id为-1
        //  来记录现在的值，即是每次set方法存放数据的地方，那我进行snap方法时就必须去对比现在的值和最后一次拍照的
        //  是否一样，但是这个判断其实在set方法中就可以做，我这种snap全遍历检查值相等的操作其实是多此一举
        List<int[]>[] history;
        int snap = 0;

    public SnapshotArray( int length){
            history = new ArrayList[length];
            for (int i = 0; i < history.length; i++) {
                history[i] = new ArrayList<>();
                history[i].add(new int[]{snap, 0});
            }
    }

        public int snap () {
            return snap++;
        }

        public void set ( int index, int val){
            //每次set的时候，将值放到下一次snap时更新的snap_id值后
            List<int[]> list = history[index];
            int[] last = list.get(list.size() - 1);
            if (last[0] == snap) {
                //说明snap值对应的快照还没开始，现在是预测时期
                last[1] = val;
            } else {
                //说明快照已经结束，该值不能动，同时需要预测下一次快照值
                list.add(new int[]{snap, val});
            }
        }
        // BUG 如果传入尚未进行的snap_id能获取到值，因为有预测值，但是题目中并没有讲遇到这种问题返回什么
        //   所以暂不考虑（并且传入snap_id的方式也是通过snap方法，但是如果传入snap()+1也能得到值，这不是
        //   大问题，但是想记录一下）
        public int get ( int index, int snap_id){
            List<int[]> list = history[index];
            int l = 0,
                    r = list.size() - 1,
                    ans = 0, mid = 0;
            //使用左右指针折半法加快查找速率
            while (l <= r) {
                mid = (l + r) / 2;
                if (list.get(mid)[0] <= snap_id) {
                    l = mid + 1;
                    ans = mid;
                } else {
                    r = mid - 1;
                }
            }
            return list.get(ans)[1];
        }



}
