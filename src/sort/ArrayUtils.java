package src.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @Author: tangkeyon@gmail.com
 * @Date: 2019/8/21 11:05
 * @Version 1.0
 */
public class ArrayUtils {
     private static Random random;

     static {
         random=new Random();
     }
    public static void swap(Comparable[] arr, int j, int i) {
        Comparable temp=0;
        temp=arr[j];
        arr[j]=arr[i];
        arr[i]=temp;
    }

    public  static Integer[] generateRandomArray(int size){
        Integer arr[]=new Integer[size];
         random=new Random();
        for(int i=0;i<=size-1;i++){
            arr[i]=random.nextInt(100);
        }
        return  arr;
    }

    public  static boolean checkValid(Comparable[]arr){
        for(int i=0;i<arr.length-1;i++){
            if(arr[i].compareTo(arr[i+1])>0)
                return false;
        }
            return true;
    }

    public static Integer[] generateNearlyOrderedArray(int capacity,int swapTime){
        Integer arr[]=new Integer[capacity];
        for(int i=0;i<capacity;i++){
            arr[i]=i;
        }
        for(int j=0;j<swapTime;j++){
            swap(arr,random.nextInt(capacity-1),random.nextInt(capacity-1));
        }
        return  arr;

    }

    public static  Integer[] generateNearlyRepeatArray(int capacity,int repeatSeed){
        Integer arr[]=new Integer[capacity];
        for(int i=0;i<capacity;i++){
            arr[i]=i%repeatSeed;
        }
         return  arr;
    }

    public static void main(String[] args) {
 //       Integer[] ints = generateRandomArray(1000);
 //       System.out.println(Arrays.toString(ints));
//        int arr[]={3,4,5,6};
//        System.out.println(checkValid(arr));

        Integer[] array = generateNearlyRepeatArray(10, 3);
        System.out.println(Arrays.toString(array));
    }

}
