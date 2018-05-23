package T;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试泛型的一些例子
 *
 * @author szh
 * @create 2018-04-07 21:14
 **/
public class TestT {
    public static void main(String[] args) {
        new TestT().selfDividingNumbers(1, 22);
    }

    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> list = new ArrayList<>();
        for (; left <= right; left++) {
            int[] temp = StirngToIntaray(String.valueOf(left));
            boolean flag = true;
            for (int i = 0; i < temp.length; i++) {
                if (temp[i] == 0){
                    continue;
                }
                if (left % temp[i] != 0) {
                    flag = false;
                }
            }
            if (flag) {
                list.add(left);
            }
        }
        return list;
    }

    public int[] StirngToIntaray(String s) {
        int[] result = new int[s.length()];
        char[] c = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            result[i] = Integer.parseInt(Character.toString(c[i]));
        }
        return result;
    }
}
