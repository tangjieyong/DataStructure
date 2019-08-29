package src.sort;

import java.util.Arrays;

/**
 * @Author: tangkeyon@gmail.com
 * @Date: 2019/8/26 21:05
 * @Version 1.0
 */
//如何让两个有序的数组合并成一个有序的数组
public class MergeArray {
    public  static int[] merge(int[]arr1,int[]arr2){
        int size=arr1.length+arr2.length;
        //创建目标数组，其规模为两个子数组的长度和
        int[]des=new int[size];
        int i=0,j=0;//使用i,j分别记录两个子数组当前的位置
          for(int k=0;k<size;k++){
              //当最值普遍分布在某一个数组时，会导致某个数组的当前指针超出数组大小
             if(i>arr1.length-1) des[k]=arr2[j++];//当一侧数组中的元素被全部取出后就依次把另外一个数组中的元素按序放入目标数组
              else if(j>arr2.length-1) des[k]=arr2[i++];
              else if(arr1[i]>arr2[j]) des[k]=arr2[j++];//依次比较两个数组中的元素，谁小就将谁取出放到目标数组中，然后索引自增
              else  des[k]=arr1[i++];
          }
          return des;
    }

    public static void main(String[] args) {
        int[]arr1={1,2,4,6};
        int[]arr2={3,5,8,9};
        int[] ints = merge(arr1, arr2);
        System.out.println(Arrays.toString(ints));
       // System.out.println(ArrayUtils.checkValid(ints));
    }
}
