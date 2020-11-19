package cn.com.djin.ssm.test;

import java.util.ArrayList;
import java.util.Arrays;

public class Layer {
    private String str;
    private Integer[] arr;
    public Layer(){
    }
    public String getStr(){
        return str;
    }
    public void setStr(String str){
        this.str=str;
    }
    public Integer[] getArr(){
        return arr;
    }
    public void setArr(Integer[] arr){
        this.arr=arr;
    }

    public Layer(String str, Integer[] arr){
        this.str=str;
        this.arr=arr;
    }

    public Object[] method(int i){
        ArrayList<Integer> arrayList = new ArrayList(Arrays.asList(arr));
        arrayList.add(i*10);
        Integer[] arr1 = new Integer[arrayList.size()];
        arrayList.toArray(arr1);
        arr = arr1;
        for (int j = 0; j <arr.length ; j++) {
            System.out.println(arr[j]);
        }
        return arr;
    }
}
