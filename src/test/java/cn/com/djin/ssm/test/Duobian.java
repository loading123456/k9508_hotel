package cn.com.djin.ssm.test;

public class Duobian {
    public double[] arr1;
    public double[] arr2;
    public double[] arrx;
    public double[] arry;
    public double area;

    public Duobian(){

    }
    public Duobian(double[] arr1,double[] arr2){
        this.arr1=arr1;
        this.arr2=arr2;
    }
    public double getArea(){
        int n= arr1.length/2;

        for (int i = 0; i < n; i++) {
            arrx[i]= arr1[2*i];
            arry[i]= arr1[2*i+1];
        }

        for (int i = 0; i < n-1 ; i++) {
            area += ((arrx[i]-arr2[0])*(arry[i+1]-arr2[1])+(arrx[i+1]-arr2[1])*(arry[i]-arr2[0]))/2;
            area =Math.abs(area);
        }
        return area;
    }
}
