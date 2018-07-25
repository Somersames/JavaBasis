package Leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author szh
 * @create 2018-06-14 18:53
 **/
public class MaxAreaofIsland {
    public int maxAreaOfIsland(int[][] grid) {
        List<Integer> list =new ArrayList<>();
        for( int i =0 ;i<grid.length ;){
            int temp=0;
            for(int j=0 ;j<grid[i].length;j++){
                if(grid[i][j] == 1){
                    temp++;
                }
            }
            i++;
            list.add(temp);
        }
        int i=0,n=0;
        for(;n<grid.length && i<grid[n].length;){
            int temp=0;
            n++;
            for(int j=0 ;j<grid[i].length;j++){
                if(grid[i][j] == 1){
                    temp++;
                }
            }
            i++;
            list.add(temp);
        }
        int max =0;
        for(int m :list){
            if(m>max){
                max=i;
            }
        }
        return max;
    }

    public static void main(String[] args) {
//        int[][] a =new int[][]{{1,1,0,0,0},{1,1,0,0,0},{0,0,0,1,1},{0,0,0,1,1}};
        int[][] a =new int[][]{{0},{0}};
        new MaxAreaofIsland().maxAreaOfIsland(a);
    }
}
