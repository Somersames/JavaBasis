package Thread.test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by szh on 2017/5/9.
 */
public class insertTest extends Thread{
    static  List<Integer> list =new ArrayList<>();
    int num =0;
    public synchronized void in(){
        list.add(num);
        num++;
    }

    @Override
    public void run() {
        in();
    }
}
