package src.sort;

import java.util.Arrays;

/**
 * @Author: tangkeyon@gmail.com
 * @Date: 2019/8/25 21:26
 * @Version 1.0
 */
//冒泡排序是比较两个相邻的元素，一轮比较结束后数组的某一侧会产生一个最值
public class BubbleSort {
//冒泡排序的外层循环控制排序的次数
    //内层循环控制从哪个数据开始排序和一直排序到哪个位置即可
    public static  void sort(Integer[]arr){
        for(int i=0;i<arr.length-1;i++){//倒数第二个元素的相邻元素是最后一个元素，所以最后一个元素不参与比较
            for (int j=0;j<arr.length-1-i;j++){//每次比较都会找出当前范围的极值，极值不参与比较
                if (arr[j]>arr[j+1]){
                    ArrayUtils.swap(arr,j,j+1);
                }
            }
        }
    }

    //数组数组内的元素有序则不用再进行循环
    public static  void sort_v2(Integer[]arr){
         boolean flag;
         for (int i=0;i<arr.length-1;i++){
             flag=false;
             for(int j=0;j<arr.length-1-i;j++){
                 if(arr[j]>arr[j+1]){//若产生了交换则表示数组无序需要进行进一步的排序
                     ArrayUtils.swap(arr,j,j+1);
                     flag=true;//若任意两个位置不需交换，即数组有序便可终止外层循环
                 }
             }
                  if(!flag) break;
         }
    }

    //在一个长度为n的数组中，若最后一次产生的交换发生在i那么索引从(n-i)一直到最后的元素都是有序的
    //可以将最后一次产生交换的索引进行记录，在下次循环中只要从数组的开头循环到此处即可
    public static void sort_v3(Integer[]arr){
          int lastSwap;//用于记录内层循环中一轮循环中最后一个产生交换的位置
          int n;//内层循环一轮结束后将交换的位置作为下一轮循环的终点
       for(int i=0;i<arr.length-1;i++){
           lastSwap=0;
           n=arr.length-1;
           for (int j=0;j<n;j++){
               if(arr[j]>arr[j+1]){
                   ArrayUtils.swap(arr,j,j+1);
                   lastSwap=j+1;//在每一轮循环中使用lastSwap保存产生交换的位置，内部循环一轮结束后，保存的就是最后一次产生交换的索引
               }
           }
           n=lastSwap;//在下一轮循环开始前，把上一轮的位置传给下一轮循环，作为下一轮内部循环的边界
          // if (lastSwap==0) break;//每一次的外部循环开始前都会重置lastSwap索引如果没有产生交换就表示数组有序，退出循环(这一步是多余的)
       }
    }

    public static void main(String[] args) {
        Integer[] array = {2,5,1,3,8,9,7};
        long start = System.currentTimeMillis();
       //sort(array);
       //sort_v2(array);
        sort_v3(array);
        long end = System.currentTimeMillis();
        System.out.println((end-start));
        System.out.println(ArrayUtils.checkValid(array));
        System.out.println(Arrays.toString(array));

//        int arr[]={5,3,4,1,7,8,9,0};
//        sort_v3(arr);
//        System.out.println(Arrays.toString(arr));
//        System.out.println(ArrayUtils.checkValid(arr));
    }
}
