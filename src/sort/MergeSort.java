package src.sort;

import java.util.Arrays;

/**
 * @Author: tangkeyon@gmail.com
 * @Date: 2019/8/26 15:54
 * @Version 1.0
 */

/**
 * 归并排序将数组左半边的元素有序排列，再将数组右半边的元素有序排列,然后将左半边元素和右半边元素进行合并
 * 为使左半部分和右半部分有序，将两部分再次分为两个部分，递归进行
 * 直到数组中只包含一个元素，这时暂停递进，进行回归，将上一步的包含两个元素的数组进行排序
 *
 * 归并算法中将数组不断的分割成左右两部分较小的数组，也就让数组成为了一棵线段树,递归的过程类似BST的前序遍历过程
 * 每一次递进都是把数组分割为更小的规模，每一次回归的过程都是把两个规模较小的数组合并成一个较大的数组
 *
 * 归并算法本质上使用的是递归的分治思想，对一个数组进行排序，降低问题的规模，如果数组的左半部分和右部分有序…… */
public class MergeSort {
    public static void merge(Comparable[]a,int lo,int mid,int hi){
        //每一次的merge操作都要将原数组需要排列的部分复制到辅助数组中
        //之所以要复制一份是为了避免直接在原数组中操作会使得原数组中的元素被覆盖
        Comparable aux[]=new Comparable[hi+1];//包含最后一个元素，所以要+1
        for(int i=lo;i<hi+1;i++){
            aux[i-lo]=a[i];//从a数组的lo位置开始复制到aux从零开始，所以要减去偏移量
       }
//
        //在辅助数组中参照元素进行比较，将比较后的值直接放入原数组
        int i=lo,j=mid+1;
        for(int k=lo;k<=hi;k++){
            //当数组的左半边的数全部小于右边的数(升序排列)
            if(i>mid) {
               a[k]=aux[j-lo];
               j++;

            }//当左边的数用尽，取右边的数
            //当数组左半边的数全部大于右边的数(升序排列)
              else if(j>hi){
                a[k]=aux[i-lo];
                i++;
            }
              else if(aux[i-lo].compareTo(aux[j-lo])>0) {
                a[k]=aux[j-lo];
                j++;
            }else{
                a[k]=aux[i-lo];
                i++;
            }

        }
    }


    //类比二叉树的前序遍历3
    private static void dealSort(Comparable[]arr,int left,int right){
        if(left>=right)
            return;
        int mid=(left+right)/2;
        dealSort(arr,left,mid);
        dealSort(arr,mid+1,right);
        merge(arr,left,mid,right);
    }

    private static void dealSortPlus(Comparable[]arr,int l,int r){
        //如果数组的规模较小就使用插入排序
        if(r-l<=15){
            InsertionSort.sort(arr,l,r);
            return;
        }
          int mid=l+(r-l)>>2;
           dealSort(arr,l,mid);
           dealSort(arr,mid+1,r);
           //如果数组有序就不用再排列了
          if(arr[mid].compareTo(arr[mid+1])>0){
              merge(arr,l,mid,r);
         }

    }

    public static void sort(Comparable []arr){
        //dealSort(arr,0,arr.length-1);
        dealSortPlus(arr,0,arr.length-1);
    }

    public static void main(String[] args) {
//        Character []arr={'M','E','R','G','E','S','O','R','T','E','X','A','M','P','L','E'};
//        sort(arr);
//        ArrayUtils.checkValid(arr);
//        System.out.println(Arrays.toString(arr));
//        System.out.println(ArrayUtils.checkValid(arr));
        Integer[] array = ArrayUtils.generateRandomArray(1000000);
        long start = System.currentTimeMillis();
        //sort(array);
        Arrays.sort(array);
        long end = System.currentTimeMillis();
        System.out.println((end-start));
        System.out.println(ArrayUtils.checkValid(array));

    }
}
