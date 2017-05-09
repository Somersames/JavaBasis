package Thread.Bank.system;

import org.junit.Test;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by szh on 2017/5/9.
 * 建立一个线程池和队列，然后让几个线程来处理叫号，接下来几个处理被叫
 * 这里最好提供一个接口往队列里面放置请求，然后让线程池的线程来处理
 */
public class DoCall implements Runnable{
     static int num =0;

    @Override
    public  void run() {
        insert(num); // 插入id
        num++;
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
    private  synchronized  void  insert(int id){
       if(Store.queue.contains(id)){
           System.out.println("该号码已经存在");
           return;
       }
        Store.queue.offer(id);
    }
    @Test
    public void qute(){
        Queue<Integer> queue =new ArrayBlockingQueue<Integer>(12);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.poll();
        System.out.println(queue.size()); // 显示当前的元素
    }
}
