package T;

import org.junit.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

/**
 * c
 *
 * @author szh
 * @create 2018-04-09 0:57
 **/
public class T2 {
//    @Test
//    public void uniqueMorseRepresentations() {
//        String[] words=new String[]{"gin", "zen", "gig", "msg"};
//        String[] s1 =new String[]{".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
//        Set<String> set =new HashSet<>();
//        for(int i =0 ;i<words.length ;i++){
//
//            String temp =words[i];
//            char[] c =temp.toCharArray();
//            StringBuffer sb =new StringBuffer();
//            for(int j =0 ;j<c.length ;j++){
//                sb.append(s1[(c[j]-97)]);
//            }
//            set.add(sb.toString());
//        }
//        System.out.println(set.size());
//        System.out.println(1 - '0');
//        System.out.println(1 % (1 - '0'));
//    }
//    @Test
//    public  void insertSortTest(){
//        int[] array =new int[]{2,4,5,7,1,3,6,8,9,0};
//        int min =array[0];
//        for(int i=1 ;i < array.length ;i++){
//            for(int j=i-1 ;j>=0 ;j--)
//                if(array[j] > array[j+1]){
//                int temp =array[j+1];
//                array[j+1]=array[j];
//                array[j]=temp;
//                }
//        }
//        for( int i :array){
//            System.out.println(i);
//        }
//    }
//    @Test
//    public void maopaoSortTest(){
//        int[] array =new int[]{2,4,5,7,1,3,6,8,9,0};
//        for(int i =0 ;i<array.length ;i++){
//            for(int j =i ;j<array.length ;j++){
//                if(array[i] >array[j]){
//                    int temp =array[j];
//                    array[j]=array[i];
//                    array[i]=temp;
//                }
//            }
//        }
//        for(int i : array){
//            System.out.println(i);
//        }
//    }
//
//    @Test
    public void quickSortTest(int[] array , int head ,int end ){
//        int[] array =new int[]{2,4,5,7,1,3,6,8,9,0};
        if(head >= end || array == null || array.length <1){
            return;
        }
        int i=head;
        int j=end;
        int mid =array[(head + end) /2];
        while(i <= j){
            while (array[i] < mid){
                i++;
            }
            while (array[j] > mid){
                j--;
            }
            if(i <= j){
                int temp =array[i];
                array[i] =array[j];
                array[j]=temp;
                ++i;
                --j;
//            }else if(i == j){
//                ++i;
            }
        }
        quickSortTest(array,head,j);
        quickSortTest(array,i,end);
    }

    public static void main(String[] args) {
        int[] array =new int[]{2,4,5,7,1,3,6,8,9,0};
        new T2().quickSortTest(array,0,array.length-1);
        for(int i :array){
            System.out.println(i);
        }
    }
    @Test
    public void te(){
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);
    }

}
