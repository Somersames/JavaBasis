package Leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author szh
 * @create 2018-08-31 22:40
 **/
public class AssignCookies {
    public int findContentChildren(int[] g, int[] s) {
        Map<Integer,Integer> map =new HashMap<>();
        for(int i : g){
            map.put(i,map.get(i) == null? 1:  map.get(i)+1);
        }
        int result=0;
        for(int i :s){
            if(map.get(i) != null && map.get(i) !=0){
                map.put(i,map.get(i)-1);
                result++;
            }
        }
        return result;
    }
}
