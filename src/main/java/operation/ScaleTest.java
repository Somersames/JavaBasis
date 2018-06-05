package operation;

import org.junit.Test;

/**
 * @author szh
 * @create 2018-05-31 17:32
 **/
public class ScaleTest {
    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(10)) ;
        System.out.println(Integer.toBinaryString(10 >> 1));
//        System.out.println(5 ^ 10);

    }
    @Test
    public void hasAlternatingBits() {
        int n=6;
        int m= n ^ (n >>1);
        if(n == 1){
            System.out.println(true);
        }
        if(m == n+(n/2) && m /2 !=0){
            System.out.println(true);
        }
        System.out.println(false);
    }
}
