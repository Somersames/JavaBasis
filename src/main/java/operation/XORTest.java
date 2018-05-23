package operation;

import org.junit.Test;

public class XORTest {
    @Test
    public  void main1() {
        char c1 ='a';
        char c2='b';
        System.out.println(c1 ^ c2);
    }
    public int numJewelsInStones(String J, String S) {
        char[] c1= J.toCharArray();
        char[] c2 =S.toCharArray();
        int total=0;
        for(int i =0 ;i<c1.length ; i++){
            for(int j=0 ;j<c2.length ;j++){
                if((c1[i] ^ c2[j]) == 0){
                    total++;
                }
            }
        }
        return total;
    }
}
