package Leetcode;

import java.util.Stack;

/**
 * @author szh
 * @create 2018-08-14 23:31
 **/
public class LemonadeChange {
    public boolean lemonadeChange(int[] bills) {
        Stack<Integer> five  =new Stack<>();
        Stack<Integer> ten  =new Stack<>();
        int[] money =new int[bills.length];
        for(int i =0 ;i<=bills.length ;i++){
            if(bills[i] == 5){
                five.push(bills[i]);
            }
            if(bills[i] == 10){
                ten.push(bills[i]);
                if(five.size() == 0){
                    return false;
                }
                five.pop();
            }
            if(bills[i] == 20){
                if(five.size() == 0){
                    return false;
                }
                if(ten.size() == 0){
                    if(five.size() >=3){
                        five.pop();
                        five.pop();
                        five.pop();
                    }else{
                        return false;
                    }
                }else{
                    ten.pop();
                    five.pop();
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] a= new int[]{5,5,5,10,5,5,10,20,20,20};
        new LemonadeChange().lemonadeChange(a);
    }
}
