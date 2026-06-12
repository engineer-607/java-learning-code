package com.nanxinda.cases;

public class case06 {
}
class Grand{
    String name="AA";
    private int age=100;
    public void g1(){};
}
    class Father extends Grand{
         String id="001";private double score;
         public void f1(){
           //super可以访问哪些成员(属性和方法)?name g1
          // this可以访问哪些成员?id score f1 name g1

         }

   }
   class Son extends Father{
        String name="BB";
        public void g1(){}
            private void show(){
        //super可以访问哪些成员(属性和方法)?name g1 id f1

                // this可以访问哪些成员? name g1 id f1 show

        }
   }