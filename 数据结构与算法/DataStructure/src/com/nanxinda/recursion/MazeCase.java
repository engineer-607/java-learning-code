package com.nanxinda.recursion;

public class MazeCase {
    public static void main(String[] args) {
        int[][] maze = new int[8][8];
        for (int i = 0; i < maze.length; i++) {
            maze[0][i] = 1;
            maze[7][i] = 1;
        }
        for (int i = 0; i < maze.length; i++) {
            maze[i][0] = 1;
            maze[i][7] = 1;
        }
        maze[3][1] = 1;
        maze[3][2] = 1;
        maze[4][3] = 1;
        maze[5][5] = 1;
        maze[5][6] = 1;
        maze[3][4] = 1;
        maze[2][4] = 1;
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                System.out.printf("%d\t",maze[i][j]);
            }
            System.out.print("\n");
        }
        System.out.println();

        if(Maze.solve(maze)){
            for (int i = 0; i < maze.length; i++) {
                for (int j = 0; j < maze[i].length; j++) {
                    System.out.printf("%d\t",maze[i][j]);
                }
                System.out.println();
            }

        }else {
            System.out.println("没找到通路");
        }


    }
}
class Maze{
    private static int[][] maze;
    //走迷宫为什么需要回溯（递归）：需要排查各种情况
    //递归的本质是自己调用自己
    //需要有递归结束的判断，递归的初始模板
     public static boolean solve(int[][] maze){
         Maze.maze = maze;
         Maze.maze[1][1] = 2;
         if(explore(5,1,1)){
             return true;
         }else {
             return false;
         }
     }
     /// 2表示路径，3表示死路，1表示障碍，过程中5、6、7、8代表探索的方向
     private static boolean explore(int direction,int x,int y){
         //添加方向，用5、6、7、8表示经过该点探测过的方向，如果之前该方向探测过便不再探测
         //避免卡死情况发生
         if(x==6&y==6){
             Maze.maze[x][y] = 2;
             return true;
         }//说明成功找到路线
         //如果暂时没有找到就先判断当前位置
         //1.如果x或者y等于8说明超界，返回false
         //2.如果该位置为1，说明此路不通，返回false
         //3.如果该位置为3，说明此路已经被探索过不通，返回false
         if(x==8||y==8||x<0||y<0){
             return false;
         }
         if(Maze.maze[x][y]>=5){
             return false;
         }
             switch (direction) {
                 case 5://下
                     if(Maze.maze[x-1][y]!=1) {
                         Maze.maze[x - 1][y] = 5;
                     }
                     break;
                 case 6://右
                     if(Maze.maze[x][y-1]!=1) {
                         Maze.maze[x][y - 1] = 6;
                     }
                     break;
                 case 7://上
                     if(Maze.maze[x+1][y]!=1) {
                         Maze.maze[x + 1][y] = 7;
                     }
                     break;
                 case 8://左
                     if(Maze.maze[x][y+1]!=1) {
                         Maze.maze[x][y + 1] = 8;
                     }
                     break;
             }
         if(Maze.maze[x][y]==1){
             return false;
         }
         if(Maze.maze[x][y]==3){
             return false;
         }

         //如果以上条件都不符合说明还在探索阶段（并舍弃一定的方向）
         //下-》右-》上-》左
         if(Maze.maze[x][y]<5&&explore(5,x+1,y)){//如果前方显示true，该侦察兵就报true，将位置显示为2
             Maze.maze[x][y] = 2;
             return true;
         }else if(Maze.maze[x][y]<6&&explore(6,x,y+1)){
             Maze.maze[x][y] = 2;
             return true;
         }else if(Maze.maze[x][y]<7&&explore(7,x-1,y)){
             Maze.maze[x][y] = 2;
             return true;
         } else if(Maze.maze[x][y]<8&&explore(8,x,y-1)){
             Maze.maze[x][y] = 2;
             return true;
         }else{
             Maze.maze[x][y] = 3;//如果前面侦察兵都报告路不通就记录为三
             return false;
         }





//         if(x==6&&y==6){
//             return true;
//         }
//         boolean judge;
//         switch (direction){
//             case 1://下
//                 if(x==7){
//                     return false;
//                 }
//                 judge = (Maze.maze[++x][y]!=1);//判断是否可以走
//                 if(judge){
//                     if(explore(1,x,y)){
//                         Maze.maze[x][y] = 2;
//                         return true;
//                     }
//                     return false;
//                 }else {
//                     if(isTurn(2,x-1,y+1)){
//                         if(explore(2,--x,y)){
//                             Maze.maze[x][y] = 2;
//                             return true;
//                         }
//                     }
//                     return false;
//                 }
//             case 2://右
//                 if(y==7){
//                     return false;
//                 }
//                 judge = (Maze.maze[x][++y]!=1);
//                 if(judge){
//                     if(explore(2,x,y)){
//                         Maze.maze[x][y] = 2;
//                         return true;
//                     }
//                     return false;
//                 }else {
//                     if(isTurn(1,x+1,y-1)){
//                         if(explore(1,x,--y)){
//                             Maze.maze[x][y] = 2;
//                             return true;
//                         }
//                     }
//                     return false;
//                 }
//         }
//         return false;
     }
//     private static boolean isTurn(int direction,int x,int y){
//         return Maze.maze[x][y]!=1;
//     }
}
