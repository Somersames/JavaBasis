package reflec.test;

/**
 * Created by szh on 2017/4/19.
 */
public class Init_a {
    static{
        System.out.println("a的static块");
    }

    public Init_a() {
        System.out.println("a的构造方法块");
    }
    public void a(){
        System.out.println("a的a方法块");
    }
}
