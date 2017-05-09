package Thread.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by szh on 2017/5/9.
 */
public class Exectest {
    public static void main(String args[]) throws InterruptedException {
    int i=0;
        for(i=0; i<10 ; i++){
         Thread.currentThread().sleep(200);
        }
        while (i>=9){
            new RunExec().sp();
        }
    }
}

class RunExec {
    ExecutorService executor = Executors.newFixedThreadPool(5);
    static int num = 0;
    public void sp() {
        if(num >1000)
        executor.shutdown();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(num);
                num++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
