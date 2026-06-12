package com.nanxinda.alorithm.seek.dichotomy;

import java.util.ArrayList;

public class DichotomyDemo {
    public static void main(String[] args) {
        int[] array = {1,2,4,5,7,8,9};
        Dichotomy dichotomy = new Dichotomy(array);
        System.out.println(dichotomy.seek(9,0,array.length-1));
        System.out.println("================================");
        int[] arrayList = {1,3,5,5,11,11,11,12,13};
        DichotomyAdvanced dichotomyAdvanced = new DichotomyAdvanced(arrayList);
        dichotomyAdvanced.seek(11,0,arrayList.length-1);
        System.out.println(dichotomyAdvanced.getArrayList());
        System.out.println("================================");
        DichotomyNonRecursion dichotomyNonRecursion = new DichotomyNonRecursion(arrayList);
        dichotomyNonRecursion.seek(0);
        System.out.println(dichotomyNonRecursion.getArrayList());
    }
}
class Dichotomy{
    private int[] array;
    public Dichotomy(int[] array){
        this.array = array;
    }
    public Integer seek(int n,int left,int right){
        if(left>right){
            return null;
        }
        int mid = (left+right)/2;
        if(n>array[mid]){
            return seek(n,mid+1,right);
        }else if(n<array[mid]){
            seek(n,left,mid-1);
        }
            return mid;

    }
}
class DichotomyAdvanced{
    private int[] array;
    private ArrayList<Integer> arrayList = new ArrayList<>();
    public DichotomyAdvanced(int[] array){
        this.array = array;
    }
    public void seek(int n, int left, int right){
        if(left>right){
            return;
        }
        int mid = (left+right)/2;
        if(n>array[mid]){
            seek(n,mid+1,right);
        }else if(n<array[mid]){
            seek(n,left,right-1);
        }else {
            int i=mid,j=mid+1;
            while (i>=0&&array[i]==n){
                arrayList.add(i);
                i--;
            }
            while (j<=array.length-1&&array[j]==n){
                arrayList.add(j);
                j++;
            }
        }
    }

    public ArrayList<Integer> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<Integer> arrayList) {
        this.arrayList = arrayList;
    }
}
class DichotomyNonRecursion{
    //升序
    private int[] array;
    private ArrayList<Integer> arrayList = new ArrayList();

    public DichotomyNonRecursion(int[] array) {
        this.array = array;
    }

    public ArrayList<Integer> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<Integer> arrayList) {
        this.arrayList = arrayList;
    }

    public void seek(int num){
        int left = 0;
        int right = array.length - 1;
        int temp = 0;
        while (left<=right){
            temp = (left+right)/2;
            if(array[temp]>num){
                right = temp - 1;
            }else if(array[temp]<num){
                left = temp + 1;
            }else {
                int leftTemp = temp;
                int rightTemp = temp+1;
                while (leftTemp>=0&&array[leftTemp]==num){
                    arrayList.add(leftTemp);
                    leftTemp--;
                }
                while (rightTemp<=array.length-1&&array[rightTemp]==num){
                    arrayList.add(rightTemp);
                    rightTemp++;
                }
                break;
            }
        }
    }

}
