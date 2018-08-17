package Leetcode;

/**
 * @author szh
 * @create 2018-08-17 22:50
 **/
public class bitand2bitCharac {
    public boolean isOneBitCharacter(int[] bits) {
        int len =bits.length;
        if(bits == null || bits.length == 0){
            return false;
        }
        if(len == 1 && bits[len-1] == 0){
            return true;
        }
        if(len == 2 && bits[0] == 0){
            return true;
        }else if(bits[0] == 1){
            return false;
        }
        if(len >=3){
            if(len % 2 == 0){
                if(bits[len-2] == 1){
                    return false;
                }else{
                    return true;
                }
            }else{
                if(bits[len-3] == 1){
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        new bitand2bitCharac().isOneBitCharacter(new int[]{1,0,0});
    }
}
