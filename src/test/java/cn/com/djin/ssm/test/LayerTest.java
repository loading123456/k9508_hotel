package cn.com.djin.ssm.test;

import java.util.ArrayList;
import java.util.Scanner;

public class LayerTest {
    public static void main(String[] args) {
        Layer layer = new Layer();
        Integer[] arr={1,2,3};
        layer.setArr(arr);
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i <10 ; i++) {
            int a =scanner.nextInt();
            layer.method(a);
        }
    }
}
