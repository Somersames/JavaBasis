package Loop;

/**
 * @author szh
 * @create 2018-05-21 20:31
 **/
public class LoopTest {
    private static void maxOut(){
        int[] a =new int[100];
        for(int i = 0; i<200 ;i++){
            for(int j=0;j<10;j++){
                a[i]=a[j]+1;
            }
        }
    }
    private static void minOut(){
        int[] a =new int[100];
        for(int i = 0; i<10 ;i++){
            for(int j=0;j<200;j++){
                a[i]=a[j]+1;
            }
        }
    }
    public static void main(String[] args){
        maxOut();
        minOut();
    }
}
