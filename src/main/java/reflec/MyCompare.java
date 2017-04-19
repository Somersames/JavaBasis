package reflec;

/**
 * Created by szh on 2017/4/19.
 */
public class MyCompare<T extends Comparable<T>> {

    public static void strCompare() {
        String s = "aa";
        String s2 = new String("aa");
        System.out.println(s.compareTo(s2));
    }

    public static void main(String args[]) {
        strCompare();
    }
}
