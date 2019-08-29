package src.sort;

import java.util.Arrays;
import java.util.Random;

import static src.sort.ArrayUtils.swap;

/**
 * @Author: tangkeyon@gmail.com
 * @Date: 2019/8/27 19:16
 * @Version 1.0
 */
//和归并排序类似，快速排序通过递归也是将数组抽象成一棵二叉树结构
//不同于归并二叉树始终两边平衡，快速排序有时(顺序排列的数组或者重复元素很多的数组)二叉树结构会严重的失去平衡
public class QuickSort {
    //不要在反复调用的方法中创建对象，方法中要使用对象可以直接使用成员变量或者将对象作为方法参数传入
    //类中使用静态方法后调用该方法就不用再创建对象，这时想要为类中的成员初始化(静态成员)可以在类中使用静态代码块的形式
      private  static  Random random;
      static  int count=0;

      static {
          random=new Random();
      }

    //左部份保存小于V的值，右部分保存大于V的值
    private static int partition(Comparable[]arr, int lo, int hi){
        //将数组的首元素作为基准值进行保存,如果数组为有序数组的话会让树状结构退化为链表结构
      int ranIndex =lo+ random.nextInt(hi - lo);
      swap(arr,ranIndex,lo);
        Comparable v=arr[lo];
        //作为优化可以随机选取一个元素作为基准值


        //定义左右扫描的两个指针
        int i=lo,j=hi+1;
        while (true){
        //先从左向右扫描如果当前索引的值小于V就继续扫描下一个位置上dd的值
        while (arr[++i].compareTo(v)<=0)
        //数组中全部的元素都比V小，那么可以终止此轮的扫描
            if (i==hi) break;
        //左边扫描暂停后开始从右向左扫描，若当前位置的值大于V就继续扫描
        while (arr[--j].compareTo(v)>=0)
        //若数组中的元素全部比V大，那么就终止此次的扫描
            if(j==lo) break;
        //如果左右指针相同或者错位就表示扫描结束
            if(j<=i) break;
        //此时左右扫描都已经暂停将左右指针的值进行交换
            swap(arr,i,j);
        }
        //扫描结束后让基准值回到数组中
           swap(arr,lo,j);
        return j;
    }



    private static void dealSort(Comparable[]arr, int lo, int hi){
          count++;
        if(hi<=lo)  return;
        int res=partition(arr,lo,hi);
        dealSort(arr,lo,res-1);
        dealSort(arr,res+1,hi);
    }

    public static void sort(Comparable[]arr){
        dealSort(arr,0,arr.length-1);
    }

    public static void main(String[] args) {
//       Integer[]arr={5,3,2,1,4,8,9,6,5};
//       sort(arr);
//       System.out.println(Arrays.toString(arr));
//        System.out.println(count);
//        Integer[] array = ArrayUtils.generateRandomArray(1000000);
//        //7886
//        //true
//        //1999801
//        long start = System.currentTimeMillis();
//        sort(array);
//        long end = System.currentTimeMillis();
//        System.out.println((end-start));
//        System.out.println(ArrayUtils.checkValid(array));
//        System.out.println(count);

//        Integer[] array = ArrayUtils.generateNearlyOrderedArray(100000, 100);
//        long start = System.currentTimeMillis();
//        sort(array);
//        long end = System.currentTimeMillis();
//        System.out.println((end-start));
//        System.out.println(ArrayUtils.checkValid(array));
//
        Integer[] array = SortTestHelper.generateRandomArray(1000000, 0, 10);
        long start = System.currentTimeMillis();
        sort(array);
        long end = System.currentTimeMillis();
        System.out.println((end-start));
        System.out.println(ArrayUtils.checkValid(array));
        System.out.println(count);



    }
}
