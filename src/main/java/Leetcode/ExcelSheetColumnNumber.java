package Leetcode;

/**
 * @author szh
 * @create 2018-08-15 22:24
 **/
public class ExcelSheetColumnNumber {
    public static void main(String[] args) {
        char c='A';
//        System.out.println(Math.abs(-64 + c));
        System.out.println(((int)Math.pow(26, 1)));

        System.out.println(new ExcelSheetColumnNumber().titleToNumber("AA"));
    }
    public int titleToNumber(String s) {
        if(s == null || s.length() ==0){
            return 0;
        }
        int flag =-64;
        int result=0;
        int length =s.length();
        for(int i=0 ;i<s.length() ;i++){
            char c = s.charAt(i);
            int var1=Math.abs(flag + c);
            int var2=(int)Math.pow(--length, 26);
            if(var2 ==0){
                var2=1;
            }
            result = var1 * var2 + result;
        }
        return result;
    }
}
