package src.sort;

import java.util.Arrays;

/**
 * @Author: tangkeyon@gmail.com
 * @Date: 2019/8/27 11:35
 * @Version 1.0
 */
//与常规的归并排序方法不同，自底而上的归并排序通过先归并较小的数组，然后在较小的数组的基础上归并成更大的数组
//将原数组看作由若干个长度为2，4，8……的子数组组成，知道原素组的长度与子数组的长度相同
//因为每个子数组的长度都是以2的指数，所以当原数组的长度为奇数时会使得子数组的Mid和r值溢出，因此子数组的首元素必须进行限制
//当sz=n时可能无法考虑到奇数长度的数组中最后一个元素
//那么当sz=n+1时一定可以将最后一个元素包括，这时r值会溢出因此要考虑Math.min(i+2*sz-1,length-1))，如果判断溢出就将最后一个元素设为r
public class MergeSortBU {

      public  static  void sort(Comparable[]arr){
          //外层循环控制待归并的较小的数组的规模
          //sz表示数组的大小，sz应该从2开始但是从2开始会导致计算的不方便，所以选择从1开始
          int length=arr.length;
          for(int sz=1; sz<=length; sz=sz*2){
              //内层循环控制较小数组的首元素的索引
              for(int i=0; i+sz<length; i+=2*sz){//注意下方mid的值不能越界
                  //当数组的长度为奇数时
                  MergeSort.merge(arr,i,i+sz-1,Math.min(i+2*sz-1,length-1));
              }
          }
      }

    public static void main(String[] args) {
       Integer[]arr={5,3,2,1,4};
        sort(arr);
        ArrayUtils.checkValid(arr);
       System.out.println(Arrays.toString(arr));
    }
}
