package Leetcode;

import java.util.Arrays;

/**
 * @author szh
 * @create 2018-09-14 23:44
 **/
public class MissingNumber {
    public int missingNumber(int[] nums) {
        Arrays.sort(nums);
        if(nums == null || nums.length == 0){
            return 0;
        }
        int result =0;
        int i =0;
        for( ; i< nums.length ; i++){
            result += (i ^ nums[i]);
        }
        result = result ^ i;
        return result;
    }

    public static void main(String[] args) {
        System.out.println(0 ^ 1);
        System.out.println(1 ^ 2);
        System.out.println((0 ^ 1));
    }
}
