package T;

/**
 * @author szh
 * @create 2018-05-30 23:55
 **/
public class IntTest {
    {
        System.out.println(this.a);
        System.out.println("a");
    }
    int a;
    {
        System.out.println("b");
    }

    public static void main(String[] args) {
        new IntTest();
    }
}
