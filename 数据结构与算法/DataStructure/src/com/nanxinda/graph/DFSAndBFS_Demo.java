package com.nanxinda.graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * DFS和BFS的核心价值并不是遍历所有的顶点，如果是这样的话，仅仅需要一层外循环足以
 * 其核心价值在于在处理过程中，发现图的关系和结构，可以在处理过程中添加一些操作得到想要的性质
 * ————连通性、层次、路径、环、依赖顺序。
 *
 *  * | 维度       | DFS                | BFS                    |
 *  * |-----------|--------------------|------------------------|
 *  * | 核心       | 一条路走到底，再回头 | 一层一层向外扩展        |
 *  * | 数据结构   | 栈（递归）          | 队列                   |
 *  * | 遍历顺序   | 纵向深入            | 横向分层               |
 *  * | 最短路径   | ❌ 不能保证         | ✅ 能（无权图）         |
 *  * | 拓扑排序   | ✅ 能              | ❌ 不能（除非Kahn算法） |
 *  * | 检测环     | ✅ 能（有向图）     | ❌ 不擅长              |
 *  * | 连通性     | ✅ 能              | ✅ 能                  |
 *  * | 内存占用   | 小（递归深度）      | 大（队列大小）          |
 *  * | 代码结构   | 递归 + 外层循环     | 三层循环（for+while+for）|
 *
 */
public class DFSAndBFS_Demo {
    public static void main(String[] args) {
        Graph graph = new Graph(4);
        String[] strings = {"A","B","C","D"};
        graph.addValue(strings);
        graph.structForm("A","B");
//        graph.structForm("A","C");
//        graph.structForm("B","D");
//        graph.structForm("B","E");
//        graph.structForm("B","D");
        graph.structForm("C","D");
        graph.showAdjList();
        graph.BFS();
    }
}
class Graph{
    //邻接表（无向）
    private int[][] adjList;
    private HashMap<String,Integer> insertList;
    private HashMap<Integer,String> reflectTable;
    private HashMap<String,Boolean> judgeTable;

    private Queue<Integer> queue;
    public Graph(int n){
        adjList = new int[n][n];
        insertList = new HashMap<>();
        reflectTable = new HashMap<>();
        judgeTable = new HashMap<>();
        queue = new LinkedList<>();
        queue.add(0);
    }
    public void addValue(String[] strings){
        int index = 0;
        for (String string : strings) {
            insertList.put(string,index++);
        }
    }
    public void structForm(String s1,String s2){
        int n1 = insertList.get(s1);
        int n2 = insertList.get(s2);
        reflectTable.put(n1,s1);
        reflectTable.put(n2,s2);
        judgeTable.put(s1,false);
        judgeTable.put(s2,false);
        adjList[n1][n2] = 1;
        adjList[n2][n1] = 1;
    }
    public void showAdjList(){
        for (int[] ints : adjList) {
            System.out.println(Arrays.toString(ints));
        }
    }
    public void DFS(){
        for (int temp = 0;temp<adjList.length;temp++) {
            if(!judgeTable.get(reflectTable.get(temp))) {
                System.out.println(reflectTable.get(temp));
                judgeTable.put(reflectTable.get(temp), true);
                DFS(temp);
            }
        }
    }
    public void DFS(int temp){
        if(temp>=adjList.length){
            return;
        }
        int index = temp;
        for (;index<adjList[temp].length;index++) {
            if(adjList[temp][index]!=0&&!judgeTable.get(reflectTable.get(index))){
                System.out.println(reflectTable.get(index));
                judgeTable.put(reflectTable.get(index),true);
                DFS(index);
            }
        }
    }

    public void BFS(){
        for (int i = 0; i < adjList.length; i++) {
            if(!judgeTable.get(reflectTable.get(i))){
                System.out.println(reflectTable.get(i));
                judgeTable.put(reflectTable.get(i),true);
                queue.offer(i);
                while (!queue.isEmpty()){
                    int temp = queue.poll();
                    for (int k = 0; k < adjList[temp].length; k++) {
                        if(adjList[temp][k]!=0&&!judgeTable.get(reflectTable.get(k))){
                            System.out.println(reflectTable.get(k));
                            judgeTable.put(reflectTable.get(k),true);
                            queue.offer(k);
                        }
                    }
                }
            }
        }
    }
}

