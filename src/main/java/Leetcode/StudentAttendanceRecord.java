package Leetcode;

/**
 * @author szh
 * @create 2018-09-15 21:53
 **/
public class StudentAttendanceRecord {
    public static void main(String[] args) {
//        char c1 = 'A';
//        char c2 = 'P';
//        char c3 = 'C';
//        System.out.println(c1 == 1);
//        System.out.println(c1 ^ c2);
//        int[] i = new int[10];
//        System.out.println('C' ^ 1);
//        System.out.println('C' ^ 2);
//        System.out.println('C' ^ 3);
//        System.out.println('C' ^ 4);
//        System.out.println('C' | 1);
//        System.out.println('C' | 2);
//        System.out.println('C' | 3);
//        System.out.println('C' | 4);
//        System.out.println('C' ^ 5);
//        System.out.println('C' ^ 6);
//        System.out.println('C' ^ 7);
//        System.out.println('C' ^ 8);
//        System.out.println('C' ^ 9);
//        System.out.println('C' ^ 10);
//        System.out.println('C' ^ 11);
//        System.out.println('C' ^ 12);
//        System.out.println('C' ^ 13);
//        int count =0;
//        System.out.println(upLouti(0, 20));
//        System.out.println(count);
//        int[] a = new int[21];
//        a[0] = 1;
//        a[1] = 1;
//        a[2] = 1;
//
//        for (int i = 3; i <= 20; i++) {
//            a[i] = a[i-1] + a[i-3];
//            System.out.println(i + "  " + a[i]);
//        }
        System.out.println(puCiZhuan(0, 20));
    }


    private static int  upLouti(int step ,int end  ){
        if(step == end || step == end -1 ){
            return 1;
        }
         return upLouti(step+1,end) + upLouti(step+2,end);
    }

    private static int puCiZhuan(int step ,int end){
        if(step >= end){
            return 0;
        }
        if(step ==  end -1){
            return 1;
        }
        if(step == end -3){
            return 2;
        }
        return puCiZhuan(step+1,end)  + puCiZhuan(step + 3 ,end);
    }
}
