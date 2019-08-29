package src.sort;

import java.util.Arrays;

/**
 * @Author: tangkeyon@gmail.com
 * @Date: 2019/8/21 10:59
 * @Version 1.0
 */
//插入排序复杂度为o(n^2)
//从数组中的第二个元素与前一个元素进行比较小于就换位
//对几乎顺序的的数组使用插入排序最有效
public class InsertionSort {
    //原生的插入排序  缺点：swap操作花费太多时间
    public static  void  sort(Integer[]arr,int length){
        for(int i=1;i<length;i++){
//            for(int j=i-1;j>=0;j--){
//                if(arr[i]<arr[j]){//这样写是错的，不是一个固定值与一个变量相比，而是两个变量进行比较，规模不断变大的冒泡排序
//                    ArrayUtils.swap(arr,i,j);
//                }
//            }
           for(int j=i;j>0;j--){
                if(arr[j]<arr[j-1])
                    ArrayUtils.swap(arr,j-1,j);//交换的本质是将每一个左边的元素与当前的元素进行比较
           //因为插入的左边按从小到大排列所以如果当前的值大于第一轮循环j-1就可以终止此轮循环了
                else break;
           }
        }
    }

    //插入排序改进：使用覆盖的方式代替swap操作
    public static  void  sortPro(Integer[]arr,int length){
            int j=0;
        for(int i=1;i<length;i++){
            int temp=arr[i];//将当前循环到的值拷贝一份
           for( j=i; j>0 && arr[j-1] >temp; j--){//如果左边的一个元素大于循环的值
               arr[j]=arr[j-1];//就将左边的元素进行移动，这时候j位置和j-1的位置上的值相同
            } 
               arr[j]=temp;
        }
    }

    /**
     * 对数组[l,r]范围进行插入排序
     * @param arr
     * @param l
     * @param r
     */
    public static void sort(Comparable[]arr,int l,int r){
        int j;
        for(int i=l+1;i<=r;i++){
            Comparable temp=arr[i];
            for( j=i;j>l && arr[j-1].compareTo(temp)>0;j--){
                arr[j]=arr[j-1];
            }
                arr[j]=temp;
        }
    }




    public static void main(String[] args) {
//        Integer[] array = ArrayUtils.generateRandomArray(100000);
//        long start = System.currentTimeMillis();
//        sortPro(array,array.length);
//        long end = System.currentTimeMillis();
//        System.out.println((end-start));
//        System.out.println(ArrayUtils.checkValid(array));
        Integer[] array = ArrayUtils.generateNearlyOrderedArray(100000, 100);
        long start = System.currentTimeMillis();
        sortPro(array,array.length);
        long end = System.currentTimeMillis();
        System.out.println((end-start));
        System.out.println(ArrayUtils.checkValid(array));


    }
}
