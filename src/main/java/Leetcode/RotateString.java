package Leetcode;

/**
 * @author szh
 * @create 2018-08-16 22:48
 **/
public class RotateString {
    public boolean rotateString(String A, String B) {
        String C = A +A ;
        for(int i =0 ;i< C.length() ;i++){
            int index =i;
            int j=0;
            for(;j<B.length() ;j++){
                if(C.charAt(index) == B.charAt(j)){
                    index++;
                }else{
                    break;
                }
            }
            if( j == B.length() ){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new RotateString().rotateString("abcde", "abced"));
    }
}
