package src.sort;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: tangkeyon@gmail.com
 * @Date: 2019/8/28 14:20
 * @Version 1.0
 */
public class Q1 {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map=new HashMap();
        for(int i=0;i<nums.length;i++){
            map.put(nums[i],i);
        }
        for (int i=0;i<nums.length;i++){
              int des=target-nums[i];
              if(map.containsKey(des) && map.get(des)!=i){
                  return new int[]{i,map.get(des)};
              }
        }
        return  null;

    }



}
