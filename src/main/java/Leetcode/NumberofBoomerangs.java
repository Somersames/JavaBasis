package Leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author szh
 * @create 2018-09-03 23:54
 **/
public class NumberofBoomerangs {
    public int numberOfBoomerangs(int[][] points) {
        Map<Integer,Integer> map =new HashMap<>();
        int res=0;
        for(int i =0 ;i< points.length ;i++){
            for(int j=0 ;j< points.length ;j++){
                if(i == j){
                    continue;
                }
                int ditance =getDistance(points[i],points[j]);
                map.put(ditance,map.getOrDefault(ditance,0)+1);
            }
            for(int val :map.values()){
                res+=(val*2);
            }
            map.clear();
        }
        return res;

    }
    public int getDistance(int[] a ,int[] b){
        // int dx =(a[0]-b[0]) < 0 ? b[0]- a[0] :a[0]-b[0];
        int dx =a[0]-b[0];
        // int dy=(a[1]-b[1]) < 0 ? b[1]- a[1] :a[1]-b[1];
        int dy=a[1]-b[1] ;
        return  dx* dx + dy *dy;
    }

    public static void main(String[] args) {
//        int[][] a =new int[][]{new int[]{0,0},new int[]{0,0},new int[]{0,0}};
        int[][] a =new int[][]{{0,0},{1,0},{2,0}};
        new NumberofBoomerangs().numberOfBoomerangs(a);
        new NumberofBoomerangs().numberOfBoomerangs2(a);
    }
    public int numberOfBoomerangs2(int[][] points) {
        int res = 0;

        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<points.length; i++) {
            for(int j=0; j<points.length; j++) {
                if(i == j)
                    continue;

                int d = getDistance2(points[i], points[j]);
                map.put(d, map.getOrDefault(d, 0) + 1);
            }

            for(int val : map.values()) {
                res += val * (val-1);
            }
            map.clear();
        }

        return res;
    }

    private int getDistance2(int[] a, int[] b) {
        int dx = a[0] - b[0];
        int dy = a[1] - b[1];

        return dx*dx + dy*dy;
    }
}
