package src.sort;

import java.util.Arrays;


/**
 * @Author: tangkeyon@gmail.com
 * @Date: 2019/8/21 10:40
 * @Version 1.0
 */
//选择排序的时间复杂度为O(n^2)级别
//选择排序的核心思想是遍历数组，首先找出全数组的最值并放到第一个位置
//在缩减的数组规模中寻找最值
public class SelectionSort{
    public  static void sort(Integer[]arr,int length){
        for(int i=0;i<length;i++){
             for(int j=i+1;j<arr.length;j++){
                 if(arr[j]<arr[i]){
                   ArrayUtils.swap(arr,i,j);
                 }
             }
        }

    }

    public static void main(String[] args) {
        Integer[] array = ArrayUtils.generateRandomArray(10000);
        long start = System.currentTimeMillis();
        sort(array,array.length);
        long end = System.currentTimeMillis();
        System.out.println((end-start));
        //System.out.println(ArrayUtils.checkValid(array));
    }
}
