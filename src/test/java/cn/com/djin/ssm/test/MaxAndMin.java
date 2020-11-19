package cn.com.djin.ssm.test;

import java.util.Scanner;

public class MaxAndMin {
    private int[] arr;
    public MaxAndMin(){}
    public MaxAndMin(int[] arr){
        this.arr=arr;
    }
    public void setArr(){
        Scanner scanner = new Scanner(System.in);
        arr = new int[8];
        for (int i = 0; i <arr.length ; i++) {
            arr[i] = scanner.nextInt();
        }
    }
    public int getMax(){
        int max=arr[0];
        for (int i = 0; i <arr.length; i++) {
            if (max<arr[i]){
                max=arr[i];
            }
        }
        return max;
    }
    public int getMin(){
        int min=arr[0];
        for (int i = 0; i <arr.length; i++) {
            if (min>arr[i]){
                min=arr[i];
            }
        }
        return min;
    }
}
