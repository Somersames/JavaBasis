package Leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author szh
 * @create 2018-08-19 14:51
 **/
public class ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        // for(int i =0 ;i<nums.length ;i++){
        //     for(int j=i+1 ; j<nums.length ;j++){
        //         if(nums[i] == nums[j]){
        //             return true;
        //         }
        //     }
        // }
        // return false;
        Set<Integer> set =new HashSet<>();
        for(int i=0 ;i<nums.length ;i++){
            set.add(nums[i]);
        }
        return set.size() == nums.length;
    }

    public static void main(String[] args) {
        System.out.println(new ContainsDuplicate().containsDuplicate(new int[]{1, 2, 3, 1}));
    }
}
