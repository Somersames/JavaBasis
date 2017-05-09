package Thread.Bank.system;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by szh on 2017/5/9.
 */
public class Store {
    public static   Queue<Integer> queue =new ArrayBlockingQueue<Integer>(10);
    public Store() {
        queue =new ArrayBlockingQueue<Integer>(10);

    }

    public Queue<Integer> getQueue() {
        return queue;
    }
}
