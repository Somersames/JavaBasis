package Thread.intercept.demo;

/**
 * Created by szh on 2017/5/16.
 */
public class InterruptTestMain {
    public static void main(String args[]) throws InterruptedException {
        Thread thread =new InterruptTest();
        thread.start();
        Thread.currentThread().sleep(2);
        thread.interrupt();
    }
}
