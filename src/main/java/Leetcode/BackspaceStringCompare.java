package Leetcode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author szh
 * @create 2018-09-17 21:10
 **/
public class BackspaceStringCompare {
    public static void main(String[] args) {
        new BackspaceStringCompare().backspaceCompare("ab#c","ad#c");
    }
    public boolean backspaceCompare(String S, String T) {
        Stack<Character> SU = new Stack<>();
        Stack<Character> TU = new Stack<>();
        char[] s1 =S.toCharArray();
        char[] t1 =T.toCharArray();
        for(int i =0 ; i < s1.length ;i++){
            if(s1[i] == '#'){
                if(!SU.isEmpty()){
                    SU.pop();
                }
            }else{
                SU.add(s1[i]);
            }
        }
        for(int i =0 ; i < t1.length ;i++){
            if(t1[i] == '#'){
                if(!TU.isEmpty()){
                    TU.pop();
                }
            }else{
                TU.add(t1[i]);
            }
        }
        while(!SU.isEmpty() && !TU.isEmpty()){
            if(SU.pop() == TU.pop()){
                continue;
            }else{
                return false;
            }
        }
        return SU.isEmpty() && TU.isEmpty() ? true :false;
    }
}
