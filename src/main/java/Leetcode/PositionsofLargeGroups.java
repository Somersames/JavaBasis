package Leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author szh
 * @create 2018-09-04 23:05
 **/
public class PositionsofLargeGroups {
    public static void main(String[] args) {
        String s ="abbxxxxzzy";
        Map<Integer,Integer> map =new HashMap<>();
        new PositionsofLargeGroups().largeGroupPositions(s);
;    }
    public List<List<Integer>> largeGroupPositions(String S) {
        char[] c =S.toCharArray();
        List<List<Integer>> result =new ArrayList<>();
        int  start =0;
        for(int i =1 ;i<c.length ;i++){
            if(c[start] == c[i]){
            }else{
                if(i -start >=3){
                    List<Integer> temp =new ArrayList<>();
                    temp.add(start);
                    temp.add(i-1);
                    result.add(temp);
                }
                start =i;
            }
        }
        return result;
    }
}
